/*
 * Copyright (c) 2020. Independent News & Media Plc.
 */

package com.addisfortune.printversion.base.ktx

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.addisfortune.printversion.base.ui.BaseFragment
import com.google.firebase.analytics.FirebaseAnalytics

inline fun Context.logEvent(event: String, bundle: Bundle) =
    FirebaseAnalytics.getInstance(this).logEvent(
        event, bundle
    )

fun Activity.setScreenName(screenName: String) {
    FirebaseAnalytics.getInstance(this).setCurrentScreen(this, screenName, null)
}

fun BaseFragment.setScreenName(screenName: String) {
    this.activity?.setScreenName(screenName)
}