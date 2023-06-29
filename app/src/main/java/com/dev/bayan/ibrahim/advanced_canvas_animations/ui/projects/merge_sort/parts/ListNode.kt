package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.parts


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.algorithm.MergeSortAlgorithmStepEffectOnNode
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.algorithm.MergeSortNodeType
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.theme.AdvancedCanvasAnimationsTheme
private const val alphaAnimDuration = 50
private const val colorAnimDuration = 50
private const val strokeAnimDuration = 50
private const val positionAnimDuration = 500

/**
 * this node is totally independent from any other node and don't receive any event except the
 * currentStepChange event, it has a list of actions that tell each animation what to do, and each
 * action to step relation is 1:1 each step refers to one action and each action is applied once
 * only.
 * in node creation step, it receive as parameter all the actions it should do and also the current
 * step.
 * each list node size it maxSize so it can be redraw or change place freely.
 */
@Composable
fun ListNode(
    modifier: Modifier = Modifier,
    /** the value that is displayed in the middle of node */
    value: Double,
    /** the absolute index of the node, that represents the init index of the node, this value is immutable and it doesn't change at all */
    nodeAbsoluteIndex: Int,
    /** node size, node ratio is always 1:1 so node size is node width or node height */
    nodeSize: Float,
    /** how much the node will shifted above default value is nodeSize * 1.5 so the remaining space between the node in init position and shifted node equals half of node size */
    verticalShifting: Float = nodeSize * 1.5f,
    /** the distance between top left corner from node in init position and the top of screen, it must be more than verticalShifting */
    verticalOffset: Float,
    /** the steps that the node change position or value according to */
    actions: List<MergeSortAlgorithmStepEffectOnNode>,
    /** current running step according to the algorithm */
    currentStep: Int
) {
    /* current action from the action list that has current step if the current step doesn't affects this node then it take NoChange value which do no thing */
    var currentAction by remember {
        mutableStateOf(
            actions.firstOrNull() {
                it.stepIndex == currentStep
            } ?: MergeSortAlgorithmStepEffectOnNode.NoChange(-1)
        )
    }
    /* coroutine scope to update currentAction */
    LaunchedEffect(key1 = currentStep) {
        currentAction = actions.find {
                it.stepIndex == currentStep
        } ?: MergeSortAlgorithmStepEffectOnNode.NoChange(-1)
    }

    /* transition for the node used for alpha and stroke width */
    val nodeFloatTransition = updateTransition(targetState = currentAction, label = "node_transition")
    val alphaAnimatable by nodeFloatTransition.animateFloat(
        transitionSpec = { tween(alphaAnimDuration, easing = LinearEasing) },
        targetValueByState = {
            if (it is MergeSortAlgorithmStepEffectOnNode.NodeChangeType) it.type.alpha else 1f
        },
        label = "alpha"
    )
    val mainStrokeAnimation by nodeFloatTransition.animateFloat(
        transitionSpec = { tween(strokeAnimDuration, easing = LinearEasing) },
        targetValueByState = {
            if (it is MergeSortAlgorithmStepEffectOnNode.NodeChangeType) it.type.strokeMainWidth
            else MergeSortNodeType.NORM_ACTIVE_NODE.strokeMainWidth
        },
        label = "main_stroke"
    )
    val secondStrokeAnimation by nodeFloatTransition.animateFloat(
        transitionSpec = { tween(strokeAnimDuration, easing = LinearEasing) },
        targetValueByState = {
            if (it is MergeSortAlgorithmStepEffectOnNode.NodeChangeType) it.type.strokeSecondWidth
            else MergeSortNodeType.NORM_ACTIVE_NODE.strokeSecondWidth
        },
        label = "second_stroke"
    )

    /* color animatable with coroutine scope to update it's value,
     it changes its value when the current action is change type only,
      then it animates to the color of new node type*/
    val colorAnimatable = remember {
        androidx.compose.animation.Animatable(Color.Black)
    }
    LaunchedEffect(key1 = currentAction) {
        if (currentAction is MergeSortAlgorithmStepEffectOnNode.NodeChangeType) {
           val targetColor = (currentAction as MergeSortAlgorithmStepEffectOnNode.NodeChangeType).type.strokeColor
            colorAnimatable.animateTo(
                targetValue = targetColor,
                animationSpec = tween(colorAnimDuration, easing = LinearEasing)
            )
        }
    }

    /* position animatable with coroutine scope for each one (to run in parallel), it changes its
       value when the current action is add to temp sub list or return from temp sublist */
    val positionXAnimation = remember {
        Animatable (nodeAbsoluteIndex * nodeSize)
    }
    val positionYAnimation = remember {
        Animatable (verticalOffset)
    }
    LaunchedEffect(key1 = currentAction) {
        if (currentAction is MergeSortAlgorithmStepEffectOnNode.NodeAddToTempList) {
            positionXAnimation.animateTo(
                targetValue = (currentAction as MergeSortAlgorithmStepEffectOnNode.NodeAddToTempList).tempListOldSize * nodeSize,
                animationSpec = tween(positionAnimDuration, easing = LinearEasing)
            )
        }
    }
    LaunchedEffect(key1 = currentAction) {
        if (currentAction is MergeSortAlgorithmStepEffectOnNode.NodeAddToTempList) {
            positionYAnimation.animateTo(
                targetValue = verticalOffset - verticalShifting,
                animationSpec = tween(positionAnimDuration, easing = LinearEasing)
            )
        } else if (currentAction is MergeSortAlgorithmStepEffectOnNode.NodeReturnedToList) {
            positionYAnimation.animateTo(
                targetValue = verticalOffset,
                animationSpec = tween(positionAnimDuration, easing = LinearEasing)
            )
        }
    }
    Box(
        modifier = modifier
            .padding(1.dp)
            .fillMaxSize()
            .drawBehind {
                drawRoundRect(
                    cornerRadius = CornerRadius(nodeSize/10, nodeSize/10),
                    color = colorAnimatable.value,
                    alpha = alphaAnimatable,
                    style = Stroke(width = mainStrokeAnimation),
                    topLeft = Offset(positionXAnimation.value, positionYAnimation.value),
                    size = Size(nodeSize, nodeSize),
                )
                val x = if (currentAction is MergeSortAlgorithmStepEffectOnNode.NodeChangeType) {
                    val type =
                        (currentAction as MergeSortAlgorithmStepEffectOnNode.NodeChangeType).type
                    if (type == MergeSortNodeType.LEFT_ACTIVE_NODE) {
                        positionXAnimation.value
                    } else {
                        positionXAnimation.value + nodeSize
                    }
                } else {
                    positionXAnimation.value + nodeSize
                }
                drawLine(
                    color = colorAnimatable.value,
                    start = Offset(
                        x,
                        positionYAnimation.value,
                    ),
                    end = Offset(
                        x,
                        positionYAnimation.value + nodeSize,
                    ),
                    strokeWidth = secondStrokeAnimation
                )
            }
    ) {
        Box (
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .size((nodeSize / LocalDensity.current.density).dp)
                .graphicsLayer {
                    translationX = positionXAnimation.value
                    translationY = positionYAnimation.value
                    alpha = alphaAnimatable
                },
        ) {
            Text(
                modifier = Modifier
                    .width( (nodeSize / LocalDensity.current.density).dp ),
                text = value.toString(),
                textAlign = TextAlign.Center,
                fontSize = nodeSize.sp / 5,
            )
        }

    }
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun ListNodeFunctionForPreview() {
    var currentStepIndex by remember {
        mutableStateOf(0)
    }
    BoxWithConstraints(
        Modifier
            .fillMaxSize()
            .clickable {
                currentStepIndex = (currentStepIndex + 1) % 8
            }) {
        ListNode(
            modifier = Modifier.size(50.dp),
            value = 25.0,
            nodeAbsoluteIndex = 5,
            currentStep = currentStepIndex,
            nodeSize = constraints.maxWidth/8f,
            verticalOffset = constraints.maxHeight/2f,
            actions = listOf(
                MergeSortAlgorithmStepEffectOnNode.NodeAddToTempList(
                    tempListOldSize =     2,
                    stepIndex = 1,
                ),
                MergeSortAlgorithmStepEffectOnNode.NodeReturnedToList(1),
                MergeSortAlgorithmStepEffectOnNode.NodeChangeType(MergeSortNodeType.KEY_NODE, 2),
                MergeSortAlgorithmStepEffectOnNode.NodeChangeType(MergeSortNodeType.SECOND_NODE, 3),
                MergeSortAlgorithmStepEffectOnNode.NodeChangeType(MergeSortNodeType.NORM_ACTIVE_NODE, 4),
                MergeSortAlgorithmStepEffectOnNode.NodeChangeType(MergeSortNodeType.LEFT_ACTIVE_NODE, 5),
                MergeSortAlgorithmStepEffectOnNode.NodeChangeType(MergeSortNodeType.INACTIVE_NODE, 6),
                MergeSortAlgorithmStepEffectOnNode.NodeChangeType(MergeSortNodeType.RIGHT_ACTIVE_NODE, 7),
            )
        )
    }
}

@Preview(showBackground = false, device = "spec:parent=pixel_5,orientation=landscape")
@Composable
private fun ListNodePreviewLight() {
    AdvancedCanvasAnimationsTheme(darkTheme = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            ListNodeFunctionForPreview()
        }
    }
}

//@Preview(showBackground = false)
@Composable
private fun ListNodePreviewDark() {
    AdvancedCanvasAnimationsTheme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            ListNodeFunctionForPreview()
        }
    }
}
