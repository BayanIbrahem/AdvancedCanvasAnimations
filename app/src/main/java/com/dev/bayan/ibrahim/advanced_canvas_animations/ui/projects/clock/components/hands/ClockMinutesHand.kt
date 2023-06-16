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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.Min
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.min
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.mappers.scaledHeadOffset
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.theme.AdvancedCanvasAnimationsTheme

fun DrawScope.clockMinutesHand(
    minutes: Min,
    color: Color = Color.Yellow,
    centerColor: Color = Color.Black,
) {
    drawCircle(
        color = color,
        radius = size.div(40f).minDimension,
    )
    drawLine(
        color = color,
        start = minutes.scaledHeadOffset(size.center, 0.8f),
        end = minutes.scaledHeadOffset(size.center, -0.10f),
        cap = StrokeCap.Round,
        strokeWidth = 7f,
    )
    drawLine(
        color = centerColor.copy(alpha = 0.7f),
        start = minutes.scaledHeadOffset(size.center, 0.77f),
        end = minutes.scaledHeadOffset(size.center, -0.07f),
        cap = StrokeCap.Round,
        strokeWidth = 1f,
    )
    drawCircle(color = centerColor, radius = size.div(100f).minDimension)
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun ClockMinutesHandFunctionForPreview() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .aspectRatio(1f)
            .drawWithCache {
                onDrawBehind {
                    clockMinutesHand(
                        minutes = 15.min
                    )
                }
            }
    )
}

@Preview(showBackground = false)
@Composable
private fun ClockMinutesHandPreviewLight() {
    AdvancedCanvasAnimationsTheme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            ClockMinutesHandFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun ClockMinutesHandPreviewDark() {
    AdvancedCanvasAnimationsTheme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            ClockMinutesHandFunctionForPreview()
        }
    }
}
