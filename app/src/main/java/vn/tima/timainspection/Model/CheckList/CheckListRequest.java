package vn.tima.timainspection.Model.CheckList;

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
import vn.tima.timainspection.Model.Contract.ContractRequest;
import vn.tima.timainspection.Model.Entity.CheckList;
import vn.tima.timainspection.Model.Entity.ContractEntity;
import vn.tima.timainspection.Model.Login.UserInfo;
import vn.tima.timainspection.Util.Constant;
import vn.tima.timainspection.Util.JSonUtils;
import vn.tima.timainspection.listener.OnResponse;
import vn.tima.timainspection.request.ApiRequest;

/**
 * Created by anhnh-dev on 30/11/2017.
 */

public class CheckListRequest extends ApiRequest {
    public static CheckListRequest mInstance;
    private static UserInfo userInfo;
    private static Context s_Context;

    public static CheckListRequest getInstance(Context context) {
        if (mInstance == null) {
            initApi();
            s_Context = context;
            mInstance = new CheckListRequest();
            userInfo = UserInfo.getInstance();
        }
        return mInstance;
    }

    public void getChecklist(final OnResponse<String, List<CheckList>> mResponse, int LoanID){
        mResponse.onStart();
        String token = userInfo.getUser(s_Context).getToken();
        Call<ResponseBody> call = m_Service.getChecklist(token,LoanID);
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
                                // List<CheckList> listData = (List<CheckList>) gson.fromJson(jsonObject.getJSONArray("Data").toString(),CheckList.class);
                                JSONArray jsonArray = jsonObject.getJSONArray(Constant.TAG_DATA);
                                Type type = new TypeToken<List<CheckList>>() {}.getType();
                                final GsonBuilder gsonBuilder = new GsonBuilder();
                                gson = gsonBuilder.create();
                                List<CheckList> listData = gson.fromJson(jsonArray.toString(), type);
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

    public void insertCoordinatorCheckList(final OnResponse<String, String> mResponse, int LoanCreditId, String ContentCheckList){
        mResponse.onStart();
        String token = userInfo.getUser(s_Context).getToken();
        int userId = Integer.parseInt(userInfo.getUser(s_Context).getID());
        int groupId = Integer.parseInt(userInfo.getUser(s_Context).getGroupId());
        Call<ResponseBody> call = m_Service.insertCoordinatorCheckList(groupId,  userId,  LoanCreditId,  ContentCheckList,token);
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
                                mResponse.onResponseSuccess(TAG, message, "Đẩy checklist lỗi");
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
