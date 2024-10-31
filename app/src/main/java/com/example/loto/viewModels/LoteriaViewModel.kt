package com.example.loto.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoteriaViewModel: ViewModel() {
    private val _lotoNumbers = mutableStateOf(emptyList<Int>())
    val lotoNumbers: State<List<Int>> = _lotoNumbers

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun generateLotoNumbers() {
        viewModelScope.launch {
            _isLoading.value = true
            _lotoNumbers.value = emptyList()
            val generatedNumbers = mutableListOf<Int>()
            (1..6).forEach {
                val newNumber = (1..60).shuffled().first()
                generatedNumbers.add(newNumber)
                _lotoNumbers.value = generatedNumbers.toList()
                delay(2000)
            }
            _isLoading.value = false
        }
    }
}