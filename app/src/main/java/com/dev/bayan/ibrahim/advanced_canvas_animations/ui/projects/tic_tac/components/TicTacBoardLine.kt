package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.tic_tac.components


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.mappers.scaleToOffset
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.theme.AdvancedCanvasAnimationsTheme

fun DrawScope.ticTacBoardLine(
    index: Int,
    color: Color = Color.Black,
    width: Float = 10f,
    drawingPercentage: Float,
) {
    val start = getStartOffsetByIndex(index, size)
    val end = getEndOffsetByIndex(index, size)
    drawLine(
        color,
        start = start,
        end = end.scaleToOffset(start, drawingPercentage),
        cap = StrokeCap.Round,
        strokeWidth = width
    )
}

private fun getStartOffsetByIndex(index: Int, size: Size): Offset = when(index) {
    0 -> Offset(x = size.width / 3, y = 0f)
    1 -> Offset(x = 2 * size.width / 3, y = 0f)
    2 -> Offset(x = 0f, y =  size.height / 3)
    3 -> Offset(x = 0f, y =  2*size.height / 3)
    else -> Offset(0f, 0f)
}
private fun getEndOffsetByIndex(index: Int, size: Size): Offset = when(index) {
    0 -> Offset(x = size.width / 3, y = size.height)
    1 -> Offset(x = 2 * size.width / 3, y = size.height)
    2 -> Offset(x = size.width, y =  size.height / 3)
    3 -> Offset(x = size.width, y =  2*size.height / 3)
    else -> Offset(0f, 0f)
}
// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun TicTacBoardLineFunctionForPreview() {
    val line1DrawAnimation = remember {
        Animatable(0f)
    }
    LaunchedEffect(Unit) {
        line1DrawAnimation.animateTo(
            targetValue = 1f,
            animationSpec = tween(500, easing = FastOutSlowInEasing)
        )
    }
    Box(
        modifier = Modifier
            .size(200.dp)
            .padding(8.dp)
            .drawBehind {
                ticTacBoardLine(index = 0, drawingPercentage = line1DrawAnimation.value)
                ticTacBoardLine(index = 1, drawingPercentage = line1DrawAnimation.value)
                ticTacBoardLine(index = 2, drawingPercentage = line1DrawAnimation.value)
                ticTacBoardLine(index = 3, drawingPercentage = line1DrawAnimation.value)
            }
    )
}

@Preview(showBackground = false)
@Composable
private fun TicTacBoardLinePreviewLight() {
    AdvancedCanvasAnimationsTheme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            TicTacBoardLineFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun TicTacBoardLinePreviewDark() {
    AdvancedCanvasAnimationsTheme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            TicTacBoardLineFunctionForPreview()
        }
    }
}
