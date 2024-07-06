/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/

package com.lllddd.fruits.utils

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur


/**
 * author: lllddd
 * created on: 2024/5/21 14:01
 * description:模糊工具类
 */
object BlurUtils {
    fun blurBitmap(context: Context, bitmap: Bitmap, radius: Int): Bitmap {
        val outputBitmap: Bitmap = Bitmap.createBitmap(bitmap)

        val renderScript = RenderScript.create(context)

        val scriptIntrinsicBlur =
            ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))

        val inputAllocation = Allocation.createFromBitmap(renderScript, bitmap)
        val outputAllocation = Allocation.createFromBitmap(renderScript, outputBitmap)

        scriptIntrinsicBlur.setRadius(radius.toFloat())
        scriptIntrinsicBlur.setInput(inputAllocation)
        scriptIntrinsicBlur.forEach(outputAllocation)

        outputAllocation.copyTo(outputBitmap)

        renderScript.destroy()

        return outputBitmap
    }
}