package com.tsy.tsy

import android.support.multidex.MultiDexApplication
import com.tsy.base.constants.BasicParams
import com.tsy.base.constants.PreferenceConstants
import com.tsy.base.tool.ChannelTool
import com.tsy.base.tool.PreferencesLoader
import com.tsy.base.tool.VersionTool

/**
 * Created by jay on 2017/11/15.
 */
class App : MultiDexApplication() {
    companion object {
        val SITE_ID = "kf_9098"
        val SDK_KEY = "7DFF8E8D-6504-48DD-8034-E5DAD2CBE750"
    }

    lateinit var preferences: PreferencesLoader

    override fun onCreate() {
        super.onCreate()

        preferences = PreferencesLoader(this)

        initParams()
    }


    private fun initParams() {
        BasicParams.channelName = ChannelTool.getChannel(this)
        BasicParams.versionName = VersionTool.getVersionName(this)
        BasicParams.versionCode = VersionTool.getVersionCode(this)
        BasicParams.appToken = preferences.getString(PreferenceConstants.APPTOKEN)
    }
}