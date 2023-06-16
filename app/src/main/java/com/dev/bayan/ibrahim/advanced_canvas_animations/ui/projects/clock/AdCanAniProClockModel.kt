package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock

import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.Hour12
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.Min
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.Sec
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.hour12
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.min
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.projects.clock.components.sec
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.util.Calendar

class AdCanAniProClockModel: ViewModel() {
    private val _timeState = MutableStateFlow<ClockTimeState>(ClockTimeState.getCurrentTime())
    val timeState: StateFlow<ClockTimeState> = _timeState.asStateFlow()
    init {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                while (true) {
                    _timeState.update { ClockTimeState.getCurrentTime() }
                    delay(1000 - timeState.value.milliSec)
                }
            }
        }
    }
}

data class ClockTimeState(
    val milliSec: Long,
    val sec: Sec,
    val min: Min,
    val hour: Hour12,
    val isAm: Boolean,
) {
    companion object {
        fun getCurrentTime(): ClockTimeState {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val time = LocalDateTime.now()
                val h = if (time.hour == 12) {
                    12
                } else {
                    time.hour % 12
                }
                ClockTimeState(
                    milliSec = time.nano/1000_000L,
                    sec = time.second.sec,
                    min = time.minute.min,
                    hour = h.hour12,
                    isAm = time.hour < 12,
                )
            } else {
                val time = Calendar.getInstance()
                ClockTimeState(
                    milliSec = time.get(Calendar.MILLISECOND).toLong(),
                    sec = time.get(Calendar.SECOND).sec,
                    min = time.get(Calendar.MINUTE).min,
                    hour = time.get(Calendar.HOUR).hour12,
                    isAm = time.get(Calendar.AM_PM) == Calendar.AM
                )
            }
        }
    }
}