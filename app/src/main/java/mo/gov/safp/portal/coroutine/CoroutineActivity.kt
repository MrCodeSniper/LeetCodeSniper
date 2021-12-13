package mo.gov.safp.portal.coroutine

import android.os.Bundle
import android.util.Log
import android.view.View
import com.alipay.mobile.framework.app.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.coroutines.*
import mo.gov.safp.portal.R
import mo.gov.safp.portal.utils.Strings
import java.lang.Exception
import java.text.SimpleDateFormat
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.math.log

/**
 * kotlin相比较与rxjava 编译器级别支持使其 在代码上看实际的异步更新逻辑上的同步  示例？对比？
 * 协程更轻量 异步 不需要进行系统调用  原理？
 * 协程是一种并发的设计模式 利用到了线程  消除回调 异步编程趋向同步
 * 代码写的顺序逻辑有问题 编译器直接提示
 */
class CoroutineActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
        btnStartCoroutineMode.setOnClickListener {
//            GlobalScope.launch() {
//                btnCoroutineStartMode()
//            }

            getUserInMain()
        }

//        tv_lan_hint.text = Strings.toDBC(resources.getString(R.string.lan))
    }

    fun btnStartCoroutine(view: View) {
        //GlobalScope 整个应用程序生命周期内运行
        val blockingTask = runBlocking{
            Log.d(TAG, "runBlocking阻塞启动一个协程")
            "return blocking task" //blocking协程返回值
        }
        Log.d(TAG, "runBlocking:$blockingTask")

        /**
         * 参数
         * 1。CoroutineContext 上下文
         * 2。CoroutineStart 启动模式
         *    DEFAULT 立即执行
         *    ATOMIC 立即执行协程体，但在开始运行之前无法取消
         *    LAZY 懒加载
         *    UNDISPATCHED 立即在当前线程执行协程体，直到第一个 suspend 调用
         * 3。协程体 类似runnable
         */
        val launchTask = GlobalScope.launch() {
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

    /**
     * 具体来说，编译器看到 suspend 关键字会去掉 suspend ，
     * 给函数添加一个额外的 Continuation 参数。
     * 这个 Continuation 就代表了一个回调：

    public interface Continuation<in T> {
    public val context: CoroutineContext

    // 用来回调的方法
    public fun resumeWith(result: Result<T>)
    }
     */
    suspend fun btnCoroutineStartMode() {
        log(1)
        val job = GlobalScope.launch {
            log(2)
        }
        log(3)
        job.join()//join 要求等待协程执行完
        log(4)
        //可能结果 1.       1 3 2 4 ,   1 2 3 4
    }

    fun btnCoroutineStartMode2() {
        log(1)
        val job = GlobalScope.launch {
            log(2)
        }
        log(3)
        job.start()
        log(4)
        //可能结果 1.       1 3 4 2
    }


    /**
     * 模拟耗时任务获取用户信息
     */
    fun getUser(callback:Callback){
        log("getUser start")
        Thread.sleep(1000)
        log("getUser end invoke callback")
        callback.invoke(User("getUser method"))
    }

    /**
     * suspendCoroutine返回continuation 可以返回回调数据 类似Emitter.emit Observer.next
     * 内部可以包含耗时方法
     * 在协程中运行可以直接获取值
     *
     */
    suspend fun getUserCoroutine() = suspendCoroutine<User> {
        getUser{ it1->
            it.resume(it1)
        }
    }


    fun getUserInMain(){
       GlobalScope.launch(Dispatchers.IO) {
           val userCoroutine = getUserCoroutine()
           val name = userCoroutine.name
           log("getUserInMain：$name")
       }
    }


    /**
     * suspend是协程的关键字，
     * 每一个被suspend修饰的方法都必须在另一个suspend函数或者Coroutine协程程序中进行调用。
     */
    suspend fun exceptionCatch(){
       val handler = CoroutineExceptionHandler{ context,throwable ->{
            log("Throws an exception with message:${throwable.message}")
        }}
        log(1)
        GlobalScope.launch(handler){
            throw NullPointerException()
        }.join()
        log(2)
    }

    /**
     * 协程作用域
     */
    suspend fun scope(){
        log(1)
        GlobalScope.launch {
            //内部子协程准守默认作用域
            log(2)
        }
        //CoroutineScope是继承外部 Job 的上下文创建作用域
        //  创建一个协程作用域
        coroutineScope{
            log(3)
            launch {
                log(4)
                delay(100)
                throw NullPointerException()
            }
            log(5)
        }
        log(6)
    }


    suspend fun scope2(){
        log(1)
        GlobalScope.launch {
            log(2)
            supervisorScope{
                log(3)
                launch {
                    log(4)
                    launch {
                        log(5)
                        delay(100)
                        throw NullPointerException()
                    }
                    log(5)
                }
                log(6)
            }
        }
    }


    /**
     * async 和await   类似消费者和生产者
     * 生产者有异常直接报错 消费者有结果再运行
     */
    suspend fun asyncTest(){
        val deferred = GlobalScope.async {
            throw NullPointerException()
        }
        try{
            val value = deferred.await()
            log(1)
        }catch (e:Exception){
            log(e.message.toString())
        }
    }

    /**
     * join()不在乎是否返回结果 执行完毕就可以 即使爆出异常 继续执行
     */
    suspend fun joinTest(){
        val deferred = GlobalScope.async {
            throw NullPointerException()
        }
        try{
            val value = deferred.join()
            log(1)
        }catch (e:Exception){
            log(e.message.toString())
        }
    }

    private fun log(message:Int){
        Log.d("chenhong", "[${Thread.currentThread().name}] $message")
    }


    private fun log(message:String){
        Log.d("chenhong", "[${Thread.currentThread().name}] $message")
    }


    val dateFormat = SimpleDateFormat("HH:mm:ss:SSS")

}

/**
 * 定义别名函数 参数是user 返回为null
 */
typealias Callback =  (User) ->Unit