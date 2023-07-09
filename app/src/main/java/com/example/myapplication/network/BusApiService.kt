package com.example.myapplication.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "http://10.0.62.78:3000"


interface BusApiService {
    @GET("/select_line_from_stop")
    fun getLineFromStop(@Query("busstop") busstop: Int, @Query("line") line: Int): Call<StopData>

    @GET("/stop")
    fun getStop(@Query("busstop") busstop: Int): Call<StopData>

    @GET("/line_stops")
    fun getStopsFromLine(@Query("line") line: Int): Call<LineData>
}