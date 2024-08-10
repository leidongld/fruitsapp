/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/

package com.lllddd.fruits.biz.add.vm

import androidx.lifecycle.LiveData
import com.lllddd.fruits.network.resp.Resp

interface AddPageViewModel {
    /**
     * 添加水果
     *
     * @param params 水果属性
     * @return 返回
     */
    fun addFruit(params: Map<String, String>): LiveData<Resp>

    /**
     * 判断输入是否合法
     *
     * @param name 水果名称
     * @param avatar 水果图片
     * @param detail 水果细节描述
     * @param price 水果价格
     * @return 输入是否合法
     */
    fun checkInput(name: String, avatar: String, detail: String, price: String): Boolean
}
