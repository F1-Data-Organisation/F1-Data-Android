package fd.f1.f1dataandroid.extensions

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun getColorFrom(hex: String?): Color {
    if (hex == null) {
        return MaterialTheme.colorScheme.onPrimary
    } else {
        val cleanHexCode = hex.trim().replace("#", "")
        val rgb = cleanHexCode.toLong(16)

        val redValue = ((rgb shr 16) and 0xFF) / 255f
        val greenValue = ((rgb shr 8) and 0xFF) / 255f
        val blueValue = (rgb and 0xFF) / 255f

        return Color(redValue, greenValue, blueValue)
    }
}

fun Color.contrasting(): Color {
    val r = this.red
    val g = this.green
    val b = this.blue

    val luminance = 0.299 * r + 0.587 * g + 0.114 * b

    return if (luminance > 0.5f) {
        Color.Black
    } else {
        Color.White
    }
}