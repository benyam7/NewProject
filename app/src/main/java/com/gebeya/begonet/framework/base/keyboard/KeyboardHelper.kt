/*
 * Copyright (c) 2020. Independent News & Media Plc.
 */

package com.addisfortune.printversion.base.keyboard

import android.content.Context
import android.os.ResultReceiver
import android.view.View
import android.view.inputmethod.InputMethodManager

import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

class KeyboardHelper(private val context: Context) {

    private val mShowImeRunnable = Runnable {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        if (imm != null) {
            var showSoftInputUnchecked: Method? = null
            try {
                showSoftInputUnchecked = imm.javaClass
                    .getMethod(
                        "showSoftInputUnchecked",
                        Int::class.javaPrimitiveType,
                        ResultReceiver::class.java
                    )
            } catch (e: NoSuchMethodException) {
            }

            if (showSoftInputUnchecked != null) {
                try {
                    showSoftInputUnchecked.invoke(imm, 0, null)
                } catch (e: IllegalAccessException) {
                } catch (e: InvocationTargetException) {
                }

            }
        }
    }

    fun setImeVisibility(view: View, visible: Boolean) {
        if (visible) {
            view.post(mShowImeRunnable)
            view.postDelayed(mShowImeRunnable, 100)
        } else {
            view.removeCallbacks(mShowImeRunnable)
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
