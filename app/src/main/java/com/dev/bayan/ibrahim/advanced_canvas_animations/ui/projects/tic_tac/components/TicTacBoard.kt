package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.tic_tac.components


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.theme.AdvancedCanvasAnimationsTheme
import kotlin.math.max

fun DrawScope.ticTacBoard(
    color: Color = Color.Black,
    bgColor: Color = Color.White,
    width: Float = 10f,
    drawingPercentage: Float,
    sequence: Boolean = false,
) {

    drawRect(bgColor)
    repeat(4) {
        val percentage = if(sequence) max(4 * drawingPercentage - it, 0f) else drawingPercentage
        ticTacBoardLine(
            index = it,
            color = color,
            drawingPercentage = if(drawingPercentage >= 1f/(4-it)) 1f else percentage % 1f,
            width = width
        )
    }
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun TicTacBoardFunctionForPreview() {
    val lineDrawAnimation = remember {
        Animatable(0f)
    }
    LaunchedEffect(Unit) {
        lineDrawAnimation.animateTo(
            targetValue = 1f,
            animationSpec = tween(500, easing = FastOutSlowInEasing)
        )
    }
    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(200.dp)
            .drawBehind {
                ticTacBoard(
                    drawingPercentage = lineDrawAnimation.value,
                    sequence = false,
                )
            }
    )
}

@Preview(showBackground = false)
@Composable
private fun TicTacBoardPreviewLight() {
    AdvancedCanvasAnimationsTheme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            TicTacBoardFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun TicTacBoardPreviewDark() {
    AdvancedCanvasAnimationsTheme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            TicTacBoardFunctionForPreview()
        }
    }
}
