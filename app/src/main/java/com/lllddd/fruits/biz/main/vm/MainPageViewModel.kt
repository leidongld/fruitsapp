/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/

package com.lllddd.fruits.biz.main.vm

import androidx.lifecycle.LiveData
import com.lllddd.fruits.network.resp.Resp

/**
 * author: lllddd
 * created on: 2024/5/27 14:18
 * description:主页ViewModel契约
 */
interface MainPageViewModel {
    /**
     * 查询水果
     *
     * @return 返回
     */
    fun queryFruits(): LiveData<Resp>

    /**
     * 删除水果
     *
     * @return 返回
     */
    fun deleteFruit(id: Int): LiveData<Resp>
}