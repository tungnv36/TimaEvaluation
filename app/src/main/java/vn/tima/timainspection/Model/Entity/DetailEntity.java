package vn.tima.timainspection.Model.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tima on 11/29/17.
 */

public class DetailEntity {
    @SerializedName("Title")
    public String Title;
    @SerializedName("Value")
    public String Value;
    @SerializedName("isTitle")
    public Boolean isTitle;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public void setTitle(Boolean title) {
        isTitle = title;
    }
}
