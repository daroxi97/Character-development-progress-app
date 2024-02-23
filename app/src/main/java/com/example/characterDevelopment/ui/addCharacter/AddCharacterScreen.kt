package com.example.characterDevelopment.ui.addCharacter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.rally.ui.components.BaseDatePickerDialog
import com.example.compose.rally.ui.components.BaseSimpleIntegersField
import com.example.compose.rally.ui.components.BaseSimpleTextField
import com.example.compose.rally.ui.components.BaseSwitch
import com.example.compose.rally.ui.components.ConfirmationDialog
import com.example.compose.rally.ui.components.dropDownMenuRow
import com.example.characterDevelopment.R
import com.example.characterDevelopment.data.database.entities.Health
import com.example.characterDevelopment.data.database.entities.healths
import com.example.characterDevelopment.data.database.entities.Mood
import com.example.characterDevelopment.data.database.entities.moods
import com.example.characterDevelopment.data.database.entities.PhysicalCondition
import com.example.characterDevelopment.data.database.entities.physicalConditions
import com.example.characterDevelopment.ui.ViewModels.CharacterCreatorViewModel
import com.example.characterDevelopment.ui.Views.CharactersList
import com.example.characterDevelopment.utils.capitalizeFirstLetter


@Composable
fun AddCharacterScreen(
    navController: NavController, charactersViewModel: CharacterCreatorViewModel
) {
    Scaffold(
        topBar = {
            if (charactersViewModel.listCharacters.value != null) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(16.dp)
                        .size(30.dp)
                        .clickable { navController.popBackStack() })
            }
        }

    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            //Content of the screen
            CharacterScreenBody(navController, charactersViewModel)
        }
    }
}

@Composable
fun CharacterScreenBody(
    navController: NavController,
    mvvm: CharacterCreatorViewModel
) {
    var confirmDialog: Boolean by remember { mutableStateOf(false) }
    var confirmationButtonActivated = false


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        var name = BaseSimpleTextField(title = stringResource(id = R.string.characterName))

        var date = BaseDatePickerDialog()

        var age =
            BaseSimpleIntegersField(title = stringResource(id = R.string.characterAge))

        var salary =
            BaseSimpleIntegersField(title = stringResource(id = R.string.characterSalary))

        var patrimony =
            BaseSimpleIntegersField(title = stringResource(id = R.string.characterPatrimony))

        var health =
            setHealth(dropDownMenuRow(stringResource(id = R.string.characterHealth), healths))

        var mood = setMood(dropDownMenuRow(stringResource(id = R.string.characterMood), moods))

        var physicalCondition = setPhysicalCondition(
            dropDownMenuRow(
                stringResource(id = R.string.characterPhysic),
                physicalConditions
            )
        )

        var mainCharacter = BaseSwitch(title = stringResource(id = R.string.defaultProfile))

        //check if all labels are set
        if (name.isNotBlank() && date.isNotBlank() && patrimony.isNotBlank() && age.isNotBlank() && salary.isNotBlank() && health != null && mood != null && physicalCondition != null) {
            confirmationButtonActivated = true
            ConfirmationDialog(
                stringResource(id = R.string.confirmCreation),
                stringResource(id = R.string.confirmCreationSubtitle),
                confirmDialog,
                { confirmDialog = false },
                {
                    mvvm.addNewCharacter(
                        name.capitalizeFirstLetter(),
                        date,
                        replaceNonIntChars(salary).toInt(),
                        replaceNonIntChars(age).toInt(),
                        replaceNonIntChars(patrimony).toInt(),
                        health,
                        mood,
                        physicalCondition,
                        mainCharacter
                    )
                    confirmDialog = false
                    navController.navigate(CharactersList.route)
                })
        }
      //Confirmation button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                modifier = Modifier.padding(end = 5.dp),
                enabled = confirmationButtonActivated,
                onClick = {
                    confirmDialog = true
                }) {
                Text(text = stringResource(id = R.string.Confirmation))
            }
        }

    }
}

private fun replaceNonIntChars(value: String): String {
    if (value == "") {
        return "0"
    }
    return value.replace(Regex("[^\\d.]"), "")
}

//Convert the strings into the correspondent enums
private fun setMood(moodString: String): Mood? =
    Mood.values().find { it.description == moodString }

private fun setHealth(healthString: String): Health? =
    Health.values().find { it.description == healthString }


private fun setPhysicalCondition(physicString: String): PhysicalCondition? =
    PhysicalCondition.values().find { it.description == physicString }



