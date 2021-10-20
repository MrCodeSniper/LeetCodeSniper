package mo.gov.safp.portal.web

import android.util.Log
import android.view.ViewGroup
import android.webkit.WebSettings
import com.alipay.mobile.nebula.webview.APWebView
import java.io.InputStream
import java.net.URL

/**
 * @author CH
 * @date 2021/10/20
 */


fun String.getUrlInputStream(): InputStream? {
    try {
        return URL(this).openStream()
    } catch (e: Exception) {
        Log.e("chenhongWeb", "getResource From Url Fail:${e.message},Url:${this}")
    }
    return null;
}

/**
 * 回收容器
 */
@JvmOverloads
fun APWebView?.recycle(container: ViewGroup?) {
    try {
        container?.removeAllViews()
        this?.stopLoading()
        this?.settings?.javaScriptEnabled = false
        this?.clearHistory()
        this?.loadUrl("about:blank")
        this?.freeMemory()
//        this?.clearCache(true)
//        this?.settings?.cacheMode = WebSettings.LOAD_NO_CACHE
//        this?.destroy()
    } catch (e: Exception) {
       e.printStackTrace()
    }
}