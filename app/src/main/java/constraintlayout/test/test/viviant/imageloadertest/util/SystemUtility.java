package constraintlayout.test.test.viviant.imageloadertest.util;

import android.os.Environment;

import java.io.File;

import constraintlayout.test.test.viviant.imageloadertest.R;

/**
 * 作者：viviant on 2016/6/30 09:29
 * 描述：
 */
public class SystemUtility {

    public static String getAppCachePath() {
        if (SystemUtility.hasSDCard() && sdcardCanWrite()) {
            return SystemUtility.getSdcardPath() + File.separator
                    + "imageloadertest" + File.separator;
        } else {
            return getAppCachePath() + File.separator;
        }
    }

    public static boolean hasSDCard() {
        boolean mHasSDcard = false;
        if (Environment.MEDIA_MOUNTED.endsWith(Environment.getExternalStorageState())) {
            mHasSDcard = true;
        } else {
            mHasSDcard = false;
        }

        return mHasSDcard;
    }

    public static String getSdcardPath() {
        if (hasSDCard())
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        return "/sdcard/";
    }

    private static boolean sdcardCanWrite() {
        return Environment.getExternalStorageDirectory().canWrite();
    }
}
