package vn.tima.timainspection.View.Splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.tima.timainspection.Model.Login.UserInfo;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Constant;
import vn.tima.timainspection.View.Login.LoginActivity;
import vn.tima.timainspection.View.Main.MainActivity;

public class SplashActivity extends AppCompatActivity {
    private Handler splash_hander;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initKeepTask();
    }

    private void initKeepTask() {
        try {
            userInfo = UserInfo.getInstance();
            if (userInfo.getUser(this).getID() != null) {
                Intent intentToMain = new Intent(SplashActivity.this, MainActivity.class);
                intentToMain.putExtra("status", 1);
                startActivity(intentToMain);
                finish();
            }else{
                initSplash();
            }

        } catch (Exception e) {
            e.printStackTrace();
            initSplash();

        }
    }

    private void initSplash() {
        splash_hander = new Handler();
        splash_hander.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intentToLoginScreen = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intentToLoginScreen);
                finish();
            }
        }, Constant.SPLASH_SCREEN_DISPLAY_TIME);


    }
}
