package cn.tsy.base.plugin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * 插件的接口
 */
public interface PluginInterface {
    void setContentView(int layoutResID);

    <T extends View> T findViewById(int id);

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onDestroy();

    void onPause();

    void onSaveInstanceState(Bundle outState);

    /**
     * 需要宿主app注入给插件app上下文
     */
    void attach(Activity activity);
}
