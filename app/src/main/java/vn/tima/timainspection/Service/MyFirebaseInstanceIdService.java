package vn.tima.timainspection.Service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import vn.tima.timainspection.Presenter.PushNotification.PushNotificationPresenter;
import vn.tima.timainspection.Util.Common;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    private PushNotificationPresenter pushNotificationPresenter;

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token= FirebaseInstanceId.getInstance().getToken();
        pushNotificationPresenter = new PushNotificationPresenter(this.getApplicationContext());
        pushNotificationPresenter.pushNotification(Common.getDeviceName(), token);
    }

    private void luuTokenVaoCSDLRieng(String token) {

    }
}
