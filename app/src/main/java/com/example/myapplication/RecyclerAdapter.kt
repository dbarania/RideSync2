package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.contracts.Returns

class recyclerAdapter(private  val itemList:List<String>):RecyclerView.Adapter<recyclerAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): recyclerAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: recyclerAdapter.ViewHolder, position: Int) {
        val item = itemList[position]
        holder.textView.text = item
        holder.itemView.setOnClickListener{
            val context = holder.itemView.context
            val intent = Intent(context,final_results_c::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.text)}
}