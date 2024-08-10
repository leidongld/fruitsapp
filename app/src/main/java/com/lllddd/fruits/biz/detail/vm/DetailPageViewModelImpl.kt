/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/

package com.lllddd.fruits.biz.detail.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lllddd.fruits.biz.constants.BizConstants
import com.lllddd.fruits.biz.beans.FruitBean
import com.lllddd.fruits.biz.detail.m.DetailPageModel
import com.lllddd.fruits.network.callback.OkHttpCallback
import com.lllddd.fruits.network.resp.Resp
import okhttp3.Response

/**
 * author: lllddd
 * created on: 2024/5/27 14:04
 * description:详情页ViewModel
 */
class DetailPageViewModelImpl(application: Application) : AndroidViewModel(application),
    DetailPageViewModel {
    private val _model = DetailPageModel()

    private val mModifyFruitLiveData = MutableLiveData<Resp>()

    override fun modifyFruit(fruit: FruitBean): LiveData<Resp> {
        val params = mapOf<String, String>(
            BizConstants.ID to fruit.id.toString(),
            BizConstants.PRICE to fruit.price.toString()
        )
        _model.modifyFruit(params, object : OkHttpCallback {
            override fun onSuccess(response: Response) {
                mModifyFruitLiveData.postValue(Resp(Resp.SUCCESS_CODE, Resp.SUCCESS_MSG, null))
            }

            override fun onFailure(e: Exception) {
                mModifyFruitLiveData.postValue(Resp(Resp.FAIL_CODE, Resp.FAIL_MSG, null))
            }
        })

        return mModifyFruitLiveData
    }
}