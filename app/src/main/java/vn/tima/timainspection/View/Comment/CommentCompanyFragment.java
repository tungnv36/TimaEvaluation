package vn.tima.timainspection.View.Comment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jp.wasabeef.richeditor.RichEditor;
import vn.tima.timainspection.Model.Login.UserInfo;
import vn.tima.timainspection.Presenter.Comment.CommentPresenter;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Common;
import vn.tima.timainspection.Util.GPSTracker;
import vn.tima.timainspection.Util.GeoPoint;
import vn.tima.timainspection.Util.SharePreferencesHelper;
import vn.tima.timainspection.View.ContractDetail.ContractDetailActivity;

/**
 * Created by tima on 11/30/17.
 */

@SuppressLint("ValidFragment")
public class CommentCompanyFragment extends Fragment implements View.OnClickListener, ICommentFragment {

    @BindView(R.id.editor)
    RichEditor editor;
    @BindView(R.id.action_bold)
    ImageButton actionBold;
    @BindView(R.id.action_italic)
    ImageButton actionItalic;
    Unbinder unbinder;
    private int loanID;
    private GPSTracker gps;
    private String address;
    private int isThamDinhCongTy;
    private ProgressDialog progressDialog;

    private CommentPresenter commentPresenter;
    private double latitude;
    private double longitude;
    private String city;
    private String nameWard;

    private DatabaseReference mData;
    private UserInfo userInfo;

    public CommentCompanyFragment(int loanID, String address, int isThamDinhCongTy, String nameWard) {
        this.loanID = loanID;
        this.address = address;
        this.isThamDinhCongTy = isThamDinhCongTy;
        this.nameWard = nameWard;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment_company, container, false);
        unbinder = ButterKnife.bind(this, view);

        gps = new GPSTracker(this.getActivity());
        editor.setEditorFontSize(22);
        editor.setPadding(10, 10, 10, 10);

        actionBold.setOnClickListener(this);
        actionItalic.setOnClickListener(this);

        String comment = SharePreferencesHelper.getIntance(this.getActivity()).getComment(String.valueOf(loanID) + "2");
        if(comment.isEmpty()) {
            editor.setHtml("<p><strong>1. Thẩm định công ty <em>(bắt buộc)</em></strong></p>" +
                    "<p>TDTD mô tả địa chỉ chi tiết ...... </p>" +
                    "<p><em>&nbsp;&nbsp;&nbsp;<strong><u>Bảo vệ 1</strong></u></em>  (ca ... tên ... ): cho biết</p>" +
                    "<p><em>&nbsp;&nbsp;&nbsp;<strong><u>Lễ tân  2</strong></u></em> ( tên  ...): cho biết</p>" +
                    "<p><em>&nbsp;&nbsp;&nbsp;<strong><u>Hành chính 3</strong></u></em> (tên  ...): cho biết</p>" +
                    "<p>&nbsp;&nbsp;&nbsp;<strong><u>Lễ tân toà nhà</strong></u> (tên  ...):</p>" +
                    "<p>&nbsp;&nbsp;&nbsp;<strong><u>Gặp khách</strong></u> (tên ...): cho biết ...</p>" +
                    "<p><strong>2. Đánh giá chung của TĐTĐ <em>(bắt buộc)</em></strong></p>" +
                    "<p><strong><u>Nhân thân KH</strong></u> ...</p>" +
                    "<p><strong><u>Tình trạng nợ nần: </u></strong> ...</p>" +
                    "<p><strong><u>Công ty quy mô</strong></u> ...</p>" +
                    "<p><strong>&nbsp;&nbsp;&nbsp;<u>Địa chỉ làm việc (Chi nhánh/Trụ sở chính)</strong></u> ...</p>" +
                    "<p><strong>&nbsp;&nbsp;&nbsp;<u>Khách hàng làm được bao lâu: </strong></u> ...</p>" +
                    "<p><strong>&nbsp;&nbsp;&nbsp;<u>Khách hàng làm ở bộ phận nào: </strong></u> ...</p>" +
                    "<p><strong><u>Đề xuất cho vay</strong></u> ...</p>" +
                    "<p><strong><u>Đánh gia chung</strong></u> ...</p>");
        } else {
            editor.setHtml(comment);
        }

        commentPresenter = new CommentPresenter(this.getActivity(), this);

        editor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {
                SharePreferencesHelper.getIntance(CommentCompanyFragment.this.getActivity()).saveComment(String.valueOf(loanID) + "2", text);
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this.getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this.getActivity());
        }
        builder.setTitle("Thông báo!")
                .setMessage("Bạn có chắc chắn muốn comment không?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
//                        item.setEnabled(false);
                        if(gps.canGetLocation()){
                            try {
                                showAlertDialogLogOut();

                                latitude = gps.getLatitude();
                                longitude = gps.getLongitude();

                                if(latitude == 0 || longitude == 0) {
//                    addCommentFail("Không thể định vị");
                                    commentPresenter.addComment(loanID, editor.getHtml(), Common.getDeviceName(),
                                            "0",
                                            "0",
                                            "",
                                            "");
                                    SharePreferencesHelper.getIntance(CommentCompanyFragment.this.getActivity()).saveComment(String.valueOf(loanID) + "2", "");
                                } else {
                                    Geocoder geocoder;
                                    List<Address> addresses;
                                    geocoder = new Geocoder(CommentCompanyFragment.this.getActivity(), Locale.getDefault());

                                    addresses = geocoder.getFromLocation(latitude, longitude, 1);

                                    String address = addresses.get(0).getAddressLine(0);
                                    city = addresses.get(0).getLocality();

                                    if (CommentCompanyFragment.this.address == null || CommentCompanyFragment.this.address.isEmpty()) {
                                        commentPresenter.addComment(loanID, editor.getHtml(), Common.getDeviceName(),
                                                String.valueOf(latitude),
                                                String.valueOf(longitude),
                                                address,
                                                city);
                                        SharePreferencesHelper.getIntance(CommentCompanyFragment.this.getActivity()).saveComment(String.valueOf(loanID) + "2", "");
                                    } else {
//                        GeoPoint point = getLocationFromAddress(this.address);
//                        Location l = new Location("newlocation");
//                        l.setLatitude(point.getLat());
//                        l.setLongitude(point.getLon());
//                        double dis = gps.getLocation().distanceTo(l);

                                        GeoPoint point = null;
                                        double dis = 0;
                                        try {
                                            point = getLocationFromAddress(CommentCompanyFragment.this.address);
                                            Location l = new Location("newlocation");
                                            l.setLatitude(point.getLat());
                                            l.setLongitude(point.getLon());
                                            dis = gps.getLocation().distanceTo(l);
                                        } catch (Exception ex) {
                                            commentPresenter.addComment(loanID, editor.getHtml(), Common.getDeviceName(),
                                                    String.valueOf(latitude),
                                                    String.valueOf(longitude),
                                                    address,
                                                    city);
                                            SharePreferencesHelper.getIntance(CommentCompanyFragment.this.getActivity()).saveComment(String.valueOf(loanID) + "2", "");
                                        }

                                        if (isThamDinhCongTy == 0) {
                                            commentPresenter.addComment(loanID, editor.getHtml(), Common.getDeviceName(),
                                                    String.valueOf(latitude),
                                                    String.valueOf(longitude),
                                                    address,
                                                    city);
                                            SharePreferencesHelper.getIntance(CommentCompanyFragment.this.getActivity()).saveComment(String.valueOf(loanID) + "2", "");
                                        } else {
                                            if (dis <= 2000) {
                                                commentPresenter.addComment(loanID, editor.getHtml(), Common.getDeviceName(),
                                                        String.valueOf(latitude),
                                                        String.valueOf(longitude),
                                                        address,
                                                        city);
                                                SharePreferencesHelper.getIntance(CommentCompanyFragment.this.getActivity()).saveComment(String.valueOf(loanID) + "2", "");
                                            } else {
                                                addCommentFail("Bạn đang cách vị trí cần thẩm định khoảng " + Math.round(dis / 1000) + " km. Vui lòng thực hiện thẩm định tại nơi ở của khách hàng");
                                            }
                                        }
                                    }
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            gps.showSettingsAlert();
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
        return super.onOptionsItemSelected(item);
    }

    private void showAlertDialogLogOut() {
        progressDialog = new ProgressDialog(this.getActivity());
        progressDialog.setMessage("loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.action_bold:
                editor.setBold();
                break;
            case R.id.action_italic:
                editor.setItalic();
                break;
        }
    }

    @Override
    public void addCommentSuccess(String msg) {
        progressDialog.dismiss();
        this.getActivity().finish();
        Toast.makeText(this.getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void addCommentFail(String msg) {
        progressDialog.dismiss();
//        Common.showAlert(this.getActivity(), msg);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle("Thông báo");

        final EditText input = new EditText(this.getActivity());
        final TextView tv = new TextView(this.getActivity());
        final LinearLayout ln = new LinearLayout(this.getActivity());
        ln.setOrientation(LinearLayout.VERTICAL);
        ln.setPadding(10, 10, 10, 10);
        ln.addView(tv);
        ln.addView(input);
        tv.setText(msg);
        tv.setPadding(5, 5, 5, 5);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setHint("Nhập mã code để comment");
        builder.setView(ln);

        Random r = new Random();
        final int i1 = r.nextInt(999 - 100) + 100;
        mData = FirebaseDatabase.getInstance().getReference();
        userInfo = UserInfo.getInstance();
        mData.child(userInfo.getUser(this.getActivity()).getUserName()).child("Code").setValue(i1);
        mData.child(userInfo.getUser(this.getActivity()).getUserName()).child("Code").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    SharePreferencesHelper.getIntance(CommentCompanyFragment.this.getActivity()).saveCode(Integer.parseInt(dataSnapshot.getValue().toString()));
                    input.setText(dataSnapshot.getValue().toString());
                } catch (Exception ex) {
                    SharePreferencesHelper.getIntance(CommentCompanyFragment.this.getActivity()).saveCode(i1);
                    input.setText(String.valueOf(i1));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if(Integer.parseInt(Common.getToday().split("-")[0].toString()) == 1) {
            mData.child(userInfo.getUser(this.getActivity()).getUserName()).child("Count").setValue(0);
        } else {
            try {
                if (SharePreferencesHelper.getIntance(CommentCompanyFragment.this.getActivity()).getCount() >= 0) {
                    mData.child(userInfo.getUser(this.getActivity()).getUserName()).child("Count").setValue(SharePreferencesHelper.getIntance(CommentCompanyFragment.this.getActivity()).getCount() + 1);
                }
            } catch(Exception ex) {
                mData.child(userInfo.getUser(this.getActivity()).getUserName()).child("Count").setValue(0);
            }
        }

        mData.child(userInfo.getUser(this.getActivity()).getUserName()).child("Count").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    SharePreferencesHelper.getIntance(CommentCompanyFragment.this.getActivity()).saveCount(Integer.parseInt(dataSnapshot.getValue().toString()));
                } catch (Exception ex) {
                    SharePreferencesHelper.getIntance(CommentCompanyFragment.this.getActivity()).saveCount(0);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!input.getText().toString().isEmpty() && Integer.parseInt(input.getText().toString())
                        == SharePreferencesHelper.getIntance(CommentCompanyFragment.this.getActivity()).getCode()) {
                    commentPresenter.addComment(loanID, editor.getHtml(), Common.getDeviceName(),
                            String.valueOf(latitude),
                            String.valueOf(longitude),
                            address,
                            city);
                    SharePreferencesHelper.getIntance(CommentCompanyFragment.this.getActivity()).saveComment(String.valueOf(loanID) + "2", "");
                } else {
                    Common.showAlert(CommentCompanyFragment.this.getActivity(), "Sai mã code");
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    @Override
    public void addLocationSuccess(String msg) {
        Toast.makeText(this.getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void addLocationFail(String msg) {
        Toast.makeText(this.getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    public GeoPoint getLocationFromAddress(String strAddress){

        Geocoder coder = new Geocoder(this.getActivity());
        List<Address> address;
        GeoPoint p1 = new GeoPoint();

        try {
            address = coder.getFromLocationName(strAddress,5);
            if (address==null) {
                return null;
            }
            Address location=address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1.setLon(location.getLongitude());
            p1.setLat(location.getLatitude());

            return p1;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
