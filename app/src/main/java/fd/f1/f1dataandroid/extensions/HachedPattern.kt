package fd.f1.f1dataandroid.extensions

import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.*

fun Modifier.hatched(foreground: Color, lineWidth: Double = 4.0): Modifier = this.then(
    Modifier.drawBehind {
        val spacing = 10.dp.toPx()
        val angle = (PI / 4).toFloat()

        val lineLength = size.height
        for (i in -size.height.toInt() until size.width.toInt() step spacing.toInt()) {
            val startX = i.toFloat()
            val startY = 0f
            val endX = startX + lineLength * tan(angle)
            val endY = lineLength

            drawLine(
                color = foreground,
                start = Offset(startX, startY),
                end = Offset(endX, endY),
                strokeWidth = lineWidth.dp.toPx()
            )
        }
    }
)

@Preview(showBackground = true)
@Composable
fun HachedPatternTest() {
    Box(modifier = Modifier
        .size(200.dp)
        .hatched(Color.Black)
    ) {
        // Dessine ici si nécessaire
    }
}