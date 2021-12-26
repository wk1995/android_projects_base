package com.wk.projects.common.resource

import android.content.Context
import androidx.core.content.ContextCompat
import com.wk.projects.common.configuration.WkProjects

/**
 * <pre>
 *      author : wk
 *      e-mail : 122642603@qq.com
 *      time   : 2018/11/24
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 *      desc   :
 * </pre>
 */
object WkContextCompat {

    fun getColor(context: Context, colorResId: Int) =
            ContextCompat.getColor(context, colorResId)

    fun getDrawable(context: Context, drawableResId: Int) =
            ContextCompat.getDrawable(context, drawableResId)

    fun getString(strResId: Int):String{
       return WkProjects.getApplication().getString(strResId)
    }

    fun getStringByFormat(strResId:Int,vararg str:String):String{
        return String.format(getString(strResId),str[0])
    }
}