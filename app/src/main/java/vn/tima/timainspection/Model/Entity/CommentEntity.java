package vn.tima.timainspection.Model.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tima on 11/30/17.
 */

public class CommentEntity {
    @SerializedName("CreateDate")
    public String CreateDate;
    @SerializedName("FullName")
    public String FullName;
    @SerializedName("Comment")
    public String Comment;
    @SerializedName("ShopName")
    public String ShopName;
    @SerializedName("Id")
    public int Id;
    @SerializedName("LoanCreditId")
    public int LoanCreditId;
    @SerializedName("IsDisplay")
    public int IsDisplay;
    @SerializedName("ActionId")
    public int ActionId;
    @SerializedName("GroupUserId")
    public int GroupUserId;
    @SerializedName("UserId")
    public int UserId;

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getLoanCreditId() {
        return LoanCreditId;
    }

    public void setLoanCreditId(int loanCreditId) {
        LoanCreditId = loanCreditId;
    }

    public int getIsDisplay() {
        return IsDisplay;
    }

    public void setIsDisplay(int isDisplay) {
        IsDisplay = isDisplay;
    }

    public int getActionId() {
        return ActionId;
    }

    public void setActionId(int actionId) {
        ActionId = actionId;
    }

    public int getGroupUserId() {
        return GroupUserId;
    }

    public void setGroupUserId(int groupUserId) {
        GroupUserId = groupUserId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}
