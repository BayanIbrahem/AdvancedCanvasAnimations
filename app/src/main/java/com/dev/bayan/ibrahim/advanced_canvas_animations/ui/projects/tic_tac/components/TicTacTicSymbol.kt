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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.mappers.getVectorByGridPosition
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.mappers.moveByVector
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.mappers.scaleToOffset
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.theme.AdvancedCanvasAnimationsTheme
import java.lang.Float.max

@Composable
fun TicTacTicSymbol(
    modifier: Modifier = Modifier,
    color: Color = Color.Red,
    widthFor200DpSquare: Float = 25f,
    sequence: Boolean = true,
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
                    ticTacTicSymbol(
                        color = color,
                        width = (widthFor200DpSquare / 200.dp.toPx()) * size.minDimension,
                        sequence = sequence,
                        drawingPercentage = ticDrawAnimation.value,
                    )
                }
            }
    )
}
private fun DrawScope.ticTacTicSymbol(
    color: Color = Color.Red,
    drawingPercentage: Float,
    width: Float = 15f,
    sequence: Boolean = true,
) {
    val firstLineStart = size.center.scaleToOffset(ratio = 1/5f)
    val firstLineEnd = size.center.scaleToOffset(ratio = 9/5f)

    val secondLineStart = Offset(x = firstLineEnd.x, y = firstLineStart.y)
    val secondLineEnd = Offset(x = firstLineStart.x, y = firstLineEnd.y)

    val firstPercentage = if (sequence) max(2 * drawingPercentage, 0f) else drawingPercentage
    val secondPercentage = if (sequence) max(2 * drawingPercentage - 1f, 0f) else drawingPercentage

    val firstSafePercentage = if (drawingPercentage >= 0.5f ) 1f else firstPercentage % 1
    val secondSafePercentage = if (drawingPercentage == 1f) 1f else secondPercentage % 1

    drawLine(
        color = color,
        start = firstLineStart,
        end = firstLineEnd.scaleToOffset(firstLineStart, firstSafePercentage),
        cap = StrokeCap.Round,
        strokeWidth = width,
    )
    drawLine(
        color = color,
        start = secondLineStart,
        end = secondLineEnd.scaleToOffset(secondLineStart, secondSafePercentage),
        cap = StrokeCap.Round,
        strokeWidth = width,
    )
}

private fun getFirstStartOffsetByPosition(position: Int, size: Size): Offset {
    val bigTicFirstStartOffset = Offset(
        x = size.width * 0.2f,
        y = size.height * 0.2f,
    )
    val scaledTicFirstStartOffset = bigTicFirstStartOffset.scaleToOffset(ratio = 0.33f)
    val vector = getVectorByGridPosition(position, size = size)
    return scaledTicFirstStartOffset.moveByVector(vector)
}
private fun getFirstEndOffsetByPosition(position: Int, size: Size): Offset {
    val bigTicFirstStartOffset = Offset(
        x = size.width * 0.8f,
        y = size.height * 0.8f,
    )
    val scaledTicFirstStartOffset = bigTicFirstStartOffset.scaleToOffset(ratio = 0.33f)
    val vector = getVectorByGridPosition(position, size = size)
    return scaledTicFirstStartOffset.moveByVector(vector)
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun TicTacTicSymbolFunctionForPreview() {
    TicTacTicSymbol(Modifier.size(200.dp))
}


@Preview(showBackground = false)
@Composable
private fun TicTacTicSymbolPreviewLight() {
    AdvancedCanvasAnimationsTheme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            TicTacTicSymbolFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun TicTacTicSymbolPreviewDark() {
    AdvancedCanvasAnimationsTheme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            TicTacTicSymbolFunctionForPreview()
        }
    }
}
