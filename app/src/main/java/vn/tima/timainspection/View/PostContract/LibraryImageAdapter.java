package vn.tima.timainspection.View.PostContract;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;

import java.util.ArrayList;

import vn.tima.timainspection.Model.Entity.LibraryImage;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Config;
import vn.tima.timainspection.Util.ImageLoaderHelper;

/**
 * Created by DinhAnh on 11/7/2016.
 */

public class LibraryImageAdapter extends RecyclerView.Adapter<LibraryImageAdapter.MyViewHolder> {
    public static final String TAG = LibraryImageAdapter.class.getSimpleName();
    private ArrayList<LibraryImage> listImage = new ArrayList<>();
    private Context context;
    private boolean isShowDelete = false;

    public void setShowDelete(boolean showDelete) {
        isShowDelete = showDelete;
    }

    public void setListImage(ArrayList<LibraryImage> listImage) {
        this.listImage = listImage;
    }

    public interface OnclickListener {
        public void onClick(int position);

        public void onButtonDeleteListener(int position);
    }

    public OnclickListener listener;

    public void setListener(OnclickListener listener) {
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_library_image, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (listImage.size() > 0) {
            String imgPath = "";
            setOnclickItem(holder.ivImage, position);
            if (listImage.get(position).getSrcImgLocal() != null) {
                imgPath = listImage.get(position).getSrcImgLocal();
                ImageLoader.getInstance().displayImage(ImageDownloader.Scheme.FILE.wrap(imgPath), holder.ivImage, new ImageLoaderHelper(context).getDisplayImageOptions());
            } else {
                imgPath = listImage.get(position).getUrlImg();
                ImageLoader.getInstance().displayImage(Config.BASE_SERVER_IMAGE_URL + imgPath, holder.ivImage, new ImageLoaderHelper(context) .getDisplayImageOptions());
            }
            holder.progress_loadimage.setVisibility(View.GONE);
            initButtonDelete(holder.btnDelete, position);
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
        ProgressBar progress_loadimage;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
            btnDelete = (Button) itemView.findViewById(R.id.btn_close);
            progress_loadimage = (ProgressBar) itemView.findViewById(R.id.progress_loadimage);
        }
    }
}
