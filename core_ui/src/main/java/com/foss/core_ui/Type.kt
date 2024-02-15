package com.foss.core_ui

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val customTypography = Typography(
    displaySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_bold_700)),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = DefaultTextColor
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_bold_700)),
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        color = DefaultTextColor
    ),
    displayLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_bold_700)),
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp
    ),

    headlineSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_regular_400)),
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        color = DefaultTextColor
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_bold_700)),
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        color = DefaultTextColor
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_bold_700)),
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        color = DefaultTextColor,
    ),

    titleSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_bold_700)),
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = DefaultTextColor,
        lineHeight = 26.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_bold_700)),
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = DefaultTextColor,
        lineHeight = 26.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_bold_700)),
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        color = DefaultTextColor,
        lineHeight = 26.sp
    ),

    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_regular_400)),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = DefaultTextColor,
        lineHeight = 26.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_regular_400)),
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = DefaultTextColor
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_regular_400)),
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        color = DefaultTextColor,
        lineHeight = 26.sp
    ),

    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_regular_400)),
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = DefaultTextColor
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_regular_400)),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = DefaultTextColor
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_regular_400)),
        fontWeight = FontWeight.Normal,
        color = DefaultTextColor,
        fontSize = 16.sp
    )
)

