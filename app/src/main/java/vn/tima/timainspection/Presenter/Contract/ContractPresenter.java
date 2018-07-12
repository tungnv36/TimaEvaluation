package vn.tima.timainspection.Presenter.Contract;

import android.content.Context;

import java.util.List;

import vn.tima.timainspection.Model.Contract.ContractRequest;
import vn.tima.timainspection.Model.Entity.ContractEntity;
import vn.tima.timainspection.Model.Entity.WaitTDHSEntity;
import vn.tima.timainspection.View.Contract.IContractActivity;
import vn.tima.timainspection.listener.OnResponse;

/**
 * Created by tima on 11/27/17.
 */

public class ContractPresenter implements IContractPresenter {

    private IContractActivity iContractActivity;
    private ContractRequest contractRequest;

    private int PAGE_NUMBER = 1;
    private int PAGE_SIZE = 1000;

    public ContractPresenter(Context context, IContractActivity iContractActivity) {
        this.iContractActivity = iContractActivity;
        contractRequest= ContractRequest.getInstance(context);
    }

    @Override
    public void getContractList(String fromDate, String toDate, String customerName, int idContract, int status, int TypeId) {
        contractRequest.getKSNBManagerContractList(new OnResponse<String, List<ContractEntity>>() {
            @Override
            public void onResponseSuccess(String tag, String rs, List<ContractEntity> extraData) {
                if(extraData == null) {
                    iContractActivity.getContractListFail(rs);
                } else {
                    iContractActivity.getContractList(extraData);
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                iContractActivity.getContractListFail(message);
            }
        }, customerName, fromDate, toDate, PAGE_SIZE, status, PAGE_NUMBER, idContract, TypeId);
    }

    @Override
    public void insertProductAppraiser(int LoanCreditId, int ProductId, int Type, int Status, int IsWord) {
        contractRequest.insertProductAppraiser(new OnResponse<String, Boolean>() {
            @Override
            public void onResponseSuccess(String tag, String rs, Boolean extraData) {
                if(extraData) {
                    iContractActivity.insertProductAppraiserSuccess(rs);
                } else {
                    iContractActivity.insertProductAppraiserFail(rs);
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                iContractActivity.insertProductAppraiserFail(message);
            }
        }, LoanCreditId, ProductId, Type, Status, IsWord);
    }

    @Override
    public void getContractListChoTD(String fromDate, String toDate, String customerName, int idContract, int status, int TypeId) {
        contractRequest.getListCustomerCreditForCoordinatorAwait(new OnResponse<String, List<WaitTDHSEntity>>() {
            @Override
            public void onResponseSuccess(String tag, String rs, List<WaitTDHSEntity> extraData) {
                if(extraData == null) {
                    iContractActivity.getContractListChoTDFail(rs);
                } else {
                    iContractActivity.getContractListChoTD(extraData);
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                iContractActivity.getContractListChoTDFail(message);
            }
        }, customerName, fromDate, toDate, PAGE_SIZE, status, PAGE_NUMBER, idContract, TypeId);
    }
}
