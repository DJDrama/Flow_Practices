package com.dj.flowpractice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    // Fake
    val data = listOf(
        "hello",
        "world",
        "my",
        "name",
        "is"
    )


    private val _mutableStateFlow = MutableStateFlow<List<String>>(listOf())
    val stateFlow: StateFlow<List<String>> = _mutableStateFlow.asStateFlow()

    // replay: lets you send a number of previously-emitted values for new subscribers
    private val _mutableSharedFlow = MutableSharedFlow<List<String>>(replay = 0)
    val sharedFlow = _mutableSharedFlow.asSharedFlow()
    init{
        fetchData()
    }

      fun fetchData(){
        viewModelScope.launch {
                myData().collect {
                    //  _mutableStateFlow.value = it
                    _mutableSharedFlow.emit(it)
                }
        }
    }

    private fun myData(): Flow<List<String>> = flow{
        delay(3000)
        emit(data)
    }
}