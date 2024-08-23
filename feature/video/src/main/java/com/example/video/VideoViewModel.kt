package com.example.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ApiResult
import com.example.domain.usecase.GetScriptSection
import com.example.domain.usecase.GetScriptTextItem
import com.example.model.TextItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    getScriptSection: GetScriptSection,
    getScriptTextItem: GetScriptTextItem
): ViewModel() {

    private val _currentTime = MutableStateFlow(0)
    val currentTime get() = _currentTime.asStateFlow()
    val scriptName = "test_data_lexical.txt"
    val sectionTime = 10

    private val script = getScriptTextItem(scriptName)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val sectionScripts = script.combine(currentTime) { items, time ->
        getScriptSection(
            items,
            sectionTime,
            time
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    /*val sectionScript = currentTime
        .filter { !scriptError }
        .combine(script) { time, list ->
            ScriptState(
                list, time
            )
        }
        .flatMapLatest { state ->
            getScriptSection(
                script = scriptName,
                sectionTime = sectionTime,
                start = time,
            ).map { apiResult ->
                when(apiResult) {
                    is ApiResult.Success -> {
                        apiResult.value
                    }
                    else -> {
                        scriptError = true
                        ""
                    }
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ""
        )*/

    fun updateCurrentTime(time: String) {
        _currentTime.value = time.toFloat().toInt()
    }
}

data class ScriptState(
    val items: List<TextItem>,
    val time: Int
)