package vn.tima.timainspection.Model.ReportBacklog;

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
import vn.tima.timainspection.Model.Entity.ContractEntity;
import vn.tima.timainspection.Model.Entity.ReportBacklogEntity;
import vn.tima.timainspection.Model.Login.UserInfo;
import vn.tima.timainspection.Util.Constant;
import vn.tima.timainspection.Util.JSonUtils;
import vn.tima.timainspection.listener.OnResponse;
import vn.tima.timainspection.request.ApiRequest;

/**
 * Created by tima on 3/14/18.
 */

public class ReportBacklogRequest extends ApiRequest {

    public static ReportBacklogRequest mInstance;
    private static UserInfo userInfo;
    private static Context s_Context;

    public static ReportBacklogRequest getInstance(Context context) {
        if (mInstance == null) {
            initApi();
            s_Context = context;
            mInstance = new ReportBacklogRequest();
            userInfo = UserInfo.getInstance();
        }
        return mInstance;
    }

    public void getReportBacklogList(final OnResponse<String, List<ReportBacklogEntity>> mResponse,
                                     String fromDate, String toDate) {
        mResponse.onStart();
        String token = userInfo.getUser(s_Context).getToken();
        Call<ResponseBody> call = m_Service.getReportInventoryField(token, fromDate, toDate);
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
                                Type type = new TypeToken<List<ReportBacklogEntity>>() {}.getType();
                                final GsonBuilder gsonBuilder = new GsonBuilder();
                                final Gson gson = gsonBuilder.create();
                                List<ReportBacklogEntity> listData = gson.fromJson(jsonArray.toString(), type);
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

}
