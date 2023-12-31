package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.tic_tac


import android.util.Log
import android.view.MotionEvent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.tic_tac.components.TicTacTacSymbol
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.tic_tac.components.TicTacTicSymbol
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.tic_tac.components.ticTacBoard
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.theme.AdvancedCanvasAnimationsTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AdCanAniProTicTac(
    modifier: Modifier = Modifier,
    boardBgColor: Color = Color.White,
    boardLinesColor: Color = Color.Black,
    ticColor: Color = Color.Red,
    tacColor: Color = Color.Green,
    ticFirst: Boolean = true,
    drawingDuration: Int = 500,
) {
    val bgLinesDrawingAnimation = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        bgLinesDrawingAnimation.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = drawingDuration, easing = FastOutSlowInEasing),
        )
    }
    var squares by remember { mutableStateOf(List(9){-1}) }
    var currentTurn by remember { mutableStateOf(0) }
    var completedRounds by remember { mutableStateOf(0) }

    var releasedFingerPosition by remember {
        mutableStateOf(Offset(-100f, -100f))
    }
    var boxSize by remember {
        mutableStateOf(Size(0f, 0f))
    }
    var gameEnded by remember {
        mutableStateOf(false)
    }
    var gameEndingLine by remember {
        mutableStateOf(listOf(-1, -1, -1))
    }
    LaunchedEffect(releasedFingerPosition) {
        val xStep = (3 * releasedFingerPosition.x / boxSize.width).toInt()
        val yStep = (3 * releasedFingerPosition.y / boxSize.height).toInt()
        val index = (3 * yStep + xStep)
        if (releasedFingerPosition.x > 0 && releasedFingerPosition.y > 0) {
            squares = squares.mapIndexed {i, square ->
                if (i == index && square == -1) {
                    if (ticFirst) {
                        currentTurn % 2.also {
                            currentTurn += 1
                        }
                    } else {
                        currentTurn += 1
                        currentTurn % 2
                    }
                } else {
                    square
                }
            }
            val ender = isGameEnded(squares)
            gameEnded = ender != -1 || squares.filter{it == -1}.isEmpty()
            gameEndingLine = gameEndingLineFromEnder(ender)
        }
    }

    Box(
        modifier = modifier
            .aspectRatio(1f)
            .padding(8.dp)
            .pointerInteropFilter {event ->
                Log.d("pointer_interop_filter", "action: ${event.action}")
                if (event.action == MotionEvent.ACTION_UP){
                    if (gameEnded.not()) {
                        releasedFingerPosition = Offset(event.x, event.y)
                    }
                }
                /* is event consumed?
                 this will be true if we don't need other children to receive this event */
                gameEnded.not()
            }
            .drawWithCache {
                onDrawBehind {
                    boxSize = size
                    ticTacBoard(
                        color = boardLinesColor,
                        bgColor = boardBgColor,
                        drawingPercentage = bgLinesDrawingAnimation.value
                    )
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        Column (Modifier.fillMaxSize()) {
            repeat(3) {row ->
                Row(
                    Modifier
                        .weight(1f)
                        .fillMaxSize()) {
                    repeat(3) { item ->
                        val index = 3 * row + item
                        Box(
                            modifier = Modifier
                                .alpha(
                                    if (index in gameEndingLine && gameEnded) 1f
                                    else if (gameEnded.not()) 1f
                                    else 0.3f
                                )
                                .weight(1f)
                                .fillMaxSize()
                        ) {
                            val state = squares[index]
                            if (state == 0) {
                                TicTacTicSymbol(color = ticColor)
                            } else if (state == 1) {
                                TicTacTacSymbol(color = tacColor)
                            }
                        }
                    }
                }
            }
        }
        AnimatedVisibility(
            visible = gameEnded,
            enter = slideInVertically(initialOffsetY = { -40 }, animationSpec = tween(500)),
            exit = slideOutVertically(targetOffsetY = { -40 }, animationSpec = tween(500)),
            label = "",
        ) {
            ElevatedButton(
                onClick = {
                    squares = List(9){-1}
                    gameEnded = false
                    completedRounds += 1
                    currentTurn = completedRounds % 2
                }
            ) {
                val ticWin = squares.getOrNull(gameEndingLine.first()) == 0
                val tacWin = squares.getOrNull(gameEndingLine.first()) == 1
                Text(text = "Game Ended ${if(ticWin) "X Wins" else if(tacWin) "O Wins" else "Draw"}")
            }
        }
    }

}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun AdCanAniProTicTacFunctionForPreview() {
    AdCanAniProTicTac(ticFirst = false)
}

@Preview(showBackground = false)
@Composable
private fun AdCanAniProTicTacPreviewLight() {
    AdvancedCanvasAnimationsTheme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            AdCanAniProTicTacFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun AdCanAniProTicTacPreviewDark() {
    AdvancedCanvasAnimationsTheme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            AdCanAniProTicTacFunctionForPreview()
        }
    }
}
