
package com.example.characterDevelopment.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val Green500 = Color(0xFF1EB980)
val DarkBlue900 = Color(0xFF26282F)

val lightGreen = Color (244, 254, 193)
val tabGreen = Color (217, 249, 165)
val deepGreen = Color (57, 91, 80)
val textBrown = Color (80, 36, 25)


val DarkColorPalette = darkColors(
    primary = Green500,
    surface = DarkBlue900,
    onSurface = Color.White,
    background = DarkBlue900,
    onBackground = Color.White
)

val ColorPalette =  lightColors(
    primary = deepGreen,
    surface = tabGreen,
    onSurface = textBrown,
    background = lightGreen,
    onBackground = textBrown
)
