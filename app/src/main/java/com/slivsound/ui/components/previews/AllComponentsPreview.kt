package com.slivsound.ui.components.previews

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.slivsound.ui.components.button.AppButton
import com.slivsound.ui.components.button.AppButtonOutline
import com.slivsound.ui.components.card.AppCard
import com.slivsound.ui.components.chip.SelectableChip
import com.slivsound.ui.components.mask.MaskBar
import com.slivsound.ui.components.search.SearchField
import com.slivsound.ui.components.slider.AppSlider
import com.slivsound.ui.theme.SlivsoundTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AllComponentsPreview() {
    SlivsoundTheme {
        Column(modifier = Modifier.padding(20.dp)) {
            AppButton(text = "Залитая кнопка", onClick = {})
            Spacer(Modifier.height(8.dp))
            AppButtonOutline(text = "Кнопка Outline", onClick = {})
            Spacer(Modifier.height(8.dp))
            SelectableChip(text = "Chip", selected = true, onSelectedChange = {})
            Spacer(Modifier.height(8.dp))
            AppCard(title = "Карточка", subtitle = "Подзаголовок")
            Spacer(Modifier.height(8.dp))
            SearchField(value = "", onValueChange = {}, onSearch = {})
            Spacer(Modifier.height(8.dp))
            AppSlider(value = 50f, onValueChange = {}, valueRange = 0f..100f)
            Spacer(Modifier.height(8.dp))
            MaskBar()
        }
    }
}
