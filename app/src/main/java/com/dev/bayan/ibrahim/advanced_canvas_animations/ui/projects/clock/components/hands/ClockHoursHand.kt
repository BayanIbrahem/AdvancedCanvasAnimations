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
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.Hour12
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.hour12
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.mappers.scaledHeadOffset
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.theme.AdvancedCanvasAnimationsTheme

fun DrawScope.clockHoursHand(
    hours: Hour12,
    color: Color = Color.Black,
    centerColor: Color = Color.Black,
) {
    drawCircle(
        color = color,
        radius = size.div(40f).minDimension,
    )
    drawLine(
        color = color,
        start = hours.scaledHeadOffset(size.center, 0.7f),
        end = hours.scaledHeadOffset(size.center, -0.05f),
        strokeWidth = 10f,
        cap = StrokeCap.Round,
    )
    drawLine(
        color = centerColor.copy(alpha = 0.7f),
        start = hours.scaledHeadOffset(size.center, 0.67f),
        end = size.center,
        strokeWidth = 1f,
        cap = StrokeCap.Round,
    )
    drawCircle(color = centerColor, radius = size.div(100f).minDimension)
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun ClockHoursHandFunctionForPreview() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .aspectRatio(1f)
            .drawWithCache {
                onDrawBehind {
                    clockHoursHand(
                        hours = 15.hour12
                    )
                }
            }
    )
}

@Preview(showBackground = false)
@Composable
private fun ClockHoursHandPreviewLight() {
    AdvancedCanvasAnimationsTheme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            ClockHoursHandFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun ClockHoursHandPreviewDark() {
    AdvancedCanvasAnimationsTheme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            ClockHoursHandFunctionForPreview()
        }
    }
}
