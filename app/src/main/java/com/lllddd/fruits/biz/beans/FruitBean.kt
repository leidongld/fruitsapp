/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/


package com.lllddd.fruits.biz.beans

import java.io.Serializable

/**
 * author: lllddd
 * created on: 2024/5/17 16:24
 * description:水果POJO
 */
data class FruitBean(
    val id: Int,
    val name: String,
    val avatar: String,
    val detail: String,
    val valid: String,
    var price: Float
) : Serializable {

}
