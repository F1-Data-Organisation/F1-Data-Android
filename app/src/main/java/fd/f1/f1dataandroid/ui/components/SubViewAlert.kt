package fd.f1.f1dataandroid.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fd.f1.f1dataandroid.extensions.f1Bold

@Composable
fun SubViewAlert(
    buttonDialog: @Composable (RowScope.() -> Unit),
    content: @Composable (() -> Unit)
) {
    var showDialog by remember { mutableStateOf(false) }

    Box {
        TextButton(
            onClick = { showDialog = true },
            content = buttonDialog
        )

        if (showDialog) {
            AlertDialog(
                text = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        content()
                    }
                },
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    TextButton(
                        onClick = { showDialog = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "OK",
                            style = TextStyle(
                                textDecoration = TextDecoration.Underline,
                                color = MaterialTheme.colorScheme.onPrimary
                            ).f1Bold(16.sp)
                        )
                    }
                }
            )
        }
    }
}