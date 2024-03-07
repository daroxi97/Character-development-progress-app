package com.example.characterDevelopment.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.characterDevelopment.data.database.entities.Theme


@Composable
fun AppTheme(theme: Theme, content: @Composable () -> Unit) {
    if (theme == Theme.LIGHT) {
        MaterialTheme(colorScheme = ColorPalette, typography = LightTypography, content = content)
    } else {
        MaterialTheme(colorScheme = DarkColorPalette, typography = DarkTypography, content = content)

    }
}

