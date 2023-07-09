package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ResultAdapter(private val itemList: List<result>) :
    RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {

    inner class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lineNumberTextView: TextView = itemView.findViewById(R.id.lineNumber)
        val departureTimeTextView: TextView = itemView.findViewById(R.id.departureTime)
        val destinationTextView: TextView = itemView.findViewById(R.id.destination)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.result_item, parent, false)
        return ResultViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.lineNumberTextView.text = currentItem.line.toString()
        holder.departureTimeTextView.text = currentItem.arrival
        holder.destinationTextView.text = currentItem.dest
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}