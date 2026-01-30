package com.slivsound.feature.sound.presentation.components

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.slivsound.R
import com.slivsound.feature.discover.domain.SoundModel
import com.slivsound.ui.components.Badge
import com.slivsound.ui.components.ButtonPlus
import com.slivsound.ui.components.SoundListItem

@Composable
fun SoundCategorySection(
    size: Int,
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
                val context = LocalContext.current

                Text(text = "$size/6")
                Spacer(modifier = Modifier.width(8.dp))

                ButtonPlus(
                    icon = painterResource(id = R.drawable.ic_plus),
                    onAddClick = {
                        if (size < 6) {
                            onAddClick()
                        } else {
                            Toast.makeText(
                                context,
                                R.string.sound_screen_section_add_nature_sounds,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                )

            }

        }

    }
}