package vn.tima.timainspection.View.Login;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.tima.timainspection.Presenter.Login.LoginPresenter;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Common;
import vn.tima.timainspection.Util.GPSTracker;
import vn.tima.timainspection.Util.SharePreferencesHelper;
import vn.tima.timainspection.View.Main.MainActivity;

/**
 * Created by tima on 11/25/17.
 */

public class LoginActivity extends Activity implements View.OnClickListener, ILoginActivity {

    @BindView(R.id.imgLogo)
    ImageView imgLogo;
    @BindView(R.id.txtUserName)
    EditText txtUserName;
    @BindView(R.id.txtPassword)
    EditText txtPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.chkRemeberlogin)
    CheckBox chkRemeberlogin;
    @BindView(R.id.txtForgotPassword)
    TextView txtForgotPassword;
    private GPSTracker gps;

    private LoginPresenter loginPresenter;
    private static int PERMISSION_REQUEST_CODE = 1;
    private static final String[] INITIAL_PERMS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private static final String TAG = "TAG";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        try {
            getActionBar().hide();
        } catch (Exception ex) {
            ex.getMessage();
        }

        gps = new GPSTracker(this);
        if(!gps.canGetLocation()){
            gps.showSettingsAlert();
        }

        setPermision();

        loginPresenter = new LoginPresenter(this);

        btnLogin.setOnClickListener(this);
        initRemberLogin();

//        txtUserName.setText("tdtd_hieudt");
//        txtPassword.setText("123456");
//        Common.showAlert(this, "dhasd lksadhksa lkasjdlkas dlkasjdlksa dlkas");
    }



    private void setPermision() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==
                            PackageManager.PERMISSION_GRANTED) {
            } else {
                requestPermissions(INITIAL_PERMS,PERMISSION_REQUEST_CODE);
            }
        }
    }

    private void initRemberLogin() {
        try {
            SharePreferencesHelper sharePreferencesHelper = SharePreferencesHelper.getIntance(LoginActivity.this);
            if (sharePreferencesHelper.getSharedPreferences().contains(SharePreferencesHelper.REMEMBER_KEY)) {
                txtUserName.setText(sharePreferencesHelper.getUsername());
                txtPassword.setText(sharePreferencesHelper.getPassword());
                chkRemeberlogin.setChecked(true);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        if(gps.canGetLocation()) {
            loginPresenter.checkLogin(this, txtUserName.getText().toString(), txtPassword.getText().toString());
        } else {
            gps.showSettingsAlert();
        }
    }

    @Override
    public void loginSuccess() {
        if (chkRemeberlogin.isChecked()) {
            SharePreferencesHelper
                    .getIntance(LoginActivity.this)
                    .saveLogin(txtUserName.getText().toString(), txtPassword.getText().toString(), true);

        } else {
            SharePreferencesHelper
                    .getIntance(LoginActivity.this).clearLogin();
        }
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void loginFailed(String msg) {
        Common.showAlert(this, msg);
    }

}