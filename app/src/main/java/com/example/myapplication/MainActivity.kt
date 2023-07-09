package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridView

class MainActivity : AppCompatActivity() {
    private lateinit var lineGV:GridView
    var nie:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_page_a)
        lineGV = findViewById(R.id.lines_grid)

        val list = mutableListOf<String>("Nie", "lubic", "swiat")
        val adapter = gridAdapter(this,list){x->nie = x
        Log.d("MainActivity", "nie value: $nie")
        startNewActivity()
        }
        lineGV.adapter = adapter
    }
    private fun startNewActivity(){
        val intent = Intent(this,stops_b::class.java)
        intent.putExtra("nieValue",nie) 
        startActivity(intent)
    }
}