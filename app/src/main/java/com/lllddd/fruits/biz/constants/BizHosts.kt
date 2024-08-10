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
        /**
         * Base url
         */
        private const val BASE_URL = "http://192.168.20.144:8080"

        /**
         * Login
         */
        const val LOGIN_URL = "$BASE_URL/login"

        /**
         * Register
         */
        const val REGISTER_URL = "$BASE_URL/register"

        /**
         * Query all fruits
         */
        const val QUERY_ALL_URL = "$BASE_URL/queryAll"

        /**
         * Delete fruit
         */
        const val DELETE_URL = "$BASE_URL/delete"

        /**
         * Modify fruit info
         */
        const val MODIFY_URL = "$BASE_URL/modify"

        /**
         * Add a fruit
         */
        const val ADD_URL = "$BASE_URL/add"
    }
}