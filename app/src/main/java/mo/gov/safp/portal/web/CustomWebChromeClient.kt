package mo.gov.safp.portal.web

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.view.View
import android.webkit.ConsoleMessage
import android.widget.FrameLayout
import com.alipay.mobile.h5container.api.H5Page
import com.alipay.mobile.nebula.webview.APJsResult
import com.alipay.mobile.nebula.webview.APWebChromeClient
import com.alipay.mobile.nebula.webview.APWebView
import com.alipay.mobile.nebulacore.core.H5PageImpl
import com.alipay.mobile.nebulacore.web.H5WebChromeClient

/**
 * WebView代理类
 * @author ch
 */
class CustomWebChromeClient(var context: Activity, page: H5Page?) :
    H5WebChromeClient(page as H5PageImpl?) {

    private var mFullScreenContainer: FrameLayout? = null

    override fun onReceivedTitle(view: APWebView?, title: String?) {
        super.onReceivedTitle(view, title)
    }

    /**
     * 页面加载进度
     */
    override fun onProgressChanged(view: APWebView?, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
    }

    override fun onShowCustomView(view: View, callback: APWebChromeClient.CustomViewCallback) {
        super.onShowCustomView(view, callback)
        showCustomView(view, callback)
    }

    override fun onHideCustomView() {
        super.onHideCustomView()
        hideCustomView()
    }

    /**
     * 展示全屏
     */
    private fun showCustomView(view: View?, callback: APWebChromeClient.CustomViewCallback?) {
        if (mFullScreenContainer != null) {
            callback?.onCustomViewHidden()
            return
        }
        mFullScreenContainer = FrameLayout(context).apply { setBackgroundColor(Color.BLACK) }
        mFullScreenContainer?.addView(view)
        val decorView = (context as? Activity)?.window?.decorView as? FrameLayout
        context.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        decorView?.addView(mFullScreenContainer)
    }

    /**
     * 隐藏全屏
     */
    @SuppressLint("SourceLockedOrientationActivity")
    private fun hideCustomView() {
        if (mFullScreenContainer == null) {
            return
        }
        val decorView = (context as? Activity)?.window?.decorView as? FrameLayout
        (context as? Activity)?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        mFullScreenContainer?.removeAllViews()
        decorView?.removeView(mFullScreenContainer)
        mFullScreenContainer = null
        mCustomViewCallback?.onCustomViewHidden()
        mCustomViewCallback = null
    }


    override fun onJsAlert(
        apWebView: APWebView?,
        url: String?,
        message: String?,
        result: APJsResult?
    ): Boolean {
        return try {
            super.onJsAlert(apWebView, url, message, result)
        } catch (e: Exception) {
            result?.cancel()
            true
        }
    }

    override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        return super.onConsoleMessage(consoleMessage)
    }

}