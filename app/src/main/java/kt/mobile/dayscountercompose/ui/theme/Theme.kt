package kt.mobile.dayscountercompose.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = Color.Black,
    primaryVariant = Purple700,
    surface = surfaceDark,
    onSurface = Color.White,
    secondary = Color.Black,
    background = Color.Black,
    onBackground = onBackgroundDark
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    surface = surfaceLight,
    onSurface = Color.Black,
    primaryVariant = Purple700,
    secondary = Color.White,
    background = backgroundLight,
    onBackground = Color.White

)

@Composable
fun DaysCounterComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}