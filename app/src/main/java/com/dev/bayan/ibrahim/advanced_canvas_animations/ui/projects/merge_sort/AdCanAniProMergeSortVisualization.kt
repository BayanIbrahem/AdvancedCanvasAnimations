package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.algorithm.MergeSortAlgorithmStep
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.algorithm.MergeSortAlgorithmStepEffectOnNode
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.algorithm.MergeSortDataNode
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.mappers.toMergeSortDataNode
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.parts.ListNode
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.view_model.MergeSortViewModel
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.theme.AdvancedCanvasAnimationsTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AdCanAniProMergeSortVisualization(
    modifier: Modifier = Modifier,
    effectOnNodesState: Map<Int, List<MergeSortAlgorithmStepEffectOnNode>>,
    currentStep: Int,
    data: List<MergeSortDataNode>,
    steps: List<MergeSortAlgorithmStep>,
    onNextStep: () -> Boolean,
    onPrevStep: () -> Boolean,
    nodeWidth: Float,
    verticalOffset: Float,
) {
    BoxWithConstraints(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
            .clickable {
                onNextStep()
                Log.e("click", "box clicked")
            },
    ) {
        val nodeWidth = nodeWidth
        val verticalOffset = verticalOffset
        Text(text = "#$currentStep ${steps[currentStep]}")

        effectOnNodesState.forEach { node ->
            ListNode(
                currentStep = currentStep,
                nodeAbsoluteIndex = node.key,
                nodeSize = nodeWidth,
                actions = node.value,
                value = data[node.key].value,
                verticalOffset = verticalOffset,
            )
        }
    }
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun MergeSortScreenFunctionForPreview() {
    val values = listOf(6, 1, 3, 0, 5, 4, 2, 10, 12, 7, 9, 13)
    val data = values.toMergeSortDataNode()
    val viewModel by remember {
        mutableStateOf(MergeSortViewModel(data))
    }
    val currentStep by viewModel.currentStep.collectAsState()
    val steps by viewModel.steps.collectAsState()
    val effectOnNodeState by viewModel.effectOnNodesMap.collectAsState()
    AdCanAniProMergeSortVisualization(
        effectOnNodesState = effectOnNodeState,
        currentStep = currentStep,
        data = data,
        steps = steps,
        onNextStep = viewModel::onNextStep,
        onPrevStep = viewModel::onPrevStep,
        nodeWidth = 200f,
        verticalOffset = 250f
    )

}

@Preview(showBackground = false)
@Composable
private fun MergeSortScreenPreviewLight() {
    AdvancedCanvasAnimationsTheme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            MergeSortScreenFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun MergeSortScreenPreviewDark() {
    AdvancedCanvasAnimationsTheme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            MergeSortScreenFunctionForPreview()
        }
    }
}
