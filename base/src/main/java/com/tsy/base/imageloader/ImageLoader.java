package com.tsy.base.imageloader;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;


/**
 * Created by jay on 17/8/3.
 */

public enum ImageLoader implements ImageLoaderStrategy {

    instance;

    private ImageLoaderStrategy mStrategy;

    public void setStrategy(ImageLoaderStrategy strategy) {
        this.mStrategy = strategy;
    }

    @Override
    public <T extends ImageView> void loadImage(T t, String url) {
        mStrategy.loadImage(t, url);
    }

    @Override
    public <T extends ImageView> void loadImage(T t, String url, @DrawableRes int placeHolder) {
        mStrategy.loadImage(t, url, placeHolder);
    }

    @Override
    public <T extends ImageView> void loadImage(T t, String url, @DrawableRes int placeHolder, @DrawableRes int errorDrawable) {
        mStrategy.loadImage(t, url, placeHolder, errorDrawable);
    }


    @Override
    public <T extends ImageView> void loadGifImage(T t, String url) {
        mStrategy.loadGifImage(t, url);
    }

    @Override
    public void clearImageDiskCache(Context context) {
        mStrategy.clearImageDiskCache(context);
    }

    @Override
    public void clearImageMemoryCache(Context context) {
        mStrategy.clearImageMemoryCache(context);
    }

    @Override
    public void trimMemory(Context context, int level) {
        mStrategy.trimMemory(context, level);
    }

    @Override
    public String getCacheSize(Context context) {
        return mStrategy.getCacheSize(context);
    }
}
