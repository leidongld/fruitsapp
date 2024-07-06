/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/

package com.lllddd.fruits.biz.main.listeners

interface OnFruitItemLongClickListener {
    /**
     * 水果条目长按点击回调
     *
     * @param position 游标
     */
    fun onFruitItemLongClick(position: Int)
}
