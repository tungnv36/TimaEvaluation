package vn.tima.timainspection.Model.Entity;

/**
 * Created by My PC on 1/9/2017.
 */

public class PostAppraisal {
    String LoanCreditId;
    String ProductId;
    String ProductReviewId;
    String IsCheck;// input: true or false
    boolean IsCancel;

    public boolean isCancel() {
        return IsCancel;
    }

    public void setCancel(boolean cancel) {
        IsCancel = cancel;
    }

    public String getLoanCreditId() {
        return LoanCreditId;
    }

    public void setLoanCreditId(String loanCreditId) {
        LoanCreditId = loanCreditId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductReviewId() {
        return ProductReviewId;
    }

    public void setProductReviewId(String productReviewId) {
        ProductReviewId = productReviewId;
    }

    public String getIsCheck() {
        return IsCheck;
    }

    public void setIsCheck(String isCheck) {
        IsCheck = isCheck;
    }
}
