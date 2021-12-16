package com.wk.projects.common.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 *
 *      author : wk
 *      e-mail : 1226426603@qq.com
 *      time   : 2021/12/14
 *      desc   :
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 * */
abstract class BaseRecyclerViewAdapter<R, T : RecyclerView.ViewHolder>(
        private val mData: MutableList<R> = ArrayList(),
        val adapter: RecyclerView.Adapter<T>? = null)
    : RecyclerView.Adapter<T>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        val result = adapter?.onCreateViewHolder(parent, viewType)
        return if (result != null) {
            result
        } else {
            val rootView = LayoutInflater.from(parent.context).inflate(
                    getItemLayoutResId(parent, viewType), parent, false)
            createVH(rootView)
        }
    }


    abstract fun getItemLayoutResId(parent: ViewGroup, viewType: Int): Int

    abstract fun createVH(rootView: View): T

    override fun onBindViewHolder(holder: T, position: Int) {
        adapter?.onBindViewHolder(holder, position)
    }

    /**包括了header和footer*/
    override fun getItemCount() = mData.size

    private fun getDataCount()= mData.size

    fun getDataReallyPosition(position: Int) = position

    fun getItem(position: Int) = mData[getDataReallyPosition(position)]

    fun updateData(data:List<R>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun addData(data:R){
        mData.add(data)
        notifyItemInserted(getDataCount())
    }

    fun addData(index:Int,data: R){
        mData.add(index,data)
        notifyItemRangeChanged(index,itemCount)
    }

    fun replaceData(index: Int,data: R){

    }


}


