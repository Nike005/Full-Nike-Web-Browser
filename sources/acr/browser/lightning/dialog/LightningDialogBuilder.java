package acr.browser.lightning.dialog;

import acr.browser.lightning.activity.MainActivity;
import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.constant.BookmarkPage;
import acr.browser.lightning.constant.Constants;
import acr.browser.lightning.controller.UIController;
import acr.browser.lightning.database.HistoryItem;
import acr.browser.lightning.database.bookmark.BookmarkModel;
import acr.browser.lightning.database.downloads.DownloadsModel;
import acr.browser.lightning.database.history.HistoryModel;
import acr.browser.lightning.dialog.BrowserDialog;
import acr.browser.lightning.preference.PreferenceManager;
import acr.browser.lightning.utils.C3245Utils;
import acr.browser.lightning.utils.IntentUtils;
import acr.browser.lightning.utils.Preconditions;
import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import com.anthonycr.bonsai.CompletableOnSubscribe;
import com.anthonycr.bonsai.Schedulers;
import com.anthonycr.bonsai.SingleOnSubscribe;
import com.wnikebrow_13999769.R;
import java.util.List;
import javax.inject.Inject;

public class LightningDialogBuilder {
    private static final String TAG = "LightningDialogBuilder";
    @Inject
    BookmarkModel mBookmarkManager;
    @Inject
    DownloadsModel mDownloadsModel;
    @Inject
    PreferenceManager mPreferenceManager;

    public enum NewTab {
        FOREGROUND,
        BACKGROUND,
        INCOGNITO
    }

    @Inject
    public LightningDialogBuilder() {
        BrowserApp.getAppComponent().inject(this);
    }

    public void showLongPressedDialogForBookmarkUrl(final Activity activity, final UIController uIController, String str) {
        if (!str.startsWith(Constants.FILE) || !str.endsWith(BookmarkPage.FILENAME)) {
            this.mBookmarkManager.findBookmarkForUrl(str).subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<HistoryItem>() {
                public void onItem(HistoryItem historyItem) {
                    if (historyItem != null) {
                        LightningDialogBuilder.this.showLongPressedDialogForBookmarkUrl(activity, uIController, historyItem);
                    }
                }
            });
            return;
        }
        String lastPathSegment = Uri.parse(str).getLastPathSegment();
        String substring = lastPathSegment.substring(0, (lastPathSegment.length() - 14) - 1);
        HistoryItem historyItem = new HistoryItem();
        historyItem.setIsFolder(true);
        historyItem.setTitle(substring);
        historyItem.setImageId(R.drawable.ic_folder);
        historyItem.setUrl(Constants.FOLDER + substring);
        showBookmarkFolderLongPressedDialog(activity, uIController, historyItem);
    }

    public void showLongPressedDialogForBookmarkUrl(final Activity activity, final UIController uIController, final HistoryItem historyItem) {
        final UIController uIController2 = uIController;
        final HistoryItem historyItem2 = historyItem;
        final Activity activity2 = activity;
        BrowserDialog.show(activity, (int) R.string.action_bookmarks, new BrowserDialog.Item(R.string.dialog_open_new_tab) {
            public void onClick() {
                uIController.handleNewTab(NewTab.FOREGROUND, historyItem.getUrl());
            }
        }, new BrowserDialog.Item(R.string.dialog_open_background_tab) {
            public void onClick() {
                uIController.handleNewTab(NewTab.BACKGROUND, historyItem.getUrl());
            }
        }, new BrowserDialog.Item(R.string.dialog_open_incognito_tab, activity instanceof MainActivity) {
            public void onClick() {
                uIController2.handleNewTab(NewTab.INCOGNITO, historyItem2.getUrl());
            }
        }, new BrowserDialog.Item(R.string.action_share) {
            public void onClick() {
                new IntentUtils(activity).shareUrl(historyItem.getUrl(), historyItem.getTitle());
            }
        }, new BrowserDialog.Item(R.string.dialog_copy_link) {
            public void onClick() {
                BrowserApp.copyToClipboard(activity, historyItem.getUrl());
            }
        }, new BrowserDialog.Item(R.string.dialog_remove_bookmark) {
            public void onClick() {
                LightningDialogBuilder.this.mBookmarkManager.deleteBookmark(historyItem).subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<Boolean>() {
                    public void onItem(Boolean bool) {
                        Preconditions.checkNonNull(bool);
                        if (bool.booleanValue()) {
                            uIController.handleBookmarkDeleted(historyItem);
                        }
                    }
                });
            }
        }, new BrowserDialog.Item(R.string.dialog_edit_bookmark) {
            public void onClick() {
                LightningDialogBuilder.this.showEditBookmarkDialog(activity2, uIController2, historyItem2);
            }
        });
    }

    public void showLongPressedDialogForDownloadUrl(Activity activity, final UIController uIController, String str) {
        BrowserDialog.show(activity, (int) R.string.action_downloads, new BrowserDialog.Item(R.string.dialog_delete_all_downloads) {
            public void onClick() {
                LightningDialogBuilder.this.mDownloadsModel.deleteAllDownloads().subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new CompletableOnSubscribe() {
                    public void onComplete() {
                        uIController.handleDownloadDeleted();
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void showEditBookmarkDialog(Activity activity, UIController uIController, HistoryItem historyItem) {
        final Activity activity2 = activity;
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle((int) R.string.title_edit_bookmark);
        final View inflate = View.inflate(activity, R.layout.dialog_edit_bookmark, (ViewGroup) null);
        final EditText editText = (EditText) inflate.findViewById(R.id.bookmark_title);
        final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.showOnHomePage);
        checkBox.setChecked(historyItem.isShowOnMainScreen());
        editText.setText(historyItem.getTitle());
        final EditText editText2 = (EditText) inflate.findViewById(R.id.bookmark_url);
        editText2.setText(historyItem.getUrl());
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) inflate.findViewById(R.id.bookmark_folder);
        autoCompleteTextView.setHint(R.string.folder);
        autoCompleteTextView.setText(historyItem.getFolder());
        final HistoryItem historyItem2 = historyItem;
        final UIController uIController2 = uIController;
        this.mBookmarkManager.getFolderNames().subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<List<String>>() {
            public void onItem(List<String> list) {
                Preconditions.checkNonNull(list);
                ArrayAdapter arrayAdapter = new ArrayAdapter(activity2, 17367050, list);
                autoCompleteTextView.setThreshold(1);
                autoCompleteTextView.setAdapter(arrayAdapter);
                builder.setView(inflate);
                builder.setPositiveButton((CharSequence) activity2.getString(R.string.action_ok), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        HistoryItem historyItem = new HistoryItem();
                        historyItem.setTitle(editText.getText().toString());
                        historyItem.setShowOnMainScreen(checkBox.isChecked());
                        historyItem.setUrl(editText2.getText().toString());
                        historyItem.setUrl(editText2.getText().toString());
                        historyItem.setFolder(autoCompleteTextView.getText().toString());
                        LightningDialogBuilder.this.mBookmarkManager.editBookmark(historyItem2, historyItem).subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new CompletableOnSubscribe() {
                            public void onComplete() {
                                uIController2.handleBookmarksChange();
                            }
                        });
                    }
                });
                BrowserDialog.setDialogSize(activity2, builder.show());
            }
        });
    }

    public void showBookmarkFolderLongPressedDialog(Activity activity, final UIController uIController, final HistoryItem historyItem) {
        final Activity activity2 = activity;
        final UIController uIController2 = uIController;
        final HistoryItem historyItem2 = historyItem;
        BrowserDialog.show(activity, (int) R.string.action_folder, new BrowserDialog.Item(R.string.dialog_rename_folder) {
            public void onClick() {
                LightningDialogBuilder.this.showRenameFolderDialog(activity2, uIController2, historyItem2);
            }
        }, new BrowserDialog.Item(R.string.dialog_remove_folder) {
            public void onClick() {
                LightningDialogBuilder.this.mBookmarkManager.deleteFolder(historyItem.getTitle()).subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new CompletableOnSubscribe() {
                    public void onComplete() {
                        uIController.handleBookmarkDeleted(historyItem);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void showRenameFolderDialog(Activity activity, final UIController uIController, final HistoryItem historyItem) {
        BrowserDialog.showEditText(activity, R.string.title_rename_folder, R.string.hint_title, historyItem.getTitle(), R.string.action_ok, new BrowserDialog.EditorListener() {
            public void onClick(String str) {
                if (!TextUtils.isEmpty(str)) {
                    String title = historyItem.getTitle();
                    HistoryItem historyItem = new HistoryItem();
                    historyItem.setTitle(str);
                    historyItem.setUrl(Constants.FOLDER + str);
                    historyItem.setFolder(historyItem.getFolder());
                    historyItem.setIsFolder(true);
                    LightningDialogBuilder.this.mBookmarkManager.renameFolder(title, str).subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new CompletableOnSubscribe() {
                        public void onComplete() {
                            uIController.handleBookmarksChange();
                        }
                    });
                }
            }
        });
    }

    public void showLongPressedHistoryLinkDialog(final Activity activity, final UIController uIController, final String str) {
        final UIController uIController2 = uIController;
        final String str2 = str;
        BrowserDialog.show(activity, (int) R.string.action_history, new BrowserDialog.Item(R.string.dialog_open_new_tab) {
            public void onClick() {
                uIController.handleNewTab(NewTab.FOREGROUND, str);
            }
        }, new BrowserDialog.Item(R.string.dialog_open_background_tab) {
            public void onClick() {
                uIController.handleNewTab(NewTab.BACKGROUND, str);
            }
        }, new BrowserDialog.Item(R.string.dialog_open_incognito_tab, activity instanceof MainActivity) {
            public void onClick() {
                uIController2.handleNewTab(NewTab.INCOGNITO, str2);
            }
        }, new BrowserDialog.Item(R.string.action_share) {
            public void onClick() {
                new IntentUtils(activity).shareUrl(str, (String) null);
            }
        }, new BrowserDialog.Item(R.string.dialog_copy_link) {
            public void onClick() {
                BrowserApp.copyToClipboard(activity, str);
            }
        }, new BrowserDialog.Item(R.string.dialog_remove_from_history) {
            public void onClick() {
                HistoryModel.deleteHistoryItem(str).subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new CompletableOnSubscribe() {
                    public void onComplete() {
                        uIController.handleHistoryChange();
                    }
                });
            }
        });
    }

    public void showLongPressImageDialog(final Activity activity, final UIController uIController, final String str, String str2) {
        final UIController uIController2 = uIController;
        final String str3 = str;
        final Activity activity2 = activity;
        final String str4 = str;
        final String str5 = str2;
        BrowserDialog.show(activity, str.replace(Constants.HTTP, ""), new BrowserDialog.Item(R.string.dialog_open_new_tab) {
            public void onClick() {
                uIController.handleNewTab(NewTab.FOREGROUND, str);
            }
        }, new BrowserDialog.Item(R.string.dialog_open_background_tab) {
            public void onClick() {
                uIController.handleNewTab(NewTab.BACKGROUND, str);
            }
        }, new BrowserDialog.Item(R.string.dialog_open_incognito_tab, activity instanceof MainActivity) {
            public void onClick() {
                uIController2.handleNewTab(NewTab.INCOGNITO, str3);
            }
        }, new BrowserDialog.Item(R.string.action_share) {
            public void onClick() {
                new IntentUtils(activity).shareUrl(str, (String) null);
            }
        }, new BrowserDialog.Item(R.string.dialog_copy_link) {
            public void onClick() {
                BrowserApp.copyToClipboard(activity, str);
            }
        }, new BrowserDialog.Item(R.string.dialog_download_image) {
            public void onClick() {
                C3245Utils.downloadFile(activity2, LightningDialogBuilder.this.mPreferenceManager, str4, str5, "attachment", LightningDialogBuilder.this.mDownloadsModel);
            }
        });
    }

    public void showLongPressLinkDialog(final Activity activity, final UIController uIController, final String str) {
        final UIController uIController2 = uIController;
        final String str2 = str;
        BrowserDialog.show(activity, str, new BrowserDialog.Item(R.string.dialog_open_new_tab) {
            public void onClick() {
                uIController.handleNewTab(NewTab.FOREGROUND, str);
            }
        }, new BrowserDialog.Item(R.string.dialog_open_background_tab) {
            public void onClick() {
                uIController.handleNewTab(NewTab.BACKGROUND, str);
            }
        }, new BrowserDialog.Item(R.string.dialog_open_incognito_tab, activity instanceof MainActivity) {
            public void onClick() {
                uIController2.handleNewTab(NewTab.INCOGNITO, str2);
            }
        }, new BrowserDialog.Item(R.string.action_share) {
            public void onClick() {
                new IntentUtils(activity).shareUrl(str, (String) null);
            }
        }, new BrowserDialog.Item(R.string.dialog_copy_link) {
            public void onClick() {
                BrowserApp.copyToClipboard(activity, str);
            }
        });
    }
}
