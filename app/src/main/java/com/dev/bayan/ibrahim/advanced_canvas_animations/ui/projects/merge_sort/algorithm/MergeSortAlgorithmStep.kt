package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.algorithm

sealed class MergeSortAlgorithmStep(val name: String) {
    data class SortedOneItemSubList(
        val index: Int,
        val absoluteIndex: Int
        ): MergeSortAlgorithmStep("Sorted One Item List ($index)")

    data class SortingSubList(
        val startIndex: Int,
        val endIndex: Int,
        val absoluteIndexes: List<Int>,
    ): MergeSortAlgorithmStep("Start Sorting SubList ($startIndex, $endIndex)")

    data class SplittingCurrentSubList(
        val firstStartIndex: Int,
        val firstEndIndex: Int,
        val secondStartIndex: Int,
        val secondEndIndex: Int,
        val firstAbsoluteIndexes: List<Int>,
        val secondAbsoluteIndexes: List<Int>,
    ): MergeSortAlgorithmStep("Splitting Current Sublist ($firstStartIndex, $firstEndIndex), ($secondStartIndex, $secondEndIndex)")

    data class ComparingTwoNodes(
        val firstNodeIndex: Int,
        val secondNodeIndex: Int,
        val firstNodeAbsoluteIndex: Int,
        val secondNodeAbsoluteIndex: Int,
    ): MergeSortAlgorithmStep("Comparing Two Nodes ($firstNodeIndex, $secondNodeIndex)")

    data class AddNodeToTempSortedList(
        val nodeIndex: Int,
        val nextPositionInSubSortedTempList: Int,
        val nodeAbsoluteIndex: Int,
        val nextAbsolutePositionInSubSortedTempList: Int,
    ): MergeSortAlgorithmStep("Add Node To Timber Sorted List ($nodeIndex)")

    data class ReturnTempSortedListToItsPlace(
        val startIndex: Int,
        val endIndex: Int,
        val absoluteStartIndexes: List<Int>,
    ): MergeSortAlgorithmStep("Return Timber Sorted List To Its Place ($startIndex)")
}

sealed class MergeSortAlgorithmStepEffectOnNode(open val stepIndex: Int) {
    data class NodeChangeType(
        val type: MergeSortNodeType,
        override val stepIndex: Int
    ): MergeSortAlgorithmStepEffectOnNode(stepIndex)

    data class NodeAddToTempList(
        val tempListOldSize: Int,
        override val stepIndex: Int,
    ): MergeSortAlgorithmStepEffectOnNode(stepIndex)

    data class NodeReturnedToList (
        override val stepIndex: Int
    ): MergeSortAlgorithmStepEffectOnNode(stepIndex)

    class NoChange(
        override val stepIndex: Int
    ): MergeSortAlgorithmStepEffectOnNode(stepIndex)
}
