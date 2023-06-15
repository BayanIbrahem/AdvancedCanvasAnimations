package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.enums

data class AdCanAniProject(val title: String, val description: String) {
    companion object{
        val DefaultProject = AdCanAniProject(title = "Hello Android", description = "My Beloved Software")
    }
}