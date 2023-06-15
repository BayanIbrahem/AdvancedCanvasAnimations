package com.dev.bayan.ibrahim.advanced_canvas_animations.ui


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.dev.bayan.ibrahim.advanced_canvas_animations.R
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.bar.AdCanAniTopAppBar
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.navigation.NavGraph
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.navigation.AdCanAniNavScreens
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.enums.AdCanAniProject
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.theme.AdvancedCanvasAnimationsTheme
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.theme.Measures


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdCanAniApp(
    modifier: Modifier = Modifier,
) {
    val controller = rememberNavController()
    var currentScreen by remember {
        mutableStateOf(AdCanAniNavScreens.Home)
    }
    var _currentProject by remember {
        val p : AdCanAniProject? = null
        mutableStateOf(p)
    }
    val currentProject: AdCanAniProject = _currentProject?: AdCanAniProject.DefaultProject
    val primary = MaterialTheme.colorScheme.primary
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            AdCanAniTopAppBar(
                modifier = Modifier,
                navigateUp = {
                    controller.navigateUp()
                    currentScreen = AdCanAniNavScreens.Home
                    _currentProject = null
                },
                title = {
                    val text = if (currentScreen == AdCanAniNavScreens.Home) {
                        stringResource(R.string.screen_title_home)
                    } else {
                        currentProject.title
                    }
                    Box(
                        modifier = Modifier.height(Measures.topAppBarHeight),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(text = text, style = MaterialTheme.typography.headlineSmall)
                    }
                },
                currentScreen = currentScreen,
            )
        }
    ) {
        NavGraph(
            controller = controller,
            modifier = Modifier.padding(it),
            onSelectProject = {newProject ->
                _currentProject = newProject
                currentScreen = AdCanAniNavScreens.Project
            }
        )
    }
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun AdvancedCanvasAnimationsAppFunctionForPreview() {
    AdCanAniApp()
}

@Preview(showBackground = false)
@Composable
private fun AdvancedCanvasAnimationsAppPreviewLight() {
    AdvancedCanvasAnimationsTheme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            AdvancedCanvasAnimationsAppFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun AdvancedCanvasAnimationsAppPreviewDark() {
    AdvancedCanvasAnimationsTheme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            AdvancedCanvasAnimationsAppFunctionForPreview()
        }
    }
}
