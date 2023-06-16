package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components

data class Sec(val value: Int)
data class Min(val value: Int)
data class Hour12(val value: Int)
data class Hour24(val value: Int)

val Int.sec: Sec
    get() = Sec(this % 60)

val Int.min: Min
    get() = Min(this % 60)

val Int.hour12: Hour12
    get() = Hour12(if(this == 12) 12 else this % 12)

val Int.hour24: Hour24
    get() = Hour24(this % 24)
