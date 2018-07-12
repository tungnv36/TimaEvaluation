package vn.tima.timainspection.Model.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tima on 3/22/18.
 */

public class RBacklogEntity {
    @Expose
    @SerializedName("TongHoSoNhan")
    private int tonghosonhan;
    @Expose
    @SerializedName("TongHoSoTon")
    private int tonghosoton;
    @Expose
    @SerializedName("TongHoSoXuLy")
    private int tonghosoxuly;

    public int getTonghosonhan() {
        return tonghosonhan;
    }

    public void setTonghosonhan(int tonghosonhan) {
        this.tonghosonhan = tonghosonhan;
    }

    public int getTonghosoton() {
        return tonghosoton;
    }

    public void setTonghosoton(int tonghosoton) {
        this.tonghosoton = tonghosoton;
    }

    public int getTonghosoxuly() {
        return tonghosoxuly;
    }

    public void setTonghosoxuly(int tonghosoxuly) {
        this.tonghosoxuly = tonghosoxuly;
    }

//    @Expose
//    @SerializedName("TotalMoney")
//    private int totalmoney;
//    @Expose
//    @SerializedName("CustomerCreditName")
//    private String customercreditname;
//    @Expose
//    @SerializedName("DistrictName")
//    private String districtname;
//    @Expose
//    @SerializedName("DistrictId")
//    private int districtid;
//    @Expose
//    @SerializedName("CityName")
//    private String cityname;
//    @Expose
//    @SerializedName("CityId")
//    private int cityid;
//    @Expose
//    @SerializedName("LoanTime")
//    private int loantime;
//    @Expose
//    @SerializedName("IsCongTy")
//    private boolean iscongty;
//    @Expose
//    @SerializedName("IsNha")
//    private boolean isnha;
//    @Expose
//    @SerializedName("ProductID")
//    private int productid;
//    @Expose
//    @SerializedName("OpenningDay")
//    private String openningday;
//    @Expose
//    @SerializedName("FullName")
//    private String fullname;
//    @Expose
//    @SerializedName("UserID")
//    private int userid;
//    @Expose
//    @SerializedName("LoanCreditID")
//    private int loancreditid;
//    @Expose
//    @SerializedName("LoanCreditOpenningID")
//    private int loancreditopenningid;
//
//    public int getTotalmoney() {
//        return totalmoney;
//    }
//
//    public void setTotalmoney(int totalmoney) {
//        this.totalmoney = totalmoney;
//    }
//
//    public String getCustomercreditname() {
//        return customercreditname;
//    }
//
//    public void setCustomercreditname(String customercreditname) {
//        this.customercreditname = customercreditname;
//    }
//
//    public String getDistrictname() {
//        return districtname;
//    }
//
//    public void setDistrictname(String districtname) {
//        this.districtname = districtname;
//    }
//
//    public int getDistrictid() {
//        return districtid;
//    }
//
//    public void setDistrictid(int districtid) {
//        this.districtid = districtid;
//    }
//
//    public String getCityname() {
//        return cityname;
//    }
//
//    public void setCityname(String cityname) {
//        this.cityname = cityname;
//    }
//
//    public int getCityid() {
//        return cityid;
//    }
//
//    public void setCityid(int cityid) {
//        this.cityid = cityid;
//    }
//
//    public int getLoantime() {
//        return loantime;
//    }
//
//    public void setLoantime(int loantime) {
//        this.loantime = loantime;
//    }
//
//    public boolean getIscongty() {
//        return iscongty;
//    }
//
//    public void setIscongty(boolean iscongty) {
//        this.iscongty = iscongty;
//    }
//
//    public boolean getIsnha() {
//        return isnha;
//    }
//
//    public void setIsnha(boolean isnha) {
//        this.isnha = isnha;
//    }
//
//    public int getProductid() {
//        return productid;
//    }
//
//    public void setProductid(int productid) {
//        this.productid = productid;
//    }
//
//    public String getOpenningday() {
//        return openningday;
//    }
//
//    public void setOpenningday(String openningday) {
//        this.openningday = openningday;
//    }
//
//    public String getFullname() {
//        return fullname;
//    }
//
//    public void setFullname(String fullname) {
//        this.fullname = fullname;
//    }
//
//    public int getUserid() {
//        return userid;
//    }
//
//    public void setUserid(int userid) {
//        this.userid = userid;
//    }
//
//    public int getLoancreditid() {
//        return loancreditid;
//    }
//
//    public void setLoancreditid(int loancreditid) {
//        this.loancreditid = loancreditid;
//    }
//
//    public int getLoancreditopenningid() {
//        return loancreditopenningid;
//    }
//
//    public void setLoancreditopenningid(int loancreditopenningid) {
//        this.loancreditopenningid = loancreditopenningid;
//    }
}
