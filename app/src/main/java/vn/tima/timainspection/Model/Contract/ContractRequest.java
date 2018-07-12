package vn.tima.timainspection.Model.Contract;

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
import vn.tima.timainspection.Model.Entity.ContractEntity;
import vn.tima.timainspection.Model.Entity.LibraryImage;
import vn.tima.timainspection.Model.Entity.WaitTDHSEntity;
import vn.tima.timainspection.Model.Login.UserInfo;
import vn.tima.timainspection.Util.Constant;
import vn.tima.timainspection.Util.JSonUtils;
import vn.tima.timainspection.listener.OnResponse;
import vn.tima.timainspection.request.ApiRequest;

/**
 * Created by tima on 11/27/17.
 */

public class ContractRequest extends ApiRequest {

    public static ContractRequest mInstance;
    private static UserInfo userInfo;
    private static Context s_Context;

    public static ContractRequest getInstance(Context context) {
        if (mInstance == null) {
            initApi();
            s_Context = context;
            mInstance = new ContractRequest();
            userInfo = UserInfo.getInstance();
        }
        return mInstance;
    }

    public void getKSNBManagerContractList(final OnResponse<String, List<ContractEntity>> mResponse,
                                           String customer, String fromDate, String toDate, int pageSize,
                                           int status, int pageCurrent, int loanId, int TypeId) {
//        Log.d("getKSNBManagerContractList", "customer" + customer + ", fromDate" + fromDate + ", toDate" + toDate + ", pageSize" + pageSize + ", status" + status + ", pageCurrent" + pageCurrent + ", loanId" + loanId + ", TypeId" + TypeId);
        mResponse.onStart();
        String token = userInfo.getUser(s_Context).getToken();
        int CoordinatorId = Integer.parseInt(userInfo.getUser(s_Context).getID());
        Call<ResponseBody> call = m_Service.getListCreditCoordinator(token, customer, fromDate, toDate, pageSize, status, pageCurrent, loanId, CoordinatorId, TypeId);
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
                                Type type = new TypeToken<List<ContractEntity>>() {}.getType();
                                final GsonBuilder gsonBuilder = new GsonBuilder();
                                final Gson gson = gsonBuilder.create();
                                List<ContractEntity> listData = gson.fromJson(jsonArray.toString(), type);
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
                    mResponse.onResponseError(TAG, "Mã lỗi : " + response.code());
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

    public void getListCustomerCreditForCoordinatorAwait(final OnResponse<String, List<WaitTDHSEntity>> mResponse,
                                                         String customer, String fromDate, String toDate, int pageSize,
                                                         int status, int pageCurrent, int loanId, int TypeId) {
        Log.d("ABC", "customer" + customer + ", fromDate" + fromDate + ", toDate" + toDate + ", pageSize" + pageSize + ", status" + status + ", pageCurrent" + pageCurrent + ", loanId" + loanId + ", TypeId" + TypeId);
        mResponse.onStart();
        String token = userInfo.getUser(s_Context).getToken();
        Call<ResponseBody> call = m_Service.GetListCustomerCreditForCoordinatorAwait(token, customer, fromDate, toDate, pageSize, status, pageCurrent, loanId, TypeId);
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
                                Type type = new TypeToken<List<WaitTDHSEntity>>() {}.getType();
                                final GsonBuilder gsonBuilder = new GsonBuilder();
                                final Gson gson = gsonBuilder.create();
                                List<WaitTDHSEntity> listData = gson.fromJson(jsonArray.toString(), type);
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
                    mResponse.onResponseError(TAG, "Mã lỗi : " + response.code());
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

    public void deleteImage(String loanCreditId,String imgId, final OnResponse<String, String> mResponse) {
        mResponse.onStart();
        String token = userInfo.getUser(s_Context).getToken();
        String userId = userInfo.getUser(s_Context).getID();
//        String loanCreditId = String.valueOf(MecashApplication.get().getContract().getLoanCreditId());
        Call<ResponseBody> call = m_Service.deleteImage(token, userId, loanCreditId, imgId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, "onResponse deleteImage: " + response.message());

                    if (response.code() == 200) {
                        String body = response.body().string();
                        Log.d(TAG, "onResponse deleteImage: " + body);
                        JSONObject jsonObject = new JSONObject(body);
                        String message = JSonUtils.getMessage(jsonObject);
                        mResponse.onResponseSuccess(TAG, message, jsonObject.toString());
                    } else {
                        mResponse.onResponseError(TAG, response.code() + "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mResponse.onResponseError(TAG, t.toString());
            }
        });
    }

    public void getListImage(String typeId, String loanCreditId, final OnResponse<String, List<LibraryImage>> mResponse) {
        mResponse.onStart();
        String token = userInfo.getUser(s_Context).getToken();
        String userId = userInfo.getUser(s_Context).getID();
        Call<ResponseBody> call = m_Service.getListImage(token, typeId, loanCreditId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String body = response.body().string();
                    Log.e(TAG, "onResponse getListImage typeId: " + body);
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(body);
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.TAG_DATA);

                        Gson gson = new Gson();
                        Type type = new TypeToken<List<LibraryImage>>() {
                        }.getType();
                        List<LibraryImage> listData = gson.fromJson(jsonArray.toString(), type);

                        mResponse.onResponseSuccess(TAG, "", listData);
                    } else {
                        mResponse.onResponseError(TAG, response.code() + "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mResponse.onResponseError(TAG, t.toString());
            }
        });
    }

    public void insertProductAppraiser(final OnResponse<String, Boolean> mResponse,
                               int LoanCreditId, int ProductId, int Type, int Status, int IsWord) {
        mResponse.onStart();
        String token = userInfo.getUser(s_Context).getToken();
        Call<ResponseBody> call = m_Service.insertProductAppraiser(LoanCreditId, ProductId, Type, Status, token, IsWord);//, IsWord
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

}
