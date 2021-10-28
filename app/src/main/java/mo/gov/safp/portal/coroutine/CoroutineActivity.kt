package mo.gov.safp.portal.coroutine

import android.os.Bundle
import android.util.Log
import android.view.View
import com.alipay.mobile.framework.app.ui.BaseActivity
import kotlinx.coroutines.*
import mo.gov.safp.portal.R

class CoroutineActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
    }

    fun btnStartCoroutine(view: View) {
        //GlobalScope 整个应用程序生命周期内运行
        val blockingTask = runBlocking{
            Log.d(TAG, "runBlocking阻塞启动一个协程")
            "return blocking task" //blocking协程返回值
        }
        Log.d(TAG, "runBlocking:$blockingTask")
        val launchTask = GlobalScope.launch {
            Log.d(TAG, "launch非阻塞启动一个协程")
        }
        Log.d(TAG, "launch:$launchTask")


        GlobalScope.launch {
            val asyncTask =  async {
                Log.d(TAG, "async非阻塞启动一个协程")
                "return async value"
            }
            Log.d(TAG, "async:$asyncTask")
            //通过await获取返回值
            //await是挂起函数 只能在协程作用域里执行
            //public suspend fun await(): T
            //suspend挂起 会等待协程执行完毕
            Log.d(TAG, "asyncJob.await:${asyncTask.await()}")
        }

        //指定主线程 协程是同步执行的
        //如果协程处于Dispatchers.Main调度器，它会将协程调度到UI事件循环中执行，即通常在主线程上执行
        GlobalScope.launch(Dispatchers.Main) {
            for (index in 1 until  10) {
                //同步执行
                launch {
                    Log.d("launch$index", "启动一个协程")
                }
            }
        }
    }

    companion object {
        private const val TAG = "CoroutineActivity"
    }

}