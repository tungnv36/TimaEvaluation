package vn.tima.timainspection.Util;

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import vn.tima.timainspection.R;

/**
 * Created by anhnh-dev on 30/11/2017.
 */

public class ImageLoaderHelper {
    private DisplayImageOptions displayImageOptions;
    Context context;
    public  ImageLoaderHelper mInstance;

    public ImageLoaderHelper(Context context) {
        mInstance = this;
        this.context = context;
        initDisplayImageOptions();
    }

    public void initDisplayImageOptions() {
        displayImageOptions = new DisplayImageOptions.Builder()
                .showImageOnFail(R.mipmap.ic_no_image)
                .cacheInMemory(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new SimpleBitmapDisplayer())
                .build();
        configureDefaultImageLoader(context);
    }

    public DisplayImageOptions getDisplayImageOptions() {
        return displayImageOptions;
    }

    public  void configureDefaultImageLoader(Context context) {
        ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory().discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.FIFO).build();
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(imageLoaderConfiguration);
    }
}
