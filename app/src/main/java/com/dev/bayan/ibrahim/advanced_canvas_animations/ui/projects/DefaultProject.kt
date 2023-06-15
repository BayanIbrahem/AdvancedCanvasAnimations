package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.theme.AdvancedCanvasAnimationsTheme

@Composable
fun DefaultProject(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Hello to Android", style = MaterialTheme.typography.displayLarge)
    }
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun DefaultProjectFunctionForPreview() {
    DefaultProject()
}

@Preview(showBackground = false)
@Composable
private fun DefaultProjectPreviewLight() {
    AdvancedCanvasAnimationsTheme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            DefaultProjectFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun DefaultProjectPreviewDark() {
    AdvancedCanvasAnimationsTheme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            DefaultProjectFunctionForPreview()
        }
    }
}
