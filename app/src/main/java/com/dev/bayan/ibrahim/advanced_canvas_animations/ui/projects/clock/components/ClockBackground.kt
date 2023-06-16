package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.hands.clockHoursHand
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.hands.clockMinutesHand
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.hands.clockSecondsHand
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.theme.AdvancedCanvasAnimationsTheme

fun DrawScope.clockBackground(
    color: Color,
) {
    drawCircle(color)
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun ClockBackgroundFunctionForPreview() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .aspectRatio(1f)
            .drawWithCache {
                onDrawBehind {
                    clockBackground(Color.Green)
                }
            }
    )
}

@Preview(showBackground = false)
@Composable
private fun ClockBackgroundPreviewLight() {
    AdvancedCanvasAnimationsTheme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            ClockBackgroundFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun ClockBackgroundPreviewDark() {
    AdvancedCanvasAnimationsTheme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            ClockBackgroundFunctionForPreview()
        }
    }
}
