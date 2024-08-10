/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/

package com.lllddd.fruits.biz.login.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.lllddd.fruits.biz.constants.BizConstants
import com.lllddd.fruits.biz.login.m.LoginModel
import com.lllddd.fruits.network.callback.OkHttpCallback
import com.lllddd.fruits.network.resp.Resp
import okhttp3.Response

/**
 * author: lllddd
 * created on: 2024/8/7 10:54
 * description:登录ViewModel
 */
class LoginViewModelImpl(application: Application) : AndroidViewModel(application), LoginViewModel {
    private val _model = LoginModel()

    private val mLoginFlag = MutableLiveData<Resp>()

    override fun checkInputLegal(username: String, password: String): Boolean {
        return username.isNotEmpty() && password.length >= 8
    }

    override fun login(username: String, password: String): MutableLiveData<Resp> {
        val params = mapOf(
            BizConstants.USERNAME to username,
            BizConstants.PASSWORD to password
        )

        _model.login(params, object : OkHttpCallback {
            override fun onSuccess(response: Response) {
                mLoginFlag.postValue(Resp(Resp.SUCCESS_CODE, Resp.SUCCESS_MSG, null))
            }

            override fun onFailure(e: Exception) {
                mLoginFlag.postValue(Resp(Resp.FAIL_CODE, Resp.FAIL_MSG, null))
            }
        })

        return mLoginFlag
    }
}