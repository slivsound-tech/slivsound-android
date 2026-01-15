package com.slivsound.feature.sound.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.slivsound.R
import com.slivsound.feature.discover.domain.SoundModel
import com.slivsound.ui.components.Badge
import com.slivsound.ui.components.ButtonPlus
import com.slivsound.ui.components.SoundListItem

@Composable
fun SoundCategorySection(
    soundId: SoundModel,
    onAddClick: () -> Unit
) {

    Column {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Badge(icon = painterResource(R.drawable.ic_music))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "1/1")
                    Spacer(modifier = Modifier.width(8.dp))
                    ButtonPlus(
                        icon = painterResource(R.drawable.ic_plus), onAddClick = {})
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                SoundListItem(
                    title = soundId.title,
                    description = soundId.description,
                    imageUrl = soundId.imageUrl
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Badge(icon = painterResource(R.drawable.ic_music_note))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
//                Text (text = "${size.toString()}/6")
                Text("0/6")
                Spacer(modifier = Modifier.width(8.dp))
                ButtonPlus(
                    icon = painterResource(R.drawable.ic_plus),
                    onAddClick = onAddClick
                )
            }

        }

    }
}