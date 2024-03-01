package com.example.characterDevelopment.ui.appConfiguration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.characterDevelopment.ui.components.dropDownMenuRowWithConfirmation
import com.example.characterDevelopment.R
import com.example.characterDevelopment.data.database.entities.AppLanguage
import com.example.characterDevelopment.data.database.entities.Theme
import com.example.characterDevelopment.data.database.entities.languages
import com.example.characterDevelopment.data.database.entities.themes
import com.example.characterDevelopment.domain.Models.SettingsDomainModel
import com.example.characterDevelopment.ui.viewModels.CharacterCreatorViewModel
import com.example.characterDevelopment.ui.viewModels.SettingsViewModel
import com.example.characterDevelopment.ui.views.UpdateTextsLanguage
import com.example.characterDevelopment.ui.components.ClickableSimpleTextFieldWithConfirmation

@Composable
fun ConfigurationScreen(charactersVm: CharacterCreatorViewModel, settingsVm: SettingsViewModel) {

    val settings by settingsVm.currentConfiguration

    var language: AppLanguage by remember {
        mutableStateOf(
            getStartingLanguage(
                settingsVm,
                settings
            )
        )
    }
    var theme: Theme by remember { mutableStateOf(getStartingTheme(settings)) }

    var changeTheme: Boolean by remember { mutableStateOf(false) }
    var changeLanguage: Boolean by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        //Set theme dropdown menu, returns a the selected label as an string
        theme = setTheme(
            dropDownMenuRowWithConfirmation(
                stringResource(id = R.string.appTheme),
                elements = themes,
                confirmationTitle = stringResource(id = R.string.changeThemeTitle),
                confirmationSubtitle = stringResource(id = R.string.changeLanguageSubtitle),
                clickAction = {
                    changeTheme = true
                },
                selectedLabel = theme.description
            )
        )

        //Set language dropdown menu, returns a the selected label as an string
        language =
            setLanguage(
                dropDownMenuRowWithConfirmation(
                    stringResource(id = R.string.appLanguage),
                    elements = languages,
                    confirmationTitle = stringResource(id = R.string.changeLanguageTitle),
                    confirmationSubtitle = stringResource(id = R.string.changeLanguageSubtitle),
                    clickAction = {
                        changeLanguage = true
                    },
                    selectedLabel = language.description
                )
            )
   //Button to delete all the saved profiles. If you click it a confirmation window appears.
        ClickableSimpleTextFieldWithConfirmation(
            title = stringResource(id = R.string.charactersListScreenTitle),
            text = stringResource(id = R.string.deleteProfiles),
            confirmationTitle = stringResource(id = R.string.deleteAllProfilesConfirmationTitle),
            confirmationSubtitle = stringResource(id = R.string.deleteConfirmationSubtitle),
            clickAction = {
                charactersVm.deleteAllCharactersFromDatabase()
            },
        )

        if (changeTheme) {
            changeTheme = false
            settingsVm.updateConfiguration = true
            settingsVm.saveTheme(theme)
        }

        if (changeLanguage) {
            changeLanguage = false
            settingsVm.updateConfiguration = true
            settingsVm.saveLanguage(LocalContext.current, language)
            UpdateTextsLanguage()
        }
    }
}

private fun getStartingTheme(settings: SettingsDomainModel?): Theme {
    settings?.theme?.let {
        return it
    }
    return Theme.DARK
}

private fun getStartingLanguage(
    settingsVm: SettingsViewModel,
    settings: SettingsDomainModel?
): AppLanguage {
    //if there the user have set a language, start with that language, if not, use the device language
    settings?.language?.let {
        if (it != AppLanguage.DEFAULT) {
            return it
        }
    }
    return settingsVm.deviceLanguage
}

private fun setLanguage(languageString: String): AppLanguage {

    for (languageValue in AppLanguage.values()) {
        if (languageString == languageValue.description) {
            return languageValue
        }
    }
    return AppLanguage.ENGLISH
}


private fun setTheme(themeString: String): Theme {

    for (themeValue in Theme.values()) {
        if (themeString == themeValue.description) {
            return themeValue

        }
    }
    return Theme.DARK

}



