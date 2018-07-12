package vn.tima.timainspection.View.PostContract;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import vn.tima.timainspection.Model.Entity.LibraryImage;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Config;
import vn.tima.timainspection.Util.ImageLoaderHelper;
import vn.tima.timainspection.Util.TouchImageView;

/**
 * Created by hoangngoc on 8/22/16.
 */

public class ImageSlideAdapter extends PagerAdapter {
    public static final String TAG = ImageSlideAdapter.class.getSimpleName();
    Context context;
    private ArrayList<LibraryImage> listImages;
    LayoutInflater inflater;

    public ImageSlideAdapter(Context context, ArrayList<LibraryImage> listImages) {
        this.context = context;
        this.listImages = listImages;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = inflater.inflate(R.layout.row_image, container, false);
        TouchImageView imageView = (TouchImageView) view.findViewById(R.id.iv_photo);
        String imgPath = Config.BASE_SERVER_IMAGE_URL + listImages.get(position).getUrlImg();
        ImageLoader.getInstance().displayImage(imgPath, imageView, new ImageLoaderHelper(context).getDisplayImageOptions());
        container.addView(view);
        return view;
    }
}
