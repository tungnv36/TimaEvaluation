package vn.tima.timainspection.View.PostContract;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.tima.timainspection.Model.Entity.ContractEntity;
import vn.tima.timainspection.Model.Entity.LibraryImage;
import vn.tima.timainspection.Model.Entity.PostImage;
import vn.tima.timainspection.Model.Login.UserInfo;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Common;
import vn.tima.timainspection.Util.Config;
import vn.tima.timainspection.Util.Constant;
import vn.tima.timainspection.Util.DialogUtils;
import vn.tima.timainspection.Util.MultipartRequestHelper;
import vn.tima.timainspection.View.Dialog.LoadingDialog;
import vn.tima.timainspection.request.ApiService;

/**
 * Created by DinhAnh on 10/18/2016.
 */
public class PostContractActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = PostContractActivity.class.getSimpleName();

    @BindView(R.id.rl_images)
    RelativeLayout rl_images;

    @BindView(R.id.ll_content_images)
    LinearLayout ll_content_images;

    @BindView(R.id.tv_title_image)
    TextView tv_title_image;
    @BindView(R.id.v_action_image)
    View v_action_image;
    @BindView(R.id.rv_post_image)
    RecyclerView rv_post_image;

    private UserInfo userInfo;

    public Activity mActivity;
    public static final int RESULT_LOAD_IMAGE = 101;
    public static final int RESULT_LOAD_IMAGE_FROM_CAMERA = 201;
    private ArrayList<PostImage> listImage;
    private PostImageAdapter postImageAdapter;
    private int typeId;
    private int status;
    private int loanCreditId;
    private String totalMoneyFirst;
    private String customerCreditId;
    private int customerId;
    private PostImage postImage;
    String typePostSuccess = "";
    ArrayList<String> listTypePost = new ArrayList<>();
    String cityId = "";
    ContractEntity contractEntity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_contract);
        contractEntity = (ContractEntity) getIntent().getSerializableExtra("contractEntity");
        listImage = new ArrayList<>();
        userInfo = UserInfo.getInstance();
        status = contractEntity.getStatus();
        loanCreditId = contractEntity.getLoancreditid() ;
        totalMoneyFirst = contractEntity.getTotalmoney() + "";
        customerId = contractEntity.getCustomerid();
        typeId = contractEntity.getTypeid();
//        cityId = MecashApplication.get().getDetailContract().getCustomer().getCityId();
        ButterKnife.bind(this);
        mActivity = this;
        rl_images.setOnClickListener(this);
        initRecyclerView();

    }

    private void initRecyclerView() {
       try {
           setListPostImageByCase();
           postImageAdapter = new PostImageAdapter(this,loanCreditId+"");
           Common.setVerticalRecyclerView(this, rv_post_image);
           postImageAdapter.setListMenu(listImage);
           postImageAdapter.setTypePostUploadSuccess(new PostImageAdapter.OnTypePostUploadSuccess() {
               @Override
               public void onTypeIdSuccess(int type) {
                   addTypePostSuccess(type);
               }
           });
           postImageAdapter.setListener(new PostImageAdapter.OnclickTitleListener() {
               @Override
               public void onClick(PostImage data) {
                   postImage = data;
                   showDialogMedia();
               }
           });
           rv_post_image.setAdapter(postImageAdapter);
       }catch (Exception e){
           e.printStackTrace();
       }
    }


    public void showDialogMedia() {
        final Dialog dialog = new Dialog(mActivity, R.style.ProgressHUD);
        dialog.setTitle("");
        dialog.setContentView(R.layout.dialog_select_media);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.2f;
        dialog.getWindow().setAttributes(lp);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        Button btnAlbum = (Button) dialog.findViewById(R.id.btn_album);
        Button btnCamera = (Button) dialog.findViewById(R.id.btn_camera);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showDialogGallery();
//                String[] permisstions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
//                if (Common.checkPermisstion(mActivity, permisstions, 101)) {
                    openAlbum();
                    dialog.dismiss();
//                }
            }
        });
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showCamera()
//                String[] permisstions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
//                if (Common.checkPermisstion(mActivity, permisstions, 100)) {
                    openCamera();
                    dialog.dismiss();
//                }
            }
        });
        dialog.show();
    }



    public void openAlbum() {
        Intent i = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/*");
        mActivity.startActivityForResult(i, PostContractActivity.RESULT_LOAD_IMAGE);

//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMAGE);
    }

    public String getRealPathFromURI(Uri uri) {
        if (uri == null) {
            return null;
        }
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();
    }

    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mActivity.startActivityForResult(intent, PostContractActivity.RESULT_LOAD_IMAGE_FROM_CAMERA);
    }

    private void setListPostImageByCase() {
        if ((status == Constant.STATUS_TVV_HUY_DO_K_KY_DUOC_HD
                || status == Constant.STATUS_CHO_KSNB_DUYET_15
                || status == Constant.STATUS_DL_TRA_LAI_KSNB_N_15
                || status == Constant.STATUS_CHO_TU_VAN_VIEN_DI_LAY_HO_SO
                || status == Constant.STATUS_HO_SO_BI_HUY
                || status == Constant.STATUS_KE_TOAN_TRA_LAI_TVV
                || status == Constant.STATUS_HO_SO_BI_HUY_N_11
                || status == Constant.STATUS_TDTD_HO_SO
                || status == Constant.STATUS_HO_SO_BI_HUY_N_12) && typeId == Constant.TYPE_ID_KHOAN_VAY_TIN_CHAP_THEO_LUONG) {
            listImage.add(new PostImage(typeId, status, Constant.TYPE_1_POST_ANH_CMNN, "Ảnh chứng minh nhân dân "));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_1_POST_ANH_SO_HO_KHAU, "Ảnh sổ hộ khẩu "));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_1_POST_ANH_CHUNG_MINH_THU_NHAP, "Ảnh giấy tờ CM Thu Nhập "));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_1_POST_ANH_MAT, "Ảnh Mặt "));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_1_POST_ANH_NHA, "Ảnh Nhà * (Bắt buộc)"));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_1_POST_ANH_CONG_TY, "Ảnh công ty * (Bắt buộc)"));
            /*
            * không bắt buộc up ảnh
            * */
            if (userInfo.getUser(this).getGroupId().equals(Constant.GROUP_ID_KSNB_TD_THUC_DIA)) {
                addTypePostSuccess(Constant.TYPE_1_POST_ANH_CMNN);
                addTypePostSuccess(Constant.TYPE_1_POST_ANH_SO_HO_KHAU);
                addTypePostSuccess(Constant.TYPE_1_POST_ANH_CHUNG_MINH_THU_NHAP);
                addTypePostSuccess(Constant.TYPE_1_POST_ANH_MAT);
            } else {
                addTypePostSuccess(Constant.TYPE_1_POST_ANH_NHA);
                addTypePostSuccess(Constant.TYPE_1_POST_ANH_CONG_TY);
            }
            return;
        } else if ((status == Constant.STATUS_TVV_HUY_DO_K_KY_DUOC_HD
                || status == Constant.STATUS_CHO_KSNB_DUYET_15
                || status == Constant.STATUS_DL_TRA_LAI_KSNB_N_15
                || status == Constant.STATUS_TDTD_HO_SO
                || status == Constant.STATUS_KE_TOAN_TRA_LAI_TVV)
                && typeId == Constant.TYPE_ID_KHOAN_VAY_TIN_CHAP_THEO_LUONG) {
            listImage.add(new PostImage(typeId, status, Constant.TYPE_1_POST_ANH_HD_TU_VAN, "Ảnh hợp đồng tư vấn "));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_1_POST_ANH_HD_CAM_CO, "Ảnh hợp đồng cầm cố "));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_1_POST_ANH_HD_GUI_GIU_TAI_SAN, "Ảnh hợp đồng giữ tài sản "));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_1_POST_ANH_HD_THONG_TIN_CHUNG_KHOAN_VAY, "Ảnh thông tin khoản vay "));
            return;
        } else if ((status == Constant.STATUS_TVV_HUY_DO_K_KY_DUOC_HD
                || status == Constant.STATUS_CHO_KSNB_DUYET_15
                || status == Constant.STATUS_DL_TRA_LAI_KSNB_N_15
                || status == Constant.STATUS_CHO_TU_VAN_VIEN_DI_LAY_HO_SO
                || status == Constant.STATUS_TDTD_HO_SO
                || status == Constant.STATUS_HO_SO_BI_HUY
                || status == Constant.STATUS_HO_SO_BI_HUY_N_11
                || status == Constant.STATUS_HO_SO_BI_HUY_N_12) && (typeId == Constant.TYPE_ID_KHOAN_VAY_THEO_DANG_KY_XE
                || typeId == Constant.TYPE_ID_KHOAN_VAY_THEO_DANG_KY_XE_K_CHINH_CHU)) {
            listImage.add(new PostImage(typeId, status, Constant.TYPE_2_POST_ANH_CMT, "Chứng minh thư "));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_2_POST_ANH_NHA, "Ảnh Nhà * (Bắt buộc)"));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_2_POST_ANH_MAT, " Ảnh Mặt "));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_2_POST_ANH_GIAY_DK_XE, "Ảnh Xe (Ảnh Xe,Đăng Ký,Số Khung,Số Máy,Thân Xe) "));
            /*
            * không bắt buộc up ảnh
            * */
            if (userInfo.getUser(this).getGroupId().equals(Constant.GROUP_ID_KSNB_TD_THUC_DIA)) {
                addTypePostSuccess(Constant.TYPE_2_POST_ANH_CMT);
                addTypePostSuccess(Constant.TYPE_2_POST_ANH_MAT);
                addTypePostSuccess(Constant.TYPE_2_POST_ANH_GIAY_DK_XE);
            } else {
                addTypePostSuccess(Constant.TYPE_2_POST_ANH_NHA);
            }
            return;
        } else if ((status == Constant.STATUS_TVV_HUY_DO_K_KY_DUOC_HD
                || status == Constant.STATUS_CHO_KSNB_DUYET_15
                || status == Constant.STATUS_DL_TRA_LAI_KSNB_N_15
                || status == Constant.STATUS_TDTD_HO_SO
                || status == Constant.STATUS_KE_TOAN_TRA_LAI_TVV) && (typeId == Constant.TYPE_ID_KHOAN_VAY_THEO_DANG_KY_XE
                || typeId == Constant.TYPE_ID_KHOAN_VAY_THEO_DANG_KY_XE_K_CHINH_CHU)) {
            if (typeId == Constant.TYPE_ID_KHOAN_VAY_THEO_DANG_KY_XE) {
                listImage.add(new PostImage(typeId, status, Constant.TYPE_2_POST_HD_TU_VAN, "Hợp đồng tư vấn "));
                listImage.add(new PostImage(typeId, status, Constant.TYPE_2_POST_HD_MUA_BAN, "Hợp đồng mua bán "));
                listImage.add(new PostImage(typeId, status, Constant.TYPE_2_POST_HD_THUE_XE, "Hợp đồng thuê xe "));
            } else if (typeId == Constant.TYPE_ID_KHOAN_VAY_THEO_DANG_KY_XE_K_CHINH_CHU) {
                listImage.add(new PostImage(typeId, status, Constant.TYPE_2_POST_HD_TU_VAN_K_CHINH_CHU, "Hợp đồng tư vấn "));
                listImage.add(new PostImage(typeId, status, Constant.TYPE_2_POST_HD_MUA_BAN_K_CHINH_CHU, "Hợp đồng mua bán "));
                listImage.add(new PostImage(typeId, status, Constant.TYPE_2_POST_HD_THUE_XE_K_CHINH_CHU, "Hợp đồng thuê xe "));
            }

            if (userInfo.getUser(this).getGroupId().equals(Constant.GROUP_ID_KSNB_TD_THUC_DIA)) {
                if (typeId == Constant.TYPE_ID_KHOAN_VAY_THEO_DANG_KY_XE) {
                    addTypePostSuccess(Constant.TYPE_2_POST_HD_TU_VAN);
                    addTypePostSuccess(Constant.TYPE_2_POST_HD_MUA_BAN);
                    addTypePostSuccess(Constant.TYPE_2_POST_HD_THUE_XE);
                } else if (typeId == Constant.TYPE_ID_KHOAN_VAY_THEO_DANG_KY_XE_K_CHINH_CHU) {
                    addTypePostSuccess(Constant.TYPE_2_POST_HD_TU_VAN_K_CHINH_CHU);
                    addTypePostSuccess(Constant.TYPE_2_POST_HD_MUA_BAN_K_CHINH_CHU);
                    addTypePostSuccess(Constant.TYPE_2_POST_HD_THUE_XE_K_CHINH_CHU);
                }
            } else {
            }
            return;
        } else if ((status == Constant.STATUS_TVV_HUY_DO_K_KY_DUOC_HD//-------
                || status == Constant.STATUS_CHO_KSNB_DUYET_15
                || status == Constant.STATUS_DL_TRA_LAI_KSNB_N_15
                || status == Constant.STATUS_TDTD_HO_SO
                || status == Constant.STATUS_CHO_TU_VAN_VIEN_DI_LAY_HO_SO
                || status == Constant.STATUS_HO_SO_BI_HUY
                || status == Constant.STATUS_HO_SO_BI_HUY_N_11
                || status == Constant.STATUS_HO_SO_BI_HUY_N_12)
                && (typeId == Constant.TYPE_ID_KHOAN_VAY_THEO_HO_KHAU
                ||typeId == Constant.TYPE_ID_KHOAN_VAY_THEO_NHA_DAT)) {
            listImage.add(new PostImage(typeId, status, Constant.TYPE_2_POST_ANH_CA_NHAN, "Ảnh cá nhân (Mặt,Facebook) "));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_2_POST_ANH_GIAY_TO_NHAN_THAN, "Ảnh giấy tờ nhân thân (CMND/CCCD) "));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_2_POST_ANH_GIAY_TO_CU_TRU, "Ảnh giấy tờ cư trú (hộ khẩu) "));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_2_POST_ANH_HOA_DON, "Ảnh Hóa Đơn ( Điện/Nước/Điện thoại cố định) "));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_2_POST_ANH_DINH_VI_NHA, "Ảnh Nhà nhà * (Nhà,Selfie TVV & Nhà,Chụp định vị Nhà) (Bắt buộc)"));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_2_POST_ANH_KHAC, "Ảnh Khác (Các loại giấy tờ khác nếu có)"));
            /*
            * không bắt buộc up ảnh
            * */
            if (userInfo.getUser(this).getGroupId().equals(Constant.GROUP_ID_KSNB_TD_THUC_DIA)) {
                addTypePostSuccess(Constant.TYPE_2_POST_ANH_CA_NHAN);
                addTypePostSuccess(Constant.TYPE_2_POST_ANH_GIAY_TO_NHAN_THAN);
                addTypePostSuccess(Constant.TYPE_2_POST_ANH_GIAY_TO_CU_TRU);
                addTypePostSuccess(Constant.TYPE_2_POST_ANH_HOA_DON);
                addTypePostSuccess(Constant.TYPE_2_POST_ANH_KHAC);
            } else {
                addTypePostSuccess(Constant.TYPE_2_POST_ANH_DINH_VI_NHA);
                addTypePostSuccess(Constant.TYPE_2_POST_ANH_KHAC);
            }
            addTypePostSuccess(Constant.TYPE_2_POST_ANH_HOA_DON);

        } else if ((status == Constant.STATUS_TVV_HUY_DO_K_KY_DUOC_HD
                || status == Constant.STATUS_CHO_KSNB_DUYET_15
                || status == Constant.STATUS_TDTD_HO_SO
                || status == Constant.STATUS_DL_TRA_LAI_KSNB_N_15
                || status == Constant.STATUS_KE_TOAN_TRA_LAI_TVV
                || status == Constant.STATUS_KE_TOAN_TRA_LAI_TVV)
                && typeId == Constant.TYPE_ID_KHOAN_VAY_THEO_HO_KHAU) {
            listImage.add(new PostImage(typeId, status, Constant.TYPE_2_HD_TU_VAN_6_BUOC_2, "HĐ Tư Vấn 6 Bước 2 Vay Qua Sổ Hộ khẩu)"));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_2_HD_CAM_CO_6_BUOC_2, "HĐ Cầm Cố 6 Bước 2 Vay Qua Sổ Hộ khẩu"));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_2_HD_GIU_TAI_SAN_6_BUOC_2, "HĐ Gửi Giữ Tài Sản 6 Bước 2 Vay Qua Sổ Hộ khẩu"));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_2_KHOAN_VAY_6_BUOC_2, "Khoản vay 6 Bước 2 Vay Qua Sổ Hộ khẩu"));
        }else if ((status == Constant.STATUS_TVV_HUY_DO_K_KY_DUOC_HD//-------
                || status == Constant.STATUS_CHO_KSNB_DUYET_15
                || status == Constant.STATUS_DL_TRA_LAI_KSNB_N_15
                || status == Constant.STATUS_TDTD_HO_SO
                || status == Constant.STATUS_TDTD_HO_SO
                || status == Constant.STATUS_CHO_TU_VAN_VIEN_DI_LAY_HO_SO
                || status == Constant.STATUS_HO_SO_BI_HUY
                || status == Constant.STATUS_HO_SO_BI_HUY_N_11
                || status == Constant.STATUS_HO_SO_BI_HUY_N_12)
                && (typeId == Constant.TYPE_ID_KHOAN_VAY_THEO_OTO || typeId == Constant.TYPE_ID_KHOAN_VAY_THEO_OTO_2)) {
            listImage.add(new PostImage(typeId, status, Constant.TYPE_NHAN_THAN_VAY_THE_CHAP_OTO, "Ảnh nhân thân vay thế chấp oto ngân hàng"));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_CU_TRU_VAY_THE_CHAP_OTO, "Ảnh cư trú vay thế chấp oto ngân hàng"));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_CU_CONG_VIEC_THE_CHAP_OTO, "Ảnh công viêc vay thế chấp oto ngân hàng"));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_THU_NHAP_VIEC_THE_CHAP_OTO, "Ảnh thu nhập vay thế chấp oto ngân hàng"));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_GIAY_TO_XE_THE_CHAP_OTO, "Ảnh giấy tờ vay thế chấp oto ngân hàng"));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_HD_TU_VAN_THE_CHAP_OTO, "Ảnh giấy tờ ngân vay thế chấp oto "));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_ANH_NHA_THE_CHAP_OTO, "Ảnh nhà vay thế chấp oto "));
            listImage.add(new PostImage(typeId, status, Constant.TYPE_ANH_CTY_THE_CHAP_OTO, "Ảnh công ty vay thế chấp oto "));

        }
    }

    @SuppressLint("NewApi")

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        onResult(requestCode, resultCode, intent, postImage);
    }

    public void onResult(int requestCode, int resultCode, Intent intent, final PostImage data) {
        if (requestCode == PostContractActivity.RESULT_LOAD_IMAGE || requestCode == PostContractActivity.RESULT_LOAD_IMAGE_FROM_CAMERA
                && (resultCode == 1 || resultCode == -1)) {
            try {
                if (intent != null) {
                    Uri selectedImage = intent.getData();

                    String[] filePathColumn = {MediaStore.Images.Media.DATA, MediaStore.Files.FileColumns.DATA};

                    String path = getRealPathFromURI(selectedImage);

                    Cursor cursor = mActivity.getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    Log.e(TAG, "picturePath: " + picturePath + "/" + data.getTypePost());
                    handlerDataPicture(picturePath, data.getTypePost());
                    cursor.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void handlerDataPicture(final String srcImage, final int type) {
        final LoadingDialog dialog = new LoadingDialog(this,"Đang upload ảnh");
        dialog.showDialog();
        ArrayList<String> listImage = new ArrayList<>();
        listImage.add(srcImage);
        String typePost = postImage.getTypePost() + "";
        Log.e(TAG, "handlerDataPicture typePost: " + typePost);
        MultipartRequestHelper multipartRequestHelper = new MultipartRequestHelper(typePost,
                MultipartRequestHelper.TYPE_SEND_IMAGE, customerId,loanCreditId,this, Config.BASE_SERVER_URL + ApiService.API_UPLOAD_IMAGE, listImage,
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.showAlertDialog(PostContractActivity.this,"Upload ảnh lỗi", "Hãy kiểm tra lại kết nối mạng của bạn.");
            }
        }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    dialog.dismissDialog();
                    JSONObject jsonObject = new JSONObject(response);
                    int status = jsonObject.getInt("Status");
                    int code = jsonObject.getInt("Code");
                    String message = jsonObject.getString("Message") + "@" + jsonObject.getInt("Status") + "@" + jsonObject.getInt("Code");
                    if (!Common.checkToken(message, mActivity)) {
                        return;
                    }
                    if (status == 1 && code == 1) {
                        addTypePostSuccess(postImage.getTypePost());
                        Log.e(TAG, "handlerDataPicture: type" + type);
                        if (postImageAdapter.getMapListImage().get(type).size() > 0) {
                            for (int i = 0; i < postImageAdapter.getMapListImage().get(type).size(); i++) {
                                if (postImageAdapter.getMapListImage().get(type).get(i).getSrcImgLocal() != null &&
                                        postImageAdapter.getMapListImage().get(type).get(i).getSrcImgLocal().equals(srcImage)) {
                                    Toast.makeText(mActivity, "Ảnh đã được chọn rồi, hãy chọn lại!", Toast.LENGTH_LONG).show();
                                    return;
                                }
                            }
                            JSONArray jsArr = jsonObject.getJSONArray("Data");
                            JSONObject jsonData = jsArr.getJSONObject(0);
                            String idImage = jsonData.getString("ID");
                            String filePath = jsonData.getString("FilePath");

                            LibraryImage libraryImage = new LibraryImage();
                            libraryImage.setSrcImgLocal(srcImage);
                            libraryImage.setUrlImg(filePath);
                            libraryImage.setID(idImage);
                            postImageAdapter.getMapListImage().get(type).add(libraryImage);
                            postImageAdapter.notifyDataSetChangeImageChose(type);
                        } else {
                            LibraryImage libraryImage = new LibraryImage();
                            libraryImage.setSrcImgLocal(srcImage);
                            postImageAdapter.getMapListImage().get(type).add(libraryImage);
                            postImageAdapter.notifyDataSetChangeImageChose(type);
                        }
                    } else {
                        DialogUtils.showAlertDialog(PostContractActivity.this,"Thông báo","Upload ảnh lỗi.");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        multipartRequestHelper.setRetryPolicy(new DefaultRetryPolicy(50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Common.addToRequestQueue(multipartRequestHelper, this);
    }

    private void addTypePostSuccess(int typePost) {
        if (!listTypePost.contains(typePost + "")) {
            listTypePost.add(typePost + "");
            Log.e(TAG, "addTypePostSuccess addTypePostSuccess: " + listTypePost.size());
        }
    }

    private boolean isValidPostImage() {
        if (listTypePost.size() > 0 && listTypePost.size() == listImage.size()) {
            return true;
        }
        DialogUtils.showAlertDialog(PostContractActivity.this, "Cảnh báo", "Mỗi loại ảnh cần đẩy ít nhất 1 ảnh.");
        return false;
    }

    @Override
    public void onClick(View v) {

    }
}
