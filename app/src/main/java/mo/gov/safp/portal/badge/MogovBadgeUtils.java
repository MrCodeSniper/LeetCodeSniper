package mo.gov.safp.portal.badge;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

import mo.gov.safp.portal.MainActivity;
import mo.gov.safp.portal.shortcutbadger.ShortcutBadger;

/**
 * @author CH
 * @date 2021/9/24
 */
public class MogovBadgeUtils {

    public static void set(Activity activity,int badgeCount){
        if (Build.MANUFACTURER.equalsIgnoreCase("samsung") && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            activity.startService(new Intent(activity, BadgeIntentService.class).putExtra("badgeCount", badgeCount));
        }
        ShortcutBadger.applyCount(activity, badgeCount);
    }

}
