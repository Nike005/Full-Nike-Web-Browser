package org.altbeacon.beacon.service;

import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import android.content.pm.ApplicationInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.beacon.service.scanner.NonBeaconLeScanCallback;

public class ScanDataProcessor {
    private static final String TAG = ScanDataProcessor.class.getSimpleName();
    private Set<BeaconParser> mBeaconParsers = new HashSet();
    private DetectionTracker mDetectionTracker = DetectionTracker.getInstance();
    private ExtraDataBeaconTracker mExtraDataBeaconTracker;
    private MonitoringStatus mMonitoringStatus;
    private NonBeaconLeScanCallback mNonBeaconLeScanCallback;
    private Map<Region, RangeState> mRangedRegionState = new HashMap();
    private Service mService;
    int trackedBeaconsPacketCount;

    public ScanDataProcessor(Service service, ScanState scanState) {
        this.mService = service;
        this.mMonitoringStatus = scanState.getMonitoringStatus();
        this.mRangedRegionState = scanState.getRangedRegionState();
        this.mMonitoringStatus = scanState.getMonitoringStatus();
        this.mExtraDataBeaconTracker = scanState.getExtraBeaconDataTracker();
        this.mBeaconParsers = scanState.getBeaconParsers();
    }

    public void process(ScanResult scanResult) {
        process(new ScanData(scanResult.getDevice(), scanResult.getRssi(), scanResult.getScanRecord().getBytes()));
    }

    /* JADX WARNING: Removed duplicated region for block: B:1:0x0007 A[LOOP:0: B:1:0x0007->B:4:0x001d, LOOP_START, PHI: r1 
      PHI: (r1v1 org.altbeacon.beacon.Beacon) = (r1v0 org.altbeacon.beacon.Beacon), (r1v6 org.altbeacon.beacon.Beacon) binds: [B:0:0x0000, B:4:0x001d] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void process(org.altbeacon.beacon.service.ScanDataProcessor.ScanData r6) {
        /*
            r5 = this;
            java.util.Set<org.altbeacon.beacon.BeaconParser> r0 = r5.mBeaconParsers
            java.util.Iterator r0 = r0.iterator()
            r1 = 0
        L_0x0007:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x001f
            java.lang.Object r1 = r0.next()
            org.altbeacon.beacon.BeaconParser r1 = (org.altbeacon.beacon.BeaconParser) r1
            byte[] r2 = r6.scanRecord
            int r3 = r6.rssi
            android.bluetooth.BluetoothDevice r4 = r6.device
            org.altbeacon.beacon.Beacon r1 = r1.fromScanData(r2, r3, r4)
            if (r1 == 0) goto L_0x0007
        L_0x001f:
            if (r1 == 0) goto L_0x0030
            org.altbeacon.beacon.service.DetectionTracker r6 = r5.mDetectionTracker
            r6.recordDetection()
            int r6 = r5.trackedBeaconsPacketCount
            int r6 = r6 + 1
            r5.trackedBeaconsPacketCount = r6
            r5.processBeaconFromScan(r1)
            goto L_0x003d
        L_0x0030:
            org.altbeacon.beacon.service.scanner.NonBeaconLeScanCallback r0 = r5.mNonBeaconLeScanCallback
            if (r0 == 0) goto L_0x003d
            android.bluetooth.BluetoothDevice r1 = r6.device
            int r2 = r6.rssi
            byte[] r6 = r6.scanRecord
            r0.onNonBeaconLeScan(r1, r2, r6)
        L_0x003d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.altbeacon.beacon.service.ScanDataProcessor.process(org.altbeacon.beacon.service.ScanDataProcessor$ScanData):void");
    }

    private class ScanData {
        BluetoothDevice device;
        int rssi;
        byte[] scanRecord;

        public ScanData(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            this.device = bluetoothDevice;
            this.rssi = i;
            this.scanRecord = bArr;
        }
    }

    private void processBeaconFromScan(Beacon beacon) {
        if (Stats.getInstance().isEnabled()) {
            Stats.getInstance().log(beacon);
        }
        if (LogManager.isVerboseLoggingEnabled()) {
            LogManager.m6046d(TAG, "beacon detected : %s", beacon.toString());
        }
        Beacon track = this.mExtraDataBeaconTracker.track(beacon);
        if (track != null) {
            this.mMonitoringStatus.updateNewlyInsideInRegionsContaining(track);
            String str = TAG;
            LogManager.m6046d(str, "looking for ranging region matches for this beacon out of " + this.mRangedRegionState.keySet().size() + " regions.", new Object[0]);
            synchronized (this.mRangedRegionState) {
                for (Region next : matchingRegions(track, this.mRangedRegionState.keySet())) {
                    LogManager.m6046d(TAG, "matches ranging region: %s", next);
                    RangeState rangeState = this.mRangedRegionState.get(next);
                    if (rangeState != null) {
                        rangeState.addBeacon(track);
                    }
                }
            }
        } else if (LogManager.isVerboseLoggingEnabled()) {
            LogManager.m6046d(TAG, "not processing detections for GATT extra data beacon", new Object[0]);
        }
    }

    private List<Region> matchingRegions(Beacon beacon, Collection<Region> collection) {
        ArrayList arrayList = new ArrayList();
        for (Region next : collection) {
            if (next.matchesBeacon(beacon)) {
                arrayList.add(next);
            } else {
                LogManager.m6046d(TAG, "This region (%s) does not match beacon: %s", next, beacon);
            }
        }
        return arrayList;
    }

    public void onCycleEnd() {
        this.mMonitoringStatus.updateNewlyOutside();
        processRangeData();
        if (BeaconManager.getBeaconSimulator() == null) {
            return;
        }
        if (BeaconManager.getBeaconSimulator().getBeacons() != null) {
            ApplicationInfo applicationInfo = this.mService.getApplicationContext().getApplicationInfo();
            int i = applicationInfo.flags & 2;
            applicationInfo.flags = i;
            if (i != 0) {
                for (Beacon processBeaconFromScan : BeaconManager.getBeaconSimulator().getBeacons()) {
                    processBeaconFromScan(processBeaconFromScan);
                }
                return;
            }
            LogManager.m6054w(TAG, "Beacon simulations provided, but ignored because we are not running in debug mode.  Please remove beacon simulations for production.", new Object[0]);
            return;
        }
        LogManager.m6054w(TAG, "getBeacons is returning null. No simulated beacons to report.", new Object[0]);
    }

    private void processRangeData() {
        synchronized (this.mRangedRegionState) {
            for (Region next : this.mRangedRegionState.keySet()) {
                LogManager.m6046d(TAG, "Calling ranging callback", new Object[0]);
                new Callback(this.mService.getPackageName()).call(this.mService, "rangingData", new RangingData(this.mRangedRegionState.get(next).finalizeBeacons(), next).toBundle());
            }
        }
    }
}
