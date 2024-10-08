package fd.f1.f1dataandroid.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import fd.f1.f1dataandroid.R

@Composable
fun ControlFlagGenerator(flag: String, number: Int?) {
    val numberText = number?.toString() ?: ""

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .border(5.dp, Color.Gray, CircleShape)
    ) {
        Image(
            painter = painterResource(id = R.drawable::class.java.getField(flag).getInt(null)),
            contentDescription = "$flag FLAG",
        )

        Text(
            text = numberText,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ControlFlagGeneratorPreview() {
    ControlFlagGenerator("BLUE", 5)
}