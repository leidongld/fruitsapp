/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/


package com.lllddd.fruits.biz.detail.v

import android.graphics.Bitmap
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lllddd.fruits.biz.constants.BizConstants
import com.lllddd.fruits.R
import com.lllddd.fruits.biz.beans.FruitBean
import com.lllddd.fruits.biz.detail.vm.DetailPageViewModelImpl
import com.lllddd.fruits.network.resp.Resp
import com.lllddd.fruits.utils.BlurUtils

/**
 * author: lllddd
 * created on: 2024/5/17 16:24
 * description:水果详情页
 */
class FruitDetailActivity : AppCompatActivity() {
    private lateinit var _viewModel: DetailPageViewModelImpl

    private lateinit var mImgAvatar: ImageView
    private lateinit var mTxtName: TextView
    private lateinit var mTxtDesc: TextView
    private lateinit var mEdtPrice: EditText
    private lateinit var mBtnModify: FloatingActionButton

    private lateinit var mFruit: FruitBean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _viewModel = ViewModelProvider(this)[DetailPageViewModelImpl::class.java]

        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        setContentView(R.layout.activity_fruit_detail)

        initData()

        initWidgets()
    }

    private fun initWidgets() {
        mTxtName = findViewById(R.id.txt_name)
        mImgAvatar = findViewById(R.id.img_avatar)
        mTxtDesc = findViewById(R.id.txt_detail)
        mEdtPrice = findViewById(R.id.edt_price)
        mBtnModify = findViewById(R.id.btn_modify)

        mTxtName.text = mFruit.name
        Glide.with(this).asBitmap().load(mFruit.avatar).into(object : SimpleTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                val bitmap = BlurUtils.blurBitmap(this@FruitDetailActivity, resource, 20)
                mImgAvatar.setImageBitmap(bitmap)
            }
        })
        mTxtDesc.text = mFruit.detail
        mEdtPrice.setText(mFruit.price.toString())

        mBtnModify.setOnClickListener {
            mFruit.price = mEdtPrice.text.toString().toFloat()
            _viewModel.modifyFruit(mFruit).observe(this) {
                if (Resp.SUCCESS_CODE == it.respCode) {
                    Toast.makeText(this, "Modify fruit success.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Modify fruit fail.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initData() {
        mFruit = intent.getSerializableExtra(BizConstants.FRUIT) as FruitBean
    }
}