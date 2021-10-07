package mo.gov.safp.portal.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;


public final class SharedPreferencesUtils {

    private final static String MAIN_PREFERENCES_KEY = "mo.gov.smart.common.pref_info";

    static final String SETTING_APP_PERMISSION = "app_permission"; //是否检查过权限
    static final String SETTING_APP_MOVED = "app_moved"; //app是否移动过

    static final String SETTING_APP_UPGRADE = "app_upgrade_info"; //app更新信息

    static final String ACTIVE_MOBILE = "login_mobile";  //验证手机
    static final String ACTIVE_MOBILE_JWT = "login_mobile_jwt"; //手机获取的token

    private SharedPreferencesUtils(){
    }

    //首页app 是否进行过移动
    public static boolean isMovedApp(Context context) {
        boolean moved = (boolean) getValue(context, SETTING_APP_MOVED, false);
        return moved;
    }

    // 设置app进行过移动
    public static void setMovedApp(Context context) {
        putValue(context, SETTING_APP_MOVED, true);
    }

    // 是否已经对app权限进行询问过了
    public static boolean isCheckAppPermission(Context context) {
        boolean checked = (boolean) getValue(context, SETTING_APP_PERMISSION, false);
        if(!checked){
            putValue(context, SETTING_APP_PERMISSION, true);
        }
        return checked;
    }

    public static String getValue(Context context, String key) {
        SharedPreferences sp = getSharedPreferences(context);
        return (sp != null) ? sp.getString(key, "") : "";
    }

    public static boolean getBooleanValue(Context context, String key) {
        SharedPreferences sp = getSharedPreferences(context);
        return (sp != null) ? sp.getBoolean(key, false) : false;
    }

    /**
     * 清除所有数据
     *
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences sp = getSharedPreferences(context);
        if (sp == null) {
            return;
        }

        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        apply(editor);
    }

    /**
     * 得到保存数据的方法，根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object getValue(Context context, String key, Object defaultObject) {
        SharedPreferences sp = getSharedPreferences(context);
        if (sp == null) {
            return defaultObject;
        }

        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        }
        return defaultObject;
    }

    /**
     * 保存数据的方法，根据类型调用不同的保存方法
     *
     * @param context
     * @param key
     * @param valueObject
     * @return
     */
    public static boolean putValue(Context context, String key, Object valueObject) {
        SharedPreferences sp = getSharedPreferences(context);
        if (sp == null) {
            return false;
        }
        SharedPreferences.Editor editor = sp.edit();

        if (valueObject instanceof String) {
            editor.putString(key, (String) valueObject);
        } else if (valueObject instanceof Integer) {
            editor.putInt(key, (Integer) valueObject);
        } else if (valueObject instanceof Long) {
            editor.putLong(key, (Long) valueObject);
        } else if (valueObject instanceof Boolean) {
            editor.putBoolean(key, (Boolean) valueObject);
        } else if (valueObject instanceof Float) {
            editor.putFloat(key, (Float) valueObject);
        } else {
            editor.putString(key, valueObject.toString());
        }

        apply(editor);

        return true;
    }

    private static void remove(Context context, String key) {
        apply(getEditor(context).remove(key));
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        return getSharedPreferences(context).edit();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(MAIN_PREFERENCES_KEY, Context.MODE_PRIVATE);
    }

    private static void apply(@NonNull SharedPreferences.Editor editor) {
        try {
            editor.apply();
        } catch (AbstractMethodError unused) {
            editor.commit();
        }
    }
}