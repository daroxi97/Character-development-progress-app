package com.example.characterDevelopment.ui.views


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
interface AppScreens {
    val icon: ImageVector
    val route: String
    var screenTitle: String

}

/**
 *app navigation destinations
 */
object MainCharacter : AppScreens {
    override val icon = Icons.Filled.Accessibility
    override val route = "Level"
    override var screenTitle: String by mutableStateOf("")
}

object AppSettings: AppScreens {
    override val icon = Icons.Filled.Settings
    override val route = "Configuration"
    override var screenTitle: String by mutableStateOf("")

}

object CharactersList: AppScreens {
    override val icon = Icons.Filled.Contacts
    override val route = "List"
    override var screenTitle: String by mutableStateOf("")

}

const val addCharacterRoute = "Add"

// Screens to be displayed in the top TabRow
val appTabRowScreen = listOf(MainCharacter, CharactersList, AppSettings)
