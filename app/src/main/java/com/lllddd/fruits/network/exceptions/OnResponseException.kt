/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/

package com.lllddd.fruits.network.exceptions

/**
 * author: lllddd
 * created on: 2024/5/27 9:45
 * description:网络请求进到onResponse但是实际网络请求失败的异常
 */
class OnResponseException(private val code: Int) : Exception() {
    fun getErrorCode() = code
}
