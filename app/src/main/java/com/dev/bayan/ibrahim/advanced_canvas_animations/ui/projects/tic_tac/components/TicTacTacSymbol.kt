package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.tic_tac.components


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.mappers.scale
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.mappers.scaleToOffset
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.theme.AdvancedCanvasAnimationsTheme

@Composable
fun TicTacTacSymbol(
    modifier: Modifier = Modifier,
    color: Color = Color.Green,
    widthFor200DpSquare: Float = 15f,
    drawingDuration: Int = 500,
) {
    val ticDrawAnimation = remember {
        Animatable(0f)
    }
    LaunchedEffect(Unit) {
        ticDrawAnimation.animateTo(
            targetValue = 1f,
            animationSpec = tween(drawingDuration, easing = FastOutSlowInEasing)
        )
    }
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .padding(8.dp)
            .drawWithCache {
                onDrawBehind {
                    ticTacTacSymbol(
                        color = color,
                        width = (widthFor200DpSquare / 200.dp.toPx()) * size.minDimension,
                        drawingPercentage = ticDrawAnimation.value,
                    )
                }
            }
    )
}
private fun DrawScope.ticTacTacSymbol(
    color: Color = Color.Green,
    drawingPercentage: Float,
    width: Float = 15f,
) {
    val scaledCircleTopLeft = size.center.scaleToOffset(ratio = 1/5f)
    drawArc(
        color = color,
        startAngle = - 90f,
        sweepAngle = - 360f * drawingPercentage,
        useCenter = false,
        style = Stroke(width = width, cap = StrokeCap.Round),
        topLeft = scaledCircleTopLeft,
        size = size.scale(4/5f),
    )

}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun TicTacTacSymbolFunctionForPreview() {
    TicTacTacSymbol(Modifier.size(200.dp))
}


@Preview(showBackground = false)
@Composable
private fun TicTacTacSymbolPreviewLight() {
    AdvancedCanvasAnimationsTheme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            TicTacTacSymbolFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun TicTacTacSymbolPreviewDark() {
    AdvancedCanvasAnimationsTheme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            TicTacTacSymbolFunctionForPreview()
        }
    }
}
