package com.foss.core_ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember

/**
 *
 * @Description:
 *
 *
 * @Author: Abhishek Kumar
 * Created On: 13-12-2023
 */
@Composable
fun ProvideThemeUtils(
    dimensions: Dimensions,
    orientation: Orientation,
    content: @Composable () ->Unit
) {
    val dimSet = remember{dimensions}
    val newOrientation = remember{orientation}
    CompositionLocalProvider(
        LocalAppDimens provides dimSet,
        LocalOrientationMode provides newOrientation,
        content = content
    )
}

val LocalAppDimens = compositionLocalOf {
    smallDimensions
}

val LocalOrientationMode = compositionLocalOf {
    Orientation.Portrait
}