package vn.tima.timainspection.Model.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anhnh-dev on 12/01/2018.
 */

public class InsertBacklog {
    @SerializedName("token")
    String token;
    @SerializedName("Type")
    int Type;
    @SerializedName("LoanCreditId")
    int LoanCreditId;
    @SerializedName("Note")
    String Note;
    @SerializedName("TypeReport")
    int TypeReport;
    @SerializedName("ProductId")
    int ProductId;

    public InsertBacklog(String token, int Type, int LoanCreditId, String Note, int TypeReport, int ProductId) {
        this.token = token;
        this.Type = Type;
        this.LoanCreditId = LoanCreditId;
        this.Note = Note;
        this.TypeReport = TypeReport;
        this.ProductId = ProductId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public int getLoanCreditId() {
        return LoanCreditId;
    }

    public void setLoanCreditId(int loanCreditId) {
        LoanCreditId = loanCreditId;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public int getTypeReport() {
        return TypeReport;
    }

    public void setTypeReport(int typeReport) {
        TypeReport = typeReport;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }
}
