package org.altbeacon.beacon;

import android.bluetooth.BluetoothDevice;
import android.util.Log;
import androidx.core.view.InputDeviceCompat;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.bluetooth.BleAdvertisement;
import org.altbeacon.bluetooth.Pdu;
import org.apache.commons.lang3.StringUtils;

public class BeaconParser implements Serializable {
    public static final String ALTBEACON_LAYOUT = "m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25";
    private static final Pattern D_PATTERN = Pattern.compile("d\\:(\\d+)\\-(\\d+)([bl]*)?");
    public static final String EDDYSTONE_TLM_LAYOUT = "x,s:0-1=feaa,m:2-2=20,d:3-3,d:4-5,d:6-7,d:8-11,d:12-15";
    public static final String EDDYSTONE_UID_LAYOUT = "s:0-1=feaa,m:2-2=00,p:3-3:-41,i:4-13,i:14-19";
    public static final String EDDYSTONE_URL_LAYOUT = "s:0-1=feaa,m:2-2=10,p:3-3:-41,i:4-21v";
    private static final char[] HEX_ARRAY = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final Pattern I_PATTERN = Pattern.compile("i\\:(\\d+)\\-(\\d+)([blv]*)?");
    private static final String LITTLE_ENDIAN_SUFFIX = "l";
    private static final Pattern M_PATTERN = Pattern.compile("m\\:(\\d+)-(\\d+)\\=([0-9A-Fa-f]+)");
    private static final Pattern P_PATTERN = Pattern.compile("p\\:(\\d+)\\-(\\d+)\\:?([\\-\\d]+)?");
    private static final Pattern S_PATTERN = Pattern.compile("s\\:(\\d+)-(\\d+)\\=([0-9A-Fa-f]+)");
    private static final String TAG = "BeaconParser";
    public static final String URI_BEACON_LAYOUT = "s:0-1=fed8,m:2-2=00,p:3-3:-41,i:4-21v";
    private static final String VARIABLE_LENGTH_SUFFIX = "v";
    private static final Pattern X_PATTERN = Pattern.compile(AvidJSONUtil.KEY_X);
    protected List<BeaconParser> extraParsers = new ArrayList();
    protected Boolean mAllowPduOverflow = true;
    protected String mBeaconLayout;
    protected Integer mDBmCorrection;
    protected final List<Integer> mDataEndOffsets = new ArrayList();
    protected final List<Boolean> mDataLittleEndianFlags = new ArrayList();
    protected final List<Integer> mDataStartOffsets = new ArrayList();
    protected Boolean mExtraFrame;
    protected int[] mHardwareAssistManufacturers = {76};
    protected String mIdentifier;
    protected final List<Integer> mIdentifierEndOffsets = new ArrayList();
    protected final List<Boolean> mIdentifierLittleEndianFlags = new ArrayList();
    protected final List<Integer> mIdentifierStartOffsets = new ArrayList();
    protected final List<Boolean> mIdentifierVariableLengthFlags = new ArrayList();
    protected Integer mLayoutSize;
    private Long mMatchingBeaconTypeCode;
    protected Integer mMatchingBeaconTypeCodeEndOffset;
    protected Integer mMatchingBeaconTypeCodeStartOffset;
    protected Integer mPowerEndOffset;
    protected Integer mPowerStartOffset;
    protected Long mServiceUuid;
    protected Integer mServiceUuidEndOffset;
    protected Integer mServiceUuidStartOffset;

    public BeaconParser() {
    }

    public BeaconParser(String str) {
        this.mIdentifier = str;
    }

    public BeaconParser setBeaconLayout(String str) {
        String str2 = str;
        this.mBeaconLayout = str2;
        Log.d(TAG, "Parsing beacon layout: " + str2);
        String[] split = str2.split(",");
        this.mExtraFrame = false;
        int length = split.length;
        int i = 0;
        while (i < length) {
            String str3 = split[i];
            Matcher matcher = I_PATTERN.matcher(str3);
            boolean z = false;
            while (matcher.find()) {
                try {
                    int parseInt = Integer.parseInt(matcher.group(1));
                    int parseInt2 = Integer.parseInt(matcher.group(2));
                    this.mIdentifierLittleEndianFlags.add(Boolean.valueOf(matcher.group(3).contains(LITTLE_ENDIAN_SUFFIX)));
                    this.mIdentifierVariableLengthFlags.add(Boolean.valueOf(matcher.group(3).contains(VARIABLE_LENGTH_SUFFIX)));
                    this.mIdentifierStartOffsets.add(Integer.valueOf(parseInt));
                    this.mIdentifierEndOffsets.add(Integer.valueOf(parseInt2));
                    z = true;
                } catch (NumberFormatException unused) {
                    throw new BeaconLayoutException("Cannot parse integer byte offset in term: " + str3);
                }
            }
            Matcher matcher2 = D_PATTERN.matcher(str3);
            while (matcher2.find()) {
                try {
                    int parseInt3 = Integer.parseInt(matcher2.group(1));
                    int parseInt4 = Integer.parseInt(matcher2.group(2));
                    this.mDataLittleEndianFlags.add(Boolean.valueOf(matcher2.group(3).contains(LITTLE_ENDIAN_SUFFIX)));
                    this.mDataStartOffsets.add(Integer.valueOf(parseInt3));
                    this.mDataEndOffsets.add(Integer.valueOf(parseInt4));
                    z = true;
                } catch (NumberFormatException unused2) {
                    throw new BeaconLayoutException("Cannot parse integer byte offset in term: " + str3);
                }
            }
            Matcher matcher3 = P_PATTERN.matcher(str3);
            while (matcher3.find()) {
                try {
                    int parseInt5 = Integer.parseInt(matcher3.group(1));
                    int parseInt6 = Integer.parseInt(matcher3.group(2));
                    this.mDBmCorrection = Integer.valueOf(matcher3.group(3) != null ? Integer.parseInt(matcher3.group(3)) : 0);
                    this.mPowerStartOffset = Integer.valueOf(parseInt5);
                    this.mPowerEndOffset = Integer.valueOf(parseInt6);
                    z = true;
                } catch (NumberFormatException unused3) {
                    throw new BeaconLayoutException("Cannot parse integer power byte offset in term: " + str3);
                }
            }
            Matcher matcher4 = M_PATTERN.matcher(str3);
            while (matcher4.find()) {
                try {
                    int parseInt7 = Integer.parseInt(matcher4.group(1));
                    int parseInt8 = Integer.parseInt(matcher4.group(2));
                    this.mMatchingBeaconTypeCodeStartOffset = Integer.valueOf(parseInt7);
                    this.mMatchingBeaconTypeCodeEndOffset = Integer.valueOf(parseInt8);
                    String group = matcher4.group(3);
                    try {
                        this.mMatchingBeaconTypeCode = Long.decode("0x" + group);
                        z = true;
                    } catch (NumberFormatException unused4) {
                        throw new BeaconLayoutException("Cannot parse beacon type code: " + group + " in term: " + str3);
                    }
                } catch (NumberFormatException unused5) {
                    throw new BeaconLayoutException("Cannot parse integer byte offset in term: " + str3);
                }
            }
            Matcher matcher5 = S_PATTERN.matcher(str3);
            while (matcher5.find()) {
                try {
                    int parseInt9 = Integer.parseInt(matcher5.group(1));
                    int parseInt10 = Integer.parseInt(matcher5.group(2));
                    this.mServiceUuidStartOffset = Integer.valueOf(parseInt9);
                    this.mServiceUuidEndOffset = Integer.valueOf(parseInt10);
                    String group2 = matcher5.group(3);
                    try {
                        this.mServiceUuid = Long.decode("0x" + group2);
                        z = true;
                    } catch (NumberFormatException unused6) {
                        throw new BeaconLayoutException("Cannot parse serviceUuid: " + group2 + " in term: " + str3);
                    }
                } catch (NumberFormatException unused7) {
                    throw new BeaconLayoutException("Cannot parse integer byte offset in term: " + str3);
                }
            }
            Matcher matcher6 = X_PATTERN.matcher(str3);
            while (matcher6.find()) {
                this.mExtraFrame = true;
                z = true;
            }
            if (z) {
                i++;
            } else {
                LogManager.m6046d(TAG, "cannot parse term %s", str3);
                throw new BeaconLayoutException("Cannot parse beacon layout term: " + str3);
            }
        }
        if (!this.mExtraFrame.booleanValue()) {
            if (this.mIdentifierStartOffsets.size() == 0 || this.mIdentifierEndOffsets.size() == 0) {
                throw new BeaconLayoutException("You must supply at least one identifier offset with a prefix of 'i'");
            } else if (this.mPowerStartOffset == null || this.mPowerEndOffset == null) {
                throw new BeaconLayoutException("You must supply a power byte offset with a prefix of 'p'");
            }
        }
        if (this.mMatchingBeaconTypeCodeStartOffset == null || this.mMatchingBeaconTypeCodeEndOffset == null) {
            throw new BeaconLayoutException("You must supply a matching beacon type expression with a prefix of 'm'");
        }
        this.mLayoutSize = Integer.valueOf(calculateLayoutSize());
        return this;
    }

    public boolean addExtraDataParser(BeaconParser beaconParser) {
        return beaconParser != null && beaconParser.mExtraFrame.booleanValue() && this.extraParsers.add(beaconParser);
    }

    public List<BeaconParser> getExtraDataParsers() {
        return new ArrayList(this.extraParsers);
    }

    public String getIdentifier() {
        return this.mIdentifier;
    }

    public int[] getHardwareAssistManufacturers() {
        return this.mHardwareAssistManufacturers;
    }

    public void setHardwareAssistManufacturerCodes(int[] iArr) {
        this.mHardwareAssistManufacturers = iArr;
    }

    public void setAllowPduOverflow(Boolean bool) {
        this.mAllowPduOverflow = bool;
    }

    public Long getMatchingBeaconTypeCode() {
        return this.mMatchingBeaconTypeCode;
    }

    public int getMatchingBeaconTypeCodeStartOffset() {
        return this.mMatchingBeaconTypeCodeStartOffset.intValue();
    }

    public int getMatchingBeaconTypeCodeEndOffset() {
        return this.mMatchingBeaconTypeCodeEndOffset.intValue();
    }

    public Long getServiceUuid() {
        return this.mServiceUuid;
    }

    public int getMServiceUuidStartOffset() {
        return this.mServiceUuidStartOffset.intValue();
    }

    public int getServiceUuidEndOffset() {
        return this.mServiceUuidEndOffset.intValue();
    }

    public Beacon fromScanData(byte[] bArr, int i, BluetoothDevice bluetoothDevice) {
        return fromScanData(bArr, i, bluetoothDevice, new Beacon());
    }

    /* access modifiers changed from: protected */
    public Beacon fromScanData(byte[] bArr, int i, BluetoothDevice bluetoothDevice, Beacon beacon) {
        int i2;
        Pdu pdu;
        int i3;
        boolean z;
        Beacon beacon2;
        String str;
        String str2;
        boolean z2;
        String str3;
        String str4;
        byte[] bArr2 = bArr;
        BleAdvertisement bleAdvertisement = new BleAdvertisement(bArr2);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<Pdu> it = bleAdvertisement.getPdus().iterator();
        while (true) {
            i2 = 0;
            if (!it.hasNext()) {
                pdu = null;
                break;
            }
            pdu = it.next();
            if (pdu.getType() != 22 && pdu.getType() != -1) {
                if (LogManager.isVerboseLoggingEnabled()) {
                    LogManager.m6046d(TAG, "Ignoring pdu type %02X", Byte.valueOf(pdu.getType()));
                }
            }
        }
        if (LogManager.isVerboseLoggingEnabled()) {
            LogManager.m6046d(TAG, "Processing pdu type %02X: %s with startIndex: %d, endIndex: %d", Byte.valueOf(pdu.getType()), bytesToHex(bArr), Integer.valueOf(pdu.getStartIndex()), Integer.valueOf(pdu.getEndIndex()));
        }
        if (pdu == null) {
            if (LogManager.isVerboseLoggingEnabled()) {
                LogManager.m6046d(TAG, "No PDUs to process in this packet.", new Object[0]);
            }
            beacon2 = beacon;
            z = true;
            i3 = 0;
        } else {
            byte[] longToByteArray = longToByteArray(getMatchingBeaconTypeCode().longValue(), (this.mMatchingBeaconTypeCodeEndOffset.intValue() - this.mMatchingBeaconTypeCodeStartOffset.intValue()) + 1);
            byte[] longToByteArray2 = getServiceUuid() != null ? longToByteArray(getServiceUuid().longValue(), (this.mServiceUuidEndOffset.intValue() - this.mServiceUuidStartOffset.intValue()) + 1, false) : null;
            i3 = pdu.getStartIndex();
            if (getServiceUuid() != null ? !byteArraysMatch(bArr2, this.mServiceUuidStartOffset.intValue() + i3, longToByteArray2) || !byteArraysMatch(bArr2, this.mMatchingBeaconTypeCodeStartOffset.intValue() + i3, longToByteArray) : !byteArraysMatch(bArr2, this.mMatchingBeaconTypeCodeStartOffset.intValue() + i3, longToByteArray)) {
                z2 = false;
            } else {
                z2 = true;
            }
            if (!z2) {
                if (getServiceUuid() == null) {
                    if (LogManager.isVerboseLoggingEnabled()) {
                        LogManager.m6046d(TAG, "This is not a matching Beacon advertisement. (Was expecting %s.  The bytes I see are: %s", byteArrayToString(longToByteArray), bytesToHex(bArr));
                    }
                } else if (LogManager.isVerboseLoggingEnabled()) {
                    LogManager.m6046d(TAG, "This is not a matching Beacon advertisement. Was expecting %s at offset %d and %s at offset %d.  The bytes I see are: %s", byteArrayToString(longToByteArray2), Integer.valueOf(this.mServiceUuidStartOffset.intValue() + i3), byteArrayToString(longToByteArray), Integer.valueOf(this.mMatchingBeaconTypeCodeStartOffset.intValue() + i3), bytesToHex(bArr));
                }
                beacon2 = null;
                z = true;
            } else {
                if (LogManager.isVerboseLoggingEnabled()) {
                    LogManager.m6046d(TAG, "This is a recognized beacon advertisement -- %s seen", byteArrayToString(longToByteArray));
                    LogManager.m6046d(TAG, "Bytes are: %s", bytesToHex(bArr));
                }
                beacon2 = beacon;
                z = false;
            }
            if (z2) {
                if (bArr2.length <= this.mLayoutSize.intValue() + i3 && this.mAllowPduOverflow.booleanValue()) {
                    if (LogManager.isVerboseLoggingEnabled()) {
                        LogManager.m6046d(TAG, "Expanding buffer because it is too short to parse: " + bArr2.length + ", needed: " + (this.mLayoutSize.intValue() + i3), new Object[0]);
                    }
                    bArr2 = ensureMaxSize(bArr2, this.mLayoutSize.intValue() + i3);
                }
                int i4 = 0;
                while (true) {
                    str3 = " PDU endIndex: ";
                    if (i4 >= this.mIdentifierEndOffsets.size()) {
                        break;
                    }
                    int intValue = this.mIdentifierEndOffsets.get(i4).intValue() + i3;
                    if (intValue > pdu.getEndIndex() && this.mIdentifierVariableLengthFlags.get(i4).booleanValue()) {
                        if (LogManager.isVerboseLoggingEnabled()) {
                            LogManager.m6046d(TAG, "Need to truncate identifier by " + (intValue - pdu.getEndIndex()), new Object[0]);
                        }
                        arrayList.add(Identifier.fromBytes(bArr2, this.mIdentifierStartOffsets.get(i4).intValue() + i3, pdu.getEndIndex() + 1, this.mIdentifierLittleEndianFlags.get(i4).booleanValue()));
                    } else if (intValue <= pdu.getEndIndex() || this.mAllowPduOverflow.booleanValue()) {
                        arrayList.add(Identifier.fromBytes(bArr2, this.mIdentifierStartOffsets.get(i4).intValue() + i3, intValue + 1, this.mIdentifierLittleEndianFlags.get(i4).booleanValue()));
                    } else {
                        if (LogManager.isVerboseLoggingEnabled()) {
                            LogManager.m6046d(TAG, "Cannot parse identifier " + i4 + " because PDU is too short.  endIndex: " + intValue + str3 + pdu.getEndIndex(), new Object[0]);
                        }
                        z = true;
                    }
                    i4++;
                }
                int i5 = 0;
                while (i5 < this.mDataEndOffsets.size()) {
                    int intValue2 = this.mDataEndOffsets.get(i5).intValue() + i3;
                    if (intValue2 <= pdu.getEndIndex() || this.mAllowPduOverflow.booleanValue()) {
                        str4 = str3;
                        arrayList2.add(Long.decode(byteArrayToFormattedString(bArr2, this.mDataStartOffsets.get(i5).intValue() + i3, intValue2, this.mDataLittleEndianFlags.get(i5).booleanValue())));
                    } else {
                        if (LogManager.isVerboseLoggingEnabled()) {
                            LogManager.m6046d(TAG, "Cannot parse data field " + i5 + " because PDU is too short.  endIndex: " + intValue2 + str3 + pdu.getEndIndex() + ".  Setting value to 0", new Object[i2]);
                        }
                        str4 = str3;
                        arrayList2.add(new Long(0));
                    }
                    i5++;
                    str3 = str4;
                    i2 = 0;
                }
                String str5 = str3;
                if (this.mPowerStartOffset != null) {
                    int intValue3 = this.mPowerEndOffset.intValue() + i3;
                    try {
                        if (intValue3 <= pdu.getEndIndex() || this.mAllowPduOverflow.booleanValue()) {
                            int parseInt = Integer.parseInt(byteArrayToFormattedString(bArr2, this.mPowerStartOffset.intValue() + i3, this.mPowerEndOffset.intValue() + i3, false)) + this.mDBmCorrection.intValue();
                            if (parseInt > 127) {
                                parseInt += InputDeviceCompat.SOURCE_ANY;
                            }
                            beacon2.mTxPower = parseInt;
                        } else {
                            try {
                                if (LogManager.isVerboseLoggingEnabled()) {
                                    LogManager.m6046d(TAG, "Cannot parse power field because PDU is too short.  endIndex: " + intValue3 + str5 + pdu.getEndIndex(), new Object[0]);
                                }
                            } catch (NullPointerException | NumberFormatException unused) {
                            }
                            z = true;
                        }
                    } catch (NullPointerException | NumberFormatException unused2) {
                    }
                }
            }
        }
        if (z) {
            return null;
        }
        int parseInt2 = Integer.parseInt(byteArrayToFormattedString(bArr2, this.mMatchingBeaconTypeCodeStartOffset.intValue() + i3, this.mMatchingBeaconTypeCodeEndOffset.intValue() + i3, false));
        int parseInt3 = Integer.parseInt(byteArrayToFormattedString(bArr2, i3, i3 + 1, true));
        if (bluetoothDevice != null) {
            str = bluetoothDevice.getAddress();
            str2 = bluetoothDevice.getName();
        } else {
            str2 = null;
            str = null;
        }
        beacon2.mIdentifiers = arrayList;
        beacon2.mDataFields = arrayList2;
        beacon2.mRssi = i;
        beacon2.mBeaconTypeCode = parseInt2;
        Long l = this.mServiceUuid;
        if (l != null) {
            beacon2.mServiceUuid = (int) l.longValue();
        } else {
            beacon2.mServiceUuid = -1;
        }
        beacon2.mBluetoothAddress = str;
        beacon2.mBluetoothName = str2;
        beacon2.mManufacturer = parseInt3;
        beacon2.mParserIdentifier = this.mIdentifier;
        beacon2.mMultiFrameBeacon = this.extraParsers.size() > 0 || this.mExtraFrame.booleanValue();
        return beacon2;
    }

    public byte[] getBeaconAdvertisementData(Beacon beacon) {
        if (beacon.getIdentifiers().size() == getIdentifierCount()) {
            int i = -1;
            Integer num = this.mMatchingBeaconTypeCodeEndOffset;
            if (num != null && num.intValue() > -1) {
                i = this.mMatchingBeaconTypeCodeEndOffset.intValue();
            }
            Integer num2 = this.mPowerEndOffset;
            if (num2 != null && num2.intValue() > i) {
                i = this.mPowerEndOffset.intValue();
            }
            for (int i2 = 0; i2 < this.mIdentifierEndOffsets.size(); i2++) {
                if (this.mIdentifierEndOffsets.get(i2) != null && this.mIdentifierEndOffsets.get(i2).intValue() > i) {
                    i = this.mIdentifierEndOffsets.get(i2).intValue();
                }
            }
            for (int i3 = 0; i3 < this.mDataEndOffsets.size(); i3++) {
                if (this.mDataEndOffsets.get(i3) != null && this.mDataEndOffsets.get(i3).intValue() > i) {
                    i = this.mDataEndOffsets.get(i3).intValue();
                }
            }
            int i4 = 0;
            for (int i5 = 0; i5 < this.mIdentifierStartOffsets.size(); i5++) {
                if (this.mIdentifierVariableLengthFlags.get(i5).booleanValue()) {
                    i4 = (i4 + beacon.getIdentifier(i5).getByteCount()) - ((this.mIdentifierEndOffsets.get(i5).intValue() - this.mIdentifierStartOffsets.get(i5).intValue()) + 1);
                }
            }
            byte[] bArr = new byte[(((i + i4) + 1) - 2)];
            getMatchingBeaconTypeCode().longValue();
            for (int intValue = this.mMatchingBeaconTypeCodeStartOffset.intValue(); intValue <= this.mMatchingBeaconTypeCodeEndOffset.intValue(); intValue++) {
                bArr[intValue - 2] = (byte) ((int) (255 & (getMatchingBeaconTypeCode().longValue() >> ((this.mMatchingBeaconTypeCodeEndOffset.intValue() - intValue) * 8))));
            }
            for (int i6 = 0; i6 < this.mIdentifierStartOffsets.size(); i6++) {
                byte[] byteArrayOfSpecifiedEndianness = beacon.getIdentifier(i6).toByteArrayOfSpecifiedEndianness(!this.mIdentifierLittleEndianFlags.get(i6).booleanValue());
                if (byteArrayOfSpecifiedEndianness.length < getIdentifierByteCount(i6)) {
                    if (!this.mIdentifierVariableLengthFlags.get(i6).booleanValue()) {
                        if (this.mIdentifierLittleEndianFlags.get(i6).booleanValue()) {
                            byteArrayOfSpecifiedEndianness = Arrays.copyOf(byteArrayOfSpecifiedEndianness, getIdentifierByteCount(i6));
                        } else {
                            byte[] bArr2 = new byte[getIdentifierByteCount(i6)];
                            System.arraycopy(byteArrayOfSpecifiedEndianness, 0, bArr2, getIdentifierByteCount(i6) - byteArrayOfSpecifiedEndianness.length, byteArrayOfSpecifiedEndianness.length);
                            byteArrayOfSpecifiedEndianness = bArr2;
                        }
                    }
                    LogManager.m6046d(TAG, "Expanded identifier because it is too short.  It is now: " + byteArrayToString(byteArrayOfSpecifiedEndianness), new Object[0]);
                } else if (byteArrayOfSpecifiedEndianness.length > getIdentifierByteCount(i6)) {
                    if (this.mIdentifierLittleEndianFlags.get(i6).booleanValue()) {
                        byteArrayOfSpecifiedEndianness = Arrays.copyOfRange(byteArrayOfSpecifiedEndianness, getIdentifierByteCount(i6) - byteArrayOfSpecifiedEndianness.length, getIdentifierByteCount(i6));
                    } else {
                        byteArrayOfSpecifiedEndianness = Arrays.copyOf(byteArrayOfSpecifiedEndianness, getIdentifierByteCount(i6));
                    }
                    LogManager.m6046d(TAG, "Truncated identifier because it is too long.  It is now: " + byteArrayToString(byteArrayOfSpecifiedEndianness), new Object[0]);
                } else {
                    LogManager.m6046d(TAG, "Identifier size is just right: " + byteArrayToString(byteArrayOfSpecifiedEndianness), new Object[0]);
                }
                for (int intValue2 = this.mIdentifierStartOffsets.get(i6).intValue(); intValue2 <= (this.mIdentifierStartOffsets.get(i6).intValue() + byteArrayOfSpecifiedEndianness.length) - 1; intValue2++) {
                    bArr[intValue2 - 2] = byteArrayOfSpecifiedEndianness[intValue2 - this.mIdentifierStartOffsets.get(i6).intValue()];
                }
            }
            Integer num3 = this.mPowerStartOffset;
            if (!(num3 == null || this.mPowerEndOffset == null)) {
                for (int intValue3 = num3.intValue(); intValue3 <= this.mPowerEndOffset.intValue(); intValue3++) {
                    bArr[intValue3 - 2] = (byte) ((beacon.getTxPower() >> ((intValue3 - this.mPowerStartOffset.intValue()) * 8)) & 255);
                }
            }
            for (int i7 = 0; i7 < this.mDataStartOffsets.size(); i7++) {
                long longValue = beacon.getDataFields().get(i7).longValue();
                int intValue4 = this.mDataEndOffsets.get(i7).intValue() - this.mDataStartOffsets.get(i7).intValue();
                for (int i8 = 0; i8 <= intValue4; i8++) {
                    bArr[(this.mDataStartOffsets.get(i7).intValue() - 2) + (!this.mDataLittleEndianFlags.get(i7).booleanValue() ? intValue4 - i8 : i8)] = (byte) ((int) ((longValue >> (i8 * 8)) & 255));
                }
            }
            return bArr;
        }
        throw new IllegalArgumentException("Beacon has " + beacon.getIdentifiers().size() + " identifiers but format requires " + getIdentifierCount());
    }

    public BeaconParser setMatchingBeaconTypeCode(Long l) {
        this.mMatchingBeaconTypeCode = l;
        return this;
    }

    public int getIdentifierByteCount(int i) {
        return (this.mIdentifierEndOffsets.get(i).intValue() - this.mIdentifierStartOffsets.get(i).intValue()) + 1;
    }

    public int getIdentifierCount() {
        return this.mIdentifierStartOffsets.size();
    }

    public int getDataFieldCount() {
        return this.mDataStartOffsets.size();
    }

    public String getLayout() {
        return this.mBeaconLayout;
    }

    public int getPowerCorrection() {
        return this.mDBmCorrection.intValue();
    }

    protected static String bytesToHex(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i] & 255;
            int i2 = i * 2;
            char[] cArr2 = HEX_ARRAY;
            cArr[i2] = cArr2[b >>> 4];
            cArr[i2 + 1] = cArr2[b & 15];
        }
        return new String(cArr);
    }

    public static class BeaconLayoutException extends RuntimeException {
        public BeaconLayoutException(String str) {
            super(str);
        }
    }

    public static byte[] longToByteArray(long j, int i) {
        return longToByteArray(j, i, true);
    }

    public static byte[] longToByteArray(long j, int i, boolean z) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = ((i - (z ? i2 : (i - i2) - 1)) - 1) * 8;
            bArr[i2] = (byte) ((int) (((255 << i3) & j) >> ((int) ((long) i3))));
        }
        return bArr;
    }

    private int calculateLayoutSize() {
        List<Integer> list = this.mIdentifierEndOffsets;
        int i = 0;
        if (list != null) {
            for (Integer intValue : list) {
                int intValue2 = intValue.intValue();
                if (intValue2 > i) {
                    i = intValue2;
                }
            }
        }
        List<Integer> list2 = this.mDataEndOffsets;
        if (list2 != null) {
            for (Integer intValue3 : list2) {
                int intValue4 = intValue3.intValue();
                if (intValue4 > i) {
                    i = intValue4;
                }
            }
        }
        Integer num = this.mPowerEndOffset;
        if (num != null && num.intValue() > i) {
            i = this.mPowerEndOffset.intValue();
        }
        Integer num2 = this.mServiceUuidEndOffset;
        if (num2 != null && num2.intValue() > i) {
            i = this.mServiceUuidEndOffset.intValue();
        }
        return i + 1;
    }

    private boolean byteArraysMatch(byte[] bArr, int i, byte[] bArr2) {
        int length = bArr2.length;
        if (bArr.length - i < length) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (bArr[i + i2] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    private String byteArrayToString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            sb.append(String.format("%02x", new Object[]{Byte.valueOf(bArr[i])}));
            sb.append(StringUtils.SPACE);
        }
        return sb.toString().trim();
    }

    private String byteArrayToFormattedString(byte[] bArr, int i, int i2, boolean z) {
        int i3 = i2 - i;
        int i4 = i3 + 1;
        byte[] bArr2 = new byte[i4];
        if (z) {
            for (int i5 = 0; i5 <= i3; i5++) {
                bArr2[i5] = bArr[((i + i4) - 1) - i5];
            }
        } else {
            for (int i6 = 0; i6 <= i3; i6++) {
                bArr2[i6] = bArr[i + i6];
            }
        }
        if (i4 < 5) {
            long j = 0;
            for (int i7 = 0; i7 < i4; i7++) {
                double d = (double) i7;
                Double.isNaN(d);
                j += ((long) (bArr2[(i4 - i7) - 1] & 255)) * ((long) Math.pow(256.0d, d * 1.0d));
            }
            return Long.toString(j);
        }
        String bytesToHex = bytesToHex(bArr2);
        if (i4 == 16) {
            return bytesToHex.substring(0, 8) + "-" + bytesToHex.substring(8, 12) + "-" + bytesToHex.substring(12, 16) + "-" + bytesToHex.substring(16, 20) + "-" + bytesToHex.substring(20, 32);
        }
        return "0x" + bytesToHex;
    }

    private byte[] ensureMaxSize(byte[] bArr, int i) {
        if (bArr.length >= i) {
            return bArr;
        }
        return Arrays.copyOf(bArr, i);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.mMatchingBeaconTypeCode, this.mIdentifierStartOffsets, this.mIdentifierEndOffsets, this.mIdentifierLittleEndianFlags, this.mDataStartOffsets, this.mDataEndOffsets, this.mDataLittleEndianFlags, this.mIdentifierVariableLengthFlags, this.mMatchingBeaconTypeCodeStartOffset, this.mMatchingBeaconTypeCodeEndOffset, this.mServiceUuidStartOffset, this.mServiceUuidEndOffset, this.mServiceUuid, this.mExtraFrame, this.mPowerStartOffset, this.mPowerEndOffset, this.mDBmCorrection, this.mLayoutSize, this.mAllowPduOverflow, this.mIdentifier, this.mHardwareAssistManufacturers, this.extraParsers});
    }

    public boolean equals(Object obj) {
        try {
            BeaconParser beaconParser = (BeaconParser) obj;
            return beaconParser.mBeaconLayout != null && beaconParser.mBeaconLayout.equals(this.mBeaconLayout) && beaconParser.mIdentifier != null && beaconParser.mIdentifier.equals(this.mIdentifier);
        } catch (ClassCastException unused) {
            return false;
        }
    }
}
