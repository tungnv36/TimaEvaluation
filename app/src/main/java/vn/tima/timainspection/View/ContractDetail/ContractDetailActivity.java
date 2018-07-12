package vn.tima.timainspection.View.ContractDetail;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.google.firebase.database.DatabaseReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.hoang8f.android.segmented.SegmentedGroup;
import vn.tima.timainspection.Model.Entity.ContractEntity;
import vn.tima.timainspection.Model.Entity.WaitTDHSEntity;
import vn.tima.timainspection.Model.Login.UserInfo;
import vn.tima.timainspection.Presenter.ContractDetail.ContractDetailPresenter;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Common;
import vn.tima.timainspection.Util.DialogUtils;
import vn.tima.timainspection.View.CheckListVehicle.ValuationTabFragment;
import vn.tima.timainspection.View.Checklist.CheckListActivity;
import vn.tima.timainspection.View.Comment.CommentActivity;
import vn.tima.timainspection.View.ContractHistory.ContractHistoryFragment;
import vn.tima.timainspection.View.ContractVouchers.ContractVouchersFragment;
import vn.tima.timainspection.View.CoordinatorFortransfer.CoordinatorFortransferActivity;
import vn.tima.timainspection.View.Dialog.BacklogDialog;
import vn.tima.timainspection.View.Dialog.ErrorDialog;
import vn.tima.timainspection.View.PostContract.PostContractActivity;

/**
 * Created by tima on 11/29/17.
 */

public class ContractDetailActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, IContractDetailActivity {

    @BindView(R.id.rbTongHop)
    RadioButton rbTongHop;
    @BindView(R.id.rbLichSu)
    RadioButton rbLichSu;
    @BindView(R.id.rbChungTu)
    RadioButton rbChungTu;
    @BindView(R.id.rbChecklistVehicle)
    RadioButton rbChecklistVehicle;
    @BindView(R.id.segment)
    SegmentedGroup segment;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    SlidePagerAdapter mPagerAdapter;
    public static int totalMoneyDiscount;
    public static int myPosition = -1;
    public static int isThamDinhNha = 0;
    public static int isThamDinhCongTy = 0;
    public static int type = 0;

    static final int NUM_ITEMS = 4;

    private ContractDetailPresenter contractDetailPresenter;
    ContractEntity contractEntity;
    WaitTDHSEntity waitTDHSEntity;
    int LoanID = 0;
    public static String addressHouse;
    public static String addressCompany;
    public static String nameWard;

    private DatabaseReference mData;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_detail);
        ButterKnife.bind(this);

        rbTongHop.setOnCheckedChangeListener(this);
        rbLichSu.setOnCheckedChangeListener(this);
        rbChungTu.setOnCheckedChangeListener(this);
        rbChecklistVehicle.setOnCheckedChangeListener(this);

        type = getIntent().getIntExtra("type", 0);
        contractDetailPresenter = new ContractDetailPresenter(this, this);
        if (type == 0) {
            contractEntity = (ContractEntity) getIntent().getSerializableExtra("contractEntity");
            if (getIntent().getIntExtra("TypeID", 0) != 2 &&
                    getIntent().getIntExtra("TypeID", 0) != 5) {
                rbChecklistVehicle.setVisibility(View.GONE);
            } else {
                if (contractEntity.getIsthamdinh() == 0) {
                    rbChecklistVehicle.setVisibility(View.GONE);
                }
            }
        } else {
            waitTDHSEntity = (WaitTDHSEntity) getIntent().getSerializableExtra("contractChoTDEntity");
            if (getIntent().getIntExtra("TypeID", 0) != 2 &&
                    getIntent().getIntExtra("TypeID", 0) != 5) {
                rbChecklistVehicle.setVisibility(View.GONE);
            } else {
                if (waitTDHSEntity.getIsThamDinh() == 0) {
                    rbChecklistVehicle.setVisibility(View.GONE);
                }
            }
        }

        mPagerAdapter = new SlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mPagerAdapter);
        if(getIntent().getIntExtra("tabPosition", 0) > 0) {
            viewPager.setCurrentItem(getIntent().getIntExtra("tabPosition", 0));
            rbChecklistVehicle.setChecked(true);
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rbTongHop.setChecked(true);
                        break;
                    case 1:
                        rbLichSu.setChecked(true);
                        break;
                    case 2:
                        if (getIntent().getIntExtra("TypeID", 0) != 2 &&
                                getIntent().getIntExtra("TypeID", 0) != 5) {
                            rbChungTu.setChecked(true);
                        } else {
                            if (type == 0) {
                                if (contractEntity.getIsthamdinh() == 0) {
                                    rbChungTu.setChecked(true);
                                } else {
                                    rbChecklistVehicle.setChecked(true);
                                }
                            } else {
                                if (waitTDHSEntity.getIsThamDinh() == 0) {
                                    rbChungTu.setChecked(true);
                                } else {
                                    rbChecklistVehicle.setChecked(true);
                                }
                            }
                        }
                        break;
                    case 3:
                        rbChungTu.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE, ActionBar.DISPLAY_SHOW_TITLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Random r = new Random();
//        final int i1 = r.nextInt(999 - 100) + 100;
//        mData = FirebaseDatabase.getInstance().getReference();
//        userInfo = UserInfo.getInstance();
//        mData.child(userInfo.getUser(this).getUserName()).child("Code").setValue(i1);
//        mData.child(userInfo.getUser(this).getUserName()).child("Code").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                try {
//                    SharePreferencesHelper.getIntance(ContractDetailActivity.this).saveCode(Integer.parseInt(dataSnapshot.getValue().toString()));
//                } catch (Exception ex) {
//                    SharePreferencesHelper.getIntance(ContractDetailActivity.this).saveCode(i1);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//        mData.child(userInfo.getUser(this).getUserName()).child("Count").setValue(i1);
//        mData.child(userInfo.getUser(this).getUserName()).child("Count").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                try {
//                    SharePreferencesHelper.getIntance(ContractDetailActivity.this).saveCount(Integer.parseInt(dataSnapshot.getValue().toString()));
//                } catch (Exception ex) {
//                    SharePreferencesHelper.getIntance(ContractDetailActivity.this).saveCount(0);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (type == 0) {
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        LoanID = getIntent().getIntExtra("LoanID", 0);
        if (item.getItemId() == R.id.action_menu) {
            if (isThamDinhNha == 0) {
                item.getSubMenu().findItem(R.id.menu_comment_house).setVisible(false);
            } else {
                item.getSubMenu().findItem(R.id.menu_comment_house).setVisible(true);
            }
            if (isThamDinhCongTy == 0) {
                item.getSubMenu().findItem(R.id.menu_comment_company).setVisible(false);
            } else {
                item.getSubMenu().findItem(R.id.menu_comment_company).setVisible(true);
            }
        } else if (item.getItemId() == R.id.menu_comment_house) {
            if (isThamDinhNha == 0) {
                Common.showAlert(this, "Không thể comment hợp đồng này");
            } else {
                Intent intent = new Intent(ContractDetailActivity.this, CommentActivity.class);
                intent.putExtra("address", addressHouse);
                intent.putExtra("isThamDinh", 0);
                intent.putExtra("LoanID", LoanID);
                intent.putExtra("IsThamDinhNha", isThamDinhNha);
                intent.putExtra("IsThamDinhCongTy", isThamDinhCongTy);
                intent.putExtra("nameWard", nameWard);
                startActivity(intent);
            }
            return true;
        } else if (item.getItemId() == R.id.menu_comment_company) {
            if (isThamDinhCongTy == 0) {
                Common.showAlert(this, "Không thể comment hợp đồng này");
            } else {
                Intent intent = new Intent(ContractDetailActivity.this, CommentActivity.class);
                intent.putExtra("address", addressCompany);
                intent.putExtra("isThamDinh", 1);
                intent.putExtra("LoanID", LoanID);
                intent.putExtra("IsThamDinhNha", isThamDinhNha);
                intent.putExtra("IsThamDinhCongTy", isThamDinhCongTy);
                intent.putExtra("nameWard", nameWard);
                startActivity(intent);
            }
            return true;
        } else if (item.getItemId() == R.id.menu_checklist) {
            Intent intent = new Intent(ContractDetailActivity.this, CheckListActivity.class);
            intent.putExtra("LoanID", LoanID);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.menu_upload) {
            ContractEntity contractEntity = (ContractEntity) getIntent().getSerializableExtra("contractEntity");
            Intent intent = new Intent(ContractDetailActivity.this, PostContractActivity.class);
            intent.putExtra("LoanID", LoanID);
            intent.putExtra("contractEntity", contractEntity);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.menu_return) {

            DialogUtils.showExitDialog3(this, "Bạn có chắc chắn muốn trả lại hồ sơ này?", new DialogUtils.OnClickListener2() {
                @Override
                public void onClickSuccess() {

//                    DialogUtils.showExitDialog2(ContractDetailActivity.this, "Hồ sơ này đã hoàn thành chưa?", new DialogUtils.OnClickListener2() {
//                        @Override
//                        public void onClickSuccess() {
//                            contractDetailPresenter.traLaiHopDong(LoanID, 1);// Đã thẩm định
//                        }
//
//                        @Override
//                        public void onClickCancel() {
//                            contractDetailPresenter.traLaiHopDong(LoanID, 0);// Chưa thẩm định
//                        }
//                    }); code cũ yêu cầu
                    contractDetailPresenter.traLaiHopDong(LoanID, 1);// Đã thẩm định

                }

                @Override
                public void onClickCancel() {

                }
            });


        } else if (item.getItemId() == R.id.menu_chuyentd) {
            Intent intent = new Intent(ContractDetailActivity.this, CoordinatorFortransferActivity.class);
            intent.putExtra("LoanID", LoanID);
            startActivity(intent);
        } else if (item.getItemId() == R.id.menu_baoloi) {
            ErrorDialog dialog = new ErrorDialog(this);
            dialog.setLoanId(LoanID);
            dialog.showDialog();
        } else if (item.getItemId() == R.id.menu_ton) {
            BacklogDialog backlogDialog = new BacklogDialog(this);
            backlogDialog.setLoanId(LoanID);
            backlogDialog.showDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            switch (compoundButton.getId()) {
                case R.id.rbTongHop:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.rbLichSu:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.rbChecklistVehicle:
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.rbChungTu:
                    viewPager.setCurrentItem(3);
                    break;
            }
        }
    }

    @Override
    public void returnSuccess(String msg) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Thông báo")
                .setMessage(msg)
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ContractDetailActivity.this.finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void returnFail(String msg) {
        Common.showAlert(this, msg);
    }

    public class SlidePagerAdapter extends FragmentPagerAdapter {
        public SlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return new ContractDetailFragment(getIntent().getIntExtra("TypeID", 0));
            else if (position == 1)
                return new ContractHistoryFragment(getIntent().getIntExtra("LoanID", 0));
            else if (position == 2)
                if (getIntent().getIntExtra("TypeID", 0) != 2 &&
                        getIntent().getIntExtra("TypeID", 0) != 5) {
                    return new ContractVouchersFragment(type);
                } else {
                    if (type == 0) {
                        if (contractEntity.getIsthamdinh() == 0) {
                            return new ContractVouchersFragment(type);
                        } else {
                            return new ValuationTabFragment();
                        }
                    } else {
                        if (waitTDHSEntity.getIsThamDinh() == 0) {
                            return new ContractVouchersFragment(type);
                        } else {
                            return new ValuationTabFragment();
                        }
                    }
                }
            else
                return new ContractVouchersFragment(type);
        }

        @Override
        public int getCount() {
            if (getIntent().getIntExtra("TypeID", 0) != 2 &&
                    getIntent().getIntExtra("TypeID", 0) != 5) {
                return 3;
            } else {
                if (type == 0) {
                    if (contractEntity.getIsthamdinh() == 0) {
                        return 3;
                    } else {
                        return 4;
                    }
                } else {
                    if (waitTDHSEntity.getIsThamDinh() == 0) {
                        return 3;
                    } else {
                        return 4;
                    }
                }
            }
        }
    }

}
