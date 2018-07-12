package vn.tima.timainspection.Model.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tima on 12/21/17.
 */

public class ReportTDHS2AndDaiLyEntity {
    @SerializedName("NumberHomeEvaluation")
    public int NumberHomeEvaluation;
    @SerializedName("NumberCompanyEvaluation")
    public int NumberCompanyEvaluation;
    @SerializedName("NumberMotobikeEvaluation")
    public int NumberMotobikeEvaluation;
    @SerializedName("ProductName")
    public String ProductName;
    @SerializedName("CreateDate")
    public String CreateDate;
    @SerializedName("CreateDateInfo")
    public String CreateDateInfo;
    @SerializedName("FieldEvaluationConfirm")
    public int FieldEvaluationConfirm;
    @SerializedName("AgencyConfirm")
    public int AgencyConfirm;
    @SerializedName("NumberMotobikeEvaluationHub")
    public int NumberMotobikeEvaluationHub;
    @SerializedName("NumberHomeEvaluationHub")
    public int NumberHomeEvaluationHub;
    @SerializedName("NumberCompanyEvaluationHub")
    public int NumberCompanyEvaluationHub;
    @SerializedName("ProductId")
    public int ProductId;

    public int getNumberHomeEvaluation() {
        return NumberHomeEvaluation;
    }

    public void setNumberHomeEvaluation(int numberHomeEvaluation) {
        NumberHomeEvaluation = numberHomeEvaluation;
    }

    public int getNumberCompanyEvaluation() {
        return NumberCompanyEvaluation;
    }

    public void setNumberCompanyEvaluation(int numberCompanyEvaluation) {
        NumberCompanyEvaluation = numberCompanyEvaluation;
    }

    public int getNumberMotobikeEvaluation() {
        return NumberMotobikeEvaluation;
    }

    public void setNumberMotobikeEvaluation(int numberMotobikeEvaluation) {
        NumberMotobikeEvaluation = numberMotobikeEvaluation;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getCreateDateInfo() {
        return CreateDateInfo;
    }

    public void setCreateDateInfo(String createDateInfo) {
        CreateDateInfo = createDateInfo;
    }

    public int getFieldEvaluationConfirm() {
        return FieldEvaluationConfirm;
    }

    public void setFieldEvaluationConfirm(int fieldEvaluationConfirm) {
        FieldEvaluationConfirm = fieldEvaluationConfirm;
    }

    public int getAgencyConfirm() {
        return AgencyConfirm;
    }

    public void setAgencyConfirm(int agencyConfirm) {
        AgencyConfirm = agencyConfirm;
    }

    public int getNumberHomeEvaluationHub() {
        return NumberHomeEvaluationHub;
    }

    public void setNumberHomeEvaluationHub(int numberHomeEvaluationHub) {
        NumberHomeEvaluationHub = numberHomeEvaluationHub;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getNumberMotobikeEvaluationHub() {
        return NumberMotobikeEvaluationHub;
    }

    public void setNumberMotobikeEvaluationHub(int numberMotobikeEvaluationHub) {
        NumberMotobikeEvaluationHub = numberMotobikeEvaluationHub;
    }

    public int getNumberCompanyEvaluationHub() {
        return NumberCompanyEvaluationHub;
    }

    public void setNumberCompanyEvaluationHub(int numberCompanyEvaluationHub) {
        NumberCompanyEvaluationHub = numberCompanyEvaluationHub;
    }
}
