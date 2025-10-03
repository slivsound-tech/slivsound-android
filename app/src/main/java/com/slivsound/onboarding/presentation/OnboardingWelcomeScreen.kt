package com.slivsound.onboarding.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.slivsound.R
import com.slivsound.ui.components.Button
import com.slivsound.ui.components.ButtonSize
import com.slivsound.ui.components.IconSide
import com.slivsound.ui.theme.SlivsoundTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnboardingWelcomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: OnboardingWelcomeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    OnboardingWelcomeView(
        modifier = modifier,
        state = state,
        listener = object : OnboardingWelcomeClickListener {
            override fun onContinue() {
                navController.navigate("question")
            }
        }
    )
}

@Composable
fun OnboardingWelcomeView(
    modifier: Modifier = Modifier,
    state: OnboardingWelcomeState,
    listener: OnboardingWelcomeClickListener
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
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = stringResource(R.string.onboarding_welcome_title),
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(R.string.onboarding_welcome_subtitle),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(100.dp))
            Button(
                size = ButtonSize.Medium,
                iconSide = IconSide.Right,
                icon = painterResource(R.drawable.ic_right),
                title = stringResource(R.string.onboarding_welcome_button),
                onClick = { listener.onContinue() }
            )
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

interface OnboardingWelcomeClickListener {
    fun onContinue()
}

@Preview(showBackground = true)
@Composable
fun OnboardingWelcomeViewPreview() {
    SlivsoundTheme {
        OnboardingWelcomeView(
            state = OnboardingWelcomeState(),
            listener = object : OnboardingWelcomeClickListener {
                override fun onContinue() {}
            }
        )
    }
}
