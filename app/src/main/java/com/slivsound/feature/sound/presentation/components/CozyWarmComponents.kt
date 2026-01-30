package com.slivsound.feature.sound.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.slivsound.R
import com.slivsound.feature.discover.domain.SoundModel

@Composable
fun CozyWarm(
    sound: SoundModel,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {

            Text(
                text = sound.title,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = sound.description,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)

        ) {

            Image(
                painter = painterResource(R.drawable.ic_like_filled),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
            )
            Image(
                painter = painterResource(R.drawable.ic_like),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
            )
            Spacer(Modifier.width(16.dp))
            Image(
                painter = painterResource(R.drawable.ic_download),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
            )
            Spacer(Modifier.width(16.dp))

            IconButton(
                onClick = onClick,
                modifier = Modifier
                    .size(32.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = "Share melody"
                )
            }

        }

    }

}
