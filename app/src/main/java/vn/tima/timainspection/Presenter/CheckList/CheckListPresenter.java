package vn.tima.timainspection.Presenter.CheckList;

import android.content.Context;

import java.util.List;

import vn.tima.timainspection.Model.CheckList.CheckListRequest;
import vn.tima.timainspection.Model.Contract.ContractRequest;
import vn.tima.timainspection.Model.Entity.CheckList;
import vn.tima.timainspection.View.Checklist.ICheckListActivity;
import vn.tima.timainspection.listener.OnResponse;

/**
 * Created by anhnh-dev on 28/11/2017.
 */

public class CheckListPresenter implements ICheckListPresenter{
    private ICheckListActivity iCheckListActivity;
    private CheckListRequest checkListRequest;

    public CheckListPresenter(Context context, ICheckListActivity iCheckListActivity) {
        this.iCheckListActivity = iCheckListActivity;
        this.checkListRequest = CheckListRequest.getInstance(context);
    }

    @Override
    public void getCheckList(int LoanID) {
        checkListRequest.getChecklist(new OnResponse<String, List<CheckList>>() {
            @Override
            public void onResponseSuccess(String tag, String rs, List<CheckList> extraData) {
                iCheckListActivity.getCheckList(extraData);
            }

            @Override
            public void onResponseError(String tag, String message) {
                iCheckListActivity.getCheckListListFail(message);
            }
        }, LoanID);
    }

    @Override
    public void insertCoordinatorCheckList( int LoanCreditId, String ContentCheckList) {
        checkListRequest.insertCoordinatorCheckList(new OnResponse<String, String>() {
            @Override
            public void onResponseSuccess(String tag, String rs, String extraData) {
                iCheckListActivity.insertCoordinatorCheckList(extraData);
            }

            @Override
            public void onResponseError(String tag, String message) {
                iCheckListActivity.insertCoordinatorCheckList(message);
            }
        },  LoanCreditId,ContentCheckList);
    }
}
