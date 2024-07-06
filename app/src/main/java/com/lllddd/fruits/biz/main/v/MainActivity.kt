/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/


package com.lllddd.fruits.biz.main.v

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lllddd.fruits.biz.constants.BizConstants
import com.lllddd.fruits.R
import com.lllddd.fruits.biz.beans.FruitBean
import com.lllddd.fruits.biz.add.v.AddFruitActivity
import com.lllddd.fruits.biz.detail.v.FruitDetailActivity
import com.lllddd.fruits.biz.main.adapters.FruitAdapter
import com.lllddd.fruits.biz.main.listeners.OnFruitItemClickListener
import com.lllddd.fruits.biz.main.listeners.OnFruitItemLongClickListener
import com.lllddd.fruits.biz.main.vm.MainPageViewModel
import com.lllddd.fruits.network.resp.Resp


/**
 * author: lllddd
 * created on: 2024/5/17 16:24
 * description:主页
 */
class MainActivity : AppCompatActivity(), OnFruitItemClickListener, OnFruitItemLongClickListener {
    private lateinit var _viewModel: MainPageViewModel

    private val mFruitList: MutableList<FruitBean> = mutableListOf()

    private lateinit var mImgNoContent: ImageView

    private lateinit var mRcvFruits: RecyclerView

    private lateinit var mBtnAddFruit: FloatingActionButton

    private lateinit var mAdapter: FruitAdapter

    private lateinit var _launcher: ActivityResultLauncher<Intent>

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _viewModel = ViewModelProvider(this).get(MainPageViewModel::class.java)

        setContentView(R.layout.activity_main)

        initWidgets()

        // 查询水果
        queryFruits()

        initLauncher()
    }

    private fun initLauncher() {
        _launcher = registerForActivityResult(StartActivityForResult()) {
            queryFruits()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun queryFruits() {
        _viewModel.queryFruits().observe(this) {
            if (Resp.SUCCESS_CODE == it.respCode) {
                mImgNoContent.visibility = View.GONE
                mRcvFruits.visibility = View.VISIBLE
                mBtnAddFruit.visibility = View.VISIBLE

                mFruitList.clear()
                mFruitList.addAll(it.data as List<FruitBean>)
                mAdapter.notifyDataSetChanged()
            } else {
                mImgNoContent.visibility = View.VISIBLE
                mRcvFruits.visibility = View.GONE
                mBtnAddFruit.visibility = View.GONE

                Toast.makeText(this@MainActivity, "Query fruits fail.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initWidgets() {
        mImgNoContent = findViewById(R.id.img_no_content)

        mRcvFruits = findViewById(R.id.rcv_fruits)
        mAdapter = FruitAdapter(this, mFruitList)
        mRcvFruits.adapter = mAdapter
        mAdapter.itemClickListener = this
        mAdapter.itemLongClickListener = this

        mBtnAddFruit = findViewById(R.id.btn_add)
        mBtnAddFruit.setOnClickListener {
            val intent = Intent(this, AddFruitActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            _launcher.launch(intent)
        }
    }

    override fun onFruitItemClick(position: Int) {
        val intent = Intent(this, FruitDetailActivity::class.java)

        intent.putExtra(BizConstants.FRUIT, mFruitList[position])

        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

        _launcher.launch(intent)
    }

    override fun onFruitItemLongClick(position: Int) {
        showDeleteWarningDialog(position)
    }

    private fun showDeleteWarningDialog(position: Int) {
        AlertDialog.Builder(this)
            .setTitle("Warning")
            .setMessage("Are you sure to delete?")
            .setPositiveButton(
                "Confirm"
            ) { _, _ ->
                // 删除水果
                _viewModel.deleteFruit(mFruitList[position].id).observe(this) {
                    if (Resp.SUCCESS_CODE == it.respCode) {
                        mFruitList.removeAt(position)
                        mAdapter.notifyItemRemoved(position)
                    } else {
                        Toast.makeText(this@MainActivity, "Delete fruit fail.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
            .setNegativeButton(
                "Cancel"
            ) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}