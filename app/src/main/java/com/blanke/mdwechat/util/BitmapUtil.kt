package com.blanke.mdwechat.util

import android.graphics.Bitmap
import android.graphics.Matrix

object BitmapUtil {
    fun scaleImage(source: Bitmap, toWidth: Int, toHeight: Int): Bitmap {
        // 计算缩放比例
        val scaleWidth = toWidth.toFloat() / source.width
        val scaleHeight = toHeight.toFloat() / source.height
        // 取得想要缩放的matrix参数
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        // 得到新的图片
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
    }
}