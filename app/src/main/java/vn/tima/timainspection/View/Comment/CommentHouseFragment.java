package vn.tima.timainspection.View.Comment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
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

import org.w3c.dom.Comment;

import java.io.IOException;
import java.text.DecimalFormat;
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

/**
 * Created by tima on 11/30/17.
 */

@SuppressLint("ValidFragment")
public class CommentHouseFragment extends Fragment implements View.OnClickListener, ICommentFragment {

    @BindView(R.id.action_bold)
    ImageButton actionBold;
    @BindView(R.id.action_italic)
    ImageButton actionItalic;
    @BindView(R.id.editor)
    RichEditor editor;
    Unbinder unbinder;
    private int loanID;
    private String address;
    private GPSTracker gps;
    private int isThamDinhNha;
    private ProgressDialog progressDialog;
    private double latitude;
    private double longitude;
    private String city;
    private String nameWard;

    private DatabaseReference mData;
    private UserInfo userInfo;

    private CommentPresenter commentPresenter;

    public CommentHouseFragment(int loanID, String address, int isThamDinhNha, String nameWard) {
        this.loanID = loanID;
        this.address = address;
        this.isThamDinhNha = isThamDinhNha;
        this.nameWard = nameWard;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment_house, container, false);
        unbinder = ButterKnife.bind(this, view);

        gps = new GPSTracker(this.getActivity());
        editor.setEditorFontSize(22);
        editor.setPadding(10, 10, 10, 10);

        actionBold.setOnClickListener(this);
        actionItalic.setOnClickListener(this);

        String comment = SharePreferencesHelper.getIntance(this.getActivity()).getComment(String.valueOf(loanID) + "1");
        if(comment.isEmpty()) {
            editor.setHtml("<p><strong>1. Thẩm định nh&agrave; <em>(bắt buộc)</em></strong></p>" +
                    "<p>M&ocirc; tả địa chỉ ......</p>" +
                    "<p><em>&nbsp;&nbsp;&nbsp;<strong><u>H&agrave;ng x&oacute;m 1</u></strong></em> (số nh&agrave; ....t&ecirc;n .... ): cho biết</p>" +
                    "<p><em>&nbsp;&nbsp;&nbsp;<strong><u>H&agrave;ng x&oacute;m 2</u></strong></em> (số nh&agrave; ....t&ecirc;n .... ): cho biết</p>" +
                    "<p><em>&nbsp;&nbsp;&nbsp;<strong><u>H&agrave;ng x&oacute;m 3</u></strong></em> (số nh&agrave; ....t&ecirc;n .... ): cho biết</p>" +
                    "<p>&nbsp;&nbsp;&nbsp;<strong><u>Tr&agrave; đ&aacute;</u></strong> (m&ocirc; tả địa chỉ .....t&ecirc;n .... ):</p>" +
                    "<p>&nbsp;&nbsp;&nbsp;<strong><u>Nh&agrave; KH:</u></strong> gặp ai (bố, mẹ, ... ) cho biết</p>" +
                    "<p>&nbsp;&nbsp;&nbsp;<strong><u>Tổ trưởng tổ d&acirc;n phố</u></strong> (số nh&agrave; ..... t&ecirc;n .... số điện thoại .... số tổ ....): cho biết&hellip;&hellip;</p>" +
                    "<p><strong>2. Đ&aacute;nh gi&aacute; chung của TĐTĐ <em>(bắt buộc)</em></strong></p>" +
                    "<p><strong><u>Nh&acirc;n th&acirc;n</u></strong> ...</p>" +
                    "<p><strong><u>Khách hàng đang sống cùng ai: </u></strong> ...</p>" +
                    "<p><strong><u>Khách hàng sống ở đây bao nhiêu năm: </u></strong> ...</p>" +
                    "<p><strong><u>Tình trạng nợ nần: </u></strong> ...</p>" +
                    "<p><strong><u>Điều kiện kinh tế</u></strong> ...</p>" +
                    "<p><strong><u>Nhà khách hàng có mấy tầng: </u></strong> ...</p>" +
                    "<p><strong><u>Điều kiện sống: </u></strong> ...</p>" +
                    "<p><strong><u>Đề xuất:</u></strong> ...</p>");
        } else {
            editor.setHtml(comment);
        }

        commentPresenter = new CommentPresenter(this.getActivity(), this);

        editor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {
                SharePreferencesHelper.getIntance(CommentHouseFragment.this.getActivity()).saveComment(String.valueOf(loanID) + "1", text);
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
                        //item.setEnabled(false);
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
                                    SharePreferencesHelper.getIntance(CommentHouseFragment.this.getActivity()).saveComment(String.valueOf(loanID) + "1", "");
                                } else {
                                    Geocoder geocoder;
                                    List<Address> addresses;
                                    geocoder = new Geocoder(CommentHouseFragment.this.getActivity(), Locale.getDefault());

                                    addresses = geocoder.getFromLocation(latitude, longitude, 1);

                                    String address = addresses.get(0).getAddressLine(0);
                                    city = addresses.get(0).getLocality();

                                    if (CommentHouseFragment.this.address == null || CommentHouseFragment.this.address.isEmpty()) {
                                        commentPresenter.addComment(loanID, editor.getHtml(), Common.getDeviceName(),
                                                String.valueOf(latitude),
                                                String.valueOf(longitude),
                                                address,
                                                city);
                                        SharePreferencesHelper.getIntance(CommentHouseFragment.this.getActivity()).saveComment(String.valueOf(loanID) + "1", "");
                                    } else {
                                        GeoPoint point = null;//"số 17 ngõ 26 phố lý thường kiệt"
                                        double dis = 0;
                                        try {
                                            point = getLocationFromAddress(CommentHouseFragment.this.address);
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
                                            SharePreferencesHelper.getIntance(CommentHouseFragment.this.getActivity()).saveComment(String.valueOf(loanID) + "1", "");
                                        }

                                        if (isThamDinhNha == 0) {
                                            commentPresenter.addComment(loanID, editor.getHtml(), Common.getDeviceName(),
                                                    String.valueOf(latitude),
                                                    String.valueOf(longitude),
                                                    address,
                                                    city);
                                            SharePreferencesHelper.getIntance(CommentHouseFragment.this.getActivity()).saveComment(String.valueOf(loanID) + "1", "");
                                        } else {
                                            if (dis <= 2000) {
                                                commentPresenter.addComment(loanID, editor.getHtml(), Common.getDeviceName(),
                                                        String.valueOf(latitude),
                                                        String.valueOf(longitude),
                                                        address,
                                                        city);
                                                SharePreferencesHelper.getIntance(CommentHouseFragment.this.getActivity()).saveComment(String.valueOf(loanID) + "1", "");
                                            } else {
                                                addCommentFail("Bạn đang cách vị trí cần thẩm định khoảng " + Math.round(dis / 1000) + " km. Vui lòng thực hiện thẩm định tại nơi ở của khách hàng");
                                            }
                                        }
                                    }
                                }
                            } catch (Exception ex) {
                                addCommentFail(ex.getMessage());
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
        progressDialog.setMessage("loading....");
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
                    SharePreferencesHelper.getIntance(CommentHouseFragment.this.getActivity()).saveCode(Integer.parseInt(dataSnapshot.getValue().toString()));
                    input.setText(dataSnapshot.getValue().toString());
                } catch (Exception ex) {
                    SharePreferencesHelper.getIntance(CommentHouseFragment.this.getActivity()).saveCode(i1);
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
                if (SharePreferencesHelper.getIntance(CommentHouseFragment.this.getActivity()).getCount() >= 0) {
                    mData.child(userInfo.getUser(this.getActivity()).getUserName()).child("Count").setValue(SharePreferencesHelper.getIntance(CommentHouseFragment.this.getActivity()).getCount() + 1);
                }
            } catch(Exception ex) {
                mData.child(userInfo.getUser(this.getActivity()).getUserName()).child("Count").setValue(0);
            }
        }

        mData.child(userInfo.getUser(this.getActivity()).getUserName()).child("Count").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    SharePreferencesHelper.getIntance(CommentHouseFragment.this.getActivity()).saveCount(Integer.parseInt(dataSnapshot.getValue().toString()));
                } catch (Exception ex) {
                    SharePreferencesHelper.getIntance(CommentHouseFragment.this.getActivity()).saveCount(0);
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
                        == SharePreferencesHelper.getIntance(CommentHouseFragment.this.getActivity()).getCode()) {
                    commentPresenter.addComment(loanID, editor.getHtml(), Common.getDeviceName(),
                            String.valueOf(latitude),
                            String.valueOf(longitude),
                            address,
                            city);
                    SharePreferencesHelper.getIntance(CommentHouseFragment.this.getActivity()).saveComment(String.valueOf(loanID) + "1", "");
                } else {
                    Common.showAlert(CommentHouseFragment.this.getActivity(), "Sai mã code");
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

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public double CalculationByDistance(Location StartP, Location EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.getLatitude();
        double lat2 = EndP.getLatitude();
        double lon1 = StartP.getLongitude();
        double lon2 = EndP.getLongitude();
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));

        return Radius * c;
    }

}
