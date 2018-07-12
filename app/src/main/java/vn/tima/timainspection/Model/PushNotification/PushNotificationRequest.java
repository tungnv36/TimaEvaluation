package vn.tima.timainspection.Model.PushNotification;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.tima.timainspection.Model.Entity.NotificationEntity;
import vn.tima.timainspection.Model.Entity.objcomment;
import vn.tima.timainspection.Model.Login.UserInfo;
import vn.tima.timainspection.Util.JSonUtils;
import vn.tima.timainspection.listener.OnResponse;
import vn.tima.timainspection.request.ApiRequest;

public class PushNotificationRequest extends ApiRequest {

    public static PushNotificationRequest mInstance;
    private static UserInfo userInfo;
    private static Context s_Context;

    public static PushNotificationRequest getInstance(Context context) {
        if (mInstance == null) {
            initApi();
            s_Context = context;
            mInstance = new PushNotificationRequest();
            userInfo = UserInfo.getInstance();
        }
        return mInstance;
    }

    public void pushNotification(final OnResponse<String, Boolean> mResponse,
                           String deviceId, String TokenDevice) {
        mResponse.onStart();
        String token = userInfo.getUser(s_Context).getToken();
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setToken(token);
        notificationEntity.setDeviceId(2);
        notificationEntity.setTokenDevice(TokenDevice);
        Call<ResponseBody> call = m_Service.pushNotificationDevice(notificationEntity);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        String message = JSonUtils.getMessage(jsonObject);
                        if (JSonUtils.checkSuccess(jsonObject)) {
                            try {
                                mResponse.onResponseSuccess(TAG, message, true);
                            } catch (Exception e) {
                                e.printStackTrace();
                                mResponse.onResponseSuccess(TAG, message, false);
                            }
                        } else {
                            mResponse.onResponseSuccess(TAG, message, false);
                        }
                    } else {
                        mResponse.onResponseError(TAG, "Mã lỗi : " + response.code() + " - " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mResponse.onFinish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mResponse.onResponseError(TAG, t.getMessage());
                mResponse.onFinish();
            }
        });
    }

}
