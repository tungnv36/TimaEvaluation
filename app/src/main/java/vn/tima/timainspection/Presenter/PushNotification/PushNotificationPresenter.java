package vn.tima.timainspection.Presenter.PushNotification;

import android.content.Context;
import android.util.Log;

import vn.tima.timainspection.Model.PushNotification.PushNotificationRequest;
import vn.tima.timainspection.listener.OnResponse;

public class PushNotificationPresenter implements IPushNotificationPresenter {

    private PushNotificationRequest pushNotificationRequest;

    public PushNotificationPresenter(Context context) {
        pushNotificationRequest = PushNotificationRequest.getInstance(context);
    }

    @Override
    public void pushNotification(String deviceId, String TokenDevice) {
        pushNotificationRequest.pushNotification(new OnResponse<String, Boolean>() {
            @Override
            public void onResponseSuccess(String tag, String rs, Boolean extraData) {
                if(extraData) {
                    Log.i("DATA: ", "OK");
                } else {
                    Log.i("DATA: ", rs);
                }
            }

            @Override
            public void onResponseError(String tag, String message) {

            }
        }, deviceId, TokenDevice);
    }

}
