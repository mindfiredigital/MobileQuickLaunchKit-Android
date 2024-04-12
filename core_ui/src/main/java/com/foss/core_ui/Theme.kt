package com.foss.core_ui

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.foss.core.utils.ConfigReader

@Composable
fun MQLKTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    lightTheme: MQLKThemeModel = MQLKThemeModel(
        primary = Red,
        onPrimary = DefaultTextColor,       /// Text Color
        secondary = colorE6E6E6,            /// Border Color
        tertiary = color808080,
        onTertiary = color808080,           /// Placeholder color
        background = Color.White,
        surface = Color.White,
    ),
    darkTheme: MQLKThemeModel = MQLKThemeModel(
        primary = LightPink,
        onPrimary = LightGrey,
        secondary = MedGrey,
        tertiary = Pink40,
        onTertiary = color333333,
        background = DarkBlue,
        surface = Ebony
    ),
    dynamicColor: Boolean = false,
    windowSizeClass: WindowSizeClass,
    byPassConfig: Boolean = false,
    content: @Composable () -> Unit,
) {

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (isDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        isDarkTheme -> darkTheme.toDarkColorScheme()
        else -> lightTheme.toLightColorScheme()
    }
    val view = LocalView.current
    val context = LocalContext.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                !isDarkTheme
        }
    }

    val orientation = when {
        windowSizeClass.width.size > windowSizeClass.height.size -> Orientation.Landscape
        else -> Orientation.Portrait
    }

    val windowSize = when (orientation) {
        Orientation.Portrait -> windowSizeClass.width
        else -> windowSizeClass.height
    }

//    val typography = when (windowSize) {
//        is WindowSize.Small -> typographySmall
//        is WindowSize.Compact -> typographyCompact
//        is WindowSize.Medium -> typographyMedium
//        else -> typographyBig
//    }

    val dimensions = when (windowSize) {
        is WindowSize.Small -> smallDimensions
        is WindowSize.Compact -> compactDimensions
        is WindowSize.Medium -> mediumDimensions
        else -> largeDimensions
    }

    ProvideThemeUtils(dimensions = dimensions, orientation = orientation) {
        MaterialTheme(colorScheme = colorScheme, typography = customTypography, content = {
            if (ConfigReader.readConfig(context) || byPassConfig) {
                content()
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Please provide config file")
                }
            }
        })
    }
}

object AppTheme {
    val dimens: Dimensions
        @Composable get() = LocalAppDimens.current

    val orientation: Orientation
        @Composable get() = LocalOrientationMode.current
}

private fun MQLKThemeModel.toLightColorScheme(): ColorScheme {
    return lightColorScheme(
        primary = this.primary,
        onPrimary = this.onPrimary,
        secondary = this.secondary,
        tertiary = this.tertiary,
        onTertiary = this.onTertiary,
        background = this.background,
        surface = this.surface,
    )
}

private fun MQLKThemeModel.toDarkColorScheme(): ColorScheme {
    return darkColorScheme(
        primary = primary,
        onPrimary = onPrimary,
        secondary = secondary,
        tertiary = tertiary,
        onTertiary = onTertiary,
        background = background,
        surface = surface
    )
}