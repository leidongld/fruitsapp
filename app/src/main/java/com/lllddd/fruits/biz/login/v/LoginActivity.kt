/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/

package com.lllddd.fruits.biz.login.v

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.lllddd.fruits.R
import com.lllddd.fruits.biz.login.vm.LoginViewModel
import com.lllddd.fruits.biz.login.vm.LoginViewModelImpl
import com.lllddd.fruits.biz.main.v.MainActivity
import com.lllddd.fruits.biz.register.v.RegisterActivity
import com.lllddd.fruits.network.resp.Resp

class LoginActivity : AppCompatActivity() {
    private lateinit var _viewModel: LoginViewModel

    private lateinit var mEdtUsername: EditText

    private lateinit var mEdtPassword: EditText

    private lateinit var mBtnLogin: Button

    private lateinit var mBtnRegister: Button

    private lateinit var _launcher: ActivityResultLauncher<Intent>

    private lateinit var mUsername: String
    private lateinit var mPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _viewModel = ViewModelProvider(this)[LoginViewModelImpl::class.java]

        setContentView(R.layout.activity_login)

        initWidgets()

        initLauncher()
    }

    private fun initLauncher() {
        _launcher = registerForActivityResult(StartActivityForResult()) {
            // Do nothing
        }
    }

    private fun initWidgets() {
        mEdtUsername = findViewById(R.id.edt_username)
        mEdtUsername.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                mUsername = mEdtUsername.text.toString()
                mBtnLogin.isEnabled = _viewModel.checkInputLegal(mUsername, mPassword)
            }
        })


        mEdtPassword = findViewById(R.id.edt_password)
        mEdtPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                mPassword = mEdtPassword.text.toString()
                mBtnLogin.isEnabled = _viewModel.checkInputLegal(mUsername, mPassword)
            }
        })


        mBtnLogin = findViewById(R.id.btn_login)
        mBtnLogin.setOnClickListener {
            _viewModel.login(mUsername, mPassword).observe(this) {
                if (it.respCode == Resp.SUCCESS_CODE) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    _launcher.launch(intent)
                    finish()
                } else {
                    mEdtUsername.text = null
                    mEdtPassword.text = null
                    Toast.makeText(
                        this@LoginActivity,
                        "Your username or password is not correct, please try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        mBtnRegister = findViewById(R.id.btn_register)
        mBtnRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            _launcher.launch(intent)
        }
    }
}