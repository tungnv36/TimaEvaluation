package vn.tima.timainspection.Model.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tima on 3/14/18.
 */

public class ReportBacklogEntity {
    @Expose
    @SerializedName("LoanCreditOpenningID")
    private int loancreditopenningid;
    @Expose
    @SerializedName("TotalMoney")
    private int totalmoney;
    @Expose
    @SerializedName("CustomerCreditName")
    private String customercreditname;
    @Expose
    @SerializedName("DistrictName")
    private String districtname;
    @Expose
    @SerializedName("DistrictId")
    private int districtid;
    @Expose
    @SerializedName("CityName")
    private String cityname;
    @Expose
    @SerializedName("CityId")
    private int cityid;
    @Expose
    @SerializedName("LoanTime")
    private int loantime;
    @Expose
    @SerializedName("Type")
    private int type;
    @Expose
    @SerializedName("ProductID")
    private int productid;
    @Expose
    @SerializedName("OpenningDay")
    private String openningday;
    @Expose
    @SerializedName("FullName")
    private String fullname;
    @Expose
    @SerializedName("UserID")
    private int userid;
    @Expose
    @SerializedName("LoanCreditID")
    private int loancreditid;

    public int getLoancreditopenningid() {
        return loancreditopenningid;
    }

    public void setLoancreditopenningid(int loancreditopenningid) {
        this.loancreditopenningid = loancreditopenningid;
    }

    public int getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(int totalmoney) {
        this.totalmoney = totalmoney;
    }

    public String getCustomercreditname() {
        return customercreditname;
    }

    public void setCustomercreditname(String customercreditname) {
        this.customercreditname = customercreditname;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    public int getDistrictid() {
        return districtid;
    }

    public void setDistrictid(int districtid) {
        this.districtid = districtid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public int getCityid() {
        return cityid;
    }

    public void setCityid(int cityid) {
        this.cityid = cityid;
    }

    public int getLoantime() {
        return loantime;
    }

    public void setLoantime(int loantime) {
        this.loantime = loantime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getOpenningday() {
        return openningday;
    }

    public void setOpenningday(String openningday) {
        this.openningday = openningday;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getLoancreditid() {
        return loancreditid;
    }

    public void setLoancreditid(int loancreditid) {
        this.loancreditid = loancreditid;
    }

//    @Expose
//    @SerializedName("LoanCreditOpenningID")
//    private int loancreditopenningid;
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
//
//    public int getLoancreditopenningid() {
//        return loancreditopenningid;
//    }
//
//    public void setLoancreditopenningid(int loancreditopenningid) {
//        this.loancreditopenningid = loancreditopenningid;
//    }
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
////    @Expose
////    @SerializedName("LoanCreditOpenningID")
////    private int loancreditopenningid;
////    @Expose
////    @SerializedName("TotalMoney")
////    private int totalmoney;
////    @Expose
////    @SerializedName("LoanTime")
////    private int loantime;
////    @Expose
////    @SerializedName("ProductName")
////    private String productname;
////    @Expose
////    @SerializedName("StatusReally")
////    private int statusreally;
////    @Expose
////    @SerializedName("CounselorId")
////    private int counselorid;
////    @Expose
////    @SerializedName("OpenningDay")
////    private String openningday;
////    @Expose
////    @SerializedName("OpenningNote")
////    private String openningnote;
////    @Expose
////    @SerializedName("OpenningType")
////    private int openningtype;
////    @Expose
////    @SerializedName("GroupUserID")
////    private int groupuserid;
////    @Expose
////    @SerializedName("FullName")
////    private String fullname;
////    @Expose
////    @SerializedName("UserID")
////    private int userid;
////    @Expose
////    @SerializedName("DistrictName")
////    private String districtname;
////    @Expose
////    @SerializedName("DistrictID")
////    private int districtid;
////    @Expose
////    @SerializedName("LoanCreditID")
////    private int loancreditid;
////
////    public int getLoancreditopenningid() {
////        return loancreditopenningid;
////    }
////
////    public void setLoancreditopenningid(int loancreditopenningid) {
////        this.loancreditopenningid = loancreditopenningid;
////    }
////
////    public int getTotalmoney() {
////        return totalmoney;
////    }
////
////    public void setTotalmoney(int totalmoney) {
////        this.totalmoney = totalmoney;
////    }
////
////    public int getLoantime() {
////        return loantime;
////    }
////
////    public void setLoantime(int loantime) {
////        this.loantime = loantime;
////    }
////
////    public String getProductname() {
////        return productname;
////    }
////
////    public void setProductname(String productname) {
////        this.productname = productname;
////    }
////
////    public int getStatusreally() {
////        return statusreally;
////    }
////
////    public void setStatusreally(int statusreally) {
////        this.statusreally = statusreally;
////    }
////
////    public int getCounselorid() {
////        return counselorid;
////    }
////
////    public void setCounselorid(int counselorid) {
////        this.counselorid = counselorid;
////    }
////
////    public String getOpenningday() {
////        return openningday;
////    }
////
////    public void setOpenningday(String openningday) {
////        this.openningday = openningday;
////    }
////
////    public String getOpenningnote() {
////        return openningnote;
////    }
////
////    public void setOpenningnote(String openningnote) {
////        this.openningnote = openningnote;
////    }
////
////    public int getOpenningtype() {
////        return openningtype;
////    }
////
////    public void setOpenningtype(int openningtype) {
////        this.openningtype = openningtype;
////    }
////
////    public int getGroupuserid() {
////        return groupuserid;
////    }
////
////    public void setGroupuserid(int groupuserid) {
////        this.groupuserid = groupuserid;
////    }
////
////    public String getFullname() {
////        return fullname;
////    }
////
////    public void setFullname(String fullname) {
////        this.fullname = fullname;
////    }
////
////    public int getUserid() {
////        return userid;
////    }
////
////    public void setUserid(int userid) {
////        this.userid = userid;
////    }
////
////    public String getDistrictname() {
////        return districtname;
////    }
////
////    public void setDistrictname(String districtname) {
////        this.districtname = districtname;
////    }
////
////    public int getDistrictid() {
////        return districtid;
////    }
////
////    public void setDistrictid(int districtid) {
////        this.districtid = districtid;
////    }
////
////    public int getLoancreditid() {
////        return loancreditid;
////    }
////
////    public void setLoancreditid(int loancreditid) {
////        this.loancreditid = loancreditid;
////    }

}
