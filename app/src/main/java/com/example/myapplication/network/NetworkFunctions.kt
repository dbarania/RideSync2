package com.example.myapplication.network

import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun getStopsFromLine(lista: MutableList<Int>, line: Int) {
    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BusApiService::class.java)

    api.getStopsFromLine(line).enqueue(object : Callback<LineData> {
        override fun onResponse(call: Call<LineData>, response: Response<LineData>) {
            if (response.isSuccessful) {
                val data: LineData? = response.body()
                data?.let {
                    val ids: HashMap<String, Int> = it.id
                    val stopIds: HashMap<String, Int> = it.stopId
                    val stopSeqences: HashMap<String, Int> = it.stopSequence

                    // Handle the data as needed
                    //Log.i(TAG, "onResponse: ${ids.values}, ${stopIds}, ${stopSeqences}")

                    // Clear the existing elements in the 'lista' mutable list
                    lista.clear()

                    // Add the new values to the 'lista' mutable list
                    lista.addAll(ids.values)
                }
            }
        }

        override fun onFailure(call: Call<LineData>, t: Throwable) {
            //Log.i(TAG, "onFailure: ${t.message}")
        }
    })
}



fun getStopData(textView: TextView, busstop: Int){
    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BusApiService::class.java)

    api.getStop(busstop).enqueue(object: Callback<StopData> {
        override fun onResponse(call: Call<StopData>, response: Response<StopData>) {
            if(response.isSuccessful){
                val data: StopData? = response.body()
                data?.let {
                    val routeIds: HashMap<String, Int> = it.routeId
                    val headsigns: HashMap<String, String> = it.headsign
                    val theoreticalTimes: HashMap<String, String> = it.theoreticalTime

                    // Handle the data as needed
                    //Log.i(TAG, "onResponse: ${routeIds.values}, ${headsigns}, ${theoreticalTimes}")
                    textView.text=routeIds.values.toString()
                }
            }
        }

        override fun onFailure(call: Call<StopData>, t: Throwable) {
            //Log.i(TAG, "onFailure: ${t.message}")
        }

    })
}

fun getLineDataFromStop(textView: TextView, busstop: Int, line: Int){
    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BusApiService::class.java)

    api.getLineFromStop(busstop, line).enqueue(object: Callback<StopData> {
        override fun onResponse(
            call: Call<StopData>,
            response: Response<StopData>
        ) {
            if(response.isSuccessful){
                val data: StopData? = response.body()

                data?.let {
                    val routeIds: HashMap<String, Int> = it.routeId
                    val headsigns: HashMap<String, String> = it.headsign
                    val theoreticalTimes: HashMap<String, String> = it.theoreticalTime

                    // Handle the data as needed
                    //Log.i(TAG, "onResponse: ${routeIds.values}, ${headsigns}, ${theoreticalTimes}")
                    textView.text=routeIds.values.toString()
                }
            }
        }

        override fun onFailure(call: Call<StopData>, t: Throwable) {
            //Log.i(TAG, "onFailure: ${t.message}")
        }

    })
}