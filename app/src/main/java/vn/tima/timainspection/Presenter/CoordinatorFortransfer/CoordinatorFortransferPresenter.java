package vn.tima.timainspection.Presenter.CoordinatorFortransfer;

import android.content.Context;

import java.util.List;

import vn.tima.timainspection.Model.CoordinatorFortransfer.CoordinatorFortransferRequest;
import vn.tima.timainspection.Model.Entity.CoordinatorFortransferEntity;
import vn.tima.timainspection.View.CoordinatorFortransfer.ICoordinatorFortransferActivity;
import vn.tima.timainspection.listener.OnResponse;

/**
 * Created by tima on 12/1/17.
 */

public class CoordinatorFortransferPresenter implements ICoordinatorFortransferPresenter {

    private ICoordinatorFortransferActivity iCoordinatorFortransferActivity;
    private CoordinatorFortransferRequest coordinatorFortransferRequest;

    public CoordinatorFortransferPresenter(Context context, ICoordinatorFortransferActivity iCoordinatorFortransferActivity) {
        this.iCoordinatorFortransferActivity = iCoordinatorFortransferActivity;
        coordinatorFortransferRequest = CoordinatorFortransferRequest.getInstance(context);
    }

    @Override
    public void getListThamDinh(int LoanCreditID) {
        coordinatorFortransferRequest.getListThamDinh(new OnResponse<String, List<CoordinatorFortransferEntity>>() {
            @Override
            public void onResponseSuccess(String tag, String rs, List<CoordinatorFortransferEntity> extraData) {
                if(extraData == null) {
                    iCoordinatorFortransferActivity.getListCoordinatorFortransferFail(rs);
                } else {
                    iCoordinatorFortransferActivity.getListCoordinatorFortransfer(extraData);
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                iCoordinatorFortransferActivity.getListCoordinatorFortransferFail(message);
            }
        }, LoanCreditID);
    }

    @Override
    public void chuyenThamDinh(int LoanCreditID, int CoordinatorId, String CoordinatorName, int IsComplete) {
        coordinatorFortransferRequest.chuyenThamDinh(new OnResponse<String, Boolean>() {
            @Override
            public void onResponseSuccess(String tag, String rs, Boolean extraData) {
                if(extraData) {
                    iCoordinatorFortransferActivity.dieuChuyenSuccess(rs);
                } else {
                    iCoordinatorFortransferActivity.dieuChuyenFail(rs);
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                iCoordinatorFortransferActivity.dieuChuyenFail(message);
            }
        }, LoanCreditID, CoordinatorId, CoordinatorName, IsComplete);
    }

}
