package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class final_results_c : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_results_c)
        val selectedKey = intent.getStringExtra("selectedKey")
        Log.d("A", "witamy w c $selectedKey")
    }
}