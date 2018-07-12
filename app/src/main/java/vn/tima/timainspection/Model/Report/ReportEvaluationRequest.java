package vn.tima.timainspection.Model.Report;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.tima.timainspection.Model.Entity.RBacklogEntity;
import vn.tima.timainspection.Model.Entity.ReportByWeekEntity;
import vn.tima.timainspection.Model.Entity.ReportEvaluationEntity;
import vn.tima.timainspection.Model.Entity.ReportTDHS2AndDaiLyEntity;
import vn.tima.timainspection.Model.Login.UserInfo;
import vn.tima.timainspection.Util.Constant;
import vn.tima.timainspection.Util.JSonUtils;
import vn.tima.timainspection.listener.OnResponse;
import vn.tima.timainspection.request.ApiRequest;

/**
 * Created by tima on 12/20/17.
 */

public class ReportEvaluationRequest extends ApiRequest {
    public static ReportEvaluationRequest mInstance;
    private static UserInfo userInfo;
    private static Context s_Context;

    public static ReportEvaluationRequest getInstance(Context context) {
        if (mInstance == null) {
            initApi();
            s_Context = context;
            mInstance = new ReportEvaluationRequest();
            userInfo = UserInfo.getInstance();
        }
        return mInstance;
    }

    public void getReportEvaluationHomeAndCompany(final OnResponse<String, ArrayList<ReportEvaluationEntity>> mResponse, String fromDate, String toDate) {
        mResponse.onStart();
        String token = userInfo.getUser(s_Context).getToken();
        Call<ResponseBody> call = m_Service.getReportEvaluationHomeAndCompany(token, fromDate, toDate);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        String message = JSonUtils.getMessage(jsonObject);
                        try {
                            if (JSonUtils.checkSuccess(jsonObject)) {
                                JSONArray json = jsonObject.getJSONArray(Constant.TAG_DATA);
                                ArrayList<ReportEvaluationEntity> list_report = new ArrayList<ReportEvaluationEntity>();
                                Gson gson = new Gson();
                                Type type = new TypeToken<List<ReportEvaluationEntity>>() {
                                }.getType();
                                list_report = gson.fromJson(json.toString(), type);
                                mResponse.onResponseSuccess(TAG, message, list_report);
                            } else {
                                mResponse.onResponseSuccess(TAG, message, null);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            mResponse.onResponseSuccess(TAG, message, null);
                        }
                    } else {
                        mResponse.onResponseError(TAG, response.code() + "");
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

    public void getReportEvaluationByWeekend(final OnResponse<String, ArrayList<ReportByWeekEntity>> mResponse, String fromDate, String toDate) {
        mResponse.onStart();
        String token = userInfo.getUser(s_Context).getToken();
        Call<ResponseBody> call = m_Service.getReportEvaluationByWeekend(token, fromDate, toDate);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        String message = JSonUtils.getMessage(jsonObject);
                        try {
                            if (JSonUtils.checkSuccess(jsonObject)) {
                                JSONArray json = jsonObject.getJSONArray(Constant.TAG_DATA);
                                ArrayList<ReportByWeekEntity> list_report = new ArrayList<ReportByWeekEntity>();
                                Gson gson = new Gson();
                                Type type = new TypeToken<List<ReportByWeekEntity>>() {
                                }.getType();
                                list_report = gson.fromJson(json.toString(), type);
                                mResponse.onResponseSuccess(TAG, message, list_report);
                            } else {
                                mResponse.onResponseSuccess(TAG, message, null);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            mResponse.onResponseSuccess(TAG, message, null);
                        }
                    } else {
                        mResponse.onResponseError(TAG, response.code() + "");
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

    public void getReportEvaluationByLoanCredit(final OnResponse<String, ArrayList<ReportTDHS2AndDaiLyEntity>> mResponse, String fromDate, String toDate) {
        mResponse.onStart();
        String token = userInfo.getUser(s_Context).getToken();
        Call<ResponseBody> call = m_Service.getReportEvaluationByLoanCredit(token, fromDate, toDate);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        String message = JSonUtils.getMessage(jsonObject);
                        try {
                            if (JSonUtils.checkSuccess(jsonObject)) {
                                JSONArray json = jsonObject.getJSONArray(Constant.TAG_DATA);
                                ArrayList<ReportTDHS2AndDaiLyEntity> list_report = new ArrayList<ReportTDHS2AndDaiLyEntity>();
                                Gson gson = new Gson();
                                Type type = new TypeToken<List<ReportTDHS2AndDaiLyEntity>>() {
                                }.getType();
                                list_report = gson.fromJson(json.toString(), type);
                                mResponse.onResponseSuccess(TAG, message, list_report);
                            } else {
                                mResponse.onResponseSuccess(TAG, message, null);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            mResponse.onResponseSuccess(TAG, message, null);
                        }
                    } else {
                        mResponse.onResponseError(TAG, response.code() + "");
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

    public void getReportBacklog(final OnResponse<String, RBacklogEntity> mResponse, String fromDate, String toDate) {
        mResponse.onStart();
        String token = userInfo.getUser(s_Context).getToken();
        Call<ResponseBody> call = m_Service.getReportInventory(token, fromDate, toDate);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        String message = JSonUtils.getMessage(jsonObject);
                        try {
                            if (JSonUtils.checkSuccess(jsonObject)) {
                                JSONObject jsonObj = jsonObject.getJSONObject(Constant.TAG_DATA);
                                final GsonBuilder gsonBuilder = new GsonBuilder();
                                final Gson gson = gsonBuilder.create();
                                RBacklogEntity rBacklogEntity = gson.fromJson(jsonObj.toString(), RBacklogEntity.class);
                                mResponse.onResponseSuccess(TAG, message, rBacklogEntity);
                            } else {
                                mResponse.onResponseSuccess(TAG, message, null);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            mResponse.onResponseSuccess(TAG, message, null);
                        }
                    } else {
                        mResponse.onResponseError(TAG, response.code() + "");
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
