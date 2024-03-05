package com.example.characterDevelopment.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp


val DarkTypography = Typography(
    displayLarge = TextStyle(
        fontWeight = FontWeight.W100,
        fontSize = 96.sp,
    ),
    displayMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 44.sp,
        letterSpacing = 1.5.sp
    ),
    displaySmall = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
    headlineLarge = TextStyle(
        fontWeight = FontWeight.W700,
        fontSize = 34.sp
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 20.sp,
        letterSpacing = 3.sp
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 20.sp,
        letterSpacing = 3.sp
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 3.sp
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.1.em
    ),
    titleSmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        letterSpacing = 0.1.em
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.em
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.2.em
    ),
    bodySmall = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 10.sp
    )
)

val LightTypography = Typography(
    displayLarge = TextStyle(
        fontWeight = FontWeight.W100,
        fontSize = 96.sp,
    ),
    displayMedium = TextStyle(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 44.sp,
        letterSpacing = 1.5.sp,


    ),
    displaySmall = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,


    ),
    headlineLarge = TextStyle(
        fontWeight = FontWeight.W700,
        fontSize = 34.sp,


    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp,
        lineHeight = 20.sp,
        letterSpacing = 4.sp

    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        letterSpacing = 2.sp,

    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 3.sp,


    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.1.em,

    ),
    titleSmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        letterSpacing = 0.1.em,

    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.em,

    ),

    bodyMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.2.em,

    ),
    bodySmall = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 12.sp,

    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 10.sp,

    )
)
