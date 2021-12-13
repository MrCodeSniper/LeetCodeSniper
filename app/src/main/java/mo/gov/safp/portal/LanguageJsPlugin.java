package mo.gov.safp.portal;

import com.alipay.mobile.h5container.api.H5BridgeContext;
import com.alipay.mobile.h5container.api.H5Event;
import com.alipay.mobile.h5container.api.H5EventFilter;
import com.alipay.mobile.h5container.api.H5SimplePlugin;
import com.mpaas.nebula.adapter.api.MPNebula;

import mo.gov.safp.portal.web.H5PluginManagerKt;
import mo.gov.safp.portal.web.H5Response;
import mo.gov.safp.portal.web.Language;

import static mo.gov.safp.portal.ConstantsKt.EMPTY_STRING;
import static mo.gov.safp.portal.ConstantsKt.SCOPE;

/**
 * @author CH
 * @date 2021/12/10
 */
public class LanguageJsPlugin extends H5SimplePlugin {

    public static final String PRE_CHINESE_SIMPLIFIED = "zh-CN";//简体中文
    public static final String PRE_CHINESE_TRADITIONAL = "zh-MO";//繁体中文

    public static final String CHINESE_SIMPLIFIED = "zh-Hans";//简体中文
    public static final String ENGLISH = "en";//英文
    public static final String CHINESE_TRADITIONAL = "zh-Hant";//繁体中文
    public static final String PORTUGAL = "pt";//葡文

    public static final String JS_ON_CHANGE_LANGUAGE = "onChangeLanguage";
    private static final String JS_GET_LANGUAGE = "getLanguage";
    private static final String JS_SET_LANGUAGE = "setLanguage";
    private static final String TAG = "LanguageJsPlugin";


    @Override
    public void onPrepare(H5EventFilter filter) {
        super.onPrepare(filter);
        filter.addAction(JS_GET_LANGUAGE);
        filter.addAction(JS_SET_LANGUAGE);

    }

    /**
     * 注册语言相关接口
     */
    public static void registerLanguagePlugin() {
        MPNebula.registerH5Plugin(
                LanguageJsPlugin.class.getName(),
                EMPTY_STRING,
                SCOPE,
                new String[]{JS_GET_LANGUAGE,
                        JS_SET_LANGUAGE}
        );
    }

    @Override
    public boolean handleEvent(H5Event event, H5BridgeContext context) {
        String action = event.getAction();
        if (JS_GET_LANGUAGE.equalsIgnoreCase(action)) {
            Language language = new Language();
            language.setLanguage(CHINESE_TRADITIONAL);
            H5Response h5Response = new H5Response();
            h5Response.success = true;
            h5Response.data = language;
            H5PluginManagerKt.callbackToH5(context, h5Response);
            return true;
        }
        return super.handleEvent(event, context);
    }
}
