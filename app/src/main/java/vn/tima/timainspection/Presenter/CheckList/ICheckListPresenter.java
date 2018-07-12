package vn.tima.timainspection.Presenter.CheckList;

/**
 * Created by anhnh-dev on 28/11/2017.
 */

public interface ICheckListPresenter {
    void getCheckList(int LoanID);
    void insertCoordinatorCheckList(int LoanCreditId, String ContentCheckList);
}
