package vn.tima.timainspection.Util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import android.view.View;
import android.view.Window;
import android.widget.Button;

import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import id.zelory.compressor.Compressor;
import vn.tima.timainspection.R;
import vn.tima.timainspection.View.Login.LoginActivity;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Created by tima on 11/27/17.
 */

public class Common {

    public static String PREFS_NAME = "mData";

    public final static String TYPE_TINCHAP = "Cầm cố điện thoại";
    public final static String TYPE_ĐANG_KY_XE_CHINH_CHU = "Cầm cố xe máy";
    public final static String TYPE_KD_CA_THE = "Cầm cố tài sản HKD";
    public final static String TYPE_VAY_THEO_SO_HO_KHAU = "Hộ khẩu";
    public final static String TYPE_ĐANG_KY_XE_K_CHINH_CHU = "Cầm cố xe máy KCC";
    public final static String TYPE_VAY_THEO_OTO = "Ô tô ngân hàng";
    public final static String TYPE_VAY_THEO_NHA_DAT = "Nhà đất";
    public final static String TYPE_VAY_THEO_CAM_DK_OTO = "Đăng ký xe ô tô";
    static RequestQueue mRequestQueue;

    public static long roundMoney(long money, long threshold) {
        try {
            return ((money + (threshold / 2) - 1) / threshold) * threshold;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return money;
    }

    public static void showAlertRoot(Context context, String msg) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }

        builder.setTitle("Thông báo")
                .setMessage(msg)
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public static void hideKeyboard(Activity activity) { // hide soft keyboard
        try {

            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showKeyboard(Context context, EditText editText) { // hide soft keyboard
        try {

            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @SuppressLint("ResourceType")
    public static void showAlert(Context context, String msg) {
        try {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.findViewById(R.layout.dialog_alert);
            TextView tvTitle = (TextView) dialog.findViewById(R.id.tvTitle);
            TextView tvContent = (TextView) dialog.findViewById(R.id.tvContent);
            Button bt1 = (Button) dialog.findViewById(R.id.bt1);
            Button bt2 = (Button) dialog.findViewById(R.id.bt2);

            tvTitle.setText("Thông báo");
            tvContent.setText(msg);
            bt2.setVisibility(View.GONE);
            bt1.setText("OK");

            bt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        } catch (Exception ex) {
            ex.printStackTrace();
            showAlertRoot(context, msg);
        }
    }

    public static File compressImage(Context context, File file){
        try {
             new Compressor(context)
                    .setMaxWidth(640)
                    .setMaxHeight(480)
                    .setQuality(75)
                    .setCompressFormat(Bitmap.CompressFormat.WEBP)

                    .compressToFile(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static boolean checkToken(String message, Activity a) {
        try {
            String[] message_array = message.split("@");// lay mang message tra ve, 0 la message, 1 la status, 2 la code
            if (message_array[2].equals(String.valueOf(Constant.TOKEN_OUT_OF_DATE)) || message_array[2].equals(String.valueOf(Constant.TOKEN_UNAVAILABLE))) {
                toLogin(a);
                DialogUtils.showAlertDialog(a, "Thông baó", message_array[0]);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void toLogin(Activity a) {
        Intent intentToLogin = new Intent(a, LoginActivity.class);
        a.startActivity(intentToLogin);
        a.finish();
    }

    public static String getMD5Code(String string) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.reset();
            return String.format("%032x", new BigInteger(1, digest.digest(string.getBytes("UTF-8"))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    @RequiresApi(api = Build.VERSION_CODES.ECLAIR_MR1)
    public static void setHorizontalRecyclerView(Context context, RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    @RequiresApi(api = Build.VERSION_CODES.ECLAIR_MR1)
    public static void setVerticalRecyclerView(Context context, RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean checkLocationPermisstion(Activity activity, String[] permission, int requestLocation) {//Manifest.permission.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(permission[0]) != PackageManager.PERMISSION_GRANTED
                    || activity.checkSelfPermission(permission[1]) != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{permission[0], permission[1]},
                        requestLocation);
                return false;
            }
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean checkPermisstion(Activity activity, String permission) {//Manifest.permission.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(permission)
                    != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{permission},
                        1);
                return false;
            }
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean checkPermisstion(Activity activity, String permission, int request) {//Manifest.permission.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(permission)
                    != PackageManager.PERMISSION_GRANTED) {
//                activity.requestPermissions(new String[]{permission},
//                        request);
                ActivityCompat.requestPermissions(activity, new String[]{permission},
                        request);
                return false;
            }
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean checkPermisstion(Activity activity, String[] permission, int request) {//Manifest.permission.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (int i = 0; i < permission.length; i++) {
                if (activity.checkSelfPermission(permission[i])
                        != PackageManager.PERMISSION_GRANTED) {
//                    activity.requestPermissions(permission,
//                            request);
                    ActivityCompat.requestPermissions(activity, permission,
                            request);
                    return false;
                }
            }
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.ECLAIR_MR1)
    public static void setGridLayoutRecyclerView(Context context, RecyclerView recyclerView, int numberCol) {
        GridLayoutManager layoutManager = new GridLayoutManager(context, numberCol);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    public static String formatMoney(long money) {
        try {
            DecimalFormat numberFormat = new DecimalFormat("###,###,###,###");
            return numberFormat.format(money);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getTypeContract(int type) {
        String typeContract = "";
        switch (type) {
            case 1:
                typeContract = TYPE_TINCHAP;
                break;
            case 2:
                typeContract = TYPE_ĐANG_KY_XE_CHINH_CHU;
                break;
            case 3:
                typeContract = TYPE_KD_CA_THE;
                break;
            case 4:
                typeContract = TYPE_VAY_THEO_SO_HO_KHAU;
                break;
            case 5:
                typeContract = TYPE_ĐANG_KY_XE_K_CHINH_CHU;
                break;
            case 6:
                typeContract = TYPE_VAY_THEO_OTO;
                break;
            case 7:
                typeContract = TYPE_VAY_THEO_NHA_DAT;
                break;
            case 8:
                typeContract = TYPE_VAY_THEO_CAM_DK_OTO;
                break;
        }
        return typeContract;
    }

    public static String formatCreateDate(String time) {
        String rawtime = time.substring(0, 19);
        rawtime = rawtime.replace("T", " ");
        SimpleDateFormat serverTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat clientTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date date = null;
        try {
            date = serverTimeFormat.parse(rawtime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return clientTimeFormat.format(date.getTime());
    }

    public static String formatOnlyDate(String time) {
        String rawtime = time.substring(0, 19);
        rawtime = rawtime.replace("T", " ");
        SimpleDateFormat serverTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat clientTimeFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = serverTimeFormat.parse(rawtime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return clientTimeFormat.format(date.getTime());
    }

    public static <T> void addToRequestQueue(Request<T> req, Context context) {
        req.setTag("addToRequestQueue");
        getRequestQueue(context).add(req);
    }

    public static RequestQueue getRequestQueue(Context context) {

        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }
        return mRequestQueue;
    }

    public static String getDeviceName() {
        try {
            String manufacturer = Build.MANUFACTURER;
            String model = Build.MODEL;
            if (model.startsWith(manufacturer)) {
                return capitalize(model);
            } else {
                return capitalize(manufacturer) + " " + model;
            }
        } catch (Exception e) {
            return null;
        }
    }

    private static String capitalize(String s) {
        try {
            if (s == null || s.length() == 0) {
                return "";
            }
            char first = s.charAt(0);
            if (Character.isUpperCase(first)) {
                return s;
            } else {
                return Character.toUpperCase(first) + s.substring(1);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String getFirstDayOfMonth() {
        Date today = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        calendar.set(Calendar.DAY_OF_MONTH, 1);

        Date firstDayOfMonth = calendar.getTime();

        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(firstDayOfMonth);
    }

    public static String getFirstDayOfWeek() {
        Date today = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        calendar.set(Calendar.DAY_OF_WEEK, 2);

        Date firstDayOfWeek = calendar.getTime();

        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(firstDayOfWeek);
    }

    public static String getToday() {
        Date today = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        calendar.set(Calendar.DAY_OF_MONTH, 1);

        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(today);
    }

    public static boolean isForeground(Context context, String myPackage) {
        ActivityManager manager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfo = manager.getRunningTasks(1);
        ComponentName componentInfo = runningTaskInfo.get(0).topActivity;
        return componentInfo.getPackageName().equals(myPackage);
    }

}
