package com.example.smartflowtechassessment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.smartflowtechassessment.R
import com.example.smartflowtechassessment.model.MakeUpProductsItem


class MakeupItemAdapter(private val makeupItems: List<MakeUpProductsItem>, private val context: Context) :
    RecyclerView.Adapter<MakeupItemAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.productNameTv)
        val image: ImageView = itemView.findViewById(R.id.productLogoIv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val makeupProduct = makeupItems[position]
        holder.productName.text = makeupProduct.name
        val imageUrl = makeupProduct.api_featured_image
        Glide.with(context)
            .load("https:$imageUrl")
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.image)
    }

    override fun getItemCount(): Int = makeupItems.size


}




