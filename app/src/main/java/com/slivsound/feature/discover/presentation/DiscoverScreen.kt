package com.slivsound.feature.discover.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.slivsound.feature.discover.presentation.components.MelodiesCard
import com.slivsound.feature.discover.presentation.components.NatureSoundsCard
import com.slivsound.feature.discover.presentation.components.NoiseforSleepCard
import com.slivsound.ui.components.SearchField
import org.koin.androidx.compose.koinViewModel

@Composable
fun DiscoverScreen(
    viewModel: DiscoverViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    DiscoverView(
        items = state,
        onSearch = { query ->
            viewModel.onEvent(DiscoverEvent.Search(query))
        }
    )
}

@Composable
fun DiscoverView(
    items: State,
    onSearch: (String) -> Unit
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
                text = stringResource(R.string.tab_discover),
                style = MaterialTheme.typography.headlineMedium
            )

            SearchField(
                modifier = Modifier
                    .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp),
                placeholder = stringResource(R.string.discover_screen_section_Search),
                value = items.searchQuery,
                onValueChange = onSearch,
                onSearch = { onSearch(items.searchQuery) }
            )

            Spacer(Modifier.height(16.dp))
            val columns = 3
            LazyColumn(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {

                if (items.filteredMelodies.isNotEmpty()) {
                    item {
                        Text(
                            text = stringResource(R.string.discover_screen_section_melodies),
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(Modifier.height(16.dp))
                    }
                    items(items.filteredMelodies.chunked(columns)) { row ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(19.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            row.forEach { item ->
                                MelodiesCard(item, Modifier.weight(1f))
                            }
                            repeat(columns - row.size) { Spacer(Modifier.weight(1f)) }
                        }
                    }
                }

                if (items.filteredSounds.isNotEmpty()) {
                    item {
                        Text(
                            text = stringResource(R.string.discover_screen_section_nature_sounds),
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(Modifier.height(16.dp))
                    }
                    items(items.filteredSounds.chunked(columns)) { row ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(19.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            row.forEach { item ->
                                NatureSoundsCard(item, Modifier.weight(1f))
                            }
                            repeat(columns - row.size) { Spacer(Modifier.weight(1f)) }
                        }
                    }
                }
                if (items.filteredNoise.isNotEmpty()) {
                    item {
                        Text(
                            text = stringResource(R.string.discover_screen_section_noise_for_sleep),
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(Modifier.height(16.dp))
                    }
                    items(items.filteredNoise.chunked(columns)) { row ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(19.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            row.forEach { item ->
                                NoiseforSleepCard(item, Modifier.weight(1f))
                            }
                            repeat(columns - row.size) { Spacer(Modifier.weight(1f)) }
                        }
                    }
                }
            }
        }
    }
}
