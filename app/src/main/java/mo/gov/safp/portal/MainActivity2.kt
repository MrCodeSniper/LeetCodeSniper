package mo.gov.safp.portal

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mpaas.nebula.adapter.api.MPNebula
import kotlinx.android.synthetic.main.activity_nestscroll_layout.*
import kotlinx.coroutines.*
import mo.gov.safp.portal.adapter.RcvAdapter
import mo.gov.safp.portal.bean.HomeItemInfo

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nestscroll_layout)
        recyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        recyclerView.adapter = RcvAdapter()
    }


    fun nebulaStart(view: View?) {
        MPNebula.startUrl("https://ss-ehr-web01.ssm.gov.mo/infrs/home")
    }

    private val mScope = MainScope() //自己创建的协程作用域 需要自己取消

    suspend fun test1() {
        val homeItemInfo = HomeItemInfo()
        homeItemInfo.adId = "89"
        delay(100)
        val job1 = mScope.launch {

        }
        val job2 = mScope.launch(Dispatchers.IO) {
            doIOThings()
            withContext(Dispatchers.Main){
                doUIThings()
            }
        }
    }

    private fun doIOThings() {
    }

    private fun doUIThings(){

    }

    override fun onDestroy() {
        super.onDestroy()
        mScope.cancel()
    }
}