package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.mappers

import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.algorithm.MergeSortDataNode

fun <T: Number> List<T>.toMergeSortDataNode(): List<MergeSortDataNode> {
    return this.mapIndexed { i, v ->
        MergeSortDataNode(
            absoluteIndex = i,
            value = v.toDouble(),
        )
    }
}
