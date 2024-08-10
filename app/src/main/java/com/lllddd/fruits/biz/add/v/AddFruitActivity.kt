/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/

package com.lllddd.fruits.biz.add.v

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.lllddd.fruits.biz.constants.BizConstants
import com.lllddd.fruits.R
import com.lllddd.fruits.biz.add.vm.AddPageViewModelImpl
import com.lllddd.fruits.network.resp.Resp

/**
 * author: lllddd
 * created on: 2024/5/28 14:34
 * description:增加水果页
 */
class AddFruitActivity : AppCompatActivity() {
    private lateinit var _viewModel: AddPageViewModelImpl

    private lateinit var mEdtName: EditText

    private lateinit var mEdtAvatar: EditText

    private lateinit var mEdtDetail: EditText

    private lateinit var mEdtPrice: EditText

    private lateinit var mBtnAddFruit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _viewModel = ViewModelProvider(this)[AddPageViewModelImpl::class.java]

        setContentView(R.layout.activity_add)

        initWidgets()
    }

    private fun initWidgets() {
        mEdtName = findViewById(R.id.edt_name)
        mEdtAvatar = findViewById(R.id.edt_avatar)
        mEdtDetail = findViewById(R.id.edt_detail)
        mEdtPrice = findViewById(R.id.edt_price)
        mBtnAddFruit = findViewById(R.id.btn_add)

        mBtnAddFruit.setOnClickListener {
            onAddFruitButtonClick()
        }
    }

    private fun onAddFruitButtonClick() {
        val name = mEdtName.text.toString()
        val avatar = mEdtAvatar.text.toString()
        val detail = mEdtDetail.text.toString()
        val price = mEdtPrice.text.toString()

        if (!_viewModel.checkInput(name, avatar, detail, price)) {
            Toast.makeText(this, "Please fill in the complete information!", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val params = mapOf(
            BizConstants.NAME to name,
            BizConstants.AVATAR to avatar,
            BizConstants.DETAIL to detail,
            BizConstants.PRICE to price,
        )

        _viewModel.addFruit(params).observe(
            this
        ) {
            if (it.respCode == Resp.SUCCESS_CODE) {
                Toast.makeText(this, "Add fruit success.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Add fruit fail.", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }
}
