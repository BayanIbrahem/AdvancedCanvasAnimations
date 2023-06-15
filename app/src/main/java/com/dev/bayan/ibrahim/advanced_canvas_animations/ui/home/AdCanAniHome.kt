package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.home


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.AdCanAinProjectCard
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.DefaultProject
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.enums.AdCanAniProject
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.theme.AdvancedCanvasAnimationsTheme

@Composable
fun AdCanAniHome(
    modifier: Modifier = Modifier,
    onSelectCard: (
        content: @Composable (Modifier) -> Unit,
        project: AdCanAniProject
    ) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 300.dp),
        modifier = modifier
    ){
        item {
            val project = AdCanAniProject.DefaultProject
            AdCanAinProjectCard(
                name = project.title,
                description = project.description,
                onClick = {
                    onSelectCard(
                        @Composable { modifier ->
                            DefaultProject(modifier)
                        },
                        project
                    )
                }
            )
        }
    }
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun AdCanAniHomeFunctionForPreview() {
    AdCanAniHome(
        onSelectCard = {content, project ->
        
        }
    )
}

@Preview(showBackground = false)
@Composable
private fun AdCanAniHomePreviewLight() {
    AdvancedCanvasAnimationsTheme(darkTheme = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            AdCanAniHomeFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun AdCanAniHomePreviewDark() {
    AdvancedCanvasAnimationsTheme(darkTheme = true) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            AdCanAniHomeFunctionForPreview()
        }
    }
}
