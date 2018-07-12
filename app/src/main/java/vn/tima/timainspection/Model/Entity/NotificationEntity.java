package vn.tima.timainspection.Model.Entity;

import com.google.gson.annotations.SerializedName;

public class NotificationEntity {
    @SerializedName("token")
    private String token;
    @SerializedName("deviceId")
    private int deviceId;
    @SerializedName("TokenDevice")
    private String TokenDevice;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getTokenDevice() {
        return TokenDevice;
    }

    public void setTokenDevice(String tokenDevice) {
        TokenDevice = tokenDevice;
    }
}
