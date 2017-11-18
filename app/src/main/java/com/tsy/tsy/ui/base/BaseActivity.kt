package com.tsy.tsy.ui.base

import android.app.ProgressDialog
import android.os.Bundle
import com.tsy.tsy.ui.base.support.SupportActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import me.yokeyword.fragmentation.SwipeBackLayout
import me.yokeyword.fragmentation_swipeback.core.ISwipeBackActivity
import me.yokeyword.fragmentation_swipeback.core.SwipeBackActivityDelegate

/**
 * Created by jay on 2017/11/15.
 */

open class BaseActivity : SupportActivity(), ISwipeBackActivity {

    private val swipeBackDelegate = SwipeBackActivityDelegate(this)

    private val compositeDisposable = CompositeDisposable()

    private lateinit var dialog: ProgressDialog;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        swipeBackDelegate.onCreate(savedInstanceState)

        dialog = ProgressDialog(this)
        dialog.isIndeterminate = true
        dialog.setMessage("请稍后...")
        dialog.setCanceledOnTouchOutside(false)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        swipeBackDelegate.onPostCreate(savedInstanceState)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    protected fun showProgress(show: Boolean) {
        if (show) {
            dialog.show()
        } else {
            dialog.dismiss()
        }
    }

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun getSwipeBackLayout(): SwipeBackLayout {
        return swipeBackDelegate.swipeBackLayout
    }

    override fun setEdgeLevel(edgeLevel: SwipeBackLayout.EdgeLevel?) {
        swipeBackDelegate.setEdgeLevel(edgeLevel)
    }

    override fun setEdgeLevel(widthPixel: Int) {
        swipeBackDelegate.setEdgeLevel(widthPixel)
    }

    override fun swipeBackPriority(): Boolean {
        return swipeBackDelegate.swipeBackPriority()
    }

    override fun setSwipeBackEnable(enable: Boolean) {
        swipeBackDelegate.setSwipeBackEnable(enable)
    }
}
