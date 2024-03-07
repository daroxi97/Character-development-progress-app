package com.example.characterDevelopment.domain.useCases

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import android.util.Log
import java.util.Locale

class ChangeLanguageUseCase(private var context: Context, var language: String) {

    operator fun invoke() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                // Use LocaleManager for Android 12 and later
                context.getSystemService(LocaleManager::class.java).applicationLocales =
                    LocaleList.forLanguageTags(language)
            } else {
                // For earlier versions, update the app's configuration manually
                val locale = Locale(language)
                Locale.setDefault(locale)

                val resources = context.resources
                val configuration = resources.configuration
                configuration.setLocale(locale)
                resources.updateConfiguration(configuration, resources.displayMetrics)

            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            Log.d("error", "Error trying to  change the language of the app")
        }
    }


}