package com.slivsound.feature.sound.presentation.components

import androidx.media3.common.MediaItem
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.media3.exoplayer.ExoPlayer

@Composable
fun MusicPlayer(
    url: String
) {
    val context = LocalContext.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(url)
            setMediaItem(mediaItem)
            prepare()
        }
    }

    var isPlaying by remember { mutableStateOf(false) }

    LaunchedEffect(isPlaying) {
        if (isPlaying) exoPlayer.play()
        else exoPlayer.pause()
    }
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    Column {
        Button(onClick = { isPlaying = !isPlaying }) {
            Text(
                text = if (isPlaying) "Пауза" else "Играть"
            )
        }
    }
}

