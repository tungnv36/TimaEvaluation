package vn.tima.timainspection.View.ContractVouchers;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.tima.timainspection.Model.Contract.ContractRequest;
import vn.tima.timainspection.Model.Entity.ContractEntity;
import vn.tima.timainspection.Model.Entity.LibraryImage;
import vn.tima.timainspection.Model.Entity.WaitTDHSEntity;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Common;
import vn.tima.timainspection.View.Dialog.ViewImageDialog;
import vn.tima.timainspection.listener.OnResponse;

/**
 * Created by tima on 11/29/17.
 */

@SuppressLint("ValidFragment")
public class ContractVouchersFragment extends Fragment {
    @BindView(R.id.rv_images)
    RecyclerView rvImage;
    private LibraryImageVoucherAdapter libraryImageAdapter;
    private ArrayList<LibraryImage> listImage;
    private ContractEntity contractEntity;
    private WaitTDHSEntity waitTDHSEntity;
    private int type;

    public ContractVouchersFragment(int type) {
        this.type = type;
    }

    @RequiresApi(api = Build.VERSION_CODES.ECLAIR_MR1)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(type == 0) {
            contractEntity = (ContractEntity) getActivity().getIntent().getSerializableExtra("contractEntity");
        } else {
            waitTDHSEntity = (WaitTDHSEntity) getActivity().getIntent().getSerializableExtra("contractChoTDEntity");
        }
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_tab_detail_contract, null, false);
        ButterKnife.bind(this, view);
        intitRecyclerView();
        getDataImage();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.ECLAIR_MR1)
    private void intitRecyclerView() {
        listImage = new ArrayList<>();
        Common.setGridLayoutRecyclerView(getContext(), rvImage, 3);
        libraryImageAdapter = new LibraryImageVoucherAdapter();
        libraryImageAdapter.setShowTitle(true);
        libraryImageAdapter.setShowUserName(true);
        libraryImageAdapter.setListImage(listImage);
        libraryImageAdapter.setListener(new LibraryImageVoucherAdapter.OnclickListener() {
            @Override
            public void onClick(int position) {
                ViewImageDialog dialog = new ViewImageDialog(getActivity(),listImage,position);
                dialog.setCancelable(true);
                dialog.showDialog();
            }

            @Override
            public void onButtonDeleteListener(int position) {

            }
        });
        rvImage.setAdapter(libraryImageAdapter);
    }

    private void getDataImage() {
        ContractRequest.getInstance(getContext()).getListImage("0", type == 0?String.valueOf(contractEntity.getLoancreditid()):String.valueOf(waitTDHSEntity.getLoanCreditId()), new OnResponse<String, List<LibraryImage>>() {
            @Override
            public void onResponseSuccess(String tag, String rs, List<LibraryImage> extraData) {
                listImage = (ArrayList<LibraryImage>) extraData;
                notifyDataSetChangeAdapter();
            }

            @Override
            public void onResponseError(String tag, String message) {

            }
        });

    }

    private void notifyDataSetChangeAdapter() {
        libraryImageAdapter.setListImage(listImage);
        libraryImageAdapter.notifyDataSetChanged();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
