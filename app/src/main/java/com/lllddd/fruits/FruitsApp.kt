/*******************************************************************************
 * *
 *  * Copyright (C) 2024 Lei Dong
 *  *
 *  * All rights reserved. This software is the intellectual property of Lei Dong,
 *  * and is protected by applicable copyright and other laws.
 *
 ******************************************************************************/

package com.lllddd.fruits

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.os.Bundle
import android.util.Log

/**
 * author: lllddd
 * created on: 2024/5/29 9:49
 * description:
 */
class FruitsApp : Application(), ActivityLifecycleCallbacks {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Log.d("FruitsApp", "attachBaseContext  ${System.currentTimeMillis()}")
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("FruitsApp", "onCreate  ${System.currentTimeMillis()}")
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Log.d("onActivityCreated", activity.localClassName)
    }

    override fun onActivityStarted(activity: Activity) {
        Log.d("onActivityStarted", activity.localClassName)
    }

    override fun onActivityResumed(activity: Activity) {
        Log.d("onActivityResumed", activity.localClassName)
    }

    override fun onActivityPaused(activity: Activity) {
        Log.d("onActivityPaused", activity.localClassName)
    }

    override fun onActivityStopped(activity: Activity) {
        Log.d("onActivityStopped", activity.localClassName)
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        Log.d("onActivitySaveInstanceState", activity.localClassName)
    }

    override fun onActivityDestroyed(activity: Activity) {
        Log.d("onActivityDestroyed", activity.localClassName)
    }
}