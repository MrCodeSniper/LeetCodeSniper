package mo.gov.safp.portal.lottie;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;
import com.alipay.mobile.framework.app.ui.BaseActivity;

import mo.gov.safp.portal.R;

/**
 * @author CH
 * @date 2021/10/23
 */
public class LottieActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        setContentView(R.layout.activity_lottie);
        LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.lottieView);
//        lottieAnimationView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        lottieAnimationView.setImageAssetsFolder("/images");


        Log.d("chenhong","是否硬件加速:"+lottieAnimationView.isHardwareAccelerated());
        Log.d("chenhong","是否硬件加速2:"+hasHardwareAcceleration(this));
    }

    @Override
    protected void onDestroy() {
//        LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.lottieView);
//        lottieAnimationView.setLayerType(View.LAYER_TYPE_NONE, null);
        super.onDestroy();
    }

    public static boolean hasHardwareAcceleration(Activity activity) {
        // Has HW acceleration been enabled manually in the current window?
        Window window = activity.getWindow();
        if (window != null) {
            if ((window.getAttributes().flags
                    & WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED) != 0) {
                return true;
            }
        }

        // Has HW acceleration been enabled in the manifest?
        try {
            ActivityInfo info = activity.getPackageManager().getActivityInfo(
                    activity.getComponentName(), 0);
            if ((info.flags & ActivityInfo.FLAG_HARDWARE_ACCELERATED) != 0) {
                return true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("Chrome", "getActivityInfo(self) should not fail");
        }

        return false;
    }
}
