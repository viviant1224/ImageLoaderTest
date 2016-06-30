package constraintlayout.test.test.viviant.imageloadertest.util;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.File;

import constraintlayout.test.test.viviant.imageloadertest.R;

/**
 * 作者：viviant on 2016/6/30 09:22
 * 描述：
 */
public class ImageLoaderUtil {

    private static final String PICTURE_CACHE_DIR = "picture";
    private static String TAG = "ImageLoaderUtil";

    public static void init(Context context) {
        File cacheDir = getCacheDirectory(context);  //缓存文件夹路径

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions 内存缓存文件的最大长宽
                .diskCacheExtraOptions(480, 800, null)  // 本地缓存的详细信息(缓存的最大长宽)，最好不要设置这个
//                .taskExecutor("")
//        .taskExecutorForCachedImages("")
        .threadPoolSize(3) // default  线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2) // default 设置当前线程的优先级
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024)) //可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)  // 内存缓存的最大值
                .memoryCacheSizePercentage(13) // default
                .diskCache(new UnlimitedDiscCache(cacheDir)) // default 可以自定义缓存路径
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb sd卡(本地)缓存的最大值
                .diskCacheFileCount(100)  // 可以缓存的文件数量
                // default为使用HASHCODE对UIL进行加密命名， 还可以用MD5(new Md5FileNameGenerator())加密
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .imageDownloader(new BaseImageDownloader(context)) // default
                .imageDecoder(new BaseImageDecoder(true)) // l
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs() // 打印debug log
                .build(); //开始构建
        ImageLoader.getInstance().init(config);
    }

    /**
     * 获取缓存文件
     *
     * @param context
     * @return
     */
    public final static File getCacheDirectory(Context context) {
        String cacheDir = SystemUtility.getAppCachePath();
        return createDir(cacheDir + PICTURE_CACHE_DIR);
    }

    private final static File createDir(String dir) {
        File appCacheDir = new File(dir);
        if (!appCacheDir.exists()) {
            if (!appCacheDir.mkdirs()) {
                Log.i(TAG, "createDir# Unable to create external cache directory");
                return null;
            }
        }
        return appCacheDir;
    }

    /**
     *显示出错，替换的图片
     * @return
     */
    public static DisplayImageOptions getAvatarDisplayOptions() {
        DisplayImageOptions avatarOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.error)
                .showImageForEmptyUri(R.drawable.error)
                .showImageOnFail(R.drawable.error)
                .cacheInMemory(true).cacheOnDisk(true).build();
        return avatarOptions;
    }

    /**
     * 显示图片
     *
     * @param url
     * @param imageView
     * @param options
     */
    public static void displayImage(String url, ImageView imageView,
                                    DisplayImageOptions options) {
        ImageLoader.getInstance().displayImage(url, imageView, options);
    }

}
