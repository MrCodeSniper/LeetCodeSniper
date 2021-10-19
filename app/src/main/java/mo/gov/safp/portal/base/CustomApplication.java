package mo.gov.safp.portal.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.alipay.mobile.framework.quinoxless.IInitCallback;
import com.alipay.mobile.framework.quinoxless.QuinoxlessFramework;
import com.alipay.mobile.nebula.provider.H5AppCenterPresetProvider;
import com.alipay.mobile.nebula.provider.H5PublicRsaProvider;
import com.alipay.mobile.nebula.util.H5Utils;
import com.mpaas.mps.adapter.api.MPPush;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;

import java.util.HashMap;

import mo.gov.safp.portal.H5AppCenterPresetProviderImpl;
import mo.gov.safp.portal.H5RsaProviderImpl;
import mo.gov.safp.portal.PresetAmrPipeline;

public class CustomApplication extends BaseApplication implements LifecycleObserver {

    private static CustomApplication instance;
    private static boolean isAppVisible; //是否在前台运行

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MPPush.setup(this);
        QuinoxlessFramework.setup(this, new IInitCallback() {
            @Override
            public void onPostInit() {
                // 预置离线包，包括普通离线包和公共资源包
                new Thread(new PresetAmrPipeline()).start();
                // 公共资源包返回 appid
                H5Utils.setProvider(H5AppCenterPresetProvider.class.getName(), new H5AppCenterPresetProviderImpl());
                H5Utils.setProvider(H5PublicRsaProvider.class.getName(), new H5RsaProviderImpl());
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        // 在调用TBS初始化、创建WebView之前进行如下配置
        QbSdk.setDownloadWithoutWifi(true);
        HashMap map = new HashMap();
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE, true);
        QbSdk.initTbsSettings(map);
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                Log.e("snow", "========onCoreInitFinished===");
            }

            @Override
            public void onViewInitFinished(boolean b) {
                //加载x5内核成功返回值为true，否则返回false，加载失败会调用系统的webview
                Log.e("snow", "x5初始化结果====" + b);
            }
        });
        QuinoxlessFramework.init();
    }


    public static boolean isApplicationVisible() {
        return isAppVisible;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onAppForegrounded() {
        // AppDemo 在前台运行
        isAppVisible = true;

        Log.v("ActivityLifecycle", "onAppForegrounded");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onAppBackgrounded() {
        //AppDemo 在后台运行
        isAppVisible = false;

        Log.v("ActivityLifecycle", "onAppBackgrounded");

    }

    public static CustomApplication getInstance() {
        return instance;
    }






}
