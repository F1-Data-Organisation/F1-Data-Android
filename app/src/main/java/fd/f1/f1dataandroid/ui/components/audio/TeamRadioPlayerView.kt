package fd.f1.f1dataandroid.ui.components.audio

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.*
import coil.compose.AsyncImage
import fd.f1.f1dataandroid.extensions.f1Bold
import fd.f1.f1dataandroid.extensions.getColorFrom
import fd.f1.f1dataandroid.extensions.toDate
import fd.f1.f1dataandroid.model.TeamRadio

@SuppressLint("NewApi")
@Composable
fun TeamRadioPlayerView(radio: TeamRadio, modifier: Modifier = Modifier) {
    val driver = radio.driver

    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = modifier.padding(top = 20.dp)
    ) {
        AudioPlayer(
            audioUrl = radio.recordingURL,
            tintColor = getColorFrom(driver.teamColour),
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .background(Color.Gray)
        )

        Row(
            //modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.offset(y = (-20).dp)
        ) {
            Text(
                text = "${driver.nameAcronym} - ${radio.date.toDate(true)}",
                style = TextStyle(color = getColorFrom(driver.teamColour)).f1Bold(16.sp),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(5.dp))
                    .background(Color.Gray)
                    .padding(10.dp)
            )

            AsyncImage(
                model = driver.headshotUrl,
                contentDescription = driver.fullName,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(getColorFrom(driver.teamColour))
                    .border(5.dp, Color.Gray, CircleShape),

                contentScale = ContentScale.Fit
            )
        }
    }
}