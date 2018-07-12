package vn.tima.timainspection.View.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.tima.timainspection.R;

/**
 * Created by My PC on 1/4/2017.
 */

public class LoadingDialog implements View.OnClickListener {
    public static final String TAG = LoadingDialog.class.getSimpleName();
    private Dialog mDialog;
    private Activity mContext;
    @BindView(R.id.tv_loading_message)
    TextView tv_loading;

    public LoadingDialog(Activity mContext, String message) {
        this.mContext = mContext;
        initDialog();
        initVew();
        setData();
        setMessage(message);
    }
    public boolean isShowing(){
        return mDialog!=null ? mDialog.isShowing():false;
    }

    private void initVew() {
        ButterKnife.bind(this, mDialog);

    }

    private void initDialog() {
        mDialog = new Dialog(mContext, R.style.ProgressHUD);
        mDialog.setContentView(R.layout.dialog_progress_loading);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    private void setData() {

    }

    public void setCancelable(boolean b) {
        mDialog.setCancelable(b);
    }
    public void setCanceledOnTouchOutside(boolean b) {
        mDialog.setCanceledOnTouchOutside(b);
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
    public void setMessage(String message){
        tv_loading.setText(message);
    }


    @Override
    public void onClick(View v) {

    }
}
