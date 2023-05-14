package ru.mazer.foodies.ui.screens.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import ru.mazer.foodies.R
import ru.mazer.foodies.ui.navigation.NavRoutes

@Composable
fun SplashScreen(navController: NavController) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.splashscreenanimation))
    val progress by animateLottieCompositionAsState(composition = composition)

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxSize()
    ) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier.fillMaxWidth(),
            progress = { progress }
        )
    }

    LaunchedEffect(key1 = progress) {
        if (progress == 1f) {
            navController.popBackStack()
            navController.navigate(NavRoutes.Catalog.route)
        }
    }
}