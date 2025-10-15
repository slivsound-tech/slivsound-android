package com.slivsound.feature.onboarding.presentation.question

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.slivsound.R
import com.slivsound.ui.components.Button
import com.slivsound.ui.components.ButtonSize
import com.slivsound.ui.components.Card
import com.slivsound.ui.components.IconSide
import com.slivsound.ui.theme.SlivsoundTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnboardingQuestionScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: OnboardingQuestionViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    OnboardingQuestionView(
        modifier = modifier,
        state = state,
        listener = object : OnboardingQuestionClickListener {
            override fun onNext() { navController.navigate("next_screen_route") }
            override fun onBack() { navController.popBackStack() }
        }
    )
}

@Composable
fun OnboardingQuestionView(
    modifier: Modifier = Modifier,
    state: OnboardingQuestionState,
    listener: OnboardingQuestionClickListener
) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg_welcome),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            Text(
                text = stringResource(R.string.onboarding_question_title),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.onboarding_question_subtitle),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(16.dp))

            var selectedIndex by remember { mutableStateOf(-1) }
            val cardTexts = listOf(
                R.string.onboarding_question_card_text_1,
                R.string.onboarding_question_card_text_2,
                R.string.onboarding_question_card_text_3,
                R.string.onboarding_question_card_text_4,
                R.string.onboarding_question_card_text_5,
                R.string.onboarding_question_card_text_6
            )

            cardTexts.forEachIndexed { index, textRes ->
                val isSelected = selectedIndex == index
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .clickable { selectedIndex = index }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                if (isSelected) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.outline
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = textRes),
                            style = MaterialTheme.typography.titleMedium,
                            color = if (isSelected) MaterialTheme.colorScheme.onPrimary
                            else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
                if (index < cardTexts.lastIndex) Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.height(184.dp))
            Button(
                size = ButtonSize.Medium,
                iconSide = IconSide.Right,
                icon = painterResource(R.drawable.ic_right),
                title = stringResource(R.string.onboarding_question_button),
                onClick = { listener.onNext() }
            )
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

interface OnboardingQuestionClickListener {
    fun onNext()
    fun onBack()
}

@Preview(showBackground = true)
@Composable
fun OnboardingQuestionViewPreview() {
    SlivsoundTheme {
        OnboardingQuestionView(
            state = OnboardingQuestionState(),
            listener = object : OnboardingQuestionClickListener {
                override fun onNext() {}
                override fun onBack() {}
            }
        )
    }
}
