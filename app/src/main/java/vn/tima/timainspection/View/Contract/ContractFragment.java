package vn.tima.timainspection.View.Contract;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import info.hoang8f.android.segmented.SegmentedGroup;
import vn.tima.timainspection.Model.Entity.ContractEntity;
import vn.tima.timainspection.Model.Entity.WaitTDHSEntity;
import vn.tima.timainspection.Model.Login.UserInfo;
import vn.tima.timainspection.Presenter.Comment.CommentPresenter;
import vn.tima.timainspection.Presenter.Contract.ContractPresenter;
import vn.tima.timainspection.Presenter.CoordinatorFortransfer.CoordinatorFortransferPresenter;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Common;
import vn.tima.timainspection.Util.DateTimeHelper;
import vn.tima.timainspection.View.Comment.ICommentFragment;
import vn.tima.timainspection.View.Main.MainActivity;

/**
 * Created by tima on 11/25/17.
 */

public class ContractFragment extends Fragment implements IContractActivity, CompoundButton.OnCheckedChangeListener, ICommentFragment {
    @BindView(R.id.rbTatCa)
    RadioButton rbTatCa;
    @BindView(R.id.rbLuong)
    RadioButton rbLuong;
    @BindView(R.id.rbHoKhau)
    RadioButton rbHoKhau;
    @BindView(R.id.rbNhaDat)
    RadioButton rbNhaDat;
    @BindView(R.id.rbOto)
    RadioButton rbOto;
    @BindView(R.id.rbXeMay)
    RadioButton rbXeMay;
    @BindView(R.id.sgLoaiHopDong)
    SegmentedGroup sgLoaiHopDong;
    @BindView(R.id.rvHopDong)
    RecyclerView rvHopDong;
    Unbinder unbinder;

    private UserInfo userInfo;
    private static ContractPresenter contractPresenter;
    private ContractAdapter contractAdapter;
    private static SelectContractAdapter selectContractAdapter;
    private ProgressDialog progressDoalog;
    private static List<ContractEntity> extraData = new ArrayList<>();
    private List<WaitTDHSEntity> extraDataChoTD = new ArrayList<>();
    private static int typeTab = 0;
    public static Dialog dialog;

    public static CommentPresenter commentPresenter;

    public static ContractFragment newInstance(int type) {
        typeTab = type;
        ContractFragment fragment = new ContractFragment();
        return fragment;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.filter_menu, menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if(id == R.id.action_filter){
//            Common.showAlert(this.getActivity(), "abc");
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Danh sách hợp đồng");
        View view = inflater.inflate(R.layout.fragment_contract, container, false);
        unbinder = ButterKnife.bind(this, view);

        progressDoalog = new ProgressDialog(this.getActivity());
        progressDoalog.setMessage("loading....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.setCancelable(false);
        progressDoalog.show();

        userInfo = UserInfo.getInstance();
        contractPresenter = new ContractPresenter(this.getActivity(), this);
        commentPresenter = new CommentPresenter(this.getActivity(), this);

        rbTatCa.setOnCheckedChangeListener(this);
        rbLuong.setOnCheckedChangeListener(this);
        rbHoKhau.setOnCheckedChangeListener(this);
        rbNhaDat.setOnCheckedChangeListener(this);
        rbXeMay.setOnCheckedChangeListener(this);
        rbOto.setOnCheckedChangeListener(this);
        initListContract();
        return view;
    }

    public static void showDialogSelectContract(Context context, int isHouse, ContractEntity contractEntity, int loanID, String content) {
        try {
            dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog_select_contract);
            RecyclerView rvContract = (RecyclerView)dialog.findViewById(R.id.rvContract);

            dialog.setTitle("Chọn hồ sơ đang thẩm định");
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            extraData.remove(contractEntity);
            selectContractAdapter = new SelectContractAdapter(context, extraData, contractPresenter, isHouse, loanID, content);
            Common.setVerticalRecyclerView(context, rvContract);
            rvContract.setAdapter(selectContractAdapter);

            dialog.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initListContract(){
        contractAdapter = new ContractAdapter(this.getActivity(), this.extraData, this.extraDataChoTD, contractPresenter, typeTab);
        Common.setVerticalRecyclerView(this.getActivity(), rvHopDong);
        rvHopDong.setAdapter(contractAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        int TypeID = 0;
        if(rbLuong.isChecked()) {
            TypeID = getTypeID(R.id.rbLuong);
        }
        if(rbHoKhau.isChecked()) {
            TypeID = getTypeID(R.id.rbHoKhau);
        }
        if(rbNhaDat.isChecked()) {
            TypeID = getTypeID(R.id.rbNhaDat);
        }
        if(rbOto.isChecked()) {
            TypeID = getTypeID(R.id.rbOto);
        }
        if(rbXeMay.isChecked()) {
            TypeID = getTypeID(R.id.rbXeMay);
        }
        if(typeTab == 0) {
            contractPresenter.getContractList("01-01-2016", DateTimeHelper.getCurrentDate(), "", 0, 1, TypeID);
        } else {
            contractPresenter.getContractListChoTD("01-01-2016", DateTimeHelper.getCurrentDate(), "", 0, 1, TypeID);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getContractList(List<ContractEntity> extraData) {
        this.extraData = extraData;
        contractAdapter.setContractData(extraData);
        contractAdapter.notifyDataSetChanged();
        progressDoalog.dismiss();
    }

    @Override
    public void getContractListChoTD(List<WaitTDHSEntity> extraData) {
        this.extraDataChoTD = extraData;
        contractAdapter.setContractDataChoTD(extraData);
        contractAdapter.notifyDataSetChanged();
        progressDoalog.dismiss();
    }

    @Override
    public void getContractListFail(String msg) {
        extraData.clear();
        contractAdapter.setContractData(extraData);
        contractAdapter.notifyDataSetChanged();
        progressDoalog.dismiss();
    }

    @Override
    public void getContractListChoTDFail(String msg) {
        extraDataChoTD.clear();
        contractAdapter.setContractDataChoTD(extraDataChoTD);
        contractAdapter.notifyDataSetChanged();
        progressDoalog.dismiss();
    }

    @Override
    public void insertProductAppraiserSuccess(String msg) {
//        Common.showAlert(this.getActivity(), msg);
    }

    @Override
    public void insertProductAppraiserFail(String msg) {
        Common.showAlert(this.getActivity(), msg);
//        Toast.makeText(this.getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(b) {
            progressDoalog.show();
            int typeId = getTypeID(compoundButton.getId());
            if(typeTab == 0) {
                contractPresenter.getContractList("01-01-2016", DateTimeHelper.getCurrentDate(), "", 0, 1, typeId);
            } else {
                contractPresenter.getContractListChoTD("01-01-2016", DateTimeHelper.getCurrentDate(), "", 0, 1, typeId);
            }
        }
    }

    private int getTypeID(int id) {
        switch (id) {
            case R.id.rbLuong:
                return 1;
            case R.id.rbHoKhau:
                return 4;
            case R.id.rbNhaDat:
                return 7;
            case R.id.rbXeMay:
                return 2;
            case R.id.rbOto:
                return 6;
            default:
                return 0;
        }
    }

    @Override
    public void addCommentSuccess(String msg) {
        Toast.makeText(this.getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void addCommentFail(String msg) {
        Toast.makeText(this.getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void addLocationSuccess(String msg) {

    }

    @Override
    public void addLocationFail(String msg) {

    }
}
