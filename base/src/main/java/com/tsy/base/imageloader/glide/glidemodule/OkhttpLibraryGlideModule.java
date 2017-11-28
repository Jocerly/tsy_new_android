package com.tsy.base.imageloader.glide.glidemodule;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.LibraryGlideModule;

/**
 * Created by jay on 17/8/3.
 */

@GlideModule
public class OkhttpLibraryGlideModule extends LibraryGlideModule {

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        super.registerComponents(context, glide, registry);
    }
}
