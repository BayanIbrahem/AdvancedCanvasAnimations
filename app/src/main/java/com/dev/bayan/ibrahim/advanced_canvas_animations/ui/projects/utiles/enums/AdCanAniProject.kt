package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.utiles.enums

data class AdCanAniProject(val title: String, val briefDescription: String, val description: String) {
    companion object{
        val DefaultProject = AdCanAniProject(
            title = "Hello Android, Hello Canvas",
            briefDescription = "My Beloved Software",
            description = "in this project i am training on advanced drawing in jetpack compose"
        )
    }
}