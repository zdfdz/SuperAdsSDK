package AdsDataInfo.core;

import android.content.Context;

/**
 * @author ZhengDeFeng
 * @description:用于SDK的初始操作
 * @date :2019/12/8 20:19
 */
public class SuperAdsSDK {
    private static volatile SuperAdsSDK superAdsSDK;

    public static SuperAdsSDK getInstance() {
        if (superAdsSDK == null) {
            synchronized (Mediator.class) {
                if (superAdsSDK == null) {
                    superAdsSDK = new SuperAdsSDK();
                    return superAdsSDK;
                }
            }
        }
        return superAdsSDK;
    }

    // 初始化 sdk
    public void initSDK(Context context) {
        // 重新获取配置信息
        if (superAdsSDK != null) {
            Mediator.getConfigInfo(context);
        }
    }

}
