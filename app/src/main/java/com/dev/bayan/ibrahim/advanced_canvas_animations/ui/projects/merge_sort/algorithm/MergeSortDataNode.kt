package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.algorithm

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

data class MergeSortDataNode(
    val value: Double,
    val absoluteIndex: Int,
): Comparable<MergeSortDataNode> {
    override fun compareTo(other: MergeSortDataNode): Int {
        return this.value.compareTo(other.value)
    }
}

data class MergeSortPresentationNode(
    val value: Double,
    val index: Int,
    val type: MergeSortNodeType,
    val offset: Offset,
    val isInList: Boolean = true,
): Comparable<MergeSortDataNode> {
    override fun compareTo(other: MergeSortDataNode): Int {
        return this.value.compareTo(other.value)
    }
}


enum class MergeSortNodeType(
    val strokeColor: Color,
    val strokeMainWidth: Float,
    val strokeSecondWidth: Float,
    val alpha: Float,
) {
    KEY_NODE(
        strokeColor = Color.Green,
        strokeMainWidth = 5f,
        strokeSecondWidth = 0f,
        alpha = 1f,
    ),
    SECOND_NODE(
        strokeColor = Color.Red,
        strokeMainWidth = 5f,
        strokeSecondWidth = 0f,
        alpha = 1f,
    ),
    NORM_ACTIVE_NODE (
        strokeColor = Color.Black,
        strokeMainWidth = 2f,
        strokeSecondWidth = 0f,
        alpha = 1f,
    ),
    LEFT_ACTIVE_NODE (
        strokeColor = Color.Black,
        strokeMainWidth = 2f,
        strokeSecondWidth = 5f,
        alpha = 1f,
    ),
    RIGHT_ACTIVE_NODE (
        strokeColor = Color.Black,
        strokeMainWidth = 2f,
        strokeSecondWidth = 5f,
        alpha = 1f,
    ),
    INACTIVE_NODE (
        strokeColor = Color.Black,
        strokeMainWidth = 2f,
        strokeSecondWidth = 0f,
        alpha = 0.5f,
    ),
}
