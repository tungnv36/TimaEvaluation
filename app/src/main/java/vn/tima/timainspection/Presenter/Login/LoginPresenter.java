package vn.tima.timainspection.Presenter.Login;

import android.content.Context;

import vn.tima.timainspection.Model.Entity.UserEntity;
import vn.tima.timainspection.Model.Login.UserInfo;
import vn.tima.timainspection.Util.Common;
import vn.tima.timainspection.View.Login.ILoginActivity;
import vn.tima.timainspection.listener.OnResponse;
import vn.tima.timainspection.Model.Login.UserRequest;

/**
 * Created by tima on 11/27/17.
 */

public class LoginPresenter implements ILoginPresenter {

    private ILoginActivity iLoginActivity;
    private UserRequest userRequest;
    private UserInfo userInfo;

    public LoginPresenter(ILoginActivity iLoginActivity) {
        this.iLoginActivity = iLoginActivity;
        userRequest = UserRequest.getInstance();
        userInfo = UserInfo.getInstance();
    }

    @Override
    public void checkLogin(final Context context, String userName, String passWord) {
        if(userName.isEmpty()) {
            iLoginActivity.loginFailed("Bạn chưa nhập tài khoản");
            return;
        }
        if(userName.isEmpty()) {
            iLoginActivity.loginFailed("Bạn chưa nhập mật khẩu");
            return;
        }
        userRequest.checkLogin(new OnResponse<String, UserEntity>() {
            @Override
            public void onResponseSuccess(String tag, String rs, UserEntity extraData) {
                if(extraData == null) {
                    iLoginActivity.loginFailed(rs);
                } else {
                    userInfo.setUser(context, extraData);
                    iLoginActivity.loginSuccess();
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                iLoginActivity.loginFailed(message);
            }
        }, userName, Common.getMD5Code(passWord));
    }

}
