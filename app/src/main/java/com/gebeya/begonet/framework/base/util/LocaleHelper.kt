package com.addisfortune.printversion.base.util

import android.annotation.TargetApi
import android.app.Application
import android.content.Context
import android.os.Build
import io.paperdb.Paper
import java.util.*


class LocaleHelper {


    companion object {

        private const val SELECTED_LANGUAGE = "app.preference.language"
        const val FALLBACK_LANGUAGE = "en"
        private lateinit var context: Application
        fun init(c: Application) {
            context = c
        }


        fun onAttach(): Context {
            val lang = getPersistedData()
            return setLocale(lang)
        }

        fun onAttach(defaultLanguage: String): Context {
            val lang =
                getPersistedData()
            return setLocale(lang)
        }

        fun getLanguage(): String {
            return getPersistedData()
        }

        fun setLocale(language: String?): Context {
            persist(language)

            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                updateResources(context, language)
            } else updateResourcesLegacy(context, language)

        }

        private fun getPersistedData(): String {
            return Paper.book().read(SELECTED_LANGUAGE, FALLBACK_LANGUAGE)
        }

        private fun persist(language: String?) {
            Paper.book().write(SELECTED_LANGUAGE, language)
        }

        @TargetApi(Build.VERSION_CODES.N)
        private fun updateResources(context: Context, language: String?): Context {
            val locale = Locale(language!!)
            Locale.setDefault(locale)

            val configuration = context.resources.configuration
            configuration.setLocale(locale)

            return context.createConfigurationContext(configuration)
        }

        private fun updateResourcesLegacy(context: Context, language: String?): Context {
            val locale = Locale(language!!)
            Locale.setDefault(locale)

            val resources = context.resources

            val configuration = resources.configuration
            configuration.locale = locale

            resources.updateConfiguration(configuration, resources.displayMetrics)

            return context
        }
    }


}
