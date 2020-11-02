package com.gebeya.begonet.framework.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gebeya.begonet.framework.util.logD
import com.gebeya.begonet.framework.util.logE

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        d("onCreate")
    }

    override fun onStart() {
        super.onStart()
        d("onStart")
    }

    override fun onResume() {
        super.onResume()
        d("onResume")
    }

    override fun onPause() {
        super.onPause()
        d("onPause")
    }

    override fun onStop() {
        super.onStop()
        d("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        d("onDestroy")
    }

    protected fun d(message: String) {
        logD(this, message)
    }

    protected fun e(message: String) {
        logE(this, message)
    }
}