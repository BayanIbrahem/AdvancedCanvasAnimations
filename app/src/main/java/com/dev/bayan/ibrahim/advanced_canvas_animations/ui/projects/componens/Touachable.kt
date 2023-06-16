package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.componens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.gestures.verticalDrag
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun Modifier.fingerTouch(
    onPress: (offset: Offset) -> Unit,
    onRelease: (offset: Offset) -> Unit,
): Modifier = this.composed {
    val offsetX = remember { Animatable(0f) }
    var targetX: Float by remember { mutableStateOf(0f)}
    var targetY: Float by remember { mutableStateOf(0f)}
    pointerInput(Unit) {
        val decay = splineBasedDecay<Float>(this)
        coroutineScope {
            while(true) {
                // Wait for a touch down event.
                val pointerId = awaitPointerEventScope {
                    val pointerInputChange = awaitFirstDown()
                    onPress(pointerInputChange.position)
                    pointerInputChange.id
                }
                // Prepare for drag events and record velocity of a fling.
                val velocityTracker = VelocityTracker()
                // Wait for drag events.
                awaitPointerEventScope {
                    verticalDrag(pointerId) { change ->
                        launch {
                            val velocity = velocityTracker.calculateVelocity().y
                            targetY = decay.calculateTargetValue(change.position.y, velocity)
                        }
                        velocityTracker.addPosition(change.uptimeMillis, change.position)
                        if (change.positionChange() != Offset.Zero) change.consume()
                    }
                    horizontalDrag(pointerId) { change ->
                        // TODO 6-3: Apply the drag change to the Animatable offset.
//                        val horizontalDragOffset = offsetX.value + change.positionChange().x
//                        val horizontalDragOffset = change.position.x
                        launch {
//                            offsetX.snapTo(horizontalDragOffset)
                            val velocity = velocityTracker.calculateVelocity().x
                            targetX = decay.calculateTargetValue(change.position.x, velocity)
//                            onHorizontalDrag(targetX.absoluteValue)
                        }
                        // Record the velocity of the drag.
                        velocityTracker.addPosition(change.uptimeMillis, change.position)
                        // Consume the gesture event, not passed to external
                        if (change.positionChange() != Offset.Zero) change.consume()
                    }
                }
                offsetX.updateBounds(
                    lowerBound = -size.width.toFloat(),
                    upperBound = size.width.toFloat(),
                )
                launch {
                    onRelease(Offset(targetX, targetY))
                }
            }
        }
    }
}

