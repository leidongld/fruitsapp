/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/

package com.lllddd.fruits.biz.constants

/**
 * author: lllddd
 * created on: 2024/5/27 23:01
 * description:网络url
 */
class BizHosts {
    companion object {
        private const val BASE_URL = "http://192.168.20.144:8080"

        const val QUERY_ALL_URL = "$BASE_URL/queryAll"
        const val DELETE_URL = "$BASE_URL/delete"
        const val MODIFY_URL = "$BASE_URL/modify"
        const val ADD_URL = "$BASE_URL/add"
    }
}