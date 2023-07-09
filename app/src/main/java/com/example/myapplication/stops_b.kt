package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.network.getStopsFromLine
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class stops_b : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: recyclerAdapter
//    private val itemList = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stops_b)
        val stopList = readStopsCSVData(applicationContext,"line107.csv")
        recyclerView = findViewById(R.id.recyclerView)
        adapter = recyclerAdapter(stopList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
    }
}

fun readStopsCSVData(context: Context,fileName:String):List<String>{
    val routeList = mutableListOf<String>()
    try {
        val inputStream = context.assets.open(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line: String?

        while (reader.readLine().also { line = it } != null) {
            val value = line?.split(",")
            routeList.add(value?.get(1) ?: "")
        }
        reader.close()
        inputStream.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return routeList
}