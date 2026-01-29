package com.slivsound.feature.sound.presentation.repositiry

import android.content.Context
import android.net.Uri
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PlayRepositoryImpl(
    private val context: Context
) : PlayRepository {

    private val _items = MutableStateFlow<List<Play>>(emptyList())
    override val items: StateFlow<List<Play>> = _items
    private val effectPlayers = mutableMapOf<String, ExoPlayer>()

    override suspend fun addItem(item: Play) {
        val updated = _items.value.toMutableList().apply {
            add(item)
        }
        _items.value = updated
    }

    override fun playAll() {
        effectPlayers.values.forEach { it.play() }
    }

    override fun pauseAll() {
        effectPlayers.values.forEach { it.pause() }
    }

    override fun playEffects(effects: List<Play>) {

        effects.forEach { play ->
            if (effectPlayers.containsKey(play.id)) return@forEach

            val player = ExoPlayer.Builder(context)
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(C.USAGE_MEDIA)
                        .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
                        .build(),
                    false
                )
                .build()

            player.setMediaItem(MediaItem.fromUri(Uri.parse(play.audioUrl)))
            player.volume = 1f
            player.repeatMode = Player.REPEAT_MODE_ALL
            player.prepare()
            player.play()

            effectPlayers[play.id] = player
        }
    }


}