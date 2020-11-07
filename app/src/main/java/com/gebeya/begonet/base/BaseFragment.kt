/*
 * Copyright (c) 2020. Independent News & Media Plc.
 */

package com.gebeya.begonet.base

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.Nullable
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


abstract class BaseFragment : Fragment() {

    private var mRootView: View? = null
    abstract val layoutId: Int
    open var title: String? = null

    open var titleRes: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mRootView = inflater.inflate(layoutId, container, false)
//        val toolbar = mRootView?.findViewById<MaterialToolbar>(R.id.materialToolbar)
//        if (toolbar != null) {
//            (activity as BaseActivity).setSupportActionBar(toolbar)
//        }

        return mRootView!!
    }

    override fun onResume() {
        super.onResume()
        if (title != null) {
            getSupportActionBar()?.title = title
        }

        if (titleRes != 0) {
            getSupportActionBar()?.setTitle(titleRes)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mRootView = null
    }

    fun setHomeAsUp() {
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
    }

    fun showHomeEnabled() {
        getSupportActionBar()?.setHomeButtonEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
    }

    fun setTitle(titleRes: Int) {
        this.title = null
        this.titleRes = titleRes

        getSupportActionBar()?.setTitle(titleRes)
    }

    fun hideActionBar() {
        getSupportActionBar()?.hide()
    }

    fun showActionBar() {
        getSupportActionBar()?.show()
    }

    fun disableBackButton() {
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(false);
        getSupportActionBar()?.setDisplayShowHomeEnabled(false);
    }

    fun setWindowSoftInputMode(vararg modes: Int) {
        for (mode in modes) {
            activity?.window?.setSoftInputMode(mode)
        }
    }

    fun updateStatusBarColor(color: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = requireActivity().window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.parseColor(color)
        }
    }

    @SuppressLint("RestrictedApi")
    @Nullable
    fun getSupportActionBar(): ActionBar? {
        val activity = activity
        if (activity is AppCompatActivity) {
            val compatActivity = activity as AppCompatActivity?
            compatActivity!!.supportActionBar?.setShowHideAnimationEnabled(false)
            return compatActivity.supportActionBar
        }
        return null
    }

    companion object {
        const val ADJUST_RESIZE = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        const val ADJUST_NOTHING = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING
        const val ADJUST_PAN = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
        const val STATE_HIDDEN = WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
        const val STATE_VISIBLE = WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
        const val STATE_ALWAYS_HIDDEN = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        const val STATE_ALWAYS_VISIBLE = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
    }
}
