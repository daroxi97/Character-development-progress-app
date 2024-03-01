package com.example.characterDevelopment.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.characterDevelopment.data.database.entities.Theme

@Composable
fun AppTheme(theme: Theme, content: @Composable () -> Unit) {
    if (theme == Theme.LIGHT) {
        MaterialTheme(colors = ColorPalette, typography = LightTypography, content = content)
    } else {
        MaterialTheme(colors = DarkColorPalette, typography = DarkTypography, content = content)

    }
}

