package mo.gov.safp.portal.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import androidx.annotation.RequiresApi;
import androidx.annotation.StringDef;



import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;

public class LanguageUtil {

    public static final String PRE_CHINESE_SIMPLIFIED = "zh-CN";//简体中文
    public static final String PRE_CHINESE_TRADITIONAL = "zh-MO";//繁体中文

    public static final String CHINESE_SIMPLIFIED = "zh-Hans";//简体中文
    public static final String ENGLISH = "en";//英文
    public static final String CHINESE_TRADITIONAL = "zh-Hant";//繁体中文
    public static final String PORTUGAL = "pt";//葡文

    @StringDef({CHINESE_SIMPLIFIED, ENGLISH, CHINESE_TRADITIONAL, PORTUGAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LanguageType{}

    private static final String TAG = LanguageUtil.class.getSimpleName();

    /**
     * 适配7.0以上的语言国际化
     *
     * @param context 上下文环境
     * @param language 语言字符串
     * @return 更新过Locale的上下文环境
     */
    public static Context attachBaseContext(Context context, String language) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, language);
        } else {
            return context;
        }
    }
    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, String language) {
        Resources resources = context.getResources();
        Locale locale = getLocaleByLanguage(language);
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        configuration.setLocales(new LocaleList(locale));
        return context.createConfigurationContext(configuration);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Locale getLocaleByLanguage(@LanguageType String language) {
        Locale locale = Locale.SIMPLIFIED_CHINESE;
        switch (language) {
            case CHINESE_SIMPLIFIED:
            case PRE_CHINESE_SIMPLIFIED:
                locale = Locale.SIMPLIFIED_CHINESE;
                break;
            case ENGLISH:
                locale = Locale.ENGLISH;
                break;
            case CHINESE_TRADITIONAL:
            case PRE_CHINESE_TRADITIONAL:
                locale = Locale.TRADITIONAL_CHINESE;
                break;
            case PORTUGAL:
                locale = new Locale("pt", "");
                break;
        }
        return locale;
    }

    /**
     * 改变应用语言
     *
     * @param context 上下文环境
     * @param newLanguage 想要切换的语言类型 比如 "en" ,"zh"
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void changeAppLanguage(Context context, String newLanguage) {
        if (TextUtils.isEmpty(newLanguage)) {
            return;
        }
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        //获取想要切换的语言类型
        Locale locale = getLocaleByLanguage(newLanguage);
        configuration.setLocale(locale);
        // updateConfiguration
        DisplayMetrics dm = resources.getDisplayMetrics();
        resources.updateConfiguration(configuration, dm);
    }

    /**
     * 获取映射语言码
     *  简体中文 = "zh-hans",
     *   English = "en",
     *   繁体中文 = "zh-hant",
     *   葡语 = "pt",
     * @param language
     * @return
     */
    public static String getDisplayLanguage(@LanguageType String language) {
        String displayLanguage = CHINESE_TRADITIONAL;
        if (TextUtils.equals(language, Locale.CHINESE.getLanguage())
            || TextUtils.equals(language, Locale.SIMPLIFIED_CHINESE.getLanguage())) {
            displayLanguage = CHINESE_SIMPLIFIED;
        } else if (TextUtils.equals(language, Locale.TRADITIONAL_CHINESE.getLanguage())) {
            displayLanguage = CHINESE_TRADITIONAL;
        } else if (TextUtils.equals(language, Locale.ENGLISH.getLanguage())) {
            displayLanguage = ENGLISH;
        } else if (TextUtils.equals(language, PORTUGAL)) {
            displayLanguage = PORTUGAL;
        }
        return displayLanguage;
    }

    /**
     * 判断传入的语言码是否合法
     * @param language
     * @return
     */
    public static boolean isLanguageValid(@LanguageType String language) {
        return TextUtils.equals(language, CHINESE_SIMPLIFIED)
                || TextUtils.equals(language, CHINESE_TRADITIONAL)
                || TextUtils.equals(language, ENGLISH)
                || TextUtils.equals(language, PORTUGAL);
    }

    /**
     * 更新应用语言（核心）
     * @param context
     * @param locale
     */
    public static void setAppLanguage(Context context, Locale locale) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        //Android 7.0以上的方法
        if (Build.VERSION.SDK_INT >= 24) {
            configuration.setLocale(locale);
            configuration.setLocales(new LocaleList(locale));
            context.createConfigurationContext(configuration);
            //实测，updateConfiguration这个方法虽然很多博主说是版本不适用
            //但是我的生产环境androidX+Android Q环境下，必须加上这一句，才可以通过重启App来切换语言
            resources.updateConfiguration(configuration,metrics);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            //Android 4.1 以上方法
            configuration.setLocale(locale);
            resources.updateConfiguration(configuration,metrics);
        } else {
            configuration.locale = locale;
            resources.updateConfiguration(configuration,metrics);
        }
    }

}
