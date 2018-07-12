package vn.tima.timainspection.View.Dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import vn.tima.timainspection.Model.ContractDetail.ContractDetailRequest;
import vn.tima.timainspection.Model.Entity.InsertBacklog;
import vn.tima.timainspection.Model.Login.UserInfo;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Common;
import vn.tima.timainspection.Util.DialogUtils;
import vn.tima.timainspection.listener.OnResponse;

/**
 * Created by HoangAnh on 8/2/2017.
 */

public class ReportBacklogDialog implements View.OnClickListener {
    private RadioButton rbDoKH;
    private RadioButton rbDoNV;
    private RadioButton rbTon;
    private EditText etLyDo;
    private Button btHoanTat;
    private Button btHuy;
    private Dialog mDialog;
    private Context mContext;
    private int loanId;

    private ProgressDialog progressDialog;

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btHoanTat:
                insertBacklog();
                break;
            case R.id.btHuy:
                dismissDialog();
                break;

        }
    }

    private void insertBacklog() {
        if(!rbDoKH.isChecked() && !rbDoNV.isChecked() && !rbTon.isChecked()) {
            Common.showAlert(mContext, "Vui lòng chọn 1 lý do tồn");
            return;
        }

        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("loading....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();

        String token = UserInfo.getInstance().getUser(mContext).getToken();
        InsertBacklog obj = new InsertBacklog(token, 1, loanId, etLyDo.getText().toString(), rbDoKH.isChecked() ? 1 : rbDoNV.isChecked() ? 2 : 3, 1);
        ContractDetailRequest.getInstance(mContext).insertBacklog(new OnResponse<String, String>() {
            @Override
            public void onResponseSuccess(String tag, String rs, String extraData) {
                DialogUtils.showAlertDialog(mContext, "Thông báo", extraData, new DialogUtils.OnClickListener() {
                    @Override
                    public void onClickSuccess() {
                        progressDialog.dismiss();
                        dismissDialog();
                    }
                });
            }

            @Override
            public void onResponseError(String tag, String message) {
                progressDialog.dismiss();
                DialogUtils.showAlertDialog(mContext,"Thông báo",message);
            }
        }, obj);
    }


    public ReportBacklogDialog(Context mContext) {
        this.mContext = mContext;
        initDialog();

    }

    private void initDialog() {
        mDialog = new Dialog(mContext);
        mDialog.setContentView(R.layout.dialog_backlog);
        mDialog.setTitle("Tồn");

        btHoanTat = (Button) mDialog.findViewById(R.id.btHoanTat);
        btHuy = (Button) mDialog.findViewById(R.id.btHuy);
        rbDoKH = (RadioButton) mDialog.findViewById(R.id.rbDoKH);
        rbDoNV = (RadioButton) mDialog.findViewById(R.id.rbDoNV);
        rbTon = (RadioButton) mDialog.findViewById(R.id.rbTon);
        etLyDo = (EditText) mDialog.findViewById(R.id.etLyDo);

        btHoanTat.setOnClickListener(this);
        btHuy.setOnClickListener(this);
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
