package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.GridView
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private lateinit var lineGV:GridView
    var chosenRoute:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val routeList = readRoutesCSVData(applicationContext,"routes.csv")
        val stopsMap = readStopCSVData(applicationContext,"stops_names.csv")
        setContentView(R.layout.main_page_a)
        lineGV = findViewById(R.id.lines_grid)
        val searchBar = findViewById<AutoCompleteTextView>(R.id.search_bar)
        val adapterArray = ArrayAdapter(applicationContext,android.R.layout.simple_spinner_dropdown_item,stopsMap.keys.toTypedArray())
        searchBar.setAdapter(adapterArray)
        searchBar.setOnItemClickListener{parent,view,position,id->
            val selectedKey = parent.getItemAtPosition(position) as String

            val intent = Intent(this, final_results_c::class.java)
            intent.putExtra("selectedKey", selectedKey)
            startActivity(intent)
        }

        val adapter = gridAdapter(this,routeList){x->chosenRoute = x
        Log.d("MainActivity", "nie value: $chosenRoute")
        startNewActivity()
        }
        lineGV.adapter = adapter
    }
    private fun startNewActivity(){
        val intent = Intent(this,stops_b::class.java)
        intent.putExtra("choserRoute",chosenRoute)
        startActivity(intent)
    }
}



fun readStopCSVData(context: Context, fileName: String):Map<String?,Int?>{
    val stopMap:MutableMap<String?,Int?> = mutableMapOf()
    try {
        val inputStream = context.assets.open(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line: String?

        while (reader.readLine().also { line = it } != null) {
            val values = line?.split(",")
            val id = values?.get(0)?.toInt()
            val stopName = values?.get(1).toString()
            stopMap[stopName] = id
        }

        reader.close()
        inputStream.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return stopMap
}

fun readRoutesCSVData(context: Context, fileName: String): List<String> {
    val routeSet = mutableListOf<Int>()
    try {
        val inputStream = context.assets.open(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line: String?

        while (reader.readLine().also { line = it } != null) {
            line?.let { routeSet.add(it.toInt()) }
        }
        reader.close()
        inputStream.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    routeSet.sort()
    return routeSet.map { it.toString() }

}