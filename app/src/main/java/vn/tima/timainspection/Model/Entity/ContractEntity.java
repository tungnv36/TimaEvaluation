package vn.tima.timainspection.Model.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by HoangAnh on 5/24/2017.
 */

public class ContractEntity implements Serializable {
    @Expose
    @SerializedName("STT")
    private int stt;
    @Expose
    @SerializedName("TypeOfOwnershipId")
    private int typeofownershipid;
    @Expose
    @SerializedName("IsOutProvince")
    private int isoutprovince;
    @Expose
    @SerializedName("StatusCrawlTelco")
    private boolean statuscrawltelco;
    @Expose
    @SerializedName("StatusCrawlFacebook")
    private boolean statuscrawlfacebook;
    @Expose
    @SerializedName("TypeTimerId")
    private int typetimerid;
    @Expose
    @SerializedName("Step")
    private int step;
    @Expose
    @SerializedName("PlatformType")
    private int platformtype;
    @Expose
    @SerializedName("FieldSurveyUserID")
    private int fieldsurveyuserid;
    @Expose
    @SerializedName("CompanyFieldSurveyUserID")
    private int companyfieldsurveyuserid;
    @Expose
    @SerializedName("AddressFieldSurveyStatus")
    private int addressfieldsurveystatus;
    @Expose
    @SerializedName("CompanyFieldSurveyStatus")
    private int companyfieldsurveystatus;
    @Expose
    @SerializedName("TimeDelayValue")
    private String timedelayvalue;
    @Expose
    @SerializedName("TimeDelay")
    private int timedelay;
    @Expose
    @SerializedName("BeginStartTime")
    private String beginstarttime;
    @Expose
    @SerializedName("utm_source")
    private String utmSource;
    @Expose
    @SerializedName("CoordinatorID")
    private int coordinatorid;
    @Expose
    @SerializedName("IsAgencyRefuse")
    private int isagencyrefuse;
    @Expose
    @SerializedName("ReasonId")
    private int reasonid;
    @Expose
    @SerializedName("NumberCount")
    private int numbercount;
    @Expose
    @SerializedName("Decision")
    private boolean decision;
    @Expose
    @SerializedName("NumberPassDataToAi")
    private int numberpassdatatoai;
    @Expose
    @SerializedName("Code")
    private int code;
    @Expose
    @SerializedName("ConfirmationProfile")
    private int confirmationprofile;
    @Expose
    @SerializedName("Frequency")
    private int frequency;
    @Expose
    @SerializedName("IsComplete")
    private int iscomplete;
    @Expose
    @SerializedName("NumberHourDocument")
    private int numberhourdocument;
    @Expose
    @SerializedName("ActionId")
    private int actionid;
    @Expose
    @SerializedName("NextDate")
    private String nextdate;
    @Expose
    @SerializedName("CountCall")
    private int countcall;
    @Expose
    @SerializedName("IsRead")
    private boolean isread;
    @Expose
    @SerializedName("IsThamDinhCongTy")
    private int isthamdinhcongty;
    @Expose
    @SerializedName("IsThamDinhNha")
    private int isthamdinhnha;
    @Expose
    @SerializedName("IsThamDinh")
    private int isthamdinh;
    @Expose
    @SerializedName("CoordinatorName")
    private String coordinatorname;
    @Expose
    @SerializedName("CoordinatorPhone")
    private String coordinatorphone;
    @Expose
    @SerializedName("CounselorID")
    private int counselorid;
    @Expose
    @SerializedName("CounselorName")
    private String counselorname;
    @Expose
    @SerializedName("ShopID")
    private int shopid;
    @Expose
    @SerializedName("CounselorPhone")
    private String counselorphone;
    @Expose
    @SerializedName("ModifyDate")
    private String modifydate;
    @Expose
    @SerializedName("DistrictId")
    private int districtid;
    @Expose
    @SerializedName("SupportLastID")
    private int supportlastid;
    @Expose
    @SerializedName("TypeID")
    private int typeid;
    @Expose
    @SerializedName("TotalCount")
    private int totalcount;
    @Expose
    @SerializedName("StatusTG")
    private int statustg;
    @Expose
    @SerializedName("Status")
    private int status;
    @Expose
    @SerializedName("LoanTime")
    private int loantime;
    @Expose
    @SerializedName("TotalMoney")
    private int totalmoney;
    @Expose
    @SerializedName("CreateDate")
    private String createdate;
    @Expose
    @SerializedName("NameDistrict")
    private String namedistrict;
    @Expose
    @SerializedName("NameCity")
    private String namecity;
    @Expose
    @SerializedName("CityId")
    private int cityid;
    @Expose
    @SerializedName("CardNumber")
    private String cardnumber;
    @Expose
    @SerializedName("Phone")
    private String phone;
    @Expose
    @SerializedName("FullName")
    private String fullname;
    @Expose
    @SerializedName("ContactLoanCredit")
    private int contactloancredit;
    @Expose
    @SerializedName("LoanCreditId")
    private int loancreditid;
    @Expose
    @SerializedName("CustomerId")
    private int customerid;

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public int getTypeofownershipid() {
        return typeofownershipid;
    }

    public void setTypeofownershipid(int typeofownershipid) {
        this.typeofownershipid = typeofownershipid;
    }

    public int getIsoutprovince() {
        return isoutprovince;
    }

    public void setIsoutprovince(int isoutprovince) {
        this.isoutprovince = isoutprovince;
    }

    public boolean getStatuscrawltelco() {
        return statuscrawltelco;
    }

    public void setStatuscrawltelco(boolean statuscrawltelco) {
        this.statuscrawltelco = statuscrawltelco;
    }

    public boolean getStatuscrawlfacebook() {
        return statuscrawlfacebook;
    }

    public void setStatuscrawlfacebook(boolean statuscrawlfacebook) {
        this.statuscrawlfacebook = statuscrawlfacebook;
    }

    public int getTypetimerid() {
        return typetimerid;
    }

    public void setTypetimerid(int typetimerid) {
        this.typetimerid = typetimerid;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getPlatformtype() {
        return platformtype;
    }

    public void setPlatformtype(int platformtype) {
        this.platformtype = platformtype;
    }

    public int getFieldsurveyuserid() {
        return fieldsurveyuserid;
    }

    public void setFieldsurveyuserid(int fieldsurveyuserid) {
        this.fieldsurveyuserid = fieldsurveyuserid;
    }

    public int getCompanyfieldsurveyuserid() {
        return companyfieldsurveyuserid;
    }

    public void setCompanyfieldsurveyuserid(int companyfieldsurveyuserid) {
        this.companyfieldsurveyuserid = companyfieldsurveyuserid;
    }

    public int getAddressfieldsurveystatus() {
        return addressfieldsurveystatus;
    }

    public void setAddressfieldsurveystatus(int addressfieldsurveystatus) {
        this.addressfieldsurveystatus = addressfieldsurveystatus;
    }

    public int getCompanyfieldsurveystatus() {
        return companyfieldsurveystatus;
    }

    public void setCompanyfieldsurveystatus(int companyfieldsurveystatus) {
        this.companyfieldsurveystatus = companyfieldsurveystatus;
    }

    public String getTimedelayvalue() {
        return timedelayvalue;
    }

    public void setTimedelayvalue(String timedelayvalue) {
        this.timedelayvalue = timedelayvalue;
    }

    public int getTimedelay() {
        return timedelay;
    }

    public void setTimedelay(int timedelay) {
        this.timedelay = timedelay;
    }

    public String getBeginstarttime() {
        return beginstarttime;
    }

    public void setBeginstarttime(String beginstarttime) {
        this.beginstarttime = beginstarttime;
    }

    public String getUtmSource() {
        return utmSource;
    }

    public void setUtmSource(String utmSource) {
        this.utmSource = utmSource;
    }

    public int getCoordinatorid() {
        return coordinatorid;
    }

    public void setCoordinatorid(int coordinatorid) {
        this.coordinatorid = coordinatorid;
    }

    public int getIsagencyrefuse() {
        return isagencyrefuse;
    }

    public void setIsagencyrefuse(int isagencyrefuse) {
        this.isagencyrefuse = isagencyrefuse;
    }

    public int getReasonid() {
        return reasonid;
    }

    public void setReasonid(int reasonid) {
        this.reasonid = reasonid;
    }

    public int getNumbercount() {
        return numbercount;
    }

    public void setNumbercount(int numbercount) {
        this.numbercount = numbercount;
    }

    public boolean getDecision() {
        return decision;
    }

    public void setDecision(boolean decision) {
        this.decision = decision;
    }

    public int getNumberpassdatatoai() {
        return numberpassdatatoai;
    }

    public void setNumberpassdatatoai(int numberpassdatatoai) {
        this.numberpassdatatoai = numberpassdatatoai;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getConfirmationprofile() {
        return confirmationprofile;
    }

    public void setConfirmationprofile(int confirmationprofile) {
        this.confirmationprofile = confirmationprofile;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getIscomplete() {
        return iscomplete;
    }

    public void setIscomplete(int iscomplete) {
        this.iscomplete = iscomplete;
    }

    public int getNumberhourdocument() {
        return numberhourdocument;
    }

    public void setNumberhourdocument(int numberhourdocument) {
        this.numberhourdocument = numberhourdocument;
    }

    public int getActionid() {
        return actionid;
    }

    public void setActionid(int actionid) {
        this.actionid = actionid;
    }

    public String getNextdate() {
        return nextdate;
    }

    public void setNextdate(String nextdate) {
        this.nextdate = nextdate;
    }

    public int getCountcall() {
        return countcall;
    }

    public void setCountcall(int countcall) {
        this.countcall = countcall;
    }

    public boolean getIsread() {
        return isread;
    }

    public void setIsread(boolean isread) {
        this.isread = isread;
    }

    public int getIsthamdinhcongty() {
        return isthamdinhcongty;
    }

    public void setIsthamdinhcongty(int isthamdinhcongty) {
        this.isthamdinhcongty = isthamdinhcongty;
    }

    public int getIsthamdinhnha() {
        return isthamdinhnha;
    }

    public void setIsthamdinhnha(int isthamdinhnha) {
        this.isthamdinhnha = isthamdinhnha;
    }

    public int getIsthamdinh() {
        return isthamdinh;
    }

    public void setIsthamdinh(int isthamdinh) {
        this.isthamdinh = isthamdinh;
    }

    public String getCoordinatorname() {
        return coordinatorname;
    }

    public void setCoordinatorname(String coordinatorname) {
        this.coordinatorname = coordinatorname;
    }

    public String getCoordinatorphone() {
        return coordinatorphone;
    }

    public void setCoordinatorphone(String coordinatorphone) {
        this.coordinatorphone = coordinatorphone;
    }

    public int getCounselorid() {
        return counselorid;
    }

    public void setCounselorid(int counselorid) {
        this.counselorid = counselorid;
    }

    public String getCounselorname() {
        return counselorname;
    }

    public void setCounselorname(String counselorname) {
        this.counselorname = counselorname;
    }

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public String getCounselorphone() {
        return counselorphone;
    }

    public void setCounselorphone(String counselorphone) {
        this.counselorphone = counselorphone;
    }

    public String getModifydate() {
        return modifydate;
    }

    public void setModifydate(String modifydate) {
        this.modifydate = modifydate;
    }

    public int getDistrictid() {
        return districtid;
    }

    public void setDistrictid(int districtid) {
        this.districtid = districtid;
    }

    public int getSupportlastid() {
        return supportlastid;
    }

    public void setSupportlastid(int supportlastid) {
        this.supportlastid = supportlastid;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public int getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(int totalcount) {
        this.totalcount = totalcount;
    }

    public int getStatustg() {
        return statustg;
    }

    public void setStatustg(int statustg) {
        this.statustg = statustg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLoantime() {
        return loantime;
    }

    public void setLoantime(int loantime) {
        this.loantime = loantime;
    }

    public int getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(int totalmoney) {
        this.totalmoney = totalmoney;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getNamedistrict() {
        return namedistrict;
    }

    public void setNamedistrict(String namedistrict) {
        this.namedistrict = namedistrict;
    }

    public String getNamecity() {
        return namecity;
    }

    public void setNamecity(String namecity) {
        this.namecity = namecity;
    }

    public int getCityid() {
        return cityid;
    }

    public void setCityid(int cityid) {
        this.cityid = cityid;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getContactloancredit() {
        return contactloancredit;
    }

    public void setContactloancredit(int contactloancredit) {
        this.contactloancredit = contactloancredit;
    }

    public int getLoancreditid() {
        return loancreditid;
    }

    public void setLoancreditid(int loancreditid) {
        this.loancreditid = loancreditid;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

//    @Expose
//    @SerializedName("TypeOfOwnershipId")
//    private int typeofownershipid;
//    @Expose
//    @SerializedName("IsOutProvince")
//    private int isoutprovince;
//    @Expose
//    @SerializedName("StatusCrawlTelco")
//    private boolean statuscrawltelco;
//    @Expose
//    @SerializedName("StatusCrawlFacebook")
//    private boolean statuscrawlfacebook;
//    @Expose
//    @SerializedName("TypeTimerId")
//    private int typetimerid;
//    @Expose
//    @SerializedName("Step")
//    private int step;
//    @Expose
//    @SerializedName("PlatformType")
//    private int platformtype;
//    @Expose
//    @SerializedName("FieldSurveyUserID")
//    private int fieldsurveyuserid;
//    @Expose
//    @SerializedName("CompanyFieldSurveyUserID")
//    private int companyfieldsurveyuserid;
//    @Expose
//    @SerializedName("AddressFieldSurveyStatus")
//    private int addressfieldsurveystatus;
//    @Expose
//    @SerializedName("CompanyFieldSurveyStatus")
//    private int companyfieldsurveystatus;
//    @Expose
//    @SerializedName("TimeDelayValue")
//    private String timedelayvalue;
//    @Expose
//    @SerializedName("TimeDelay")
//    private int timedelay;
//    @Expose
//    @SerializedName("BeginStartTime")
//    private String beginstarttime;
//    @Expose
//    @SerializedName("CoordinatorID")
//    private int coordinatorid;
//    @Expose
//    @SerializedName("IsAgencyRefuse")
//    private int isagencyrefuse;
//    @Expose
//    @SerializedName("ReasonId")
//    private int reasonid;
//    @Expose
//    @SerializedName("NumberCount")
//    private int numbercount;
//    @Expose
//    @SerializedName("Decision")
//    private boolean decision;
//    @Expose
//    @SerializedName("NumberPassDataToAi")
//    private int numberpassdatatoai;
//    @Expose
//    @SerializedName("Code")
//    private int code;
//    @Expose
//    @SerializedName("ConfirmationProfile")
//    private int confirmationprofile;
//    @Expose
//    @SerializedName("Frequency")
//    private int frequency;
//    @Expose
//    @SerializedName("IsComplete")
//    private int iscomplete;
//    @Expose
//    @SerializedName("NumberHourDocument")
//    private int numberhourdocument;
//    @Expose
//    @SerializedName("ActionId")
//    private int actionid;
//    @Expose
//    @SerializedName("NextDate")
//    private String nextdate;
//    @Expose
//    @SerializedName("CountCall")
//    private int countcall;
//    @Expose
//    @SerializedName("IsRead")
//    private boolean isread;
//    @Expose
//    @SerializedName("IsThamDinhCongTy")
//    private int isthamdinhcongty;
//    @Expose
//    @SerializedName("IsThamDinhNha")
//    private int isthamdinhnha;
//    @Expose
//    @SerializedName("IsThamDinh")
//    private int isthamdinh;
//    @Expose
//    @SerializedName("CoordinatorName")
//    private String coordinatorname;
//    @Expose
//    @SerializedName("CoordinatorPhone")
//    private String coordinatorphone;
//    @Expose
//    @SerializedName("CounselorID")
//    private int counselorid;
//    @Expose
//    @SerializedName("CounselorName")
//    private String counselorname;
//    @Expose
//    @SerializedName("ShopID")
//    private int shopid;
//    @Expose
//    @SerializedName("CounselorPhone")
//    private String counselorphone;
//    @Expose
//    @SerializedName("ModifyDate")
//    private String modifydate;
//    @Expose
//    @SerializedName("DistrictId")
//    private int districtid;
//    @Expose
//    @SerializedName("SupportLastID")
//    private int supportlastid;
//    @Expose
//    @SerializedName("TypeID")
//    private int typeid;
//    @Expose
//    @SerializedName("TotalCount")
//    private int totalcount;
//    @Expose
//    @SerializedName("StatusTG")
//    private int statustg;
//    @Expose
//    @SerializedName("Status")
//    private int status;
//    @Expose
//    @SerializedName("LoanTime")
//    private int loantime;
//    @Expose
//    @SerializedName("TotalMoney")
//    private int totalmoney;
//    @Expose
//    @SerializedName("CreateDate")
//    private String createdate;
//    @Expose
//    @SerializedName("NameDistrict")
//    private String namedistrict;
//    @Expose
//    @SerializedName("NameCity")
//    private String namecity;
//    @Expose
//    @SerializedName("CityId")
//    private int cityid;
//    @Expose
//    @SerializedName("CardNumber")
//    private String cardnumber;
//    @Expose
//    @SerializedName("Phone")
//    private String phone;
//    @Expose
//    @SerializedName("FullName")
//    private String fullname;
//    @Expose
//    @SerializedName("ContactLoanCredit")
//    private int contactloancredit;
//    @Expose
//    @SerializedName("LoanCreditId")
//    private int loancreditid;
//    @Expose
//    @SerializedName("CustomerId")
//    private int customerid;
//    @Expose
//    @SerializedName("STT")
//    private int stt;
//
//    public int getTypeofownershipid() {
//        return typeofownershipid;
//    }
//
//    public void setTypeofownershipid(int typeofownershipid) {
//        this.typeofownershipid = typeofownershipid;
//    }
//
//    public int getIsoutprovince() {
//        return isoutprovince;
//    }
//
//    public void setIsoutprovince(int isoutprovince) {
//        this.isoutprovince = isoutprovince;
//    }
//
//    public boolean getStatuscrawltelco() {
//        return statuscrawltelco;
//    }
//
//    public void setStatuscrawltelco(boolean statuscrawltelco) {
//        this.statuscrawltelco = statuscrawltelco;
//    }
//
//    public boolean getStatuscrawlfacebook() {
//        return statuscrawlfacebook;
//    }
//
//    public void setStatuscrawlfacebook(boolean statuscrawlfacebook) {
//        this.statuscrawlfacebook = statuscrawlfacebook;
//    }
//
//    public int getTypetimerid() {
//        return typetimerid;
//    }
//
//    public void setTypetimerid(int typetimerid) {
//        this.typetimerid = typetimerid;
//    }
//
//    public int getStep() {
//        return step;
//    }
//
//    public void setStep(int step) {
//        this.step = step;
//    }
//
//    public int getPlatformtype() {
//        return platformtype;
//    }
//
//    public void setPlatformtype(int platformtype) {
//        this.platformtype = platformtype;
//    }
//
//    public int getFieldsurveyuserid() {
//        return fieldsurveyuserid;
//    }
//
//    public void setFieldsurveyuserid(int fieldsurveyuserid) {
//        this.fieldsurveyuserid = fieldsurveyuserid;
//    }
//
//    public int getCompanyfieldsurveyuserid() {
//        return companyfieldsurveyuserid;
//    }
//
//    public void setCompanyfieldsurveyuserid(int companyfieldsurveyuserid) {
//        this.companyfieldsurveyuserid = companyfieldsurveyuserid;
//    }
//
//    public int getAddressfieldsurveystatus() {
//        return addressfieldsurveystatus;
//    }
//
//    public void setAddressfieldsurveystatus(int addressfieldsurveystatus) {
//        this.addressfieldsurveystatus = addressfieldsurveystatus;
//    }
//
//    public int getCompanyfieldsurveystatus() {
//        return companyfieldsurveystatus;
//    }
//
//    public void setCompanyfieldsurveystatus(int companyfieldsurveystatus) {
//        this.companyfieldsurveystatus = companyfieldsurveystatus;
//    }
//
//    public String getTimedelayvalue() {
//        return timedelayvalue;
//    }
//
//    public void setTimedelayvalue(String timedelayvalue) {
//        this.timedelayvalue = timedelayvalue;
//    }
//
//    public int getTimedelay() {
//        return timedelay;
//    }
//
//    public void setTimedelay(int timedelay) {
//        this.timedelay = timedelay;
//    }
//
//    public String getBeginstarttime() {
//        return beginstarttime;
//    }
//
//    public void setBeginstarttime(String beginstarttime) {
//        this.beginstarttime = beginstarttime;
//    }
//
//    public int getCoordinatorid() {
//        return coordinatorid;
//    }
//
//    public void setCoordinatorid(int coordinatorid) {
//        this.coordinatorid = coordinatorid;
//    }
//
//    public int getIsagencyrefuse() {
//        return isagencyrefuse;
//    }
//
//    public void setIsagencyrefuse(int isagencyrefuse) {
//        this.isagencyrefuse = isagencyrefuse;
//    }
//
//    public int getReasonid() {
//        return reasonid;
//    }
//
//    public void setReasonid(int reasonid) {
//        this.reasonid = reasonid;
//    }
//
//    public int getNumbercount() {
//        return numbercount;
//    }
//
//    public void setNumbercount(int numbercount) {
//        this.numbercount = numbercount;
//    }
//
//    public boolean getDecision() {
//        return decision;
//    }
//
//    public void setDecision(boolean decision) {
//        this.decision = decision;
//    }
//
//    public int getNumberpassdatatoai() {
//        return numberpassdatatoai;
//    }
//
//    public void setNumberpassdatatoai(int numberpassdatatoai) {
//        this.numberpassdatatoai = numberpassdatatoai;
//    }
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public int getConfirmationprofile() {
//        return confirmationprofile;
//    }
//
//    public void setConfirmationprofile(int confirmationprofile) {
//        this.confirmationprofile = confirmationprofile;
//    }
//
//    public int getFrequency() {
//        return frequency;
//    }
//
//    public void setFrequency(int frequency) {
//        this.frequency = frequency;
//    }
//
//    public int getIscomplete() {
//        return iscomplete;
//    }
//
//    public void setIscomplete(int iscomplete) {
//        this.iscomplete = iscomplete;
//    }
//
//    public int getNumberhourdocument() {
//        return numberhourdocument;
//    }
//
//    public void setNumberhourdocument(int numberhourdocument) {
//        this.numberhourdocument = numberhourdocument;
//    }
//
//    public int getActionid() {
//        return actionid;
//    }
//
//    public void setActionid(int actionid) {
//        this.actionid = actionid;
//    }
//
//    public String getNextdate() {
//        return nextdate;
//    }
//
//    public void setNextdate(String nextdate) {
//        this.nextdate = nextdate;
//    }
//
//    public int getCountcall() {
//        return countcall;
//    }
//
//    public void setCountcall(int countcall) {
//        this.countcall = countcall;
//    }
//
//    public boolean getIsread() {
//        return isread;
//    }
//
//    public void setIsread(boolean isread) {
//        this.isread = isread;
//    }
//
//    public int getIsthamdinhcongty() {
//        return isthamdinhcongty;
//    }
//
//    public void setIsthamdinhcongty(int isthamdinhcongty) {
//        this.isthamdinhcongty = isthamdinhcongty;
//    }
//
//    public int getIsthamdinhnha() {
//        return isthamdinhnha;
//    }
//
//    public void setIsthamdinhnha(int isthamdinhnha) {
//        this.isthamdinhnha = isthamdinhnha;
//    }
//
//    public int getIsthamdinh() {
//        return isthamdinh;
//    }
//
//    public void setIsthamdinh(int isthamdinh) {
//        this.isthamdinh = isthamdinh;
//    }
//
//    public String getCoordinatorname() {
//        return coordinatorname;
//    }
//
//    public void setCoordinatorname(String coordinatorname) {
//        this.coordinatorname = coordinatorname;
//    }
//
//    public String getCoordinatorphone() {
//        return coordinatorphone;
//    }
//
//    public void setCoordinatorphone(String coordinatorphone) {
//        this.coordinatorphone = coordinatorphone;
//    }
//
//    public int getCounselorid() {
//        return counselorid;
//    }
//
//    public void setCounselorid(int counselorid) {
//        this.counselorid = counselorid;
//    }
//
//    public String getCounselorname() {
//        return counselorname;
//    }
//
//    public void setCounselorname(String counselorname) {
//        this.counselorname = counselorname;
//    }
//
//    public int getShopid() {
//        return shopid;
//    }
//
//    public void setShopid(int shopid) {
//        this.shopid = shopid;
//    }
//
//    public String getCounselorphone() {
//        return counselorphone;
//    }
//
//    public void setCounselorphone(String counselorphone) {
//        this.counselorphone = counselorphone;
//    }
//
//    public String getModifydate() {
//        return modifydate;
//    }
//
//    public void setModifydate(String modifydate) {
//        this.modifydate = modifydate;
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
//    public int getSupportlastid() {
//        return supportlastid;
//    }
//
//    public void setSupportlastid(int supportlastid) {
//        this.supportlastid = supportlastid;
//    }
//
//    public int getTypeid() {
//        return typeid;
//    }
//
//    public void setTypeid(int typeid) {
//        this.typeid = typeid;
//    }
//
//    public int getTotalcount() {
//        return totalcount;
//    }
//
//    public void setTotalcount(int totalcount) {
//        this.totalcount = totalcount;
//    }
//
//    public int getStatustg() {
//        return statustg;
//    }
//
//    public void setStatustg(int statustg) {
//        this.statustg = statustg;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
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
//    public int getTotalmoney() {
//        return totalmoney;
//    }
//
//    public void setTotalmoney(int totalmoney) {
//        this.totalmoney = totalmoney;
//    }
//
//    public String getCreatedate() {
//        return createdate;
//    }
//
//    public void setCreatedate(String createdate) {
//        this.createdate = createdate;
//    }
//
//    public String getNamedistrict() {
//        return namedistrict;
//    }
//
//    public void setNamedistrict(String namedistrict) {
//        this.namedistrict = namedistrict;
//    }
//
//    public String getNamecity() {
//        return namecity;
//    }
//
//    public void setNamecity(String namecity) {
//        this.namecity = namecity;
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
//    public String getCardnumber() {
//        return cardnumber;
//    }
//
//    public void setCardnumber(String cardnumber) {
//        this.cardnumber = cardnumber;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
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
//    public int getContactloancredit() {
//        return contactloancredit;
//    }
//
//    public void setContactloancredit(int contactloancredit) {
//        this.contactloancredit = contactloancredit;
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
//    public int getCustomerid() {
//        return customerid;
//    }
//
//    public void setCustomerid(int customerid) {
//        this.customerid = customerid;
//    }
//
//    public int getStt() {
//        return stt;
//    }
//
//    public void setStt(int stt) {
//        this.stt = stt;
//    }
}
