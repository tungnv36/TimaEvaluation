package vn.tima.timainspection.View.ContractVouchers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import vn.tima.timainspection.Model.Entity.LibraryImage;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Config;
import vn.tima.timainspection.Util.Constant;
import vn.tima.timainspection.Util.ImageLoaderHelper;

/**
 * Created by DinhAnh on 11/7/2016.
 */

public class LibraryImageVoucherAdapter extends RecyclerView.Adapter<LibraryImageVoucherAdapter.MyViewHolder> {
    public static final String TAG = LibraryImageVoucherAdapter.class.getSimpleName();
    private ArrayList<LibraryImage> listImage = new ArrayList<>();
    private Context context;
    private boolean isShowDelete = false;
    private boolean isShowTitle = false;
    private boolean isShowUserName = false;

    public void setShowUserName(boolean showUserName) {
        isShowUserName = showUserName;
    }

    public void setShowDelete(boolean showDelete) {
        isShowDelete = showDelete;
    }

    public void setShowTitle(boolean showTitle) {
        isShowTitle = showTitle;
    }

    public void setListImage(ArrayList<LibraryImage> listImage) {
        this.listImage = listImage;
    }

    public interface OnclickListener {
        void onClick(int position);

        void onButtonDeleteListener(int position);
    }

    public OnclickListener listener;

    public void setListener(OnclickListener listener) {
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_library_image, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (listImage.size() > 0) {
            String imgPath = Config.BASE_SERVER_IMAGE_URL + listImage.get(position).getUrlImg();
            setOnclickItem(holder.ivImage, position);
            ImageLoader.getInstance().displayImage(imgPath, holder.ivImage, new ImageLoaderHelper(context).getDisplayImageOptions());
            initButtonDelete(holder.btnDelete, position);
            initTextViewTitle(holder.tv_title, position);
            initTextViewUsername(holder.tv_username, position);
        }
    }

    private void initButtonDelete(Button btnDelete, final int position) {
        if (isShowDelete) {
            btnDelete.setVisibility(View.VISIBLE);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onButtonDeleteListener(position);
                    }
                }
            });
        } else {
            btnDelete.setVisibility(View.GONE);
        }
    }

    private void initTextViewTitle(TextView textView, final int position) {
        if (isShowTitle) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(getTitle(position));
        } else {
            textView.setVisibility(View.GONE);
        }
    }

    private void initTextViewUsername(TextView textView, final int position) {
        if (isShowUserName) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(listImage.get(position).getUsername());
        } else {
            textView.setVisibility(View.GONE);
        }
    }


    private String getTitle(int position) {
        int typeId = Integer.parseInt(listImage.get(position).getTypeId());
        switch (typeId) {
            case Constant.TYPE_1_POST_ANH_CMNN:
                return "Ảnh CMND";
            case Constant.TYPE_1_POST_ANH_SO_HO_KHAU:
                return "Ảnh sổ hộ khẩu";
            case Constant.TYPE_1_POST_ANH_CHUNG_MINH_THU_NHAP:
                return "Ảnh chứng minh thu nhập";
            case Constant.TYPE_1_POST_ANH_MAT:
                return "Ảnh mặt";
            case Constant.TYPE_1_POST_ANH_NHA:
                return "Ảnh nhà";
            case Constant.TYPE_1_POST_ANH_CONG_TY:
                return "Ảnh công ty";
            case Constant.TYPE_1_POST_ANH_HD_TU_VAN:
                return "Ảnh HD tư vấn";
            case Constant.TYPE_1_POST_ANH_HD_CAM_CO:
                return "Ảnh HD cầm cố";
            case Constant.TYPE_1_POST_ANH_HD_GUI_GIU_TAI_SAN:
                return "Ảnh HD gửi giữ tài sản";
            case Constant.TYPE_1_POST_ANH_HD_THONG_TIN_CHUNG_KHOAN_VAY:
                return "Ảnh HD thông tin chứng thực khoản vay";
            case Constant.TYPE_2_POST_ANH_CMT:
                return "Ảnh chứng minh thư";
            case Constant.TYPE_2_POST_ANH_GIAY_DK_XE:
                return "Ảnh giấy Đăng Ký";
            case Constant.TYPE_2_POST_ANH_MAT:
                return "Ảnh mặt";
            case Constant.TYPE_2_POST_ANH_NHA:
                return "Ảnh nhà";
            case Constant.TYPE_2_POST_HD_MUA_BAN:
                return "Ảnh HD mua bán";
            case Constant.TYPE_2_POST_HD_THUE_XE:
                return "Ảnh HD thuê xe";
            case Constant.TYPE_2_POST_HD_TU_VAN:
                return "Ảnh HD tư vấn";
            default:
                return "";
        }
    }

    private void setOnclickItem(View view, final int position) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listImage.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        Button btnDelete;
        TextView tv_title;
        TextView tv_username;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
            btnDelete = (Button) itemView.findViewById(R.id.btn_close);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_username = (TextView) itemView.findViewById(R.id.tv_username);
        }
    }
}
