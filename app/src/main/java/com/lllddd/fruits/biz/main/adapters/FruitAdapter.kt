/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/


package com.lllddd.fruits.biz.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lllddd.fruits.R
import com.lllddd.fruits.biz.beans.FruitBean
import com.lllddd.fruits.biz.main.listeners.OnFruitItemClickListener
import com.lllddd.fruits.biz.main.listeners.OnFruitItemLongClickListener

/**
 * author: lllddd
 * created on: 2024/5/17 16:24
 * description:水果适配器
 */
class FruitAdapter(private val context: Context, private val fruitList: List<FruitBean>) :
    RecyclerView.Adapter<FruitAdapter.ViewHolder>() {
    var itemClickListener: OnFruitItemClickListener? = null
        set(value) {
            field = value
        }
    var itemLongClickListener: OnFruitItemLongClickListener? = null
        set(value) {
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fruit, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruitBean = fruitList[position]

        Glide.with(context).load(fruitBean.avatar).into(holder.imgAvatar)

        holder.txtName.text = fruitBean.name

        holder.itemView.setOnClickListener {
            itemClickListener?.onFruitItemClick(position)
        }

        holder.itemView.setOnLongClickListener {
            itemLongClickListener?.onFruitItemLongClick(position)
            true
        }
    }

    override fun getItemCount(): Int {
        return fruitList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgAvatar: ImageView = itemView.findViewById(R.id.img_avatar)
        val txtName: TextView = itemView.findViewById(R.id.txt_name)
    }
}
