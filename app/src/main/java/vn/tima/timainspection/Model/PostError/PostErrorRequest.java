package vn.tima.timainspection.Model.PostError;

import android.content.Context;
import android.util.Log;

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
import vn.tima.timainspection.Model.Entity.CheckList;
import vn.tima.timainspection.Model.Entity.InsertErrorDepartment;
import vn.tima.timainspection.Model.Entity.Reason;
import vn.tima.timainspection.Model.Login.UserInfo;
import vn.tima.timainspection.Util.Constant;
import vn.tima.timainspection.Util.JSonUtils;
import vn.tima.timainspection.listener.OnResponse;
import vn.tima.timainspection.request.ApiRequest;

/**
 * Created by anhnh-dev on 30/11/2017.
 */

public class PostErrorRequest extends ApiRequest {
    public static PostErrorRequest mInstance;
    private static UserInfo userInfo;
    private static Context s_Context;

    public static PostErrorRequest getInstance(Context context) {
        if (mInstance == null) {
            initApi();
            s_Context = context;
            mInstance = new PostErrorRequest();
            userInfo = UserInfo.getInstance();
        }
        return mInstance;
    }

    public void getListReason(final OnResponse<String, List<Reason>> mResponse){
        mResponse.onStart();
        String token = userInfo.getUser(s_Context).getToken();
        Call<ResponseBody> call = m_Service.getListReason(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String data = response.body().string();
                    Log.d(TAG, "onResponse: "+data);
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(data);
                        String message = JSonUtils.getMessage(jsonObject);
                        if (JSonUtils.checkSuccess(jsonObject)) {
                            try {
                                Gson gson = new Gson();
                                JSONArray jsonArray = jsonObject.getJSONArray(Constant.TAG_DATA);
                                Type type = new TypeToken<List<Reason>>() {}.getType();
                                final GsonBuilder gsonBuilder = new GsonBuilder();
                                gson = gsonBuilder.create();
                                List<Reason> listData = gson.fromJson(jsonArray.toString(), type);
                                mResponse.onResponseSuccess(TAG, message, listData);
                            } catch (Exception e) {
                                e.printStackTrace();
                                mResponse.onResponseSuccess(TAG, message, null);
                            }
                        } else {
                            mResponse.onResponseSuccess(TAG, message, null);
                        }
                    } else {
                        mResponse.onResponseError(TAG, "Mã lỗi : " + response.code());
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

    public void insertErrorDepartment(final OnResponse<String, String> mResponse, InsertErrorDepartment obj){
        mResponse.onStart();

        Call<ResponseBody> call = m_Service.insertErrorDepartment(obj);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String data = response.body().string();
                    Log.d(TAG, "onResponse: "+data);
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(data);
                        String message = JSonUtils.getMessage(jsonObject);
                        if (JSonUtils.checkSuccess(jsonObject)) {
                            try {
                                mResponse.onResponseSuccess(TAG, message, message);
                            } catch (Exception e) {
                                e.printStackTrace();
                                mResponse.onResponseSuccess(TAG, message, "Đẩy lỗi");
                            }
                        } else {
                            mResponse.onResponseSuccess(TAG, message, null);
                        }
                    } else {
                        mResponse.onResponseError(TAG, "Mã lỗi : " + response.code());
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
