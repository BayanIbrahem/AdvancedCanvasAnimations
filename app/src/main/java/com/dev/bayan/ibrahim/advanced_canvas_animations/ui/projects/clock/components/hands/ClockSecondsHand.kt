package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.hands


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.Sec
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.sec
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.mappers.scaledHeadOffset
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.theme.AdvancedCanvasAnimationsTheme

fun DrawScope.clockSecondsHand(
    seconds: Sec,
    color: Color = Color.Red,
    centerColor: Color = Color.Black,
) {
    drawCircle(
        color = color,
        radius = size.div(50f).minDimension,
    )
    drawLine(
        color = color,
        start = seconds.scaledHeadOffset(size.center, 0.9f),
        end = seconds.scaledHeadOffset(size.center, -0.20f),
        strokeWidth = 5f,
    )
    drawCircle(color = centerColor, radius = size.div(100f).minDimension)
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun ClockSecondsHandFunctionForPreview() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .aspectRatio(1f)
            .drawWithCache {
                onDrawBehind {
                    clockSecondsHand(
                        seconds = 15.sec
                    )
                }
            }
    )
}

@Preview(showBackground = false)
@Composable
private fun ClockSecondsHandPreviewLight() {
    AdvancedCanvasAnimationsTheme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            ClockSecondsHandFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun ClockSecondsHandPreviewDark() {
    AdvancedCanvasAnimationsTheme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            ClockSecondsHandFunctionForPreview()
        }
    }
}
