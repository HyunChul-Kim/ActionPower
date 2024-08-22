package com.example.video

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(): ViewModel() {

    private val _currentTime = MutableStateFlow("")
    val currentTime get() = _currentTime.asStateFlow()

    fun updateCurrentTime(time: String) {
        _currentTime.value = time
    }
}