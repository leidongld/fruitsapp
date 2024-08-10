/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/

package com.lllddd.fruits.biz.main.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.reflect.TypeToken
import com.lllddd.fruits.biz.beans.FruitBean
import com.lllddd.fruits.biz.constants.BizConstants
import com.lllddd.fruits.biz.main.m.MainPageModel
import com.lllddd.fruits.network.callback.OkHttpCallback
import com.lllddd.fruits.network.resp.Resp
import com.lllddd.fruits.utils.GsonUtils
import okhttp3.Response

/**
 * author: lllddd
 * created on: 2024/5/27 9:10
 * description:主页ViewModel
 */
class MainPageViewModelImpl(application: Application) : AndroidViewModel(application),
    MainPageViewModel {
    private val _model = MainPageModel()

    private val mQueryFruitsLiveData: MutableLiveData<Resp> = MutableLiveData()
    private val mDeleteFruitLiveData: MutableLiveData<Resp> = MutableLiveData()

    override fun queryFruits(): LiveData<Resp> {
        _model.queryFruits(object : OkHttpCallback {
            override fun onSuccess(response: Response) {
                val resp: Resp = GsonUtils.fromJson(response.body?.string() ?: "", Resp::class.java)

                val jsonStr = GsonUtils.toJson(resp.data)
                val typeToken: TypeToken<List<FruitBean>> = object : TypeToken<List<FruitBean>>() {}
                val list = GsonUtils.fromJson(jsonStr, typeToken)

                mQueryFruitsLiveData.postValue(Resp(Resp.SUCCESS_CODE, Resp.SUCCESS_MSG, list))
            }

            override fun onFailure(e: Exception) {
                mQueryFruitsLiveData.postValue(Resp(Resp.FAIL_CODE, Resp.FAIL_MSG, null))
            }
        })

        return mQueryFruitsLiveData
    }

    override fun deleteFruit(id: Int): LiveData<Resp> {
        val params = mapOf(
            BizConstants.ID to "$id"
        )

        _model.deleteFruit(params, object : OkHttpCallback {
            override fun onSuccess(response: Response) {
                mDeleteFruitLiveData.postValue(Resp(Resp.FAIL_CODE, Resp.FAIL_MSG, null))
            }

            override fun onFailure(e: Exception) {
                mDeleteFruitLiveData.postValue(Resp(Resp.FAIL_CODE, Resp.FAIL_MSG, null))
            }
        })

        return mDeleteFruitLiveData
    }
}