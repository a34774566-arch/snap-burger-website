package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val SnapDarkColorScheme = darkColorScheme(
    primary = SnapRed,
    onPrimary = SnapWhite,
    primaryContainer = SnapRedDark,
    onPrimaryContainer = SnapWhite,
    secondary = SnapYellow,
    onSecondary = SnapBlack,
    secondaryContainer = SnapYellowDark,
    onSecondaryContainer = SnapWhite,
    tertiary = SnapYellowLight,
    onTertiary = SnapBlack,
    background = SnapBlack,
    onBackground = SnapWhite,
    surface = SnapDarkSurface,
    onSurface = SnapWhite,
    surfaceVariant = SnapDarkCard,
    onSurfaceVariant = SnapWhite,
    outline = SnapBorder
)

private val SnapLightColorScheme = lightColorScheme(
    primary = SnapRed,
    onPrimary = SnapWhite,
    primaryContainer = SnapRedDark,
    onPrimaryContainer = SnapWhite,
    secondary = SnapYellow,
    onSecondary = SnapBlack,
    secondaryContainer = SnapYellowDark,
    onSecondaryContainer = SnapBlack,
    tertiary = SnapYellowLight,
    onTertiary = SnapBlack,
    background = SnapBlack, // We keep the signature premium black aesthetic default
    onBackground = SnapWhite,
    surface = SnapDarkSurface,
    onSurface = SnapWhite,
    surfaceVariant = SnapDarkCard,
    onSurfaceVariant = SnapWhite,
    outline = SnapBorder
)

@Composable
fun SnapBurgerTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) SnapDarkColorScheme else SnapLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

// Retain alias for any legacy references or test suites
@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    SnapBurgerTheme(darkTheme = darkTheme, content = content)
}
