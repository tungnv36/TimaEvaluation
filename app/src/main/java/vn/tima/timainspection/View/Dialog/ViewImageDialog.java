package vn.tima.timainspection.View.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import vn.tima.timainspection.Model.Entity.LibraryImage;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.DialogUtils;
import vn.tima.timainspection.View.PostContract.ImageSlideAdapter;

/**
 * Created by DinhAnh on 8/1/2016.
 */
public class ViewImageDialog implements View.OnClickListener {
    private Dialog mDialog;
    private Context mContext;
    private String urlImage;
    private ImageView ivViewImage;
    private ProgressBar mProgress;
    private RelativeLayout re_show;
    private Button btnCancel;
    private ViewPager vpImage;
    private ImageSlideAdapter adapter;
    private ArrayList<LibraryImage> listImages;
    private int position;

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public void setListImages(ArrayList<LibraryImage> listImages, int position) {
        this.listImages = listImages;
        this.position = position;
    }

    public interface OnButtonClick {
        public void onBtAcceptClick();

        public void onBtExitClick();
    }

    public OnButtonClick listener;

    public OnButtonClick getListener() {
        return listener;
    }

    public void setListener(OnButtonClick mListener) {
        this.listener = mListener;
    }

    public ViewImageDialog(Context mContext, ArrayList<LibraryImage> listImages, int position) {
        this.mContext = mContext;
        this.listImages = listImages;
        this.position = position;
        initDialog();
        initVew();
        initSlideshow();
    }

    public ViewImageDialog(Context mContext) {
        this.mContext = mContext;
        initDialog();
        initVew();
        initSlideshow();
    }

    private void initSlideshow() {
        adapter = new ImageSlideAdapter(mContext, listImages);
        vpImage.setAdapter(adapter);
        vpImage.setCurrentItem(position);
    }

    private void initVew() {
        vpImage = (ViewPager) mDialog.findViewById(R.id.vpImage);
        btnCancel = (Button) mDialog.findViewById(R.id.btn_cancel);
        mProgress = (ProgressBar) mDialog.findViewById(R.id.progress_loadimage);
        re_show = (RelativeLayout) mDialog.findViewById(R.id.re_show);
        btnCancel.setOnClickListener(this);
    }


    private void initDialog() {
        mDialog = new Dialog(mContext, R.style.ProgressHUD);
        mDialog.setContentView(R.layout.dialog_view_image);
        DialogUtils.setFullDialog(mDialog);

        setCancelable(true);
    }

    public void setCancelable(boolean b) {
        mDialog.setCancelable(b);
    }

    public void showDialog() {
        if (mDialog != null) {
            mDialog.show();
        }
    }

    public void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mContext = null;
            mDialog = null;
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_cancel:
                dismissDialog();
                break;
        }
    }
}
