package vn.tima.timainspection.Model.ContractDetail;

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
import vn.tima.timainspection.Model.Entity.ContractDetailEntity;
import vn.tima.timainspection.Model.Entity.ContractEntity;
import vn.tima.timainspection.Model.Entity.CoordinatorFortransferEntity;
import vn.tima.timainspection.Model.Entity.InsertBacklog;
import vn.tima.timainspection.Model.Entity.InsertErrorDepartment;
import vn.tima.timainspection.Model.Login.UserInfo;
import vn.tima.timainspection.Util.Constant;
import vn.tima.timainspection.Util.JSonUtils;
import vn.tima.timainspection.listener.OnResponse;
import vn.tima.timainspection.request.ApiRequest;

/**
 * Created by tima on 11/27/17.
 */

public class ContractDetailRequest extends ApiRequest {

    public static ContractDetailRequest mInstance;
    private static UserInfo userInfo;
    private static Context s_Context;

    public static ContractDetailRequest getInstance(Context context) {
        if (mInstance == null) {
            initApi();
            s_Context = context;
            mInstance = new ContractDetailRequest();
            userInfo = UserInfo.getInstance();
        }
        return mInstance;
    }

    public void getContractDetail(final OnResponse<String, ContractDetailEntity> mResponse, int customerId, int loanCreditId) {
        mResponse.onStart();
        String token = userInfo.getUser(s_Context).getToken();
        Call<ResponseBody> call = m_Service.getContractDetail(token, customerId, loanCreditId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        String message = JSonUtils.getMessage(jsonObject);
                        if (JSonUtils.checkSuccess(jsonObject)) {
                            try {
                                JSONObject jsonObj = jsonObject.getJSONObject(Constant.TAG_DATA);
                                final GsonBuilder gsonBuilder = new GsonBuilder();
                                final Gson gson = gsonBuilder.create();
                                ContractDetailEntity contractDetailEntity = gson.fromJson(jsonObj.toString(), ContractDetailEntity.class);
                                mResponse.onResponseSuccess(TAG, message, contractDetailEntity);
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

    public void traLai(final OnResponse<String, Boolean> mResponse,
                           int loanCreditId, int IsComplete) {
        mResponse.onStart();
        String token = userInfo.getUser(s_Context).getToken();
        Call<ResponseBody> call = m_Service.fieldSurveyReturnLoanCredit(token, loanCreditId, IsComplete);
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

    public void insertBacklog(final OnResponse<String, String> mResponse, InsertBacklog obj){
        mResponse.onStart();

        Call<ResponseBody> call = m_Service.insertBacklog(obj);
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

//    public void getListThamDinh(final OnResponse<String, List<CoordinatorFortransferEntity>> mResponse,
//                                           int loanId) {
//        mResponse.onStart();
//        String token = userInfo.getUser(s_Context).getToken();
//        Call<ResponseBody> call = m_Service.getListCoordinatorFortransfer(token, loanId);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    if (response.code() == 200) {
//                        JSONObject jsonObject = new JSONObject(response.body().string());
//                        String message = JSonUtils.getMessage(jsonObject);
//                        if (JSonUtils.checkSuccess(jsonObject)) {
//                            try {
//                                JSONArray jsonArray = jsonObject.getJSONArray(Constant.TAG_DATA);
//                                Type type = new TypeToken<List<CoordinatorFortransferEntity>>() {}.getType();
//                                final GsonBuilder gsonBuilder = new GsonBuilder();
//                                final Gson gson = gsonBuilder.create();
//                                List<CoordinatorFortransferEntity> listData = gson.fromJson(jsonArray.toString(), type);
//                                mResponse.onResponseSuccess(TAG, message, listData);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                mResponse.onResponseSuccess(TAG, message, null);
//                            }
//                        } else {
//                            mResponse.onResponseSuccess(TAG, message, null);
//                        }
//                    } else {
//                        mResponse.onResponseError(TAG, "Mã lỗi : " + response.code());
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                mResponse.onFinish();
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                mResponse.onResponseError(TAG, t.getMessage());
//                mResponse.onFinish();
//            }
//        });
//    }

}
