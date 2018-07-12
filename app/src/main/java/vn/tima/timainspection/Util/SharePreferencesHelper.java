package vn.tima.timainspection.Util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dell on 19/11/2016.
 */
public class SharePreferencesHelper {
    private static SharePreferencesHelper sharePreferencesHelper;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    public static final String SP_NAME = "LOGIN_MECASH";
    public static final String USERNAME_KEY = "user";
    public static final String PASSWORD_KEY = "pass";
    public static final String REMEMBER_KEY = "remember";
    public static final String TOKEN_PUSH_KEY = "token_push";
    public static final String CODE_COMMENT = "code_comment";
    public static final String COUNT_KEY = "count";
    public static final String SAVE_COMMENT = "save_comment";

    public SharePreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static SharePreferencesHelper getIntance(Context context) {
        return sharePreferencesHelper == null ? new SharePreferencesHelper(context) : sharePreferencesHelper;
    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void saveLogin(String user, String pass, Boolean isRemember) {
        try {
            editor.putString(USERNAME_KEY, user);
            editor.putString(PASSWORD_KEY, pass);
            editor.putBoolean(REMEMBER_KEY, isRemember);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveCode(int code) {
        try {
            editor.putInt(CODE_COMMENT, code);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveCount(int count) {
        try {
            editor.putInt(COUNT_KEY, count);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveComment(String key, String comment) {
        try {
            editor.putString(key, comment);
            editor.commit();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void saveTokenPush(String token) {
        try {
            editor.putString(TOKEN_PUSH_KEY, token);
            editor.commit();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String getTokenPush() {
        try {
            return sharedPreferences.getString(USERNAME_KEY, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getUsername() {
        try {
            return sharedPreferences.getString(USERNAME_KEY, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getComment(String key) {
        try {
            return sharedPreferences.getString(key, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getPassword() {
        try {
            return sharedPreferences.getString(PASSWORD_KEY, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public int getCode() {
        try {
            return sharedPreferences.getInt(CODE_COMMENT, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getCount() {
        try {
            return sharedPreferences.getInt(COUNT_KEY, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void clearLogin() {
        try {
            editor.clear();
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
