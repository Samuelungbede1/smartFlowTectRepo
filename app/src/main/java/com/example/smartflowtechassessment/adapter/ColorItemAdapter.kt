package com.example.smartflowtechassessment.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartflowtechassessment.R
import com.example.smartflowtechassessment.model.ProductColor


class ColorItemAdapter(private val colorList: ArrayList<ProductColor>, private val context: Context) :
    RecyclerView.Adapter<ColorItemAdapter.ViewHolder>(){


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val colorName: TextView = itemView.findViewById(R.id.colorNameTv)
        var colorCardView: CardView = itemView.findViewById(R.id.colorCardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.color_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val colors = colorList[position]
        holder.colorName.text = colors.colour_name
        val hexColor = colors.hex_value
        val color: Int? = Color.parseColor(hexColor)
        if (color != null) {
            holder.colorCardView.setCardBackgroundColor(color)
        }

    }

    override fun getItemCount(): Int = colorList.size



    fun addColors(colors : ArrayList<ProductColor>){
        colorList.clear()
        colorList.addAll(colors)
        notifyDataSetChanged()
    }
}




