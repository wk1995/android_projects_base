package com.wk.projects.common.helper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.wk.projects.common.configuration.WkProjects
import com.wk.projects.common.log.WkLog
import java.nio.ByteBuffer
import kotlin.math.roundToInt


/**
 *
 *      author : wk <br/>
 *      e-mail : 1226426603@qq.com<br/>
 *      time   : 2020/11/16<br/>
 *      desc   : bitmap工具类 <br/>
 *      GitHub : https://github.com/wk1995 <br/>
 *      CSDN   : http://blog.csdn.net/qq_33882671 <br/>
 * */
object WkBitmapUtil {

    /**获取图片大小*/
    @JvmStatic
    fun getBitmapSize(bitmap: Bitmap) = bitmap.byteCount


    fun calculateInSampleSize(options: BitmapFactory.Options,
                              reqWidth: Int, reqHeight: Int): Int {
        // 源图片的高度和宽度
        val height = options.outHeight
        val width = options.outWidth
        WkLog.i("wk", "height: $height  width: $width")
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            val heightRatio = (height.toFloat() / reqHeight.toFloat()).roundToInt()
            val widthRatio = (width.toFloat() / reqWidth.toFloat()).roundToInt()
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
        }
        return inSampleSize
    }


    fun getBitmapByBytes(bytes: ByteArray?, options: BitmapFactory.Options? = BitmapFactory.Options(),
                         defaultBitmap: Bitmap): Bitmap {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes?.size?:0,
                options)?:defaultBitmap

    }

    fun getBitmap(drawableResId: Int)=
        BitmapFactory.decodeResource(WkProjects.getApplication().resources,
                drawableResId)


    fun getByteArrayByBitmap(bitmap: Bitmap):ByteArray{
        val bytes: Int = bitmap.byteCount
        val buf: ByteBuffer = ByteBuffer.allocate(bytes)
        bitmap.copyPixelsToBuffer(buf)
        return buf.array()
    }


}