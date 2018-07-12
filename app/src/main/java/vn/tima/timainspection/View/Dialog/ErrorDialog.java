package vn.tima.timainspection.View.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.tima.timainspection.Model.CheckList.CheckListRequest;
import vn.tima.timainspection.Model.Entity.CheckList;
import vn.tima.timainspection.Model.Entity.InsertErrorDepartment;
import vn.tima.timainspection.Model.Entity.Reason;
import vn.tima.timainspection.Model.Login.UserInfo;
import vn.tima.timainspection.Model.PostError.PostErrorRequest;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.DialogUtils;
import vn.tima.timainspection.listener.OnResponse;

/**
 * Created by HoangAnh on 8/2/2017.
 */

public class ErrorDialog implements View.OnClickListener {
    private Dialog mDialog;
    private Context mContext;
    private int loanId;

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    EditText tv_content;
    Spinner spn_error;
    Spinner spn_group;

    Button btn_error;

    String content="";

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_error:
                insertErrorDepartment();
                break;

        }
    }

    private void insertErrorDepartment() {
        String token = UserInfo.getInstance().getUser(mContext).getToken();
        int GroupUserError = ((Reason)spn_group.getSelectedItem()).getId();
        String NoteError = tv_content.getText().toString();
        int ErrorId = ((Reason)spn_error.getSelectedItem()).getId();
        String ErrorName = ((Reason)spn_error.getSelectedItem()).getReason();
        int Step = ((Reason)spn_group.getSelectedItem()).getStep();
        InsertErrorDepartment obj = new InsertErrorDepartment(token,loanId,GroupUserError,NoteError,ErrorId,ErrorName,Step);
        PostErrorRequest.getInstance(mContext).insertErrorDepartment(new OnResponse<String, String>() {
            @Override
            public void onResponseSuccess(String tag, String rs, String extraData) {
                DialogUtils.showAlertDialog(mContext, "Thông báo", extraData, new DialogUtils.OnClickListener() {
                    @Override
                    public void onClickSuccess() {
                        dismissDialog();
                    }
                });
            }

            @Override
            public void onResponseError(String tag, String message) {
                DialogUtils.showAlertDialog(mContext,"Thông báo",message);
            }
        },obj);
    }


    public ErrorDialog(Context mContext) {
        this.mContext = mContext;
        initDialog();

    }

    private void initDialog() {
        mDialog = new Dialog(mContext);
        mDialog.setContentView(R.layout.dialog_error);

        btn_error = (Button) mDialog.findViewById(R.id.btn_error);
        tv_content = (EditText) mDialog.findViewById(R.id.tv_content);
        spn_error = (Spinner) mDialog.findViewById(R.id.spn_error);
        spn_group = (Spinner) mDialog.findViewById(R.id.spn_group);
        tv_content.setText(content);
        btn_error.setOnClickListener(this);
        getListReason();
        initDialogGroup();
    }

    private void initDialogGroup() {
        List<Reason> list = new ArrayList<>();

        list.add(new Reason(10,"Direct Sale",0));
        list.add(new Reason(12,"TĐHS1",1));
        list.add(new Reason(12,"TĐHS2",2));
        SpinnerBaseAdapter adapter = new SpinnerBaseAdapter((ArrayList<Reason>) list,mContext);
        spn_group.setAdapter(adapter);
    }

    private void getListReason(){
        PostErrorRequest.getInstance(mContext).getListReason(new OnResponse<String, List<Reason>>() {
            @Override
            public void onResponseSuccess(String tag, String rs, List<Reason> extraData) {
                SpinnerBaseAdapter adapter = new SpinnerBaseAdapter((ArrayList<Reason>) extraData,mContext);
                spn_error.setAdapter(adapter);
            }

            @Override
            public void onResponseError(String tag, String message) {

            }
        });
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
    class SpinnerBaseAdapter extends BaseAdapter {
        ArrayList<Reason> listItem;
        Context context;

        public SpinnerBaseAdapter(ArrayList<Reason> listItem, Context context) {
            this.listItem = listItem;
            this.context = context;
        }

        @Override
        public int getCount() {
            return listItem.size();
        }

        @Override
        public Object getItem(int i) {
            return listItem.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.item_spiner, null, false);
            TextView textView = (TextView) rowView.findViewById(R.id.tv_name);
            textView.setText(listItem.get(i).getReason());
            return rowView;
        }
    }
}
