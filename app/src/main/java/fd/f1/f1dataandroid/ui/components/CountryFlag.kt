package fd.f1.f1dataandroid.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import coil.compose.AsyncImage
import java.net.URL

@Composable
fun CountryFlag(code: String, width: Int) {
    val codeNew = code.replace(" ", "-").lowercase()

    AsyncImage(
        model = "https://cdn.countryflags.com/thumbs/$codeNew/flag-800.png",
        contentDescription = code,
        modifier = Modifier.width(width.dp)
            .clip(RoundedCornerShape(0.2 * width.dp)),
        contentScale = ContentScale.FillWidth
    )
}

@Preview
@Composable
fun PreviewCountryFlag() {
    Surface(color = MaterialTheme.colorScheme.background) {
        CountryFlag(code = "France", width = 100)
    }
}
