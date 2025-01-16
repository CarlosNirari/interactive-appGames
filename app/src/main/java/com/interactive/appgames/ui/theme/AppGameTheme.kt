package com.interactive.appgames.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 15/01/2025
 */

val LightColorPalette = lightColorScheme(
    primary = primaryColor,
    secondary = secondaryColor,
    background = backgroundColor,
    surface = surfaceColor,
    onPrimary = onPrimaryColor,
    onSurface = onSurfaceColor
)

val DarkColorPalette = darkColorScheme(
    primary = primaryColor,
    secondary = secondaryColor,
    background = Color.Black,
    surface = Color.DarkGray,
    onPrimary = onPrimaryColor,
    onSurface = Color.White
)


@Composable
fun AppGamesTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        //shapes = Shapes,
        content = content
    )
}