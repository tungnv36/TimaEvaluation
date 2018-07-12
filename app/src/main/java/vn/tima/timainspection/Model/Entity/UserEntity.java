package vn.tima.timainspection.Model.Entity;

import java.io.Serializable;

/**
 * Created by DinhAnh on 11/7/2016.
 */

public class UserEntity implements Serializable {
    String ID;
    String UserName;
    String Password;
    String FullName;
    String Phone;
    String Email;
    String GroupId;
    String WorkingTime;
    String ShopID;
    Integer CustomerCreditId;
    Integer AccessPrivate;
    String AccessIpAddress;
    String Token;
    String ExpriceToken;
    String PushTokenAndroid;
    String PushTokenIOS;
    Integer CoordinatorIsEnable;
    String IPPhone;
    Boolean IsHub;

    public String getGroupId() {
        return GroupId;
    }

    public void setGroupId(String groupId) {
        GroupId = groupId;
    }

    public String getShopID() {
        return ShopID;
    }

    public void setShopID(String shopID) {
        ShopID = shopID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPass() {
        return Password;
    }

    public void setPass(String Password) {
        this.Password = Password;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getExpriceToken() {
        return ExpriceToken;
    }

    public void setExpriceToken(String expriceToken) {
        ExpriceToken = expriceToken;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getWorkingTime() {
        return WorkingTime;
    }

    public void setWorkingTime(String workingTime) {
        WorkingTime = workingTime;
    }

    public Integer getCustomerCreditId() {
        return CustomerCreditId;
    }

    public void setCustomerCreditId(Integer customerCreditId) {
        CustomerCreditId = customerCreditId;
    }

    public Integer getAccessPrivate() {
        return AccessPrivate;
    }

    public void setAccessPrivate(Integer accessPrivate) {
        AccessPrivate = accessPrivate;
    }

    public String getAccessIpAddress() {
        return AccessIpAddress;
    }

    public void setAccessIpAddress(String accessIpAddress) {
        AccessIpAddress = accessIpAddress;
    }

    public String getPushTokenAndroid() {
        return PushTokenAndroid;
    }

    public void setPushTokenAndroid(String pushTokenAndroid) {
        PushTokenAndroid = pushTokenAndroid;
    }

    public String getPushTokenIOS() {
        return PushTokenIOS;
    }

    public void setPushTokenIOS(String pushTokenIOS) {
        PushTokenIOS = pushTokenIOS;
    }

    public Integer getCoordinatorIsEnable() {
        return CoordinatorIsEnable;
    }

    public void setCoordinatorIsEnable(Integer coordinatorIsEnable) {
        CoordinatorIsEnable = coordinatorIsEnable;
    }

    public String getIPPhone() {
        return IPPhone;
    }

    public void setIPPhone(String IPPhone) {
        this.IPPhone = IPPhone;
    }

    public Boolean getHub() {
        return IsHub;
    }

    public void setHub(Boolean hub) {
        IsHub = hub;
    }
}
