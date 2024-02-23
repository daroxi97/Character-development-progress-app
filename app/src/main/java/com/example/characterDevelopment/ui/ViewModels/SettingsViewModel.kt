package com.example.characterDevelopment.ui.ViewModels

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.characterDevelopment.data.database.entities.AppLanguage
import com.example.characterDevelopment.data.database.entities.Order
import com.example.characterDevelopment.data.database.entities.Theme
import com.example.characterDevelopment.domain.Models.SettingsDomainModel
import com.example.characterDevelopment.domain.UseCases.ChangeLanguageUseCase
import com.example.characterDevelopment.domain.UseCases.GetSettingsUseCase
import com.example.characterDevelopment.domain.UseCases.SaveSettingsUseCase
import com.example.characterDevelopment.utils.getOrderFromDescription
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val saveSettingsUseCase: SaveSettingsUseCase,

    ) : ViewModel() {

    val currentConfiguration: MutableState<SettingsDomainModel?> = mutableStateOf(null)

    //Required for the ui, to show visually the initial language of the device.
    var deviceLanguage: AppLanguage = AppLanguage.ENGLISH
    //If a view set this variable true, means, that if the app is restarted, will return to config screen
    var updateConfiguration: Boolean = false
    private var changeLanguage = true

    init {
        updateConfiguration()
        setInitialDeviceLanguage()

    }

    private fun updateConfiguration() {
        viewModelScope.launch {
            val configuration = getSettingsUseCase()
            currentConfiguration.value = configuration

        }
    }

    fun saveLanguage(context: Context, language: AppLanguage) {
        viewModelScope.launch {
            //Only update if the new language is different from the actual one
            if (currentConfiguration.value?.language != language) {
                changeLanguage(context, language.value)
                //get current configuration from database
                val configuration = getSettingsUseCase()
                //update the language from database
                configuration.language = language
                saveSettingsUseCase(configuration)
                updateConfiguration()

            }
        }

    }

    private fun changeLanguage(context: Context, language: String) {
        //Change the language of the app
        var changeLanguage = ChangeLanguageUseCase(context, language)
        changeLanguage()
    }

    fun saveTheme(theme: Theme) {
        viewModelScope.launch {
            //Only update if the new theme is different from the actual one
            if (currentConfiguration.value?.theme != theme) {
                //get current configuration from database
                val configuration = getSettingsUseCase()
                //update the theme from database
                configuration.theme = theme
                saveSettingsUseCase(configuration)
                updateConfiguration()

            }
        }
    }


    fun saveOrder(orderDescription: String) {
        var orderPattern = getOrderFromDescription(orderDescription)
        viewModelScope.launch {
            //Only update if the new order is different from the actual one
            if (currentConfiguration.value?.order != orderPattern) {
                //get current configuration from database
                val configuration = getSettingsUseCase()
                //update the order from database
                configuration.order = orderPattern
                saveSettingsUseCase(configuration)
                updateConfiguration()

            }
        }
    }

    fun initialSetLanguage(context: Context) {
        //the changeLanguage variable will only be true the first time, so this function can only be called the first time.
        if (changeLanguage) {
            currentConfiguration.value?.language?.let { appLanguage ->
                /*if the app language is the default, means that will always show the language of the device,
                if the user change it manually, will always show the new language set by the user
               */
                if (appLanguage != AppLanguage.DEFAULT) {
                    //change the language to the one set in the configuration previously by the user
                    ChangeLanguageUseCase(context, appLanguage.value).invoke()
                }
            }
            changeLanguage = false
        }
    }

    private fun setInitialDeviceLanguage() {
        //get language of the device
        val deviceInitialLanguage = Locale.getDefault().language
        /*Check if the device language is available in the current app languages, in that case
        update the deviceLanguage variable
         */
        AppLanguage.values().find { it.value == deviceInitialLanguage }?.let {
            deviceLanguage = it
        }
    }
}