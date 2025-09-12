package com.slivsound.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColors = lightColorScheme(
    primary = Primary,
    onPrimary = Black,
    secondary = Secondary,
    onSecondary = Black,
    background = Background,
    onBackground = TextPrimary,
    surface = Surface,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceVariant,
    outline = BorderDivider,
    error = Error,
    onError = White
)

private val DarkColors = darkColorScheme(
    primary = Primary,
    onPrimary = Black,
    secondary = Secondary,
    onSecondary = Black,
    background = Background,
    onBackground = TextPrimary,
    surface = SurfaceVariant,
    onSurface = TextPrimary,
    surfaceVariant = Surface,
    outline = BorderDivider,
    error = Error,
    onError = White
)

@Composable
fun SlivsoundTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}
