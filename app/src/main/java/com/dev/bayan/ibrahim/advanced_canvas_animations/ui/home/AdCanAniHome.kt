package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.home


import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan.ibrahim.advanced_canvas_animations.R
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.utiles.card.AdCanAinProjectCard
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.DefaultProject
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.AdCanAniProClock
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.AdCanAniProClockModel
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.AdCanAniProMergeSortVisualization
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.mappers.toMergeSortDataNode
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.view_model.MergeSortViewModel
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.tic_tac.AdCanAniProTicTac
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.utiles.enums.AdCanAniProject
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
                description = project.briefDescription,
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
        item {
            val project = AdCanAniProject(
                title = stringResource(R.string.hands_clock_title),
                briefDescription = stringResource(R.string.hands_clock_brieve_description),
                description = stringResource(R.string.hand_clock_description)
            )
            AdCanAinProjectCard(
                name = project.title,
                description = project.briefDescription,
                onClick = {
                    onSelectCard(
                        @Composable { modifier ->
                            val viewModel by remember {
                                mutableStateOf(AdCanAniProClockModel())
                            }
                            val state by viewModel.timeState.collectAsState()
                            AdCanAniProClock(
                                modifier = Modifier.size(300.dp),
                                state = state,
                                bgColor = MaterialTheme.colorScheme.secondaryContainer,
                                onBgColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            )
                        },
                        project
                    )
                }
            )
        }
        item {
            val project = AdCanAniProject(
                title = stringResource(R.string.tic_tac_toe_title),
                briefDescription = stringResource(R.string.tic_tac_toe_brief_description),
                description = stringResource(R.string.tic_tac_toe_description)
            )
            AdCanAinProjectCard(
                name = project.title,
                description = project.briefDescription,
                onClick = {
                    onSelectCard(
                        @Composable { modifier ->
                            AdCanAniProTicTac(modifier.size(200.dp))
                        },
                        project
                    )
                }
            )
        }
        item {
            val project = AdCanAniProject(
                title = stringResource(R.string.merge_sort_algo_title),
                briefDescription = stringResource(R.string.merge_sort_algo_brief_description),
                description = stringResource(R.string.merge_sort_algo_description)
            )
            AdCanAinProjectCard(
                name = project.title,
                description = project.briefDescription,
                onClick = {
                    onSelectCard(
                        @Composable { modifier ->
                            val values = listOf(6, 1, 3, 0, 5)
                            val data = values.toMergeSortDataNode()
                            val viewModel by remember {
                                mutableStateOf(MergeSortViewModel(data))
                            }
                            val currentStep by viewModel.currentStep.collectAsState()
                            val steps by viewModel.steps.collectAsState()
                            val effectOnNodeState by viewModel.effectOnNodesMap.collectAsState()
                            BoxWithConstraints() {
                                AdCanAniProMergeSortVisualization(
                                    modifier = modifier,
                                    effectOnNodesState = effectOnNodeState,
                                    currentStep = currentStep,
                                    data = data,
                                    steps = steps,
                                    onNextStep = viewModel::onNextStep,
                                    onPrevStep = viewModel::onPrevStep,
                                    nodeWidth = constraints.maxWidth / data.size.toFloat() * 0.8f,
                                    verticalOffset = (constraints.maxWidth / data.size.toFloat() * 0.8f) + 10f,
                                )
                            }
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
