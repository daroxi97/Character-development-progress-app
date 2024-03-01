/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.characterDevelopment.ui.charactersList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Reorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.characterDevelopment.ui.components.AppTabsRow
import com.example.characterDevelopment.ui.components.CharacterRow
import com.example.characterDevelopment.ui.components.ConfirmationDialog
import com.example.characterDevelopment.ui.components.dropDownMenuIcon
import com.example.characterDevelopment.R
import com.example.characterDevelopment.data.database.entities.orders
import com.example.characterDevelopment.domain.Models.CharacterDomainModel
import com.example.characterDevelopment.ui.characterLevel.CharacterScreen
import com.example.characterDevelopment.ui.viewModels.CharacterCreatorViewModel
import com.example.characterDevelopment.ui.viewModels.SettingsViewModel
import com.example.characterDevelopment.ui.views.AppSettings
import com.example.characterDevelopment.ui.views.AppScreens
import com.example.characterDevelopment.ui.views.CharactersList
import com.example.characterDevelopment.ui.views.MainCharacter
import com.example.characterDevelopment.ui.views.addCharacterRoute
import com.example.characterDevelopment.ui.views.appTabRowScreen
import com.example.characterDevelopment.ui.views.UpdateTextsLanguage
import com.example.characterDevelopment.ui.appConfiguration.ConfigurationScreen

@Composable
fun CharacterListScreen(
    navController: NavController,
    charactersVm: CharacterCreatorViewModel,
    settingsVm: SettingsViewModel
) {
    UpdateTextsLanguage()

    var currentScreen: AppScreens by remember { mutableStateOf(CharactersList) }

    //After changing configuration, sometimes the app gets restarted, in this case return to config screen
    if (settingsVm.updateConfiguration) {
        currentScreen = AppSettings
        settingsVm.updateConfiguration = false
    }

    Scaffold(
        topBar = {
            AppTabsRow(
                allScreens = appTabRowScreen,
                onTabSelected = { screen: AppScreens ->
                    currentScreen = screen
                },
                currentScreen = currentScreen,

                )
        }

    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            //currentScreen is the one selected on the topBar by the user
            when (currentScreen) {
                CharactersList -> CharacterListScreenContent(
                    navController,
                    charactersVm,
                    settingsVm
                )

                MainCharacter -> CharacterScreen(navController, charactersVm, false)
                AppSettings -> ConfigurationScreen(charactersVm, settingsVm)

            }
        }
    }
}


@Composable
fun CharacterListScreenContent(
    navController: NavController,
    charactersViewModel: CharacterCreatorViewModel,
    settingsVm: SettingsViewModel
) {
    val characters by charactersViewModel.listCharacters.observeAsState()

    var confirmDialog: Boolean by remember { mutableStateOf(false) }
    var deletedCharacter: CharacterDomainModel? = null

    Scaffold(bottomBar = {
        //A clickable var with a + button. If pressed go to the creation character profile screen
        BottomAppBar(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .clickable { navController.navigate(addCharacterRoute) }
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center)
            {
                Icon(
                    Icons.Default.Add,
                    //Alignment = Alignment.CenterHorizontally,
                    contentDescription = "add a character profile",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable { navController.navigate(addCharacterRoute) }
                )
            }
        }

    }) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                //Button with a dropwdown with diferent types of orders for the list (name, date, level)
                dropDownMenuIcon(Icons.Filled.Reorder, orders) {
                    settingsVm.saveOrder(it)
                    charactersViewModel.reorderCharacterList(it)
                }
                //List of created character profiles
                characters?.let {
                    it.forEach() { character ->
                        CharacterRow(
                            Modifier.clickable {
                                //if click a profile go to more info screen
                                charactersViewModel.setSelectedCharacter(character)
                                navController.navigate(MainCharacter.route)
                            },
                            character.name,
                            character.date,
                            character.level,
                            setColor(character.level)
                        ) {
                            //if click the remove button
                            deletedCharacter = character
                            confirmDialog = true
                        }
                        //Confirmation dialog about deleting a profile
                        ConfirmationDialog(
                            stringResource(id = R.string.deleteConfirmationTitle),
                            stringResource(id = R.string.deleteConfirmationSubtitle),
                            confirmDialog,
                            {
                                //if press cancel
                                confirmDialog = false
                            },
                            {
                                //if press confirm
                                deletedCharacter?.let { it ->
                                    charactersViewModel.removeCharacter(it.id)
                                }
                                confirmDialog = false
                            })
                    }
                }
            }
        }
    }
}

private fun setColor(level: Int): Color {
    //Set the color of the profile according to his level
    when (level) {
        in 0..24 -> {
            return Color.Gray
        }

        in 25..49 -> {
            return Color.Green
        }

        in 50..74 -> {
            return Color.Magenta
        }

        else -> {
            return Color.Yellow
        }
    }
}






