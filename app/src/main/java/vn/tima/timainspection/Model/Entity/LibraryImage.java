package vn.tima.timainspection.Model.Entity;

/**
 * Created by hoang do on 11/21/2016.
 */

public class LibraryImage {
    String ID;
    String CustomerCreditId;
    String UserId;
    String UrlImg;
    String srcImgLocal;
    String CreateOn;
    String TypeId;
    String Username;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getTypeId() {
        return TypeId;
    }

    public void setTypeId(String typeId) {
        TypeId = typeId;
    }

    public String getSrcImgLocal() {
        return srcImgLocal;
    }

    public void setSrcImgLocal(String srcImgLocal) {
        this.srcImgLocal = srcImgLocal;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCustomerCreditId() {
        return CustomerCreditId;
    }

    public void setCustomerCreditId(String customerCreditId) {
        CustomerCreditId = customerCreditId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUrlImg() {
        return UrlImg;
    }

    public void setUrlImg(String urlImg) {
        UrlImg = urlImg;
    }

    public String getCreateOn() {
        return CreateOn;
    }

    public void setCreateOn(String createOn) {
        CreateOn = createOn;
    }
}
