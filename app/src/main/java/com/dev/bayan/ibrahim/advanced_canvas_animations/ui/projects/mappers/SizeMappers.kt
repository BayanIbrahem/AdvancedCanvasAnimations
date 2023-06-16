package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.mappers

import androidx.compose.ui.geometry.Size


fun Size.scale(ratio: Float = 1f): Size {
    return this.copy(
        this.width * ratio,
        this.height * ratio,
    )
}