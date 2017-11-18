package com.tsy.tsy

import android.support.multidex.MultiDexApplication
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper

/**
 * Created by jay on 2017/11/15.
 */
class App : MultiDexApplication() {
    companion object {
        val SITE_ID = "kf_9098"
        val SDK_KEY = "7DFF8E8D-6504-48DD-8034-E5DAD2CBE750"
    }

    val mt = "Android"

    override fun onCreate() {
        super.onCreate()

        BGASwipeBackHelper.init(this, null)
    }
}