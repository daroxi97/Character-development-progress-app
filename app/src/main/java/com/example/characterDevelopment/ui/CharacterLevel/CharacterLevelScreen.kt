package com.example.characterDevelopment.ui.CharacterLevel

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.characterDevelopment.R
import com.example.characterDevelopment.ui.components.CharacterInformationBody
import com.example.compose.rally.ui.components.SimpleRow
import com.example.characterDevelopment.domain.Models.CharacterDomainModel
import com.example.characterDevelopment.ui.ViewModels.CharacterCreatorViewModel
import java.text.NumberFormat
import java.util.Locale


@Composable
fun CharacterScreen(
    navController: NavController,
    mvvm: CharacterCreatorViewModel,
    backArrow: Boolean
) {

    Scaffold(
        topBar = {
            if (backArrow) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(16.dp)
                        .size(48.dp)
                        .clickable { navController.popBackStack() })
            }
        }

    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            CharacterScreenBody(mvvm = mvvm, !backArrow)
        }
    }
}

@Composable
fun CharacterScreenBody(mvvm: CharacterCreatorViewModel, isMainCharacter: Boolean) {
    var character = mvvm.currentCharacter.value

    if (isMainCharacter) {
        character = mvvm.getMainCharacter()
    }

    character?.let { character ->
        //Shows the circle with the level
        CharacterInformationBody(modifier = Modifier.semantics {
            contentDescription = "Character level Screen"
        },
            circleValue = character.level,
            colorsCircle = listOf(Color.Gray, Color.Yellow),
            circleMaxNumber = 100f,
            circleLabel = stringResource(R.string.characterLevel),
            rows = {
                //All the labels with profile information
                characterRows(character)
            })
    } ?: run {}
}

@Composable
fun characterRows(character: CharacterDomainModel) {
    val format = NumberFormat.getNumberInstance(Locale.getDefault())

    SimpleRow(
        name = stringResource(id = R.string.characterName), amount = character.name
    )

    SimpleRow(
        name = stringResource(id = R.string.dateTitle), amount = character.date.toString()
    )

    SimpleRow(
        name = stringResource(id = R.string.characterAge), amount = character.age.toString()
    )

    SimpleRow(
        name = stringResource(id = R.string.characterSalary),
        amount = format.format(character.salary).toString() + "€"
    )

    SimpleRow(
        name = stringResource(id = R.string.characterPatrimony),
        amount = format.format(character.patrimony).toString() + "€"
    )

    SimpleRow(
        name = stringResource(id = R.string.characterMood), amount = character.happiness.description
    )
    SimpleRow(
        name = stringResource(id = R.string.characterHealth), amount = character.health.description
    )
    SimpleRow(
        name = stringResource(id = R.string.characterPhysic),
        amount = character.physicalCondition.description
    )

}







