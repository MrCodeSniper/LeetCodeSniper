package mo.gov.safp.portal.web


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
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



    companion object{
        fun start(context: Context){
            val intent = Intent(context,MpaasWebAcitvity::class.java)
            context.startActivity(intent)
        }
    }

    protected var mWebView: APWebView? = null

    private var mResultCode: String? = null
    private var mAppId: String? = null
    private var mPageBundle: Bundle? = null
    protected var mHeaderMap: Map<String?, String?>? = null
    private var mUseSystemUA: Boolean? = null

    private var mTitleContent: View? = null

//    private var mCustomWebChromeClient: CustomWebChromeClient? = null
    private var mH5Page: H5Page? = null

    /** 是否展示标题栏阴影 默认为false */
    private var mHideShadow = false

    var url: String? = "https://app-res-uat.mo.gov.mo/#/weather-warning?backAction=native"

    private var mStartTimeMills: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mStartTimeMills = System.currentTimeMillis()
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
        h5Service?.createPageAsync(this, H5Bundle(mPageBundle), this)
    }

    /**
     * 初始化容器
     */
    override fun getH5Page(h5Page: H5Page) {
        mH5Page = h5Page
        mWebView = mH5Page?.webView
        mWebView.initWebSettings(this, mUseSystemUA)
//        mH5Page?.setWebViewClient(this, mAppId, mHeaderMap, null, null)
        mH5Page?.contentView?.setBackgroundColor(resources.getColor(R.color.gov_background_view_color))
        mH5Page?.setH5ErrorHandler { message, code ->
            false
        }
        if (mHeaderMap != null) {
            mH5Page?.setExtra("mogov_header", mHeaderMap)
        }
        flRoot?.removeAllViews()
        flRoot?.addView(mH5Page?.contentView)
        mWebView?.loadUrl(url, null)
    }

}