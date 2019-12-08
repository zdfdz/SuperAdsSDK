package AdsDataInfo.core;

import android.content.Context;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.os.Build;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.core.app.ActivityCompat;

import AdsDataInfo.utils.Constants;
import AdsDataInfo.utils.FileUtils;
import AdsDataInfo.utils.OKManager;

/**
 * @author ZhengDeFeng
 * @description: 此类用于获取配置信息
 * @date :2019/12/5 23:56
 */
public class Mediator {
    private static Context mContext;
    private static String TAG = Mediator.class.getSimpleName();

    // 获取配置信息
    public static void getConfigInfo(Context context) {
        mContext = context;
        // 获取线上配置信息
        OKManager.getInstance().asyncJsonStringByURL(Constants.CONFIG_PATH, new OKManager.Func1() {
            @Override
            public void onResponse(String result) {
                // config写入本地文件
                if (FileUtils.saveToFile(result)) {
                    Log.i(TAG, "save file success");
                }
            }
        });
    }
}
