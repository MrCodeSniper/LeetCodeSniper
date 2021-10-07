package mo.gov.safp.portal.base;



/**
 * 路由常量
 */
public interface RouteConstant {

    String STG_PUBLIC_SERVICE_HOST = "app-res-uat.publicservice.gov.mo";

    String ANDROID_NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    String APP_OFFLINE_ID = "11111111";

    String COMMON_APP_RESOURCE_ID = "00000000";

    String APP_INQUIRY_OFFLINE_ID = "22222222";

    String APP_INQUIRY_OFFLINE_VERSION = "1.0.0.8";

    String COMMON_APP_RESOURCE_VERSION = "1.0.0.0";

    String APP_OFFLINE_VERSION = "1.0.0.2";

    //协议
    String SCHEME_HTTP = "http";
    String SCHEME_HTTPS = "https";
    String SCHEME_FILE = "file";
    String SCHEME_NATIVE = "safp";

    //Host
    String PAGE_HOST = "safpapp_page";
    String GOV_MO_HOST = "gov-mo";

    interface RoutePath{
        /**
         * 文件预览
         */
        String FILE_PREVIEW_PATH = "/common/preview_file";
    }

    //PATH
    String WEB_PAGE_PATH = "/web/page";
    String NATIVE_PAGE_PATH = "/native/page";
    String WEB_CLEAR_PAGE_PATH = "/web/clear_page";
    String WEB_FRAGMENT_PATH = "/web/fragment";
    String MAIN_PATH = "/home/main";
    String FILTER_PATH = "/common/filter";
    String LOGIN_PATH = "/login";
    String TEST_SERVICE_PATH = "/debug/service";
    String DEBUG_PATH = "/debug/main";
    String DEBUG_INITIAL = "/debug/initial";
    String H5_SERVICE_PATH = "/common/h5";
    String CLZ_SERVICE_PATH = "/home/class";
    String ENTRANCE_PATH = "/common/entrance";
    String COMMUNICATE_PATH = "/common/communicate";
    String USER_SERVICE_PATH = "/common/user_service";
    String USER_LOGGER_PATH = "/common/log_service";
    String ACCOUNT_LOGIN_PAGE_PATH = "/account/login_page";
    String ACCOUNT_LOGIN_ACTIVITY = "/account/login_activity";
    String ACCOUNT_BIOMETRIC_LOGIN_ACTIVITY = "/account/biometric_login_activity";
    String ACCOUNT_LOGIN_MANAGE_PATH = "/account/login_manage";
    String ACCOUNT_FINGERPRINT_LOGIN_MANAGE_PATH = "/account/fingerprint_login_manage";
    String AUTH_PAGE = "/auth/page";
    String COMMON_KV_SERVICE = "/common/kv";
    String COMMON_FOLLOW_UP_SERVICE = "/common/follow_up";
    String COMMON_CLEAR_PAGE = "/common/clear_page";

    //参数
    String PACKAGE_NAME = "packageName";
    String ACTIVITY_NAME = "activityName";
    String PASS_DATA = "passData";
    String URL_PARAM = "url";
    String RESULT_CODE_PARAM = "resultCode";
    String APPID_PARAM = "appId";
    String SCHEME_APPID_PARAM = "moGovAppId";
    String URI_PARAM = "uri";
    String CONTINUE_ROUTE = "continueRoute";
    /**
     * 容器配置参数
     */
    String OPTIONS = "options";
    String CHANNEL = "channel";
    String OUTSIDE = "outside";
    String HEADERS = "headers";
    /**
     * 拼接在路由之后的参数
     */
    String QUERIES = "queries";
    String INNER_QUERIES = "inner_queries";
    String THEME = "theme";
    String TAB_FIX_NAME = "tabFixName";
    String LOGIN_NATIVE_PATH = "loginNativePath";
    /**
     * 状态保存bundle key
     */
    String SAVE_STATE_BUNDLE = "saveStateBundle";
    /**
     * 请求头
     */
    String PAGE_HEADERS = "pageHeaders";
    /**
     * 是否使用系统内核
     */
    String USE_SYSTEM_CORE = "useSystemCore";

    /**
     * 动画形式
     */
    String POP_UP = "popUp";

    /**
     * H5离线包路由
     */
    String OFFLINE_H5_ROUTE_FLAG = "#";

    String TAB_CONFIG_FILE_NAME = "config/home_tab_offline.json";
    String ONLINE_TAB_CONFIG_FILE_NAME = "config/home_tab_online.json";

    String MINI_TAB_CONFIG_FILE_NAME = "config/mini_home_tab_offline.json";
    String MINI_ONLINE_TAB_CONFIG_FILE_NAME = "config/mini_home_tab_online.json";

    String WEB_BUNDLE = "web_bundle";
    String BUNDLE = "bundle";

    String IS_INIT_SETTING = "isInitSetting";

    /**
     * 首页tab标识
     */
    String HOME_TAB_MAIN_TAG = "home_tab_main";

    /**
     * 空页url
     */
    String EMPTY_URL = "about:blank";

    /**
     * 传递参数
     */
    interface IntentParam{
        /**
         * 本地文件路径
         */
        String LOCAL_FILE_PATH = "filePath";
    }

    /**
     * 音频格式
     */
    interface MediaType {
        /**
         * mp3格式
         */
        String MP3 = "mp3";
    }

    /**
     * 用户偏好存储key
     */
    interface USER_SP_KEY{

        String USERNAME_BIOMETRIC_KEY = "username_biometric_key";

        String PASSWORD_BIOMETRIC_KEY = "password_biometric_key";
    }

    /**
     * 页面跳转请求码
     */
    interface PageRequestCode {

        int CODE_OPEN_FILE = 888;
    }

    interface RemoteConfigKey {

        String AUTH_HOST_WHITELIST_CONFIG_KEY = "Global_Url_Header_WhiteList";

        String WEB_VIEW_BACK_HOST_WHITELIST_CONFIG_KEY = "Global_WebView_Back_WhiteList";

        String TITLE_FILTER_WHITELIST_CONFIG_KEY = "Global_WebView_Title_InterceptList";
    }

    interface AccountType{

        String CORP_ENTITY = "Corp-Entity";

        String PERSONAL = "Personal";
    }

    interface UrlCommonParam {

        String STATUS_BAR_HEIGHT = "sh";

        String TITLE_BAR_HEIGHT = "nh";

        String LANGUAGE = "language";

        String THEME = "theme";

        String FONT_SIZE = "fs";

        String ANIM_TYPE = "anim";

        String IS_DARK = "isDark";

        String WEB_TITLE = "title";

        String HIDE_TITLE_BAR = "hideNavigationBar";

        String OFFLINE_APP_ID = "moGovAppId";

        String USE_SYSTEM_UA = "systemUA";

        String IS_CHILD_ACCOUNT = "isChildAccount";

        String CHILD_ACCOUNT_INFO = "childAccountInfo";
    }

    interface HttpUrlLaunchParam {

        String AUTH_ENCODE_TOKEN = "needAuth";

        String AUTH_TOKEN = "needAuthV2";

    }

    interface AppSchemeUrlLaunchParam {

        String HOME_TAB_INDEX = "govTabIndex";

        String TRANS_EVENT_NAME = "eventName";

        String TRANS_EVENT_PARAM = "eventParam";
    }

    /**
     * 1.0JSAPI名字
     */
    interface JSAPI_NAME {
        /**
         * 人脸识别
         */
        String FACIAL_DETECT = "facialDetect";

        /**
         * 发送JS调用消息
         */
        String POST_MESSAGE = "postMessage";

        /**
         * 获取当前语言配置
         */
        String GET_LANGUAGE = "getCurrentLanaguage";

        /**
         * 检查设备种码
         */
        String CHECK_SOFT_TOKEN = "checkSoftToken";

        /**
         * 是否验证手机号
         */
        String HAS_VERIFY_MOBILE = "hasVerifyMobile";

        /**
         * 是否验证手机号
         */
        String VERIFY_MOBILE = "verifyMobile";

        /**
         * 获取手机资料
         */
        String GET_PHONE_PROFILE = "getPhoneProfile";

        /**
         * 设置亮度
         */
        String SET_BRIGHTNESS = "setBrightness";

        /**
         * 重设亮度
         */
        String RESET_BRIGHTNESS = "resetBrightness";
    }
}
