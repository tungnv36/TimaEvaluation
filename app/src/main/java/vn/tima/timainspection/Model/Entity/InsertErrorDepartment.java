package vn.tima.timainspection.Model.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anhnh-dev on 12/01/2018.
 */

public class InsertErrorDepartment {
    @SerializedName("token")
    String token;
    @SerializedName("loanCreditId")
    int loanCreditId;
    @SerializedName("GroupUserError")
    int GroupUserError;
    @SerializedName("NoteError")
    String NoteError;
    @SerializedName("ErrorId")
    int ErrorId;
    @SerializedName("ErrorName")
    String ErrorName;
    @SerializedName("Step")
    int Step;

    public InsertErrorDepartment(String token, int loanCreditId, int groupUserError, String noteError, int errorId, String errorName, int step) {
        this.token = token;
        this.loanCreditId = loanCreditId;
        GroupUserError = groupUserError;
        NoteError = noteError;
        ErrorId = errorId;
        ErrorName = errorName;
        Step = step;
    }

    public int getStep() {
        return Step;
    }

    public void setStep(int step) {
        Step = step;
    }

    //    public InsertErrorDepartment() {
//    }

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

    public int getGroupUserError() {
        return GroupUserError;
    }

    public void setGroupUserError(int groupUserError) {
        GroupUserError = groupUserError;
    }

    public String getNoteError() {
        return NoteError;
    }

    public void setNoteError(String noteError) {
        NoteError = noteError;
    }

    public int getErrorId() {
        return ErrorId;
    }

    public void setErrorId(int errorId) {
        ErrorId = errorId;
    }

    public String getErrorName() {
        return ErrorName;
    }

    public void setErrorName(String errorName) {
        ErrorName = errorName;
    }
}
