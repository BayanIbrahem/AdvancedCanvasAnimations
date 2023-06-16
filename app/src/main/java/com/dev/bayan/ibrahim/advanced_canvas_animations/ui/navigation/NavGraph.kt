package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.navigation


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dev.bayan.ibrahim.advanced_canvas_animations.HomeDestination
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.ComposeProjectScreen
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.ProjectDestination
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.home.AdCanAniHome
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.utiles.enums.AdCanAniProject
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.theme.AdvancedCanvasAnimationsTheme

@Composable
fun NavGraph(
    controller: NavHostController,
    modifier: Modifier = Modifier,
    onSelectProject: (project: AdCanAniProject) -> Unit
) {
    var content by remember {
        val c = @Composable { modifier: Modifier -> }
        mutableStateOf(c)
    }
    var project by remember {
        val p: AdCanAniProject? = null
        mutableStateOf(p)
    }
    NavHost(
        modifier = modifier,
        navController = controller,
        startDestination = HomeDestination.route
    ) {
        composable(route = HomeDestination.route) {
            AdCanAniHome(
                modifier = modifier,
                onSelectCard = {newContent, newProject ->
                    content = newContent
                    onSelectProject(newProject)
                    project = newProject
                    controller.navigate(ProjectDestination.route)
                }
            )
        }
        composable(route = ProjectDestination.route) {
            ComposeProjectScreen(
                composable = content,
                description = project?.description ?: "no Description",
            )
        }
    }
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun NavGraphFunctionForPreview() {
    NavGraph(rememberNavController()) {

    }
}

@Preview(showBackground = false)
@Composable
private fun NavGraphPreviewLight() {
    AdvancedCanvasAnimationsTheme(darkTheme = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            NavGraphFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun NavGraphPreviewDark() {
    AdvancedCanvasAnimationsTheme(darkTheme = true) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            NavGraphFunctionForPreview()
        }
    }
}
