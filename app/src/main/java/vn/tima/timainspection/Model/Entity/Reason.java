package vn.tima.timainspection.Model.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anhnh-dev on 12/01/2018.
 */

public class Reason {
    @SerializedName("Id")
    int Id;
    @SerializedName("Reason")
    String Reason;
    @SerializedName("Step")
    int Step;

    public Reason(int id, String reason, int step) {
        Id = id;
        Reason = reason;
        Step = step;
    }

    public Reason() {
    }

    public int getStep() {
        return Step;
    }

    public void setStep(int step) {
        Step = step;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }
}
