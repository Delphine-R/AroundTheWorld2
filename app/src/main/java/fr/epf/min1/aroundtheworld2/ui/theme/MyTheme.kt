package fr.epf.min1.aroundtheworld2.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun MyTheme(content: @Composable () -> Unit) {
    androidx.compose.material3.MaterialTheme( // Use the MaterialTheme from androidx.compose.material3
        colorScheme = ColorScheme(
            primary = Color(0xFF141E46), // Text and icons color
            secondary = Color(0xFF41B06E), // Other colors
            surface =Color(0xFF8DECB4), // Background color
            background = Color(255, 245, 224), // Navigation bar color
            // You can adjust the rest of the colors according to your requirements
            onPrimary = Color.White, // Text and icons color on primary background
            onSecondary = Color.Black, // Text and icons color on secondary background
            onSurface = Color.Black, // Text and icons color on surface
            onBackground = Color(0xFF141E46), // Text and icons color on background
            primaryContainer = Color.Black,
            secondaryContainer = Color.Black,
            error = Color.Red, // Error color
            errorContainer = Color.Red,
            inverseOnSurface = Color.White,
            inversePrimary = Color.White,
            onError = Color.White,
            inverseSurface = Color.Black,
            onErrorContainer = Color.Red,
            onTertiary = Color.Black,
            onPrimaryContainer = Color.White,
            onSecondaryContainer = Color.White, // Add this line
            onSurfaceVariant = Color(0xFF141E46), // Add this line
            onTertiaryContainer = Color.White, // Add this line
            outline = Color.White, // Add this line
            outlineVariant = Color.White, // Add this line
            scrim = Color.White, // Add this line
            surfaceTint = Color.White, // Add this line
            surfaceVariant = Color.White, // Add this line
            tertiary = Color.White, // Add this line
            tertiaryContainer = Color.White // Add this line
        ),
        typography = Typography(), // Use default typography
        shapes = Shapes(), // Use default shapes
        content = content
    )
}