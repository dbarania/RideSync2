package com.example.myapplication.network


data class StopData(
    val routeId: HashMap<String, Int>,
    val headsign: HashMap<String, String>,
    val theoreticalTime: HashMap<String, String>
)

data class LineData(
    val stopId: HashMap<String, Int>,
    val stopSequence: HashMap<String, Int>,
    val id: HashMap<String, Int>,
)
