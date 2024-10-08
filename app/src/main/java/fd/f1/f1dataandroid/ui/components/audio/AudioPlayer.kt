package fd.f1.f1dataandroid.ui.components.audio

import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*
import kotlinx.coroutines.*

@Composable
fun AudioPlayer(audioUrl: String, tintColor: Color, modifier: Modifier = Modifier) {
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var isPlaying by remember { mutableStateOf(false) }
    var currentTime by remember { mutableFloatStateOf(0f) }
    var duration by remember { mutableFloatStateOf(1f) }
    val handler = remember { Handler(Looper.getMainLooper()) }

    LaunchedEffect(audioUrl) {
        mediaPlayer = MediaPlayer().apply {
            setDataSource(audioUrl)
            prepare()
            duration = this.duration / 1000f

            setOnCompletionListener {
                isPlaying = false
                currentTime = 0f
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(10.dp)
    ) {
        IconButton(
            onClick = {
                if (isPlaying) {
                    mediaPlayer?.pause()
                    isPlaying = false
                } else {
                    mediaPlayer?.start()
                    isPlaying = true
                    handler.postDelayed(object : Runnable {
                        override fun run() {
                            mediaPlayer?.let {
                                if (isPlaying) {
                                    currentTime = it.currentPosition / 1000f
                                    handler.postDelayed(this, 1000)
                                }
                            }
                        }
                    }, 1000)
                }
            },
            modifier = Modifier.size(64.dp)
        ) {
            Icon(
                painter = painterResource(
                    if (isPlaying) android.R.drawable.ic_media_pause
                    else android.R.drawable.ic_media_play
                ),
                contentDescription = if (isPlaying) "Pause" else "Play",
                tint = tintColor
            )
        }

        Slider(
            value = currentTime,
            onValueChange = { value ->
                currentTime = value
                mediaPlayer?.seekTo((value * 1000).toInt())
            },
            valueRange = 0f..duration,
            modifier = Modifier.fillMaxWidth(),
            colors = SliderDefaults.colors(activeTrackColor = tintColor)
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }
}