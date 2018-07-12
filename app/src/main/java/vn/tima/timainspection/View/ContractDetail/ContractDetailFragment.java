package vn.tima.timainspection.View.ContractDetail;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.hoang8f.android.segmented.SegmentedGroup;
import vn.tima.timainspection.Model.Entity.ContractDetailEntity;
import vn.tima.timainspection.Presenter.ContractDetail.ContractDetailPresenter;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Common;
import vn.tima.timainspection.Util.SimpleDividerItemDecoration;

/**
 * Created by tima on 11/28/17.
 */

@SuppressLint("ValidFragment")
public class ContractDetailFragment extends Fragment implements IContractDetailFragment {
    @BindView(R.id.rvDetail)
    RecyclerView rvDetail;
    private ContractDetailAdapter adapter;
    private boolean hideEmpty;
    private ProgressDialog progressDoalog;
    private ContractDetailPresenter contractDetailPresenter;
    private static final String TAG = "ContractDetailFragment";
    public static ContractDetailEntity contractDetailEntity;
    private int TypeID;

    public ContractDetailFragment(int TypeID) {
        this.TypeID = TypeID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contract_detail, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        progressDoalog = new ProgressDialog(this.getActivity());
        progressDoalog.setMessage("loading....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.setCancelable(false);
        progressDoalog.show();

        contractDetailPresenter = new ContractDetailPresenter(this.getActivity(), this);
        Intent intent = getActivity().getIntent();
        int customerId = intent.getIntExtra("CustomerID", 0);
        int loanId = intent.getIntExtra("LoanID", 0);
        contractDetailPresenter.getContractDetail(customerId, loanId);
    }

    @Override
    public void getContractDetail(ContractDetailEntity contractDetailEntity) {
        ContractDetailFragment.contractDetailEntity = contractDetailEntity;
        adapter = new ContractDetailAdapter(contractDetailEntity, TypeID);
        if(ContractDetailActivity.type == 0) {
            ContractDetailActivity.isThamDinhNha = contractDetailEntity.IsThamDinhNha;
            ContractDetailActivity.isThamDinhCongTy = contractDetailEntity.IsThamDinhCongTy;
        }
        if(contractDetailEntity.getCustomer().getNameWard()!=null && !contractDetailEntity.getCustomer().getNameWard().isEmpty()) {
            ContractDetailActivity.nameWard = contractDetailEntity.getCustomer().getNameWard() + ", " + contractDetailEntity.getCustomer().getNameCity();
        }
        if(contractDetailEntity.getCustomer().getStreet() != null && contractDetailEntity.getCustomer().getStreet().contains("/")) {
            String[] addressHouses = contractDetailEntity.getCustomer().getStreet().split("/");
            ContractDetailActivity.addressHouse = addressHouses[addressHouses.length - 1].trim();
        } else {
            ContractDetailActivity.addressHouse = contractDetailEntity.getCustomer().getStreet();
        }
        if(contractDetailEntity.getCustomer().getAddressCompany() != null && contractDetailEntity.getCustomer().getAddressCompany().contains("/")) {
            String[] addressCompanies = contractDetailEntity.getCustomer().getAddressCompany().split("/");
            ContractDetailActivity.addressCompany = addressCompanies[addressCompanies.length - 1].trim();
        } else {
            ContractDetailActivity.addressCompany = contractDetailEntity.getCustomer().getAddressCompany();
        }
        GridLayoutManager manager = new GridLayoutManager(this.getActivity(), 1);
        rvDetail.setLayoutManager(manager);
        adapter.setLayoutManager(manager);
        adapter.shouldShowHeadersForEmptySections(hideEmpty);
        rvDetail.setAdapter(adapter);
        progressDoalog.dismiss();
    }

    @Override
    public void getContractDetailFail(String msg) {
        Common.showAlert(this.getActivity(), msg);
        progressDoalog.dismiss();
    }

}

