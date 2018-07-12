package vn.tima.timainspection.Presenter.Contract;

/**
 * Created by tima on 11/27/17.
 */

public interface IContractPresenter {
    void getContractList(String fromDate, String toDate, String customerName, int idContract, int status, int TypeId);
    void insertProductAppraiser(int LoanCreditId, int ProductId, int Type, int Status, int IsWord);
    void getContractListChoTD(String fromDate, String toDate, String customerName, int idContract, int status, int TypeId);
}
