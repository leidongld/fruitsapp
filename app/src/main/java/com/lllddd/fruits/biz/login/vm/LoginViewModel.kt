/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/

package com.lllddd.fruits.biz.login.vm

import androidx.lifecycle.MutableLiveData
import com.lllddd.fruits.network.resp.Resp

/**
 * author: lllddd
 * created on: 2024/8/7 10:54
 * description:登录ViewModel接口
 */
interface LoginViewModel {
    /**
     * Check whether the input username and password is valid
     *
     * @param username
     * @param password
     *
     * @return whether valid
     */
    fun checkInputLegal(username: String, password: String): Boolean

    /**
     * Login
     *
     * @param username
     * @param password
     */
    fun login(username: String, password: String): MutableLiveData<Resp>
}