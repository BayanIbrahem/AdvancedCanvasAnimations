package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.mappers

import androidx.compose.ui.geometry.Offset
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.Hour12
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.Hour24
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.Min
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.Sec
import kotlin.math.cos
import kotlin.math.sin

private fun toCircleRadian(value: Int, ratio: Int): Double {
    val degree = 90 - value * ratio
    return degree * Math.PI / 180
}
fun secondToCircleRadian(seconds: Int): Double {
    return toCircleRadian(seconds, 6)
}
fun hourToCircleRadian(hours: Int): Double {
    return toCircleRadian(hours, 30)
}

fun Sec.headOffset(center: Offset): Offset {
    val degree = secondToCircleRadian(this.value).toFloat()
    val x = (cos(degree) + 1) * center.x
    val y = (1 - sin(degree)) * center.y
    return Offset(
        x = x,
        y = y,
    )
}
fun Sec.scaledHeadOffset(center: Offset, ratio: Float): Offset {
    val headOffset = this.headOffset(center)
    return headOffset.scaleToCenter(center, ratio)
}

fun Min.headOffset(center: Offset): Offset {
    val degree = secondToCircleRadian(this.value).toFloat()
    val x = (cos(degree) + 1) * center.x
    val y = (1 - sin(degree)) * center.y
    return Offset(
        x = x,
        y = y,
    )
}
fun Min.scaledHeadOffset(center: Offset, ratio: Float): Offset {
    val headOffset = this.headOffset(center)
    return headOffset.scaleToCenter(center, ratio)
}

fun Hour12.headOffset(center: Offset): Offset {
    val degree = hourToCircleRadian(this.value).toFloat()
    val x = (cos(degree) + 1) * center.x
    val y = (1 - sin(degree)) * center.y
    return Offset(
        x = x,
        y = y,
    )
}
fun Hour12.scaledHeadOffset(center: Offset, ratio: Float): Offset {
    val headOffset = this.headOffset(center)
    return headOffset.scaleToCenter(center, ratio)
}

fun Hour24.headOffset(center: Offset): Offset {
    val degree = hourToCircleRadian(this.value).toFloat()
    val x = (cos(degree) + 1) * center.x
    val y = (1 - sin(degree)) * center.y
    return Offset(
        x = x,
        y = y,
    )
}
fun Hour24.scaledHeadOffset(center: Offset, ratio: Float): Offset {
    val headOffset = this.headOffset(center)
    return headOffset.scaleToCenter(center, ratio)
}

