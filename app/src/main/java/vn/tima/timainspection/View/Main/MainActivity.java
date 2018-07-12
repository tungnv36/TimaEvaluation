package vn.tima.timainspection.View.Main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.tima.timainspection.Model.Login.UserInfo;
import vn.tima.timainspection.Presenter.PushNotification.PushNotificationPresenter;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Service.GPSService;
import vn.tima.timainspection.Util.Common;
import vn.tima.timainspection.Util.DialogUtils;
import vn.tima.timainspection.Util.GPSTracker;
import vn.tima.timainspection.View.Contract.ContractFragment;
import vn.tima.timainspection.View.Login.LoginActivity;
import vn.tima.timainspection.View.Notification.NotificationFragment;
import vn.tima.timainspection.View.Report.ReportBacklogFragment;
import vn.tima.timainspection.View.Statistical.StatisticalFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    private DatabaseReference mData;
    private UserInfo userInfo;

    private PushNotificationPresenter pushNotificationPresenter;

    private static int READ_STORAGE_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        try {
//
//            boolean a = checkPermissionForReadExtertalStorage();
//            requestPermissionForReadExtertalStorage();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Intent intent = new Intent(MainActivity.this, GPSService.class);
        startService(intent);

        mData = FirebaseDatabase.getInstance().getReference();
        userInfo = UserInfo.getInstance();
        mData.child(userInfo.getUser(this).getUserName()).child("State").setValue("Online");

//        getSupportActionBar().hide();
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.toolbar);

//        toolbarTitle.setText("Danh sách hợp đồng");
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = ContractFragment.newInstance(0);
                                break;
                            case R.id.action_item2:
                                selectedFragment = StatisticalFragment.newInstance();
                                break;
                            case R.id.action_item3:
                                selectedFragment = ContractFragment.newInstance(1);
                                break;
                            case R.id.action_item4:
                                selectedFragment = ReportBacklogFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, ContractFragment.newInstance(0));
        transaction.commit();

        FirebaseMessaging.getInstance().subscribeToTopic("thamdinh");
        String token= FirebaseInstanceId.getInstance().getToken();
        pushNotificationPresenter = new PushNotificationPresenter(this.getApplicationContext());
        pushNotificationPresenter.pushNotification(Common.getDeviceName(), token);
    }

    public boolean checkPermissionForReadExtertalStorage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    public void requestPermissionForReadExtertalStorage() throws Exception {
        try {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    READ_STORAGE_PERMISSION_REQUEST_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DialogUtils.showExitDialog(MainActivity.this, "Bạn có muốn đăng xuất không?", new DialogUtils.OnClickListener() {
            @Override
            public void onClickSuccess() {
//                SharedPreferences preferences = getSharedPreferences(Common.PREFS_NAME, Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.clear();
//                editor.commit();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                MainActivity.this.finish();
                mData = FirebaseDatabase.getInstance().getReference();
                userInfo = UserInfo.getInstance();
                mData.child(userInfo.getUser(MainActivity.this).getUserName()).child("State").setValue("Offline");
            }
        });
        return super.onOptionsItemSelected(item);
    }
}
