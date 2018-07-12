package vn.tima.timainspection.Presenter.CoordinatorFortransfer;

/**
 * Created by tima on 12/1/17.
 */

public interface ICoordinatorFortransferPresenter {
    void getListThamDinh(int LoanCreditID);
    void chuyenThamDinh(int LoanCreditID, int CoordinatorId, String CoordinatorName, int IsComplete);
}
