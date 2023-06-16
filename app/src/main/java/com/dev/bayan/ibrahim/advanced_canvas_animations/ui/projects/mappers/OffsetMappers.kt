package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.mappers

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size

fun Offset.scaleToOffset(baseOffset: Offset = Offset(0f, 0f), ratio: Float): Offset {
    val x = baseOffset.x - (baseOffset.x - this.x) * ratio
    val y = baseOffset.y - (baseOffset.y - this.y) * ratio
    return Offset(x, y)
}
fun Offset.moveByVector(vectorEndPoint: Offset, ratio: Float = 1f): Offset {
    val x = vectorEndPoint.x * ratio + this.x
    val y = vectorEndPoint.y * ratio + this.y
    return Offset(x, y)
}
fun getVectorByGridPosition(position: Int, size: Size, rows: Int = 3, cols: Int = 3): Offset {
    val vectorX: Float = (position % rows) * size.width/rows
    val vectorY: Float = (position / cols) * size.width/cols
    return Offset(vectorX, vectorY)
}
