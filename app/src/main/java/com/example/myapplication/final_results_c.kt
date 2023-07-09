package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class final_results_c : AppCompatActivity() {
    var resultList = emptyList<result>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ResultAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_results_c)
        resultList = readResultCSV(applicationContext,"busstop182_data.csv")
        recyclerView = findViewById(R.id.resultsRecycler)
        adapter = ResultAdapter(resultList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
    }
}

fun readResultCSV(context: Context,fileName:String):List<result>{
    val resultLst = mutableListOf<result>()
    try {
        val inputStream = context.assets.open(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line: String?

        while (reader.readLine().also { line = it } != null) {
            val value = line?.split(",")
            val number = value?.get(1)?.toInt()
            val name = value?.get(2).toString()
            val time = value?.get(3).toString()
            number?.let { result(it,name,time) }?.let { resultLst.add(it) }
        }
        } catch (e: IOException) {
        e.printStackTrace()
    }
    return resultLst
}