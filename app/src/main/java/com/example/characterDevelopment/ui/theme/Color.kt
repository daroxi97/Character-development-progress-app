package com.example.characterDevelopment.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val Green500 = Color(0xFF1EB980)
val DarkBlue900 = Color(0xFF26282F)
val LighterDarkBlue900 = Color(0xFF313339)


val lightGreen = Color (244, 254, 193)
val tabGreen = Color (217, 249, 165)
val deepGreen = Color (57, 91, 80)
val textBrown = Color (80, 36, 25)


val DarkColorPalette = darkColorScheme(
    primary = Green500,
    surface = DarkBlue900,
    surfaceVariant = LighterDarkBlue900,
    onSurface = Color.White,
    background = DarkBlue900,
    onBackground = Color.White
)

val ColorPalette =  lightColorScheme(
    primary = deepGreen,
    surface = tabGreen,
    surfaceVariant = tabGreen,
    onSurface = textBrown,
    background = lightGreen,
    onBackground = textBrown
)
