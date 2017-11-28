package com.tsy.base.imageloader.glide;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tsy.base.imageloader.ImageLoaderStrategy;
import com.tsy.base.tool.FileTool;

import java.io.File;

/**
 * Created by jay on 17/8/3.
 */

public class GlideImageLoader implements ImageLoaderStrategy {


    @Override
    public <T extends ImageView> void loadImage(T t, String url) {
        Glide.with(t).load(url).into(t);
    }

    @Override
    public <T extends ImageView> void loadImage(T t, String url, @DrawableRes int placeHolder) {
        RequestOptions options = new RequestOptions().placeholder(placeHolder);
        Glide.with(t).load(url).apply(options).into(t);
    }

    @Override
    public <T extends ImageView> void loadImage(T t, String url, @DrawableRes int placeHolder, @DrawableRes int errorDrawable) {
        RequestOptions options = new RequestOptions().placeholder(placeHolder).error(errorDrawable);
        Glide.with(t).load(url).apply(options).into(t);
    }

    @Override
    public <T extends ImageView> void loadGifImage(T t, String url) {
        Glide.with(t).asGif().load(url).into(t);
    }

    @Override
    public void clearImageDiskCache(Context context) {
        Glide.get(context.getApplicationContext()).clearDiskCache();
    }

    @Override
    public void clearImageMemoryCache(Context context) {
        Glide.get(context.getApplicationContext()).clearMemory();
    }

    @Override
    public void trimMemory(Context context, int level) {
        Glide.get(context.getApplicationContext()).trimMemory(level);
    }

    @Override
    public String getCacheSize(Context context) {
        File cacheDir = Glide.getPhotoCacheDir(context.getApplicationContext());
        String size = "";
        try {
            size = FileTool.getFormatSize(FileTool.getFolderSize(cacheDir));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }


}
