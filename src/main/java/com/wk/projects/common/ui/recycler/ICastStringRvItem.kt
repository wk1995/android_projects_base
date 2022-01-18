package com.wk.projects.common.ui.recycler

/**
 * @author      :wangkang_shenlong
 * email        :shenlong.wang@tuya.com
 * create date  : 2021/11/11
 * desc         : RecycleView item 转String 接口
 */


interface ICastStringRvItem<T> {
    fun castString(t:T):String
}