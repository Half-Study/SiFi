package com.example.sifi.profileaddsetting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val _stateFlow = MutableStateFlow("")
    val stateFlow = _stateFlow.asStateFlow()

    fun changeStateFlow(currentMbti: String) {
        viewModelScope.launch {
            _stateFlow.emit(currentMbti)
            Log.d("daeYoung", "현재 MBTI: ${currentMbti.firstOrNull()}")
        }
    }
}
