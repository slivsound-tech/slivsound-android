package com.slivsound.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    background = Background,
    surface = Surface,
    surfaceVariant = SurfaceVariant,
    surfaceDim = SurfaceDimmed,
    outline = Outline,
    error = Error
)


private val DarkColors = darkColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    background = Background,
    surface = Surface,
    surfaceVariant = SurfaceVariant,
    onSurface = ContentPrimary,
    onSurfaceVariant = ContentSecondary,
    surfaceDim = SurfaceDimmed,
    outline = Outline,
    error = Error,
    tertiary = Success

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
