/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/

package com.lllddd.fruits.biz.main.listeners

interface OnFruitItemClickListener {
    /**
     * 水果条目点击回调
     *
     * @param position 游标
     */
    fun onFruitItemClick(position: Int)
}
