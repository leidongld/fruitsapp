/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/

package com.lllddd.fruits.biz.login.m

import android.util.Log
import com.lllddd.fruits.biz.constants.BizHosts
import com.lllddd.fruits.network.callback.OkHttpCallback
import com.lllddd.fruits.network.exceptions.OnResponseException
import com.lllddd.fruits.utils.GsonUtils
import okhttp3.Call
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

/**
 * author: lllddd
 * created on: 2024/8/7 10:51
 * description:登录Bean
 */
class LoginModel {
    fun login(params: Map<String, String>, callback: OkHttpCallback) {
        val client = OkHttpClient()

        val mediaType: MediaType? = "application/json".toMediaTypeOrNull()
        val requestBody = RequestBody.create(mediaType, GsonUtils.toJson(params))

        val request = Request.Builder()
            .url(BizHosts.LOGIN_URL)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("okhttp error ${call.request().url}->", e.message.toString())
                callback.onFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.code == 200) {
                    callback.onSuccess(response)
                } else {
                    callback.onFailure(OnResponseException(response.code))
                }
            }
        })
    }
}