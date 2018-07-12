package vn.tima.timainspection.View.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import vn.tima.timainspection.R;

/**
 * Created by HoangAnh on 8/2/2017.
 */

public class EditAnswerDialog implements View.OnClickListener {
    private Dialog mDialog;
    private Context mContext;

    EditText tv_content;

    Button btn_edit;

    String content="";

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_edit:
                if (onSubmitClick != null) {
                    content = tv_content.getText().toString();
                    onSubmitClick.onSubmitComment(content);
                }
                break;

        }
    }

    public interface OnSubmitClick {
        public void onSubmitComment(String answer);
    }

    private OnSubmitClick onSubmitClick;

    public void setOnSubmitClick(OnSubmitClick onSubmitClick) {
        this.onSubmitClick = onSubmitClick;
    }

    public EditAnswerDialog(Context mContext,String content) {
        this.mContext = mContext;
        this.content =content;
        initDialog();

    }
    public EditAnswerDialog(Context mContext) {
        this.mContext = mContext;
        initDialog();

    }

    private void initDialog() {
        mDialog = new Dialog(mContext);
        mDialog.setContentView(R.layout.dialog_edit_answer);

        btn_edit = (Button) mDialog.findViewById(R.id.btn_edit);
        tv_content = (EditText) mDialog.findViewById(R.id.tv_content);
        tv_content.setText(content);
        btn_edit.setOnClickListener(this);
    }




    public void showDialog() {
        if (mDialog != null) {
            mDialog.show();
        }
    }

    public void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mContext = null;
            mDialog = null;
        }
    }
}
