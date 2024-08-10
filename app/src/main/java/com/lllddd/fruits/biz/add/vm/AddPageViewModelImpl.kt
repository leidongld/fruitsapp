/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/

package com.lllddd.fruits.biz.add.vm

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lllddd.fruits.biz.add.m.AddPageModel
import com.lllddd.fruits.network.callback.OkHttpCallback
import com.lllddd.fruits.network.resp.Resp
import okhttp3.Response

/**
 * author: lllddd
 * created on: 2024/5/28 14:37
 * description:添加水果ViewModel
 */
class AddPageViewModelImpl(application: Application) : AndroidViewModel(application),
    AddPageViewModel {
    private val _model = AddPageModel()

    private val mAddFruitLiveData = MutableLiveData<Resp>()

    override fun addFruit(params: Map<String, String>): LiveData<Resp> {
        _model.addFruit(params, object : OkHttpCallback {
            override fun onSuccess(response: Response) {
                mAddFruitLiveData.postValue(Resp(Resp.SUCCESS_CODE, Resp.SUCCESS_MSG, null))
            }

            override fun onFailure(e: Exception) {
                mAddFruitLiveData.postValue(Resp(Resp.FAIL_CODE, Resp.FAIL_MSG, null))
            }
        })

        return mAddFruitLiveData
    }

    override fun checkInput(name: String, avatar: String, detail: String, price: String): Boolean {
        if (TextUtils.isEmpty(name)) {
            return false
        }

        if (TextUtils.isEmpty(avatar)) {
            return false
        }

        if (TextUtils.isEmpty(detail)) {
            return false
        }

        if (TextUtils.isEmpty(price)) {
            return false
        }

        try {
            price.toFloat()
        } catch (e: Exception) {
            return false
        }

        return true
    }
}