package vn.tima.timainspection.Presenter.Comment;

/**
 * Created by tima on 11/30/17.
 */

public interface ICommentPresenter {
    void addComment(int loanCreditId, String comment, String sDevice, String sLat, String sLong, String sAddress, String sCity);
    void putLacation(String sDevice, String sLat, String sLong, String sAddress, String sCity, int LoanCreditId);
    void getComment(int loanCreditId);
}
