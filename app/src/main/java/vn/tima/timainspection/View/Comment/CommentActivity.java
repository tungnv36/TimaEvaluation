package vn.tima.timainspection.View.Comment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.hoang8f.android.segmented.SegmentedGroup;
import vn.tima.timainspection.Model.Login.UserInfo;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.GPSTracker;
import vn.tima.timainspection.Util.SharePreferencesHelper;
import vn.tima.timainspection.View.Login.LoginActivity;

/**
 * Created by tima on 11/30/17.
 */

public class CommentActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.rbNha)
    RadioButton rbNha;
    @BindView(R.id.rbCongTy)
    RadioButton rbCongTy;
    @BindView(R.id.segment)
    SegmentedGroup segment;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private GPSTracker gps;
    private SlidePagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Comment");

        rbNha.setOnCheckedChangeListener(this);
        rbCongTy.setOnCheckedChangeListener(this);

//        if(getIntent().getIntExtra("IsThamDinhNha", 0) == 0 || getIntent().getIntExtra("IsThamDinhCongTy", 0) == 0) {
            segment.setVisibility(View.GONE);
//        }

        mPagerAdapter = new SlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rbNha.setChecked(true);
                        break;
                    default:
                        rbCongTy.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();



        gps = new GPSTracker(this);
        if(!gps.canGetLocation()){
            gps.showSettingsAlert();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(b) {
            switch (compoundButton.getId()) {
                case R.id.rbNha:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.rbCongTy:
                    viewPager.setCurrentItem(1);
                    break;
            }
        }
    }

    public class SlidePagerAdapter extends FragmentPagerAdapter {
        public SlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
//            if(getIntent().getIntExtra("IsThamDinhNha", 0) == 1 && getIntent().getIntExtra("IsThamDinhCongTy", 0) == 1) {
//                if (position == 0)
//                    return new CommentHouseFragment(getIntent().getIntExtra("LoanID", 0));
//                else
//                    return new CommentCompanyFragment(getIntent().getIntExtra("LoanID", 0));
//            }
//            if(getIntent().getIntExtra("IsThamDinhNha", 0) == 1) {
//                return new CommentHouseFragment(getIntent().getIntExtra("LoanID", 0));
//            }
//            if(getIntent().getIntExtra("IsThamDinhCongTy", 0) == 1) {
//                return new CommentCompanyFragment(getIntent().getIntExtra("LoanID", 0));
//            }
            if(getIntent().getIntExtra("isThamDinh", 0) == 0 &&
                    getIntent().getIntExtra("IsThamDinhNha", 0) == 1) {
                return new CommentHouseFragment(getIntent().getIntExtra("LoanID", 0),
                        getIntent().getStringExtra("address"),
                        getIntent().getIntExtra("IsThamDinhNha", 0),
                        getIntent().getStringExtra("nameWard"));
            }
            if(getIntent().getIntExtra("isThamDinh", 0) == 1 &&
                    getIntent().getIntExtra("IsThamDinhCongTy", 0) == 1) {
                return new CommentCompanyFragment(getIntent().getIntExtra("LoanID", 0),
                        getIntent().getStringExtra("address"),
                        getIntent().getIntExtra("IsThamDinhCongTy", 0),
                        getIntent().getStringExtra("nameWard"));
            }
            return null;
        }

        @Override
        public int getCount() {
//            if(getIntent().getIntExtra("IsThamDinhNha", 0) == 1 && getIntent().getIntExtra("IsThamDinhCongTy", 0) == 1) {
//                return 2;
//            } else {
                return 1;
//            }
        }
    }

}
