package vn.tima.timainspection.Presenter.ContractDetail;

/**
 * Created by tima on 11/29/17.
 */

public interface IContractDetailPresenter {
    void getContractDetail(int customerId, int loanCreditId);
    void traLaiHopDong(int LoanCreditId, int IsComplete);
}
