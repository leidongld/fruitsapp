/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/

package com.lllddd.fruits.biz.register.v

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.lllddd.fruits.R
import com.lllddd.fruits.biz.register.vm.RegisterViewModel
import com.lllddd.fruits.biz.register.vm.RegisterViewModelImpl

/**
 * author: lllddd
 * created on: 2024/8/9 15:08
 * description:
 */
class RegisterActivity : AppCompatActivity() {
    private lateinit var _viewModel: RegisterViewModel

    private lateinit var mEdtUsername: EditText

    private lateinit var mEdtPassword: EditText

    private lateinit var mEdtConfirmPassword: EditText

    private lateinit var mEdtEmail: EditText

    private lateinit var mBtnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _viewModel = ViewModelProvider(this)[RegisterViewModelImpl::class.java]

        setContentView(R.layout.activity_register)

        initWidgets()
    }

    private fun initWidgets() {
        mEdtUsername = findViewById(R.id.edt_username)
        mEdtPassword = findViewById(R.id.edt_password)
        mEdtConfirmPassword = findViewById(R.id.edt_confirm_password)
        mEdtEmail = findViewById(R.id.edt_email)
        
        mBtnRegister = findViewById(R.id.btn_register)
        mBtnRegister.setOnClickListener {
            // TODO:  
        }
    }


}