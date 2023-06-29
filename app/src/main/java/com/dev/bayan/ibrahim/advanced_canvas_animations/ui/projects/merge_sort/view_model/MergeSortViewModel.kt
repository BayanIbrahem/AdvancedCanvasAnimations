package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.view_model

import androidx.compose.ui.res.stringArrayResource
import androidx.lifecycle.ViewModel
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.algorithm.MergeSortAlgorithmStep
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.algorithm.MergeSortAlgorithmStepEffectOnNode
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.algorithm.MergeSortDataNode
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.algorithm.mergeSortAlgorithm
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.merge_sort.mappers.affectOnNodes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MergeSortViewModel(
    val data: List<MergeSortDataNode>
): ViewModel() {
    val effectOnNodesMap :StateFlow<Map<Int, List<MergeSortAlgorithmStepEffectOnNode>>>
    val steps :StateFlow<List<MergeSortAlgorithmStep>>
    private val _currentStep = MutableStateFlow<Int>(0)
    val currentStep : StateFlow<Int> = _currentStep

    private val stepsCount: Int

    init {
        val stepsList = mutableListOf<MergeSortAlgorithmStep>()
        mergeSortAlgorithm(data.toMutableList()) {
            stepsList.add(it)
        }
        stepsCount = stepsList.size
        steps = MutableStateFlow (
            stepsList
        ).asStateFlow()
        effectOnNodesMap = MutableStateFlow (
            stepsList.affectOnNodes(data)
        ).asStateFlow()
    }

    fun onNextStep(): Boolean {
        return if (currentStep.value < stepsCount-1) {
            _currentStep.update { it.inc() }
            true
        } else false
    }
    fun onPrevStep(): Boolean {
        return if (currentStep.value > 0) {
            _currentStep.update { it.dec() }
            true
        } else false
    }
}