/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/

package com.lllddd.fruits.biz.detail.vm

import androidx.lifecycle.LiveData
import com.lllddd.fruits.biz.beans.FruitBean
import com.lllddd.fruits.network.resp.Resp

/**
 * author: lllddd
 * created on: 2024/5/27 14:24
 * description:细节页ViewModel契约
 */
interface DetailPageViewModelContract {
    /**
     * 更新水果
     *
     * @param fruit 水果
     * @return 返回
     */
    fun modifyFruit(fruit : FruitBean) : LiveData<Resp>
}