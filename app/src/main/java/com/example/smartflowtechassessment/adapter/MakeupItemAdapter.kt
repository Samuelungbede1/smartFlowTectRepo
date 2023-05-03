package com.example.smartflowtechassessment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.smartflowtechassessment.R
import com.example.smartflowtechassessment.model.MakeUpProductsItem
import com.example.smartflowtechassessment.ui.MakeUpProductsFragmentDirections
import com.example.smartflowtechassessment.utils.OnProductItemClickListener


class MakeupItemAdapter(private val makeupItems: List<MakeUpProductsItem>, private val context: Context, val listener: OnProductItemClickListener) :
    RecyclerView.Adapter<MakeupItemAdapter.ViewHolder>(){


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
        holder.productName.text = makeupProduct.name?.trim()
        val imageUrl = makeupProduct.api_featured_image
        Glide.with(context)
            .load("https:$imageUrl")
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.image)


        /**Setting click listener to the item in the recyclerview*/
        holder.itemView.setOnClickListener {
            listener.onProductItemClick(makeupProduct)
            val action = MakeUpProductsFragmentDirections.actionMakeUpProductsFragmentToMakeupItemDetailsFragment2()
            it.findNavController().navigate(action)

        }
    }

    override fun getItemCount(): Int = makeupItems.size
}




