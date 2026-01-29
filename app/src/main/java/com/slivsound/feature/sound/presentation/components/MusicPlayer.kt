package com.slivsound.feature.sound.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.slivsound.R

@Composable
fun MusicPlayer(
    url: String,
    isPlaying: Boolean,
    onPlayPauseClick: () -> Unit
) {
    val context = LocalContext.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(url))
            prepare()
        }
    }

    var secondsLeft by remember { mutableStateOf(30 * 60) }


    LaunchedEffect(isPlaying) {
        if (isPlaying) exoPlayer.play()
        else exoPlayer.pause()
    }

    DisposableEffect(Unit) {
        onDispose { exoPlayer.release() }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(start = 63.5.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_restart),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center,

            ) {
            IconButton(
                modifier = Modifier.size(64.dp),
                onClick = onPlayPauseClick
            ) {
                Icon(
                    painter = painterResource(
                        if (isPlaying) R.drawable.ic_suspend
                        else R.drawable.ic_play
                    ),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(64.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(end = 30.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Timer(time = secondsLeft)
        }

    }
    Spacer(Modifier.height(16.dp))
    Row() {
        TimeOptionals(onTimeSelected = { minutes ->
            secondsLeft = minutes * 60
        })
    }
}


