package com.wk.projects.common.ui.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter

/**
 * <pre>
 *      author : wk
 *      e-mail : 122642603@qq.com
 *      time   : 2019/2/27
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 *      desc   :
 * </pre>
 */
interface IRvClickListener {

    fun onItemChildClick(adapter: RecyclerView.Adapter<*>?, view: View?, position: Int) {

    }

    fun onItemLongClick(adapter: RecyclerView.Adapter<*>?, view: View?, position: Int) {

    }

    fun onItemChildLongClick(adapter: RecyclerView.Adapter<*>?, view: View?, position: Int) {
    }

    fun onItemClick(adapter: RecyclerView.Adapter<*>?, view: View?, position: Int) {
    }
}