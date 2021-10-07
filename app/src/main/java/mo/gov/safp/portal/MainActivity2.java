package mo.gov.safp.portal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import mo.gov.safp.portal.badge.MogovBadgeUtils;
import mo.gov.safp.portal.base.FilePreviewActivity;
import mo.gov.safp.portal.base.FilePreviewFragment;
import mo.gov.safp.portal.base.RouteConstant;
import mo.gov.safp.portal.shortcutbadger.ShortcutBadger;
import mo.gov.safp.portal.softtoken.SoftTokenManager;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_softtoken);
    }

    public void saveSoftToken(View view) {
//        MogovBadgeUtils.set(this,10);
//        BadgeUtils.setCount(10,this);
//        BadgeUtils.setCount(10,this);

//        SoftTokenManager.getInstances().addAccount("abcdefg","hijklmn");

        Intent intent = new Intent(this, FilePreviewActivity.class);
        String filePath = getExternalFilesDir("").getAbsolutePath()+"/a.pdf";
        intent.putExtra(RouteConstant.IntentParam.LOCAL_FILE_PATH,filePath);
        startActivity(intent);
    }


    public void getSoftToken(View view) {
        String secret = SoftTokenManager.getInstances().getSoftToken("safpuser20");
        Log.d("chenhong","token:"+secret);
    }

    /**
     * 在三星手机上显示桌面徽标
     *
     * @param context 上下文
     * @param num 显示的消息数量，整数
     */
    private static void samsungShortCut(Context context, int num) {
        String launcherClassName = getLauncherComponentName(context).getClassName();
        if (launcherClassName == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", num);
        intent.putExtra("badge_count_package_name", context.getPackageName());
        intent.putExtra("badge_count_class_name", launcherClassName);

        context.sendBroadcast(intent);
        Toast.makeText(context, "三星手机," + "广播已经发送", Toast.LENGTH_LONG).show();
    }

    private static String getLauncherClassName(Context context) {
        ComponentName launchComponent = getLauncherComponentName(context);
        if (launchComponent == null) {
            return "";
        } else {
            return launchComponent.getClassName();
        }
    }

    private static ComponentName getLauncherComponentName(Context context) {
        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(context
                .getPackageName());
        if (launchIntent != null) {
            return launchIntent.getComponent();
        } else {
            return null;
        }
    }
}