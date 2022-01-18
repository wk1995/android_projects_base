package com.wk.projects.common.ui.recycler

import androidx.recyclerview.widget.RecyclerView

/**
 * @author      :wangkang_shenlong
 * email        :shenlong.wang@tuya.com
 * create date  : 2021/11/11
 * desc         : 优化Recycle Item UI细节接口
 */

interface OptimizationRvItem{
    fun optimizationCreateView(vh: RecyclerView.ViewHolder,viewType:Int)
    fun optimizationBindView(vh: RecyclerView.ViewHolder, position:Int)
}