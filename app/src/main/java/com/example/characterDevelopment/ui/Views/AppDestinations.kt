package com.example.characterDevelopment.ui.Views


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Contract for information needed on every Rally navigation destination
 */
interface AppDestinations {
    val icon: ImageVector
    val route: String
    var screenTitle: String

}

/**
 * Rally app navigation destinations
 */
object MainCharacter : AppDestinations {
    override val icon = Icons.Filled.Accessibility
    override val route = "Level"
    override var screenTitle: String by mutableStateOf("")
}

object AppSettings: AppDestinations {
    override val icon = Icons.Filled.Settings
    override val route = "Configuration"
    override var screenTitle: String by mutableStateOf("")

}

object CharactersList: AppDestinations {
    override val icon = Icons.Filled.Contacts
    override val route = "List"
    override var screenTitle: String by mutableStateOf("")

}

val addCharacterRoute = "Add"

// Screens to be displayed in the top RallyTabRow
val appTabRowScreen = listOf(MainCharacter, CharactersList, AppSettings)
