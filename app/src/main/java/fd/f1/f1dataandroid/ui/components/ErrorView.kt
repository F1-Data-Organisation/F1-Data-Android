package fd.f1.f1dataandroid.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fd.f1.f1dataandroid.extensions.f1Bold
import fd.f1.f1dataandroid.service.WSError

// Définir ErrorView comme une fonction composable
@Composable
fun ErrorView(error: Throwable) {
    var status: Int? by remember { mutableStateOf(null) }
    var color: Color by remember { mutableStateOf(Color.Gray) }

    // Traiter l'erreur pour définir la couleur et le statut
    when (error) {
        is WSError.InvalidStatusCode -> {
            status = error.code
            color = if (error.code > 404) Color.Red else Color.Yellow
        }
        is WSError.InvalidURL -> {
            color = Color.Blue
        }
        else -> {
            color = Color.Gray
        }
    }

    // Affichage de l'erreur
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .background(color, shape = RoundedCornerShape(10.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Warning, // Remplace par ton icône
            contentDescription = "Error Icon",
            modifier = Modifier.size(40.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        // Afficher le message d'erreur
        val errorMessage = status?.let { "Error $it - ${error.localizedMessage}" } ?: error.localizedMessage
        Text(
            text = errorMessage ?: "Unknown Error",
            style = TextStyle().f1Bold(16.sp),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorPreview() {
    ErrorView(error = WSError.InvalidStatusCode(504))
}