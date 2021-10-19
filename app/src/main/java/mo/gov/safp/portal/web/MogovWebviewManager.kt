package mo.gov.safp.portal.web

import android.content.Context
import android.os.Build
import android.webkit.WebSettings
import com.alipay.mobile.nebula.webview.APWebView

/**
 * @ClassName MogovWebviewManager
 * @Description webview相关配置
 * @Author CH
 * @Date 4/27/21 10:46 AM
 */

private const val TAG = "MogovWebviewManager"

/** 最大预加载容器个数 个数过多会有内存问题*/
const val MAX_PRELOAD_NUM = 3;


fun APWebView?.injectCss() {
    val css = "javascript:(function() {\n" +
            "var parent = document.getElementsByTagName('head').item(0);\n" +
            "var style = document.createElement('style');\n" +
            "style.innerHTML = \"@font-face { font-family: 'MyCustomFont'; \n src: url('/android_asset_font/DSICCC_Ming_MSCS_Full.ttf'); }  \";\n" +
            "parent.appendChild(style)})();"
    try {
        this?.loadUrl(css)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}


/**
 * 外部调用函数 初始化默认容器配置
 */
@JvmOverloads
fun APWebView?.initWebSettings(context: Context, useSystemUA: Boolean? = false) {
    initWebDebugSetting(context)
    initWebCacheSetting(context)
    initWebUA(useSystemUA)
}

/**
 * WebView调试模式配置
 */
fun APWebView?.initWebDebugSetting(context: Context) {
    val settings = this?.settings
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        settings?.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW)
    }
    settings?.javaScriptEnabled = true
}

/**
 * 容器UA设置
 */
fun APWebView?.initWebUA(useSystemUA: Boolean?) {
    val settings = this?.settings
    if (useSystemUA == true) {
        val nowAgent = settings?.userAgentString
        val tag = "AlipayDefined"
        if (nowAgent?.contains(tag) == true) {
            settings.userAgentString = nowAgent.substring(0, nowAgent.indexOf(tag))
        }
    } else {
//        val service = ARouter.getInstance().build(RouteConstant.CLZ_SERVICE_PATH).navigation() as ClzService?
//        settings?.userAgentString = settings?.userAgentString + ";env=" + service?.appEnv
    }
}


/**
 * WebView缓存配置
 */
fun APWebView?.initWebCacheSetting(context: Context) {
    val settings = this?.settings
    val cacheDirPath: String = context.filesDir.absolutePath + "cache/"
    settings?.setAppCachePath(cacheDirPath)
    settings?.setAppCacheEnabled(true)
    settings?.domStorageEnabled = true
    settings?.databasePath = cacheDirPath
    settings?.databaseEnabled = true
    settings?.cacheMode = WebSettings.LOAD_DEFAULT
}

