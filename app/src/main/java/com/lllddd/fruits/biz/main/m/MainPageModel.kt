/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/

package com.lllddd.fruits.biz.main.m

import android.util.Log
import com.lllddd.fruits.biz.constants.BizHosts
import com.lllddd.fruits.network.callback.OkHttpCallback
import com.lllddd.fruits.network.exceptions.OnResponseException
import com.lllddd.fruits.network.utils.NetworkUtils
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
 * created on: 2024/5/27 9:09
 * description:主页Model
 */
class MainPageModel {
    fun queryFruits(callback: OkHttpCallback) {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(BizHosts.QUERY_ALL_URL)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("okhttp error ${call.request().url}->", e.message.toString())
                callback.onFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                callback.onSuccess(response)
            }
        })
    }

    fun deleteFruit(params: Map<String, String>, callback: OkHttpCallback) {
        val client = OkHttpClient()

        val mediaType: MediaType? = "application/x-www-form-urlencoded".toMediaTypeOrNull()
        val requestBody = RequestBody.create(mediaType, NetworkUtils.buildFormData(params))

        val request = Request.Builder()
            .post(requestBody)
            .url(BizHosts.DELETE_URL)
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