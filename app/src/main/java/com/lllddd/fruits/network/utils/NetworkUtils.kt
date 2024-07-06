/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/

package com.lllddd.fruits.network.utils

/**
 * author: lllddd
 * created on: 2024/5/27 10:40
 * description:网络工具类
 */
object NetworkUtils {
    /**
     * 将Map中的键值对转换为可供网络请求的字符串
     *
     * @param params 键值对
     * @return 转换后的字符串
     */
    fun buildFormData(params: Map<String, String>): String {
        val formBuilder = StringBuilder()
        params.forEach { (key, value) ->
            formBuilder.append("$key=$value&")
        }
        formBuilder.deleteCharAt(formBuilder.length - 1)
        return formBuilder.toString()
    }
}