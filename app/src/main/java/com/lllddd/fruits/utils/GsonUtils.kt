/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/

package com.lllddd.fruits.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * author: lllddd
 * created on: 2024/5/28 13:51
 * description:Gson工具类
 */
class GsonUtils {
    companion object {
        private val mGson = Gson()

        fun toJson(obj: Any?): String = mGson.toJson(obj)

        fun <T> fromJson(jsonStr: String, clazz: Class<T>): T = mGson.fromJson(jsonStr, clazz)

        fun <T> fromJson(jsonStr: String, typeToken: TypeToken<T>): T =
            mGson.fromJson(jsonStr, typeToken)
    }
}