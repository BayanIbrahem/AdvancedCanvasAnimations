package com.dev.bayan.ibrahim.advanced_canvas_animations.ui


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.navigation.NavigationDestinations
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.theme.AdvancedCanvasAnimationsTheme

val ProjectDestination = object : NavigationDestinations {
    override val route: String = "project"
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ComposeProjectScreen(
    modifier: Modifier = Modifier,
    composable: @Composable (Modifier) -> Unit,
    description: String,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        stickyHeader {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                contentAlignment = Alignment.TopCenter,
            )  {
                composable(Modifier)
            }
        }
        item{
            Text(
                text = description,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun ComposeProjectScreenFunctionForPreview() {
    ComposeProjectScreen(
        description = "this is where the description appears",
        composable = {
            Box(it) {
                Text(text = "Hello In Project Preview")
            }
        }
    )
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
