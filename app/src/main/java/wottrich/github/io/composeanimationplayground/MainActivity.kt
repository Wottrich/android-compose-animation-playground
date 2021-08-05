package wottrich.github.io.composeanimationplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.RepeatMode.Reverse
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import wottrich.github.io.composeanimationplayground.ui.theme.ComposeAnimationPlaygroundTheme

val

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAnimationPlaygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    PlanetEarth()
                }
            }
        }
    }
}

@Composable
fun PlanetEarth() {
    val earthFloatAnimation by buildEarthFloatAnimation()
    val earthRotationAnimation by buildEarthRotationAnimation()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = BiasAlignment(0f, earthFloatAnimation)
    ) {
        Image(
            painter = painterResource(id = R.drawable.planet_earth_png),
            contentDescription = "Planet Earth",
            modifier = Modifier.scale(scaleX = earthRotationAnimation, scaleY = 1f)
        )
    }
}

@Composable
private fun buildEarthFloatAnimation(): State<Float> {
    val earthFloatAnimationInfiniteTransition = rememberInfiniteTransition()
    return earthFloatAnimationInfiniteTransition.animateFloat(
        initialValue = -0.1f,
        targetValue = 0.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = Reverse
        )
    )
}

@Composable
private fun buildEarthRotationAnimation(): State<Float> {
    val constInitialValue = 1f
    val constTargetValue = -1
    val earthRotationAnimationInfiniteTransition = rememberInfiniteTransition()
    return earthRotationAnimationInfiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = -1f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000),
            repeatMode = Reverse
        )
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAnimationPlaygroundTheme {
        PlanetEarth()
    }
}