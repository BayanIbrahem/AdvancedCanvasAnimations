package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.algorithm

fun mergeSortAlgorithm (
    nodes: MutableList<MergeSortDataNode>,
    start: Int = 0,
    end: Int = nodes.size-1,
    onStep: (MergeSortAlgorithmStep) -> Unit,
){
    onStep(
        MergeSortAlgorithmStep.SortingSubList(
            startIndex = start, endIndex = end,
            absoluteIndexes = nodes.subList(start, minOf(end + 1, nodes.size)).map { it.absoluteIndex }
        )
    )
    // only one item
    if (start >= end) {
        if (start == end) onStep(
            MergeSortAlgorithmStep.SortedOneItemSubList(
                index = start,
                absoluteIndex = nodes[start].absoluteIndex,
            )
        )
        return
    }
    // 0  1  2  3  4  [5  6  7 | 8  9]
    val firstEnd = (end-start)/2 + start
    val secondStart = firstEnd + 1
    onStep(
        MergeSortAlgorithmStep.SplittingCurrentSubList(
            firstStartIndex = start,
            firstEndIndex = firstEnd,
            secondStartIndex = secondStart,
            secondEndIndex = end,
            firstAbsoluteIndexes = nodes.subList(start, minOf(firstEnd + 1, nodes.size)).map { it.absoluteIndex },
            secondAbsoluteIndexes = nodes.subList(secondStart, minOf(end + 1, nodes.size)).map { it.absoluteIndex },
        )
    )
    mergeSortAlgorithm(
        nodes = nodes,
        start = start,
        end = firstEnd,
        onStep = onStep
    )
    mergeSortAlgorithm(
        nodes = nodes,
        start = secondStart,
        end = end,
        onStep = onStep
    )
    // *  *  *  *  * [5  6  9  12  14  15]
    val sortedNodes = mutableListOf<MergeSortDataNode>()
    var i = start
    var j = secondStart
    while (i <= firstEnd && j <= end) {
        val first = nodes[i]
        val second = nodes[j]
        onStep(
            MergeSortAlgorithmStep.ComparingTwoNodes(
                firstNodeIndex = i,
                secondNodeIndex = j,
                firstNodeAbsoluteIndex = nodes[i].absoluteIndex,
                secondNodeAbsoluteIndex = nodes[j].absoluteIndex,
            )
        )
        if (first < second) {
            onStep(
                MergeSortAlgorithmStep.AddNodeToTempSortedList(
                    nodeIndex = i,
                    nextPositionInSubSortedTempList = sortedNodes.size + start,
                    nodeAbsoluteIndex = nodes[i].absoluteIndex,
                    nextAbsolutePositionInSubSortedTempList = nodes[sortedNodes.size + start].absoluteIndex,
                )
            )
            sortedNodes.add(first)
            i += 1
        }
        else {
            onStep(
                MergeSortAlgorithmStep.AddNodeToTempSortedList(
                    nodeIndex = j,
                    nextPositionInSubSortedTempList = sortedNodes.size + start,
                    nodeAbsoluteIndex = nodes[j].absoluteIndex,
                    nextAbsolutePositionInSubSortedTempList = nodes[sortedNodes.size + start].absoluteIndex,
                )
            )
            sortedNodes.add(second)
            j += 1
        }
    }
    while (i <= firstEnd) {
        onStep(
            MergeSortAlgorithmStep.AddNodeToTempSortedList(
                nodeIndex = i,
                nextPositionInSubSortedTempList = sortedNodes.size + start,
                nodeAbsoluteIndex = nodes[i].absoluteIndex,
                nextAbsolutePositionInSubSortedTempList = nodes[sortedNodes.size + start].absoluteIndex,
            )
        )
        sortedNodes.add(nodes[i])
        i += 1
    }
    onStep(
        MergeSortAlgorithmStep.ReturnTempSortedListToItsPlace(
            startIndex = start,
            endIndex = sortedNodes.size + start - 1,
            absoluteStartIndexes = nodes.subList(start, minOf(sortedNodes.size + start, nodes.size)).map { it.absoluteIndex },
        )
    )
    repeat(sortedNodes.size) {i ->
        nodes[i + start] = sortedNodes[i]
    }
}
