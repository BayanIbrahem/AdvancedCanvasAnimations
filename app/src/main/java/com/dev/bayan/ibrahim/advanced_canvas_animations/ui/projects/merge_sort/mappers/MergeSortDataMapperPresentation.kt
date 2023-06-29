package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.mappers

import android.app.Presentation
import androidx.compose.ui.geometry.Offset
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.algorithm.MergeSortDataNode
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.algorithm.MergeSortNodeType
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.algorithm.MergeSortPresentationNode

/*
fun MergeSortDataNode.toPresentationNode(
    index: Int,
    nodeSize: Float,
    verticalOffset: Float,
    type: MergeSortNodeType = MergeSortNodeType.NORM_ACTIVE_NODE,
): MergeSortPresentationNode {
    return MergeSortPresentationNode(
        value = this.value,
        index = index,
        offset = Offset(index * nodeSize, verticalOffset),
        type = type,
    )
}

fun MergeSortPresentationNode.toDataNode(): MergeSortDataNode = MergeSortDataNode(this.value)

fun List<MergeSortDataNode>.mapToPresentationNodes(
    layoutWidth: Float,
    layoutHeight: Float,
): List<MergeSortPresentationNode> {
    val nodeSize = layoutWidth / this.size
    return this.mapIndexed { i, node ->
        node.toPresentationNode(
            index = i,
            nodeSize = nodeSize,
            verticalOffset = layoutHeight/2
        )
    }
}

fun List<MergeSortPresentationNode>.mapToDataNodes(): List<MergeSortDataNode> = this.map { it.toDataNode() }*/
