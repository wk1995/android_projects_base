package com.wk.projects.common.helper

import java.util.*

/**
 *
 *      author : wk
 *      e-mail : 1226426603@qq.com
 *      time   : 2021/12/8
 *      desc   :
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 * */
object CollectionUtil {


    fun <T : Comparable<T>?> insertData(originData: MutableList<T>, insertData: T, needSort: Boolean){
        originData.add(insertData)
//        originData.sortWith()
    }


}