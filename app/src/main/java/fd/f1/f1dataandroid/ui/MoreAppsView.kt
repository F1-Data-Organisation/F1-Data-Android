package fd.f1.f1dataandroid.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import fd.f1.f1dataandroid.R
import fd.f1.f1dataandroid.extensions.f1Bold

@Composable
fun MoreAppsView() {
    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.more_f1_apps),
            style = TextStyle().f1Bold(20.sp),
        )

        LazyRow(
            modifier = Modifier.padding(bottom = 25.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(listOf(
                "Official F1® App" to "com.softpauer.f1timingapp2014.basic",
                "F1 TV" to "com.formulaone.production",
                "F1 Race Guide" to "com.formula1.event",
                "F1 Play" to "com.incrowdsports.isg.predictor"
            )) { (appName, appId) ->
                F1AppItemView(appId = appId, appName = appName)
            }
        }

        Text(
            text = stringResource(id = R.string.the_f1_world),
            style = TextStyle().f1Bold(20.sp),
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(listOf(
                "Authentics" to "https://www.f1authentics.com/",
                "Experiences" to "https://f1experiences.com/",
                "Esports" to "https://f1esports.com/",
                "Hospitality" to "https://tickets.formula1.com/h-formula1-hospitality",
                "Store" to "https://f1store2.formula1.com/",
                "Tickets" to "https://tickets.formula1.com/"
            )) { (itemName, itemURL) ->
                F1PartItemView(itemName = itemName, itemURL = itemURL)
            }
        }
    }
}

@Composable
fun F1PartItemView(itemName: String, itemURL: String) {
    val context = LocalContext.current

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .background(Color.Gray.copy(alpha = 0.5f))
            .padding(16.dp)
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(itemURL))
                context.startActivity(intent)
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.f1),
            contentDescription = "F1 for $itemName",
            modifier = Modifier.width(75.dp),
            contentScale = ContentScale.FillWidth,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
        )

        Text(
            text = itemName,
            style = TextStyle().f1Bold(24.sp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, // Remplace par ton icône
            contentDescription = "Error Icon",
            modifier = Modifier.size(40.dp),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
private fun F1AppItemView(appId: String, appName: String) {
    val resource = appId.replace(".", "")
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(Color.Gray.copy(alpha = 0.5f)),
    ) {
        Image(
            painter = painterResource(id = R.drawable::class.java.getField(resource).getInt(null)),
            contentDescription = appName,
            modifier = Modifier
                .height(100.dp)
                .clip(shape = RoundedCornerShape(20.dp)),
            contentScale = ContentScale.FillHeight
        )

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = appName,
            style = TextStyle().f1Bold(20.sp),
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painter = painterResource(id = R.drawable.download_play_store),
            contentDescription = "$appName on Google Play Store",
            modifier = Modifier
                .height(40.dp)
                .clickable {
                    val url = "https://play.google.com/store/apps/details?id=$appId"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                },
            contentScale = ContentScale.FillHeight
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MoreAppsViewPreview() {
    MoreAppsView()
}