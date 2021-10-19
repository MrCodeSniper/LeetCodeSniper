package mo.gov.safp.portal.web

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentActivity
import com.alipay.mobile.framework.app.ui.BaseAppCompatActivity
import java.lang.ref.WeakReference


open class BaseActivity : BaseAppCompatActivity() {

    private var mDelegate: AppCompatDelegate? = null
    private val weakReference: WeakReference<FragmentActivity> by lazy {
        WeakReference<FragmentActivity>(
            this
        )
    }

    private var costTimeMills: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBar()
    }

    /**
     * 设置状态栏
     */
    open fun setStatusBar() {

    }


    /**
     * 布局与状态栏不重叠
     */
    open fun fitSystemWindows(): Boolean {
        return true
    }

    protected open fun compatFontScale(): Boolean {
        return true
    }


    protected open fun copy(text: String) {
        //获取剪贴板管理器：
        val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        // 创建普通字符型ClipData
        val clipData = ClipData.newPlainText("Label", text)
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(clipData);
    }


    /**
     * This one is generated when the fragment state is going to be restored.
     * Android有两种不同的classloaders：framework classloader和Apk classloader，
     * 应用启动时默认的classloader是Apk classloader，可以加载Parcelable反序列化所需的类。当内存不足时，默认classloader将变为framework classloader，它不知道如何加载自己定义的类，因而会报错。
     */
    fun intercept(context: Context, bundle: Bundle?) {
        if (bundle != null) {
            bundle.classLoader = context.classLoader
        }
    }


}