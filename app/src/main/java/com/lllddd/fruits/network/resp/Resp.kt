/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/


package com.lllddd.fruits.network.resp

import java.io.Serializable

/**
 * author: lllddd
 * created on: 2024/5/22 10:39
 * description:网络返回对象
 */
data class Resp(val respCode: Int, val respMsg: String, val data: Any?) : Serializable {
    companion object {
        const val SUCCESS_CODE = 1000
        const val FAIL_CODE = 1001

        const val SUCCESS_MSG = "成功"
        const val FAIL_MSG = "失败"
    }
}


