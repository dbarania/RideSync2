package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class gridAdapter(private val context:Context, private val routeList: List<String>, private val action:(String)->Unit):ArrayAdapter<String>(context,0,routeList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listitemView = convertView
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false)
        }

        val button = listitemView!!.findViewById<Button>(R.id.button)
        button.text = routeList[position]
        button.setOnClickListener{action(button.text as String)}
        return listitemView
    }
}