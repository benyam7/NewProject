/*
 * Copyright (c) 2020. Independent News & Media Plc.
 */

package  com.addisfortune.printversion.base.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.addisfortune.printversion.base.util.ContextWrapper
import com.addisfortune.printversion.base.util.LocaleHelper
import java.util.*

abstract class BaseActivity : AppCompatActivity() {
    abstract val layoutId: Int
    var primaryBaseActivity: Context? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
    }


    override fun attachBaseContext(newBase: Context?) {
        primaryBaseActivity = newBase
        val newLocale = Locale(LocaleHelper.getLanguage())
        val context = ContextWrapper.wrap(newBase, newLocale)
        super.attachBaseContext(context)
    }

}
