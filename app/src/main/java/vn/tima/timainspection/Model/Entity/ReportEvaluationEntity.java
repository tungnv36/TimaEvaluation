package vn.tima.timainspection.Model.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tima on 12/20/17.
 */

public class ReportEvaluationEntity {
    @SerializedName("NumberHomeEvaluation")
    public int NumberHomeEvaluation;
    @SerializedName("NumberCompanyEvaluation")
    public int NumberCompanyEvaluation;
    @SerializedName("NumberMotobikeEvaluationTima")
    public int NumberMotobikeEvaluationTima;
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
    @SerializedName("NumberHomeEvaluationHub")
    public int NumberHomeEvaluationHub;
    @SerializedName("ProductId")
    public int ProductId;
    @SerializedName("NumberCompanyEvaluationHub")
    public int NumberCompanyEvaluationHub;
    @SerializedName("NumberMotobikeEvaluationHub")
    public int NumberMotobikeEvaluationHub;

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

    public int getNumberMotobikeEvaluationTima() {
        return NumberMotobikeEvaluationTima;
    }

    public void setNumberMotobikeEvaluationTima(int numberMotobikeEvaluationTima) {
        NumberMotobikeEvaluationTima = numberMotobikeEvaluationTima;
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
