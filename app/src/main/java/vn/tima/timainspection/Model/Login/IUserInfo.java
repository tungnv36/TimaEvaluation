package vn.tima.timainspection.Model.Login;

import android.content.Context;

import vn.tima.timainspection.Model.Entity.UserEntity;

/**
 * Created by tima on 11/27/17.
 */

public interface IUserInfo {
    void setUser(Context context, UserEntity user);
    UserEntity getUser(Context context);
}
