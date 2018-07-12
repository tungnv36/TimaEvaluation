package vn.tima.timainspection.Model.Comment;

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
import vn.tima.timainspection.Model.Entity.CommentEntity;
import vn.tima.timainspection.Model.Entity.objcomment;
import vn.tima.timainspection.Model.Login.UserInfo;
import vn.tima.timainspection.Util.Constant;
import vn.tima.timainspection.Util.JSonUtils;
import vn.tima.timainspection.listener.OnResponse;
import vn.tima.timainspection.request.ApiRequest;

/**
 * Created by tima on 11/27/17.
 */

public class CommentRequest extends ApiRequest {

    public static CommentRequest mInstance;
    private static UserInfo userInfo;
    private static Context s_Context;

    public static CommentRequest getInstance(Context context) {
        if (mInstance == null) {
            initApi();
            s_Context = context;
            mInstance = new CommentRequest();
            userInfo = UserInfo.getInstance();
        }
        return mInstance;
    }

    public void getListComment(final OnResponse<String, List<CommentEntity>> mResponse,
                                           int loanCreditId) {
        mResponse.onStart();
        String token = userInfo.getUser(s_Context).getToken();
        Call<ResponseBody> call = m_Service.getListComment(token, loanCreditId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        String message = JSonUtils.getMessage(jsonObject);
                        if (JSonUtils.checkSuccess(jsonObject)) {
                            try {
                                JSONArray jsonArray = jsonObject.getJSONArray(Constant.TAG_DATA);
                                Type type = new TypeToken<List<CommentEntity>>() {}.getType();
                                final GsonBuilder gsonBuilder = new GsonBuilder();
                                final Gson gson = gsonBuilder.create();
                                List<CommentEntity> listData = gson.fromJson(jsonArray.toString(), type);
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

    public void addComment(final OnResponse<String, Boolean> mResponse,
                           int loanCreditId, String comment, String sDevice, String sLat, String sLong, String sAddress, String sCity) {
        mResponse.onStart();
        String token = userInfo.getUser(s_Context).getToken();
        objcomment objcomment = new objcomment();
        objcomment.setToken(token);
        objcomment.setLoanCreditId(loanCreditId);
        objcomment.setComment(comment);
        objcomment.setsDevice(sDevice);
        objcomment.setsLat(sLat);
        objcomment.setsLong(sLong);
        objcomment.setsAddress(sAddress);
        objcomment.setsCity(sCity);
        Call<ResponseBody> call = m_Service.addComment(objcomment);
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
