package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components


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
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.mappers.headOffset
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.mappers.scaledHeadOffset
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.theme.AdvancedCanvasAnimationsTheme

fun DrawScope.clockSteps(color: Color) {
    repeat(60) {
        val start = it.sec.headOffset(size.center)
        val end = it.sec.scaledHeadOffset(size.center, 0.85f)
        drawLine(
            color = color,
            start = start,
            end = end,
            strokeWidth = if(it % 5 == 0) 2f else 1f
        )
    }
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun ClockStepsFunctionForPreview() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .aspectRatio(1f)
            .drawWithCache {
                onDrawBehind {
                    clockSteps(Color.Gray)
                }
            }
    )
}

@Preview(showBackground = false)
@Composable
private fun ClockStepsPreviewLight() {
    AdvancedCanvasAnimationsTheme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            ClockStepsFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun ClockStepsPreviewDark() {
    AdvancedCanvasAnimationsTheme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            ClockStepsFunctionForPreview()
        }
    }
}
