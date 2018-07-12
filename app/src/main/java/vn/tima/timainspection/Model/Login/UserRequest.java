package vn.tima.timainspection.Model.Login;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.tima.timainspection.Model.Entity.UserEntity;
import vn.tima.timainspection.Util.Constant;
import vn.tima.timainspection.Util.JSonUtils;
import vn.tima.timainspection.listener.OnResponse;
import vn.tima.timainspection.request.ApiRequest;

/**
 * Created by My PC on 11/17/2016.
 */

public class UserRequest extends ApiRequest {
    public static UserRequest mInstance;

    public static UserRequest getInstance() {
        if (mInstance == null) {
            initApi();
            mInstance = new UserRequest();
        }
        return mInstance;
    }

    public void checkLogin(final OnResponse<String, UserEntity> m_Response, String username, String password) {
        m_Response.onStart();
        Call<ResponseBody> call = m_Service.checkLogin(username, password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            if (JSonUtils.checkSuccess(jsonObject)) {
                                JSONArray userJson = jsonObject.getJSONArray(Constant.TAG_DATA);
                                final GsonBuilder gsonBuilder = new GsonBuilder();
                                final Gson gson = gsonBuilder.create();
                                UserEntity respone = gson.fromJson(userJson.get(0).toString(), UserEntity.class);
                                m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), respone);
                            } else {
                                m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), null);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), null);
                        }
                    } else {
                        m_Response.onResponseError(TAG, response.code() + "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                m_Response.onFinish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                m_Response.onResponseError(TAG, t.getMessage());
                m_Response.onFinish();
            }
        });
    }
}
