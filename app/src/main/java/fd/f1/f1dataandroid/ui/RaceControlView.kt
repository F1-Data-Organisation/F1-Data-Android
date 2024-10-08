package fd.f1.f1dataandroid.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import fd.f1.f1dataandroid.model.RaceControl
import fd.f1.f1dataandroid.R
import fd.f1.f1dataandroid.extensions.contrasting
import fd.f1.f1dataandroid.extensions.f1Bold
import fd.f1.f1dataandroid.extensions.f1Regular
import fd.f1.f1dataandroid.extensions.toDate
import fd.f1.f1dataandroid.ui.components.ControlFlagGenerator

@SuppressLint("NewApi")
@Composable
fun RaceControlView(
    control: RaceControl,
    modifier: Modifier = Modifier
) {
    val bgString = getBgString(control.flag)
    val bgColor = colorResource(getBgColor(bgString))
    val textColor = if(bgString == null) MaterialTheme.colorScheme.primary
                    else bgColor.contrasting()

    Box(
        contentAlignment = Alignment.TopStart,
        modifier = modifier.padding(top = 20.dp)
    ) {
        Text(
            text = control.message,
            style = TextStyle(color = textColor).f1Regular(16.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(bgColor)
                .padding(15.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.offset(y = (-20).dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.fia_logo),
                contentDescription = "FIA",
                modifier = Modifier
                    .width(50.dp)
                    .clip(CircleShape)
                    .border(5.dp, Color.Gray, CircleShape),
                contentScale = ContentScale.FillWidth
            )

            bgString?.let { flag ->
                ControlFlagGenerator(flag, control.driverNumber)
            }

            Text(
                text = control.date.toDate(withHours = true),
                style = TextStyle(color = textColor).f1Bold(16.sp),
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(bgColor)
                    .padding(10.dp)
            )
        }
    }
}

private fun getBgString(flag: String?): String? {
    val str = "${flag?.replace(" ", "_")?.lowercase()}_flag"
    val contains = R.drawable::class.java.fields.any { it.name == str }
    return if (contains) str
           else null
}

private fun getBgColor(flag: String?): Int {
    val field = R.color::class.java.fields.find { it.name == flag }
    return field?.getInt(null) ?: R.color.gray
}

@Preview(showBackground = true)
@Composable
fun RaceControlPreview() {
    val control = RaceControl(
        date = "2023-05-28T14:30:00.000Z",
        lapNumber = 1,
        category = "Flag",
        flag = "DOUBLE YELLOW",
        scope = "Sector",
        sector = 9,
        message = "YELLOW IN TRACK SECTOR 9",
        driverNumber = null
    )
    RaceControlView(control)
}