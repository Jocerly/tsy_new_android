package com.tsy.tsy;

import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.tsy.base.api.Retrofits;
import com.tsy.base.api.TsyRetrofit;
import com.tsy.base.constants.BasicParams;
import com.tsy.base.constants.PreferenceConstants;
import com.tsy.base.imageloader.ImageLoader;
import com.tsy.base.imageloader.glide.GlideImageLoader;
import com.tsy.base.tool.ChannelTool;
import com.tsy.base.tool.PreferencesLoader;
import com.tsy.base.tool.Toasts;
import com.tsy.base.tool.VersionTool;
import com.tsy.tsy.tool.FakeCrashLibrary;

import me.yokeyword.fragmentation.Fragmentation;
import timber.log.Timber;

/**
 * Created by jay on 2017/11/23.
 */

public class App extends MultiDexApplication {

    private PreferencesLoader mPreferencesLoader;

    private BasicParams mBasicParams;

    @Override
    public void onCreate() {
        super.onCreate();

        initBasicParams();
        TsyRetrofit.setDebug(BuildConfig.DEBUG);
        initTimer();
        ImageLoader.instance.setStrategy(new GlideImageLoader());
        Toasts.install(this);

        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                .install();
    }

    private void initBasicParams() {
        mPreferencesLoader = new PreferencesLoader(this);
        mBasicParams = BasicParams.getInstance();
        mBasicParams.setVersionName(VersionTool.getVersionName(this));
        mBasicParams.setVersionCode(VersionTool.getVersionCode(this));
        mBasicParams.setChannelName(ChannelTool.getChannel(this));
        mBasicParams.setAppToken(mPreferencesLoader.getString(PreferenceConstants.APPTOKEN));
    }

    private void initTimer() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }


    /** A tree which logs important information for crash reporting. */
    private static class CrashReportingTree extends Timber.Tree {
        @Override protected void log(int priority, String tag, @NonNull String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }
            FakeCrashLibrary.log(priority, tag, message);
            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }
}
