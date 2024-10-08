package fd.f1.f1dataandroid.ui.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.compose.ui.zIndex
import fd.f1.f1dataandroid.extensions.*
import kotlinx.coroutines.flow.*

@Composable
fun AppTabRow(
    tabs: List<String>,
    contentScreens: List<@Composable () -> Unit>
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.Gray.copy(alpha = 0.5f),
            contentColor = MaterialTheme.colorScheme.primary,
            indicator = { tabPositions: List<TabPosition> ->
                Box(
                    Modifier.tabRowIndicatorOffset(tabPositions[selectedTabIndex])
                        .fillMaxSize()
                        .padding(5.dp)
                        .background(
                            Color.White.copy(alpha = 0.5f),
                            RoundedCornerShape(15.dp),
                        ),
                )
            },
            modifier = Modifier
                .padding(5.dp)
                .height(45.dp)
                .clip(
                    shape = RoundedCornerShape(15.dp)
                )
        ) {
            tabs.forEachIndexed { index, tabTitle ->
                Tab(
                    text = { Text(tabTitle, style = TextStyle(textAlign = TextAlign.Center).f1Regular(12.sp)) },
                    selected = selectedTabIndex == index,
                    interactionSource = DisabledInteractionSource(),
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(15.dp))
                        .padding(bottom = 3.dp)
                        .zIndex(2f),
                    onClick = { selectedTabIndex = index }
                )
            }
        }

        HorizontalDivider(modifier = Modifier.padding(8.dp))
        contentScreens.getOrNull(selectedTabIndex)?.invoke()
    }
}

@SuppressLint("UseOfNonLambdaOffsetOverload")
private fun Modifier.tabRowIndicatorOffset(
    currentTabPosition: TabPosition
): Modifier = composed() {
    val currentTabWidth by animateDpAsState(
        targetValue = currentTabPosition.width,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing), label = ""
    )
    val indicatorOffset by animateDpAsState(
        targetValue = currentTabPosition.left,
        animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing), label = ""
    )
    wrapContentSize(CenterStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
}

class DisabledInteractionSource : MutableInteractionSource {
    override val interactions: Flow<Interaction> = emptyFlow()
    override suspend fun emit(interaction: Interaction) {}
    override fun tryEmit(interaction: Interaction) = true
}