package com.example.characterDevelopment.ui.views


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

                AppNavigation()


        }
    }

/*
override fun attachBaseContext(newBase: Context) {
    super.attachBaseContext(ContextWrapper(newBase.setAppLocale(appLanguage.value)))
}

 */


}
