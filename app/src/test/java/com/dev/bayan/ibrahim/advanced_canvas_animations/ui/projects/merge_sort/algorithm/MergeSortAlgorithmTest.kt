package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.algorithm

import junit.framework.TestCase.assertTrue
import org.junit.Test

class MergeSortAlgorithmTest {
    @Test
    fun mergeSortAlgorithm_enterEmptyList_returnsEmptyList() {
        val list = mutableListOf<MergeSortDataNode>()
        mergeSortAlgorithm(list) {}
        assertTrue(list.isEmpty())
    }
    @Test
    fun mergeSortAlgorithm_enterOneItemList_returnsTheSameList() {
        val node = MergeSortDataNode(1.0)
        val list = mutableListOf<MergeSortDataNode>(node)
        val copy = list.map{it}.toMutableList()
        mergeSortAlgorithm(copy) {}
        assertTrue(copy.size == 1)
        assertTrue(copy.contains(node))
    }
    @Test
    fun mergeSortAlgorithm_enterRandomList_returnsSortedList() {
        val node_1 = MergeSortDataNode(1.0)
        val node_2 = MergeSortDataNode(2.0)
        val node_3 = MergeSortDataNode(3.0)
        val node_4 = MergeSortDataNode(4.0)
        val node_5 = MergeSortDataNode(5.0)
        val node_6 = MergeSortDataNode(6.0)
        val list = mutableListOf<MergeSortDataNode>(node_6, node_1, node_3, node_2, node_4, node_5)
        val copy = list.map{it}.toMutableList()
        mergeSortAlgorithm(copy) {}
        val expectedList = mutableListOf<MergeSortDataNode>(node_1, node_2, node_3, node_4, node_5, node_6)
        assertTrue(copy == expectedList)
    }
    @Test
    fun mergeSortAlgorithm_enterSortedList_returnsSameList() {
        val node_1 = MergeSortDataNode(1.0)
        val node_2 = MergeSortDataNode(2.0)
        val node_3 = MergeSortDataNode(3.0)
        val node_4 = MergeSortDataNode(4.0)
        val node_5 = MergeSortDataNode(5.0)
        val node_6 = MergeSortDataNode(6.0)
        val list = mutableListOf<MergeSortDataNode>(node_1, node_2, node_3, node_4, node_5, node_6)
        val copy = list.map{it}.toMutableList()
        mergeSortAlgorithm(copy) {}
        val expectedList = mutableListOf<MergeSortDataNode>(node_1, node_2, node_3, node_4, node_5, node_6)
        assertTrue(copy == expectedList)
    }
}