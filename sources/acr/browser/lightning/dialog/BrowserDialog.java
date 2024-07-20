package acr.browser.lightning.dialog;

import acr.browser.lightning.utils.DeviceUtils;
import acr.browser.lightning.utils.ResourceUtils;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.wnikebrow_13999769.R;
import java.util.ArrayList;

public class BrowserDialog {

    public interface EditorListener {
        void onClick(String str);
    }

    public static abstract class Item {
        private boolean mCondition;
        private final int mTitle;

        public abstract void onClick();

        Item(int i, boolean z) {
            this(i);
            this.mCondition = z;
        }

        protected Item(int i) {
            this.mCondition = true;
            this.mTitle = i;
        }

        /* access modifiers changed from: private */
        public int getTitle() {
            return this.mTitle;
        }

        /* access modifiers changed from: private */
        public boolean isConditionMet() {
            return this.mCondition;
        }
    }

    public static void show(Activity activity, int i, Item... itemArr) {
        show(activity, activity.getString(i), itemArr);
    }

    public static void show(Activity activity, String str, Item... itemArr) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View inflate = LayoutInflater.from(activity).inflate(R.layout.list_dialog, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.dialog_title);
        ListView listView = (ListView) inflate.findViewById(R.id.dialog_list);
        ArrayAdapter arrayAdapter = new ArrayAdapter(activity, 17367043);
        final ArrayList<Item> arrayList = new ArrayList<>(1);
        for (Item item : itemArr) {
            if (item.isConditionMet()) {
                arrayList.add(item);
            }
        }
        for (Item access$100 : arrayList) {
            arrayAdapter.add(activity.getString(access$100.getTitle()));
        }
        if (!TextUtils.isEmpty(str)) {
            textView.setText(str);
        }
        listView.setAdapter(arrayAdapter);
        listView.setDivider((Drawable) null);
        builder.setView(inflate);
        final AlertDialog show = builder.show();
        setDialogSize(activity, show);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ((Item) arrayList.get(i)).onClick();
                show.dismiss();
            }
        });
    }

    public static void showEditText(Activity activity, int i, int i2, int i3, EditorListener editorListener) {
        showEditText(activity, i, i2, (String) null, i3, editorListener);
    }

    public static void showEditText(Activity activity, int i, int i2, String str, int i3, final EditorListener editorListener) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.dialog_edit_text, (ViewGroup) null);
        final EditText editText = (EditText) inflate.findViewById(R.id.dialog_edit_text);
        editText.setHint(i2);
        if (str != null) {
            editText.setText(str);
        }
        setDialogSize(activity, new AlertDialog.Builder(activity).setTitle(i).setView(inflate).setPositiveButton(i3, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                editorListener.onClick(editText.getText().toString());
            }
        }).show());
    }

    public static void setDialogSize(Context context, Dialog dialog) {
        int dimen = ResourceUtils.dimen(context, R.dimen.dialog_max_size);
        int screenWidth = DeviceUtils.getScreenWidth(context) - (ResourceUtils.dimen(context, R.dimen.dialog_padding) * 2);
        if (dimen > screenWidth) {
            dimen = screenWidth;
        }
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(dimen, -2);
        }
    }
}
