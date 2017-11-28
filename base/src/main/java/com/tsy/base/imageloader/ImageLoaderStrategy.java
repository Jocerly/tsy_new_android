package com.tsy.base.imageloader;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

/**
 * Created by jay on 17/8/3.
 */

public interface ImageLoaderStrategy {

    <T extends ImageView>void loadImage(T t, String url);

    <T extends ImageView>void loadImage(T t, String url, @DrawableRes int placeHolder);

    <T extends ImageView>void loadImage(T t, String url, @DrawableRes int placeHolder, @DrawableRes int errorDrawable);

    <T extends ImageView>void loadGifImage(T t, String url);

    void clearImageDiskCache(final Context context);

    void clearImageMemoryCache(final Context context);

    void trimMemory(Context context, int level);

    String getCacheSize(final Context context);

}
