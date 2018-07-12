package vn.tima.timainspection.Model.Login;

import android.content.Context;
import android.content.SharedPreferences;

import vn.tima.timainspection.Model.Entity.UserEntity;
import vn.tima.timainspection.Util.Common;

/**
 * Created by tima on 11/27/17.
 */

public class UserInfo implements IUserInfo {

    private static UserInfo instance;

    private UserInfo() {

    }

    public static UserInfo getInstance() {
        if (instance == null) {
            instance = new UserInfo();
        }
        return instance;
    }

    @Override
    public void setUser(Context context, UserEntity user) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Common.PREFS_NAME, context.MODE_PRIVATE).edit();
        editor.putString("ID", user.getID());
        editor.putString("UserName", user.getUserName());
        editor.putString("Password", user.getPass());
        editor.putString("FullName", user.getFullName());
        editor.putString("Phone", user.getPhone());
        editor.putString("Email", user.getEmail());
        editor.putString("GroupId", user.getGroupId());
        editor.putString("WorkingTime", user.getWorkingTime());
        editor.putString("ShopID", user.getShopID());
        editor.putInt("CustomerCreditId", user.getCustomerCreditId());
        editor.putInt("AccessPrivate", user.getAccessPrivate());
        editor.putString("AccessIpAddress", user.getAccessIpAddress());
        editor.putString("Token", user.getToken());
        editor.putString("ExpriceToken", user.getExpriceToken());
        editor.putString("PushTokenAndroid", user.getPushTokenAndroid());
        editor.putString("PushTokenIOS", user.getPushTokenIOS());
        editor.putInt("CoordinatorIsEnable", user.getCoordinatorIsEnable());
        editor.putString("IPPhone", user.getIPPhone());
        editor.putBoolean("isHub", user.getHub());
        editor.apply();
    }

    @Override
    public UserEntity getUser(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Common.PREFS_NAME, context.MODE_PRIVATE);
        UserEntity user = new UserEntity();
        user.setID(prefs.getString("ID", null));
        user.setUserName(prefs.getString("UserName", null));
        user.setPass(prefs.getString("Password", null));
        user.setFullName(prefs.getString("FullName", null));
        user.setPhone(prefs.getString("Phone", null));
        user.setEmail(prefs.getString("Email", null));
        user.setGroupId(prefs.getString("GroupId", null));
        user.setWorkingTime(prefs.getString("WorkingTime", null));
        user.setShopID(prefs.getString("ShopID", null));
        user.setCustomerCreditId(prefs.getInt("CustomerCreditId", 0));
        user.setAccessPrivate(prefs.getInt("AccessPrivate", 0));
        user.setAccessIpAddress(prefs.getString("AccessIpAddress", null));
        user.setToken(prefs.getString("Token", null));
        user.setExpriceToken(prefs.getString("ExpriceToken", null));
        user.setPushTokenAndroid(prefs.getString("PushTokenAndroid", null));
        user.setPushTokenIOS(prefs.getString("PushTokenIOS", null));
        user.setCoordinatorIsEnable(prefs.getInt("CoordinatorIsEnable", 0));
        user.setIPPhone(prefs.getString("IPPhone", null));
        user.setHub(prefs.getBoolean("isHub", false));
        return user;
    }

}
