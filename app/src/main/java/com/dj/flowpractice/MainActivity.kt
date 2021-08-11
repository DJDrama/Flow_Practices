package com.dj.flowpractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launchWhenStarted {
            makeFlow().collect {
                println("received $it")
            }
            println("Reception Completed")
        }
    }

    private fun makeFlow(): Flow<Int> = flow {
        println("First")
        emit(1)
        println("Second")
        emit(2)
        println("Third")
        emit(3)
    }
}