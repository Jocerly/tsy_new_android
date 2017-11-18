package com.tsy.tsy.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by jay on 2017/11/15.
 */

open class BaseActivity : AppCompatActivity(), BGASwipeBackHelper.Delegate {

    private val compositeDisposable = CompositeDisposable()

    protected lateinit var swipeBackHelper: BGASwipeBackHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        initSwipeFinish()
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun initSwipeFinish() {
        swipeBackHelper = BGASwipeBackHelper(this, this)
    }

    override fun onBackPressed() {
        if(swipeBackHelper.isSliding) {
            return
        }
        swipeBackHelper.backward()
    }

    override fun onSwipeBackLayoutExecuted() {
        swipeBackHelper.swipeBackward()
    }

    override fun onSwipeBackLayoutSlide(slideOffset: Float) {

    }

    override fun onSwipeBackLayoutCancel() {

    }

    override fun isSupportSwipeBack(): Boolean {
        return true
    }

}
