package com.example.smartflowtechassessment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartflowtechassessment.R
import com.example.smartflowtechassessment.model.MakeUpProductType


class MakeupProductTypeAdapter(private val makeupProductTypes: ArrayList<MakeUpProductType>, private val context: Context) :
    RecyclerView.Adapter<MakeupProductTypeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTv: TextView = itemView.findViewById(R.id.productTypeTv)
        val productTypeRecyclerView: RecyclerView = itemView.findViewById(R.id.productTypeRecyclerView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_type_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productType = makeupProductTypes[position]
        holder.titleTv.text = productType.productType
        holder.productTypeRecyclerView.setHasFixedSize(true)
        holder.productTypeRecyclerView.layoutManager = LinearLayoutManager(context)
        val makeupProductItemAdapter = MakeupItemAdapter(productType.products)
        holder.productTypeRecyclerView.adapter = makeupProductItemAdapter
    }


    override fun getItemCount(): Int = makeupProductTypes.size
}
