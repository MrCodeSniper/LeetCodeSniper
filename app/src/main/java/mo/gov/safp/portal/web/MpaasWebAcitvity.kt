package mo.gov.safp.portal.web


import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.alibaba.yihutong.common.h5plugin.govappbridge.GovAppBridge
import com.alipay.mobile.framework.LauncherApplicationAgent
import com.alipay.mobile.framework.app.ui.BaseActivity
import com.alipay.mobile.h5container.api.H5Bundle
import com.alipay.mobile.h5container.api.H5Page
import com.alipay.mobile.h5container.api.H5PageReadyListener
import com.alipay.mobile.h5container.service.H5Service
import com.alipay.mobile.nebula.webview.APWebView
import kotlinx.android.synthetic.main.activity_web_container.*
import mo.gov.safp.portal.R


/**
 * 自定义MPAAS WEB容器
 *
 * @author CH
 */
open class MpaasWebAcitvity : BaseActivity(), H5PageReadyListener {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MpaasWebAcitvity::class.java)
            context.startActivity(intent)
        }
    }

    private var mWebView: APWebView? = null
    private var mUseSystemUA: Boolean? = null
    private var mH5Page: H5Page? = null

    var url: String? =
        "https://www.taobao.com"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_container)
        getAsyncPage()
    }


    /**
     * 获取异步创建的容器
     */
    private fun getAsyncPage() {
        val h5Service =
            LauncherApplicationAgent.getInstance().microApplicationContext.findServiceByInterface<H5Service>(
                H5Service::class.java.name
            )
        val bundle = Bundle()
        bundle.putString("url", url)
        h5Service?.createPageAsync(this, H5Bundle(bundle), this)
    }

    /**
     * 初始化容器
     */
    override fun getH5Page(h5Page: H5Page) {
        mH5Page = h5Page
        mWebView = mH5Page?.webView
        mWebView.initWebSettings(this, mUseSystemUA)
//        mH5Page?.setWebViewClient(this, mAppId, mHeaderMap, null, null)
        val client = CustomWebChromeClient(this,mH5Page )
        mWebView?.setWebChromeClient(client)
        mH5Page?.setH5ErrorHandler { message, code ->
            false
        }
        mWebView?.addJavascriptInterface(GovAppBridge(null), GovAppBridge::class.java.simpleName)
        flRoot?.removeAllViews()
        flRoot?.addView(mH5Page?.contentView)
    }

    override fun onDestroy() {
        super.onDestroy()
        mWebView?.recycle(flRoot)
    }

}