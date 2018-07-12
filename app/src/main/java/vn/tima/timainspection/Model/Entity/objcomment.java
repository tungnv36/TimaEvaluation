package vn.tima.timainspection.Model.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tima on 12/15/17.
 */

public class objcomment {
    @SerializedName("token")
    private String token;
    @SerializedName("loanCreditId")
    private int loanCreditId;
    @SerializedName("comment")
    private String comment;
    @SerializedName("sDevice")
    private String sDevice;
    @SerializedName("sLat")
    private String sLat;
    @SerializedName("sLong")
    private String sLong;
    @SerializedName("sAddress")
    private String sAddress;
    @SerializedName("sCity")
    private String sCity;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getLoanCreditId() {
        return loanCreditId;
    }

    public void setLoanCreditId(int loanCreditId) {
        this.loanCreditId = loanCreditId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getsDevice() {
        return sDevice;
    }

    public void setsDevice(String sDevice) {
        this.sDevice = sDevice;
    }

    public String getsLat() {
        return sLat;
    }

    public void setsLat(String sLat) {
        this.sLat = sLat;
    }

    public String getsLong() {
        return sLong;
    }

    public void setsLong(String sLong) {
        this.sLong = sLong;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getsCity() {
        return sCity;
    }

    public void setsCity(String sCity) {
        this.sCity = sCity;
    }
}
