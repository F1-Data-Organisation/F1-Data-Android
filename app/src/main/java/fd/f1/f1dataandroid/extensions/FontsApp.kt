package fd.f1.f1dataandroid.extensions

import fd.f1.f1dataandroid.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.TextUnit

// Charger les polices depuis res/font
val Formula1Bold = FontFamily(Font(R.font.f1_bold))
val Formula1Regular = FontFamily(Font(R.font.f1_regular))

fun TextStyle.f1Bold(size: TextUnit = 16.sp) = this.copy(
    fontFamily = Formula1Bold,
    fontSize = size
)

fun TextStyle.f1Regular(size: TextUnit = 16.sp) = this.copy(
    fontFamily = Formula1Regular,
    fontSize = size
)