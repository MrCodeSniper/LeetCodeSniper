package mo.gov.safp.portal.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mo.gov.safp.portal.R

class RcvAdapter : RecyclerView.Adapter<RcvAdapter.VHolder>() {

    var list:MutableList<Int> = ArrayList()

    init {
        for (i in 0..99){
            list.add(i)
        }
    }

    class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       val textTv: TextView =itemView.findViewById(R.id.pos_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        Log.d("onCreateViewHolder", "onCreateViewHolder")
       val item:View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_test_item_layout,parent,false)
        return VHolder(item)
    }

    override fun getItemCount(): Int {
       return  list.size
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.textTv.text = position.toString()
        Log.d("onBindViewHolder", "pos:$position")
    }
}
