package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.mappers

import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.algorithm.MergeSortAlgorithmStep

/**
 * this function regenerate hte steps list by changing indexes in each step after a swap step.
 * eg:
 * in step n index_3 became second visible item from left and index_2 became third from lift
 * then step n+1 tells: annotate index_3 as key_index (green stroke)
 * if no mappers set then it will take the item which is the second from the left, but
 * when the step generated it is working on last rearrange of the list
 */
fun List<MergeSortAlgorithmStep>.stepsAccordingToAbsoluteIndexes() {

}
