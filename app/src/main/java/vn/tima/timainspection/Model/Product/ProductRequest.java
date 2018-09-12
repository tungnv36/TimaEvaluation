package vn.tima.timainspection.Model.Product;

import android.content.Context;
import android.util.Log;

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
import vn.tima.timainspection.Model.Entity.ContractEntity;
import vn.tima.timainspection.Model.Entity.LibraryImage;
import vn.tima.timainspection.Model.Entity.Motobike;
import vn.tima.timainspection.Model.Entity.ObjproductReviewResult;
import vn.tima.timainspection.Model.Entity.Question;
import vn.tima.timainspection.Model.Login.UserInfo;
import vn.tima.timainspection.Util.Constant;
import vn.tima.timainspection.Util.JSonUtils;
import vn.tima.timainspection.listener.OnResponse;
import vn.tima.timainspection.request.ApiRequest;

/**
 * Created by tima on 11/27/17.
 */

public class ProductRequest extends ApiRequest {

    public static ProductRequest mInstance;
    private static UserInfo userInfo;
    private static Context s_Context;

    public static ProductRequest getInstance(Context context) {
        if (mInstance == null) {
            initApi();
            s_Context = context;
            mInstance = new ProductRequest();
            userInfo = UserInfo.getInstance();
        }
        return mInstance;
    }

    public void getAllProduct(final OnResponse<String, ArrayList<Motobike>> mResponse, String token, String loanCreditId) {
        mResponse.onStart();
        Call<ResponseBody> call = m_Service.getAllProduct(token, loanCreditId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        String message = JSonUtils.getMessage(jsonObject);
                        try {
                            if (JSonUtils.checkSuccess(jsonObject)) {
                                JSONArray motobikeJson = jsonObject.getJSONArray(Constant.TAG_DATA);
                                ArrayList<Motobike> list_motobike = new ArrayList<Motobike>();
                                Gson gson = new Gson();
                                Type type = new TypeToken<List<Motobike>>() {
                                }.getType();
                                list_motobike = gson.fromJson(motobikeJson.toString(), type);
                                mResponse.onResponseSuccess(TAG, message, list_motobike);
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

    public void getListQuestion(String idType, String productId, String loanCreditId, final OnResponse<String, List<Question>> mResponse) {
        mResponse.onStart();
        String token = userInfo.getUser(s_Context).getToken();
        String userId = userInfo.getUser(s_Context).getID();
        Call<ResponseBody> call = m_Service.getListQuestion(token, loanCreditId, idType, productId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String body = response.body().string();
                    JSONObject jsonObject = new JSONObject(body);
                    Log.e(TAG, "onResponse getListQuestion idType: " + jsonObject.toString(4));
                    if (response.code() == 200) {
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.TAG_DATA);

                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Question>>() {
                        }.getType();
                        List<Question> listData = gson.fromJson(jsonArray.toString(), type);

                        mResponse.onResponseSuccess(TAG, "", listData);
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
                mResponse.onResponseError(TAG, t.toString());
                mResponse.onFinish();
            }
        });
    }

    public void postAppraisal(String loanCreditId, String productionId, String totalMoney, String strListProductResultReview, final OnResponse<String, String> mResponse) {
        mResponse.onStart();
        String token = userInfo.getUser(s_Context).getToken();
        String shopId = userInfo.getUser(s_Context).getShopID();
        String groupId = userInfo.getUser(s_Context).getGroupId();
        String fullName = userInfo.getUser(s_Context).getFullName();
        String userId = userInfo.getUser(s_Context).getID();

        ObjproductReviewResult obj = new ObjproductReviewResult();
        obj.setLoanCreditId(loanCreditId);
        obj.setProductId(productionId);
        obj.setFullName(fullName);
        obj.setGroupId(groupId);
        obj.setShopId(shopId);
        obj.setStrListProductResultReview(strListProductResultReview);
        obj.setToken(token);
        obj.setTotalMoney(totalMoney);
        obj.setUserId(userId);
        Gson gson = new Gson();
        String jsonElements = gson.toJson(obj);

        Call<ResponseBody> call = m_Service.postAppraisal(jsonElements);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.e(TAG, "postAppraisal: " + response.code() + "/" + response.message());
                    if (response.code() == 200) {
                        mResponse.onResponseSuccess(TAG, response.code() + "", response.body().string());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mResponse.onFinish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mResponse.onResponseError(TAG, t.toString());
                mResponse.onFinish();
            }
        });

    }
}
