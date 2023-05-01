package com.example.smartflowtechassessment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartflowtechassessment.R


class TagItemAdapter(private val tagList: ArrayList<String>, private val context: Context) :
    RecyclerView.Adapter<TagItemAdapter.ViewHolder>(){


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tagName: TextView = itemView.findViewById(R.id.tagNameTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.tag_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tag = tagList[position]
        holder.tagName.text = tag

    }

    override fun getItemCount(): Int = tagList.size



    fun addTags(colors : ArrayList<String>){
        tagList.clear()
        tagList.addAll(colors)
        notifyDataSetChanged()
    }
}




