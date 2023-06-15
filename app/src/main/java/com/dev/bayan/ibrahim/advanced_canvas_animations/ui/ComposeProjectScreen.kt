package com.dev.bayan.ibrahim.advanced_canvas_animations.ui


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.navigation.NavigationDestinations
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.theme.AdvancedCanvasAnimationsTheme

val ProjectDestination = object : NavigationDestinations {
    override val route: String = "project"
}
@Composable
fun ComposeProjectScreen(
    modifier: Modifier = Modifier,
    composable: @Composable (Modifier) -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    )  {
        composable(Modifier.fillMaxSize())
    }
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun ComposeProjectScreenFunctionForPreview() {
    ComposeProjectScreen() {
        Box(it) {
            Text(text = "Hello In Project Preview")
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun ComposeProjectScreenPreviewLight() {
    AdvancedCanvasAnimationsTheme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            ComposeProjectScreenFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun ComposeProjectScreenPreviewDark() {
    AdvancedCanvasAnimationsTheme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            ComposeProjectScreenFunctionForPreview()
        }
    }
}
