package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.mappers

import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.algorithm.MergeSortAlgorithmStep
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.algorithm.MergeSortAlgorithmStepEffectOnNode
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.algorithm.MergeSortDataNode
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.algorithm.MergeSortNodeType

fun MergeSortAlgorithmStep.affectOnNode(nodeIndex: Int, stepIndex: Int): MergeSortAlgorithmStepEffectOnNode {
    return when (this) {
        is MergeSortAlgorithmStep.AddNodeToTempSortedList -> {
            if (nodeIndex == nodeAbsoluteIndex) {
//            if (nodeIndex == this.nodeIndex) {
                MergeSortAlgorithmStepEffectOnNode.NodeAddToTempList(
                    tempListOldSize = this.nextPositionInSubSortedTempList,
                    stepIndex = stepIndex,
                )
            } else {
                MergeSortAlgorithmStepEffectOnNode.NoChange(
                    stepIndex = stepIndex,
                )
            }
        }
        is MergeSortAlgorithmStep.ComparingTwoNodes -> {
            if (nodeIndex == firstNodeAbsoluteIndex) {
//            if (nodeIndex == firstNodeIndex) {
                MergeSortAlgorithmStepEffectOnNode.NodeChangeType(
                    type = MergeSortNodeType.KEY_NODE,
                    stepIndex = stepIndex,
                )
            } else if (nodeIndex == secondNodeAbsoluteIndex) {
//            } else if (nodeIndex == secondNodeIndex) {
                MergeSortAlgorithmStepEffectOnNode.NodeChangeType(
                    type = MergeSortNodeType.SECOND_NODE,
                    stepIndex = stepIndex,
                )
            } else {
                MergeSortAlgorithmStepEffectOnNode.NodeChangeType(
                    type = MergeSortNodeType.NORM_ACTIVE_NODE,
                    stepIndex = stepIndex,
                )
            }
        }
        is MergeSortAlgorithmStep.ReturnTempSortedListToItsPlace -> {
            if (nodeIndex in absoluteStartIndexes) {
//            if (nodeIndex in startIndex..endIndex) {
                MergeSortAlgorithmStepEffectOnNode.NodeReturnedToList(
                    stepIndex = stepIndex,
                )
            } else {
                MergeSortAlgorithmStepEffectOnNode.NoChange(
                    stepIndex = stepIndex,
                )
            }
        }
        is MergeSortAlgorithmStep.SortedOneItemSubList -> {
            if (nodeIndex == absoluteIndex) {
                MergeSortAlgorithmStepEffectOnNode.NodeChangeType(
                    type = MergeSortNodeType.NORM_ACTIVE_NODE,
                    stepIndex = stepIndex,
                )
            } else {
                MergeSortAlgorithmStepEffectOnNode.NodeChangeType(
                    type = MergeSortNodeType.INACTIVE_NODE,
                    stepIndex = stepIndex,
                )
            }
//            MergeSortAlgorithmStepEffectOnNode.NoChange(
//                stepIndex = stepIndex,
//            )
        }
        is MergeSortAlgorithmStep.SortingSubList -> {
            if (nodeIndex in absoluteIndexes){
//            if (nodeIndex in startIndex..endIndex){
                MergeSortAlgorithmStepEffectOnNode.NodeChangeType(
                    type = MergeSortNodeType.NORM_ACTIVE_NODE,
                    stepIndex = stepIndex,
                )
            } else {
                MergeSortAlgorithmStepEffectOnNode.NodeChangeType(
                    type = MergeSortNodeType.INACTIVE_NODE,
                    stepIndex = stepIndex,
                )
            }
        }
        is MergeSortAlgorithmStep.SplittingCurrentSubList -> {
            if (nodeIndex == firstEndIndex) {
                MergeSortAlgorithmStepEffectOnNode.NodeChangeType(
                    type = MergeSortNodeType.RIGHT_ACTIVE_NODE,
                    stepIndex = stepIndex,
                    )
            } else if (nodeIndex == secondStartIndex) {
                MergeSortAlgorithmStepEffectOnNode.NodeChangeType(
                    type = MergeSortNodeType.LEFT_ACTIVE_NODE,
                    stepIndex = stepIndex,
                )
            } else if (nodeIndex in firstStartIndex..secondEndIndex){
                MergeSortAlgorithmStepEffectOnNode.NodeChangeType(
                    type = MergeSortNodeType.NORM_ACTIVE_NODE,
                    stepIndex = stepIndex,
                )
            } else {
                MergeSortAlgorithmStepEffectOnNode.NodeChangeType(
                    type = MergeSortNodeType.INACTIVE_NODE,
                    stepIndex = stepIndex,
                )
            }
        }
    }
}

fun List<MergeSortAlgorithmStep>.affectOnNodes(
    data: List<MergeSortDataNode>,
): Map<Int, List<MergeSortAlgorithmStepEffectOnNode>> {
    val stepsEffect = mutableMapOf<Int, List<MergeSortAlgorithmStepEffectOnNode>>()
    data.forEach { dataNode ->
        val effectOnNode = mutableListOf<MergeSortAlgorithmStepEffectOnNode>()
        this.forEachIndexed {stepIndex, step ->
            val affect = step.affectOnNode(
                nodeIndex = dataNode.absoluteIndex,
                stepIndex = stepIndex,
            )
            if (
                affect != effectOnNode.getOrNull(stepIndex - 1) &&
                (affect is  MergeSortAlgorithmStepEffectOnNode.NoChange).not()
            ) {
                effectOnNode.add(affect)
            }
        }
        stepsEffect[dataNode.absoluteIndex] = effectOnNode
    }
    return stepsEffect
}