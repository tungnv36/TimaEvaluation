package vn.tima.timainspection.Model.Entity;

/**
 * Created by My PC on 1/5/2017.
 */

public class Question {
    String Id;
    String Name;
    String ParentId;
    String Status;
    String OrderNo;
    String IdType;
    String ProductReviewId;
    String MoneyDiscount;
    String State;
    String IsCancel;
    String IsCheck;
    boolean isCancelLoan = false;


    public boolean isCancelLoan() {
        return isCancelLoan;
    }

    public void setIsCancelLoan() {
        if (IsCheck != null) {
            if (IsCheck.equals(State)) {
                isCancelLoan = false;
            } else {
                if (IsCancel.equalsIgnoreCase("true")) {
                    isCancelLoan = true;
                }
            }
        }
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String parentId) {
        ParentId = parentId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public String getIdType() {
        return IdType;
    }

    public void setIdType(String idType) {
        IdType = idType;
    }

    public String getProductReviewId() {
        return ProductReviewId;
    }

    public void setProductReviewId(String productReviewId) {
        ProductReviewId = productReviewId;
    }

    public String getMoneyDiscount() {
        return MoneyDiscount;
    }

    public void setMoneyDiscount(String moneyDiscount) {
        MoneyDiscount = moneyDiscount;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getIsCancel() {
        return IsCancel;
    }

    public void setIsCancel(String isCancel) {
        IsCancel = isCancel;
    }

    public String getIsCheck() {
        return IsCheck;
    }

    public void setIsCheck(String isCheck) {
        IsCheck = isCheck;
    }
}
