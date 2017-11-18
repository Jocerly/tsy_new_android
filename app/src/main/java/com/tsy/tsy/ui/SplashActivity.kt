package com.tsy.tsy.ui

import android.content.Intent
import android.os.Bundle
import com.tsy.tsy.MainActivity
import com.tsy.tsy.R
import com.tsy.tsy.ui.base.BaseActivity
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by jay on 2017/11/16.
 */
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe({
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                })

    }
}