package com.example.characterDevelopment.ui.views

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.characterDevelopment.ui.addCharacter.AddCharacterScreen
import com.example.characterDevelopment.R
import com.example.characterDevelopment.data.database.entities.Theme
import com.example.characterDevelopment.data.database.entities.Health
import com.example.characterDevelopment.data.database.entities.Mood
import com.example.characterDevelopment.data.database.entities.PhysicalCondition
import com.example.characterDevelopment.data.database.entities.Order
import com.example.characterDevelopment.domain.Models.SettingsDomainModel
import com.example.characterDevelopment.ui.characterLevel.CharacterScreen
import com.example.characterDevelopment.ui.charactersList.CharacterListScreen
import com.example.characterDevelopment.ui.viewModels.CharacterCreatorViewModel
import com.example.characterDevelopment.ui.viewModels.SettingsViewModel
import com.example.characterDevelopment.ui.theme.AppTheme

@Composable
fun AppNavigation() {


    val characterVm: CharacterCreatorViewModel = hiltViewModel()
    val settingsVm: SettingsViewModel = hiltViewModel()

    settingsVm.currentConfiguration.value?.language?.value?.let {
        settingsVm.initialSetLanguage(LocalContext.current)
    }

    val characters by characterVm.listCharacters.observeAsState()
    val response by characterVm.firstCoroutineCompleted.observeAsState()

    val configuration: SettingsDomainModel? by settingsVm.currentConfiguration




    AppTheme(configuration?.theme ?: Theme.DARK) {

        if (response == true) {

            val navController = rememberNavController()
            val initialDestination = characters?.let {
                CharactersList.route
            } ?: addCharacterRoute

            NavHost(navController = navController, startDestination = initialDestination) {

                composable(route = MainCharacter.route, enterTransition = {
                    return@composable fadeIn(tween(1000))
                }, exitTransition = {
                    return@composable slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
                    )
                }, popEnterTransition = {
                    return@composable slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.End, tween(500)
                    )
                }) {
                    CharacterScreen(navController, characterVm, true)
                }
                composable(route = CharactersList.route, enterTransition = {
                    return@composable fadeIn(tween(1000))
                }, exitTransition = {
                    return@composable slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
                    )
                }, popEnterTransition = {
                    return@composable slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.End, tween(500)
                    )
                }) {
                    CharacterListScreen(navController, characterVm, settingsVm)
                }

                composable(route = addCharacterRoute, enterTransition = {
                    return@composable fadeIn(tween(1000))
                }, exitTransition = {
                    return@composable slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
                    )
                }, popEnterTransition = {
                    return@composable slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.End, tween(500)
                    )
                }) {
                    (AddCharacterScreen(navController, characterVm))
                }

            }

        }
    }
}

@Composable
fun UpdateTextsLanguage() {
    Health.UNHEALTHY.description = stringResource(id = R.string.unhealthLabel)
    Health.HEALTHY.description = stringResource(id = R.string.healthyLabel)
    Health.NORMAL.description = stringResource(id = R.string.normalLabel)
    Health.FINE.description = stringResource(id = R.string.fineLabel)
    Health.HORRIBLE.description = stringResource(id = R.string.horribleLabel)

    Mood.ECSTATIC.description = stringResource(id = R.string.ecstaticLabel)
    Mood.DEVASTATED.description = stringResource(id = R.string.devastatedLabel)
    Mood.NORMAL.description = stringResource(id = R.string.normalLabel)
    Mood.HAPPY.description = stringResource(id = R.string.happyLabel)
    Mood.SAD.description = stringResource(id = R.string.sadLabel)

    PhysicalCondition.UNFIT.description = stringResource(id = R.string.unfitLabel)
    PhysicalCondition.NOSHAPE.description = stringResource(id = R.string.outOfShapeLabel)
    PhysicalCondition.AVERAGE.description = stringResource(id = R.string.averageLabel)
    PhysicalCondition.FINE.description = stringResource(id = R.string.fineLabel)
    PhysicalCondition.FIT.description = stringResource(id = R.string.fitLabel)

    Order.DATE.description = stringResource(id = R.string.dateOrderText)
    Order.NAME.description = stringResource(id = R.string.nameOrderText)
    Order.LEVEL.description = stringResource(id = R.string.levelOrderText)

    Theme.DARK.description = stringResource(id = R.string.darkTheme)
    Theme.LIGHT.description = stringResource(id = R.string.lightTheme)

    MainCharacter.screenTitle = stringResource(id = R.string.mainCharacterScreenTitle)
    AppSettings.screenTitle = stringResource(id = R.string.settingsScreenTitle)
    CharactersList.screenTitle = stringResource(id = R.string.charactersListScreenTitle)
}

var language = "ca"

