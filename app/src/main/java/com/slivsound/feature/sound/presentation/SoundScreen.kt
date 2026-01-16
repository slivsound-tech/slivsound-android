package com.slivsound.feature.sound.presentation

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.slivsound.R
import com.slivsound.feature.discover.domain.SoundModel
import com.slivsound.feature.sound.presentation.components.CozyWarm
import com.slivsound.feature.sound.presentation.components.ErrorView
import com.slivsound.feature.sound.presentation.components.LoadingView
import com.slivsound.feature.sound.presentation.components.MusicPlayer
import com.slivsound.feature.sound.presentation.components.RotatingImage
import com.slivsound.feature.sound.presentation.components.SoundCategorySection
import com.slivsound.ui.components.SoundListItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun SoundScreen(
    viewModel: SoundViewModel = koinViewModel(),
    navController: NavController
) {
    val state by viewModel.uiState.collectAsState()
    val selectedSound by viewModel.selectedSound.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                SoundEffect.NavigateToDiscover -> {
                    navController.navigate("discover_main?mode=select") {
                        launchSingleTop = true
                        restoreState = false
                    }
                }
                is SoundEffect.ShareSound -> {
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(
                            Intent.EXTRA_TEXT,
                            "${effect.title}\n${effect.url}"
                        )
                    }
            }
        }
    }
    SoundView(
        items = state,
        selectedSound = selectedSound,
        onAddClick = {
            viewModel.onEvent(SoundEvent.OnAddClick)
        }
    )
}

@Composable
fun SoundView(

    items: State,
    selectedSound: SoundModel?,
    onAddClick: () -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
        tonalElevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Text(
                modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
                text = stringResource(R.string.sound_screen_title),
                style = MaterialTheme.typography.headlineMedium
            )
            LazyColumn(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                when (items) {
                    is State.Content -> {
                        val content = items

                        selectedSound?.let { sound ->
                            item {
                                RotatingImage(sound = sound)
                            }

                            item {
                                CozyWarm(sound = sound)
                            }
                            item {
                                MusicPlayer(
                                    url = sound.audioUrl
                                )

                            }
                            item {
                                selectedSound?.let { sound ->
                                    SoundCategorySection(
                                        size = items.mixSounds.size,
                                        soundId = sound,
                                        onAddClick = onAddClick
                                    )
                                }
                            }

                        }
                        items(content.mixSounds) { sound ->
                            SoundListItem(
                                title = sound.title,
                                description = sound.description,
                                imageUrl = sound.imageUrl
                            )
                        }

                    }

                    is State.Loading -> {
                        item { LoadingView() }
                    }

                    is State.Error -> {
                        item { ErrorView(items.message) }
                    }
                }
            }
        }
    }
}
