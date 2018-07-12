package vn.tima.timainspection.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.ContextThemeWrapper;
import android.view.ViewGroup;
import android.view.Window;

import vn.tima.timainspection.R;

/**
 * Created by My PC on 11/22/2016.
 */
public class DialogUtils {
    public interface OnClickListener {
        void onClickSuccess();
    }

    public interface OnClickListener2 {
        void onClickSuccess();

        void onClickCancel();
    }

    public static void showAlertDialog(Context context, String title, String mess) {
        new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.AlertDialogCustom))
                .setTitle(title)
                .setMessage(mess)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    public static void showAlertDialog(Context context, String title, String mess, final OnClickListener onClickListener) {
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.BLACK);
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(title);
        ssBuilder.setSpan(
                foregroundColorSpan,
                0,
                title.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.AlertDialogCustom))
                .setTitle(ssBuilder)
                .setMessage(mess)
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        onClickListener.onClickSuccess();
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static void showExitDialog(Activity activity, String content, final OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(activity, R.style.AlertDialogCustom));
        builder.setMessage(content);
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onClickListener != null)
                    onClickListener.onClickSuccess();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog logout_dialog = builder.create();
        logout_dialog.show();
    }

    public static void showExitDialog2(Activity activity, String content, final OnClickListener2 onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(content);
        builder.setPositiveButton("Đã hoàn thành ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onClickListener != null)
                    onClickListener.onClickSuccess();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Chưa hoàn thành", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onClickListener != null)
                    onClickListener.onClickCancel();
                dialog.dismiss();
            }
        });
        AlertDialog logout_dialog = builder.create();
        logout_dialog.show();
    }

    public static void showExitDialog3(Activity activity, String content, final OnClickListener2 onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(content);
        builder.setPositiveButton("Đồng ý ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onClickListener != null)
                    onClickListener.onClickSuccess();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onClickListener != null)
                    onClickListener.onClickCancel();
                dialog.dismiss();
            }
        });
        AlertDialog logout_dialog = builder.create();
        logout_dialog.show();
    }

    public static void showExitDialog(Activity activity, String content, String okButton, String cancelButton, final OnClickListener2 onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(content);
        builder.setPositiveButton(okButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onClickListener != null)
                    onClickListener.onClickSuccess();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(cancelButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onClickListener != null)
                    onClickListener.onClickCancel();
                dialog.dismiss();
            }
        });
        AlertDialog logout_dialog = builder.create();
        logout_dialog.show();
    }

    public static void showExitDialog4(Activity activity, String content, final OnClickListener2 onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(content);
        builder.setPositiveButton("Đã làm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onClickListener != null)
                    onClickListener.onClickSuccess();
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Chưa làm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onClickListener != null)
                    onClickListener.onClickSuccess();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onClickListener != null)
                    onClickListener.onClickCancel();
                dialog.dismiss();
            }
        });
        AlertDialog logout_dialog = builder.create();
        logout_dialog.show();
    }

    public static void setFullDialog(Dialog dialog) {
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }
}
