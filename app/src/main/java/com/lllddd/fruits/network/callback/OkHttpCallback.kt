/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/

package com.lllddd.fruits.network.callback

import okhttp3.Response

/**
 * author: lllddd
 * created on: 2024/5/27 9:45
 * description:OkHttp回调接口
 */
interface OkHttpCallback {
    /**
     * 成功
     *
     * @param response 网络响应
     */
    fun onSuccess(response: Response)

    /**
     * 失败
     *
     * @param e 异常
     */
    fun onFailure(e: Exception)
}