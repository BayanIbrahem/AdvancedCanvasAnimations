package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.clockBackground
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.clockSteps
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.hands.clockHoursHand
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.hands.clockMinutesHand
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.hands.clockSecondsHand
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.theme.AdvancedCanvasAnimationsTheme

@Composable
fun AdCanAniProClock(
    modifier: Modifier = Modifier,
    state: ClockTimeState,
    bgColor: Color,
    onBgColor: Color,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
                .padding(8.dp)
                .drawWithCache {
                    onDrawBehind {
                        clockBackground(bgColor)
                        clockSteps(onBgColor)
                        clockHoursHand(state.hour)
                        clockMinutesHand(state.min)
                        clockSecondsHand(state.sec)
                    }
                }
        )
        Text(text = "${state.hour.value}:${state.min.value}:${state.sec.value}:${state.milliSec}${if(state.isAm) "am" else "pm"}")
    }
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun AdCanAniProClockFunctionForPreview() {
    val viewModel by remember {
        mutableStateOf(AdCanAniProClockModel())
    }
    val state by viewModel.timeState.collectAsState()
    AdCanAniProClock(
        state = state,
        bgColor = MaterialTheme.colorScheme.secondaryContainer,
        onBgColor = MaterialTheme.colorScheme.onSecondaryContainer,

    )
}

@Preview(showBackground = false)
@Composable
private fun AdCanAniProClockPreviewLight() {
    AdvancedCanvasAnimationsTheme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            AdCanAniProClockFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun AdCanAniProClockPreviewDark() {
    AdvancedCanvasAnimationsTheme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            AdCanAniProClockFunctionForPreview()
        }
    }
}
