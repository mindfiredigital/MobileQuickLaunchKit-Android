package com.foss.core_ui

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 *
 * @Description:
 *
 *
 * @Author: Abhishek Kumar
 * Created On: 13-12-2023
 */
data class Dimensions(
    val default: Dp = 0.dp,
    val spaceExtraSmall: Dp = 4.dp,
    val spaceSmall: Dp = 8.dp,
    val spaceMedium: Dp = 16.dp,
    val spaceLarge: Dp = 32.dp,
    val spaceExtraLarge: Dp = 64.dp
)

val smallDimensions = Dimensions(
    default = 2.dp,
    spaceExtraSmall = 4.dp,
    spaceSmall = 8.dp,
    spaceMedium = 16.dp,
    spaceLarge = 32.dp,
    spaceExtraLarge = 64.dp
)

val compactDimensions = Dimensions(
    default = 3.dp,
    spaceExtraSmall = 5.dp,
    spaceSmall = 8.dp,
    spaceMedium = 11.dp,
    spaceLarge = 16.dp,
    spaceExtraLarge = 64.dp
)

val mediumDimensions = Dimensions(
    default = 5.dp,
    spaceExtraSmall = 7.dp,
    spaceSmall = 10.dp,
    spaceMedium = 13.dp,
    spaceLarge = 18.dp,
    spaceExtraLarge = 72.dp
)

val largeDimensions = Dimensions(
    default = 8.dp,
    spaceExtraSmall = 11.dp,
    spaceSmall = 15.dp,
    spaceMedium = 20.dp,
    spaceLarge = 25.dp,
    spaceExtraLarge = 80.dp
)

