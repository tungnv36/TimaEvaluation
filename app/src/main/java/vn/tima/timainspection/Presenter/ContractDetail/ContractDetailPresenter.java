package vn.tima.timainspection.Presenter.ContractDetail;

import android.content.Context;

import java.util.List;

import vn.tima.timainspection.Model.ContractDetail.ContractDetailRequest;
import vn.tima.timainspection.Model.CoordinatorFortransfer.CoordinatorFortransferRequest;
import vn.tima.timainspection.Model.Entity.ContractDetailEntity;
import vn.tima.timainspection.Model.Entity.CoordinatorFortransferEntity;
import vn.tima.timainspection.View.ContractDetail.IContractDetailActivity;
import vn.tima.timainspection.View.ContractDetail.IContractDetailFragment;
import vn.tima.timainspection.listener.OnResponse;

/**
 * Created by tima on 11/29/17.
 */

public class ContractDetailPresenter implements IContractDetailPresenter {

    private IContractDetailActivity iContractDetailActivity;
    private IContractDetailFragment iContractDetailFragment;
    private ContractDetailRequest contractDetailRequest;
    private CoordinatorFortransferRequest coordinatorFortransferRequest;

    public ContractDetailPresenter(Context context, IContractDetailFragment iContractDetailFragment) {
        this.iContractDetailFragment = iContractDetailFragment;
        contractDetailRequest = ContractDetailRequest.getInstance(context);
        coordinatorFortransferRequest = CoordinatorFortransferRequest.getInstance(context);
    }

    public ContractDetailPresenter(Context context, IContractDetailActivity iContractDetailActivity) {
        this.iContractDetailActivity = iContractDetailActivity;
        contractDetailRequest = ContractDetailRequest.getInstance(context);
        coordinatorFortransferRequest = CoordinatorFortransferRequest.getInstance(context);
    }

    @Override
    public void getContractDetail(int customerId, int loanCreditId) {
        contractDetailRequest.getContractDetail(new OnResponse<String, ContractDetailEntity>() {
            @Override
            public void onResponseSuccess(String tag, String rs, ContractDetailEntity extraData) {
                if(extraData == null) {
                    iContractDetailFragment.getContractDetailFail(rs);
                } else {
                    iContractDetailFragment.getContractDetail(extraData);
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                iContractDetailFragment.getContractDetailFail(message);
            }
        }, customerId, loanCreditId);
    }

    @Override
    public void traLaiHopDong(int LoanCreditId, int IsComplete) {
        contractDetailRequest.traLai(new OnResponse<String, Boolean>() {
            @Override
            public void onResponseSuccess(String tag, String rs, Boolean extraData) {
                if(extraData) {
                    iContractDetailActivity.returnSuccess(rs);
                } else {
                    iContractDetailActivity.returnFail(rs);
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                iContractDetailActivity.returnFail(message);
            }
        }, LoanCreditId, IsComplete);
    }

}
