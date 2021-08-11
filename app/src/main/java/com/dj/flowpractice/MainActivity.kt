package com.dj.flowpractice

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launchWhenStarted {
            // collect(), toList(): terminal operators
            makeFlow().collect {
                println("received $it")
            }
            // take: takes only count's emission
            makeFlow().take(2).collect {
                println("Received $it")
            }
            println("Reception Completed")


            // Test For configuration change
            // viewModel.stateFlow.collect{
            //     println("data stateFlow: $it")
            // }
            viewModel.sharedFlow.collect {
                println("data sharedFlow: $it")
            }
        }
    }

    // Flow will restart from the top every time a terminal operator is applied
    private fun makeFlow(): Flow<Int> = flow {
        println("First")
        emit(1)
        println("Second")
        emit(2)
        println("Third")
        emit(3)
    }
}