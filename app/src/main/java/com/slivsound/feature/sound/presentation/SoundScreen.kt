package com.slivsound.feature.sound.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.slivsound.R
import com.slivsound.feature.sound.presentation.components.CozyWarm
import com.slivsound.feature.sound.presentation.components.ErrorView
import com.slivsound.feature.sound.presentation.components.LoadingView
import com.slivsound.feature.sound.presentation.components.RotatingImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun SoundScreen(
    viewModel: SoundViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    SoundView(items = state)
}

@Composable
fun SoundView(
    items: State
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

                    is State.Success -> {
                        val sound = items.sound

                        item {
                            RotatingImage(sound = sound)
                        }

                        item {
                            CozyWarm(sound = sound)
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
