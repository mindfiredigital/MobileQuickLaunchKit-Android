package com.foss.core_ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration

/**
 *
 * @Description:
 *
 *
 * @Author: Abhishek Kumar
 * Created On: 13-12-2023
 */
sealed class WindowSize(val size: Int) {

    data class Small(val smallSize: Int) : WindowSize(smallSize)
    data class Compact(val compactSize: Int) : WindowSize(compactSize)
    data class Medium(val mediumSize: Int) : WindowSize(mediumSize)
    data class Large(val largeSize: Int) : WindowSize(largeSize)
}

data class WindowSizeClass(
    val width: WindowSize,
    val height: WindowSize
)

@Composable
fun rememberWindowSizeClass(): WindowSizeClass {

    val config = LocalConfiguration.current

    val width by remember(config) {
        mutableIntStateOf(config.screenWidthDp)
    }

    val height by remember(config) {
        mutableIntStateOf(config.screenHeightDp)
    }

    val windowWidthClass = when {
        width <= 320 -> WindowSize.Small(width)
        width in 321..440 -> WindowSize.Compact(width)
        width in 441..700 -> WindowSize.Medium(width)
        else -> WindowSize.Large(width)
    }

    val windowHeightClass = when {
        height <= 320 -> WindowSize.Small(height)
        height in 321..440 -> WindowSize.Compact(height)
        height in 441..700 -> WindowSize.Medium(height)
        else -> WindowSize.Large(height)
    }

    return WindowSizeClass(windowWidthClass, windowHeightClass)
}
