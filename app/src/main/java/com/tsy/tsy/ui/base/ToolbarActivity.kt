package com.tsy.tsy.ui.base

import android.support.v7.widget.Toolbar
import com.jay.sunshine.tool.bindView
import com.tsy.tsy.R

/**
 * Created by jay on 2017/11/16.
 */
open class ToolbarActivity : BaseActivity() {


    val toolbar: Toolbar by bindView(R.id.toolbar)

    open fun canBack(): Boolean {
        return false
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)

        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        val actionBar = supportActionBar

        if(actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true)
            if (canBack()) {
                actionBar.setDisplayHomeAsUpEnabled(true)
            }
        }
    }

}