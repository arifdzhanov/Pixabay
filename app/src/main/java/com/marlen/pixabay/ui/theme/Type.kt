package com.marlen.pixabay.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    headlineMedium = TextStyle(
        fontSize = 24.sp,
        lineHeight = 36.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.2.sp,
    ),
    headlineSmall = TextStyle(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.2.sp,
    ),
    titleMedium = TextStyle(
        fontSize = 18.sp,
        lineHeight = 28.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.2.sp,
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.2.sp,
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.2.sp,
    ),
    labelLarge = TextStyle(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.2.sp,
    )
)