package fd.f1.f1dataandroid.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.*
import androidx.navigation.NavBackStackEntry
import coil.compose.AsyncImage
import com.google.gson.Gson
import fd.f1.f1dataandroid.extensions.f1Bold
import fd.f1.f1dataandroid.extensions.f1Regular
import fd.f1.f1dataandroid.extensions.getColorFrom
import fd.f1.f1dataandroid.model.*

@Composable
fun DriverSessionDataView(entry: NavBackStackEntry) {
    val driver = getDriver(entry)
    val session = getSession(entry)

    Column(modifier = Modifier.fillMaxSize()) {
        driver?.let {
            Row(
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 15.dp)
            ) {
                AsyncImage(
                    model = driver.headshotUrl,
                    contentDescription = driver.fullName,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(getColorFrom(driver.teamColour))
                        .size(100.dp),
                    contentScale = ContentScale.Fit
                )

                Column(horizontalAlignment = Alignment.Start) {
                    Text(driver.fullName, style = TextStyle().f1Bold(24.sp))
                    driver.teamName?.let {
                        Text(it, style = TextStyle().f1Regular(16.sp))
                    }
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            session?.let {
                Column(modifier = Modifier.padding(horizontal = 15.dp)) {
                    if (session.name == "Race") {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {  }) {
                            Text("Stints", style = TextStyle().f1Bold(16.sp), modifier = Modifier.padding(vertical = 10.dp))
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary,
                            )
                        }
                        HorizontalDivider()
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {  }) {
                            Text("Race pace", style = TextStyle().f1Bold(16.sp), modifier = Modifier.padding(vertical = 10.dp))
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary,
                            )
                        }
                        HorizontalDivider()
                    }
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {  }) {
                        Text("Team radios", style = TextStyle().f1Bold(16.sp), modifier = Modifier.padding(vertical = 10.dp))
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                    HorizontalDivider()
                }
            }
        }
    }
}

private fun getDriver(entry: NavBackStackEntry): Driver? {
    val jsonData = entry.arguments?.getString("d_data")
    val dData = jsonData?.let { Gson().fromJson(it, Driver::class.java) }
    return dData
}

private fun getSession(entry: NavBackStackEntry): Session? {
    val jsonData = entry.arguments?.getString("s_data")
    val sData = jsonData?.let { Gson().fromJson(it, Session::class.java) }
    return sData
}