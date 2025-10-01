package com.slivsound.welcome.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import com.slivsound.R
import com.slivsound.ui.theme.SlivsoundTheme
import com.slivsound.ui.theme.AppTypography
import com.slivsound.ui.theme.OnBackground
import com.slivsound.ui.theme.OnSurface
import com.slivsound.ui.components.Button
import com.slivsound.ui.components.ButtonSize
import com.slivsound.ui.components.IconSide

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    viewModel: WelcomeViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effects.collectLatest { effect ->
            when (effect) {
                is WelcomeEffect.ShowToast -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                is WelcomeEffect.ShowError -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    WelcomeView(
        modifier = modifier,
        state = state,
        listener = object : WelcomeClickListener {
            override fun onBack() {}
            override fun onTest() {
                viewModel.onEvent(WelcomeEvent.SetWelcome("Hello from Welcome!"))
            }
        }
    )
}

@Composable
fun WelcomeView(
    modifier: Modifier = Modifier,
    state: WelcomeState = WelcomeState(),
    listener: WelcomeClickListener = object : WelcomeClickListener {
        override fun onBack() {}
        override fun onTest() {}
    }
) {
    Box(modifier = modifier.fillMaxSize()) {
        // Фон
        Image(
            painter = painterResource(id = R.drawable.welocome), // сюда свою картинку
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Контент поверх фона
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp,0.dp)
        ) {
            Spacer(modifier = Modifier.height(433.dp))

            Text(
                text = stringResource(R.string.welcome_text1),
                style = AppTypography.displayMedium, // шрифт из темы
                color = OnBackground
            )
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.welcome_text2),
                style = AppTypography.bodyLarge, // шрифт из темы
                color = OnSurface
            )
            Spacer(modifier = Modifier.height(100.dp))


            Button(

                size  = ButtonSize.Medium,
                iconSide = IconSide.Right,
                icon = painterResource(R.drawable.right_1), // если у тебя кастомный Button принимает painter
                title = stringResource(R.string.welcome_button_text),
                onClick = { listener.onTest() }

            )
        }
    }
}

interface WelcomeClickListener {
    fun onBack()
    fun onTest()
}

@Preview(showBackground = true)
@Composable
fun WelcomeViewPreview() {
    SlivsoundTheme {
        WelcomeView()
    }
}
