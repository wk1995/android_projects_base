package com.wk.projects.common.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.wk.projects.common.R
import com.wk.projects.common.constant.WkStringConstants

/**
 * <pre>
 *      author : wk
 *      e-mail : 122642603@qq.com
 *      time   : 2018/11/24
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 *      desc   : 只显示字符串的recycler Adapter
 * </pre>
 */

open class SimpleStringListAdapter<T>(
    private val data: MutableList<T> = ArrayList(),
    private val mOptimizationRvItem: OptimizationRvItem? = null,
    private val mCastStringRvItem: ICastStringRvItem<T>? = null,
    private val defaultString: String = WkStringConstants.STR_EMPTY)
    : BaseRecyclerViewAdapter<T,SimpleStringListVH>() {


    override fun getItemLayoutResId(parent: ViewGroup, viewType: Int)=R.layout.common_only_text

    override fun createVH(rootView: View, viewType: Int): SimpleStringListVH {
        val tvCommon = rootView.findViewById<TextView>(R.id.tvCommon)
        val simpleStringListVH = SimpleStringListVH(rootView, tvCommon)
        mOptimizationRvItem?.optimizationCreateView(simpleStringListVH, viewType)
        return simpleStringListVH
    }

    override fun onBindViewHolder(holder: SimpleStringListVH, position: Int) {
        mOptimizationRvItem?.optimizationBindView(holder, position)
        val text = mCastStringRvItem?.castString(data[position]) ?: defaultString
        holder.tvCommon.text = text
    }

}