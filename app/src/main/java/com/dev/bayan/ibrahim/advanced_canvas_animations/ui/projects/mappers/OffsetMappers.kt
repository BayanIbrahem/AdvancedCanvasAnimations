package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.mappers

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center

fun Offset.scaleToCenter(center: Offset, ratio: Float): Offset {
    val x = center.x - (center.x - this.x) * ratio
    val y = center.y - (center.y - this.y) * ratio
    return Offset(x, y)
}
