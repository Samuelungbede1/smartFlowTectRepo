package com.example.smartflowtechassessment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartflowtechassessment.R
import com.example.smartflowtechassessment.model.MakeupBrand
import com.example.smartflowtechassessment.utils.OnProductItemClickListener

class BrandsAdapter(private val makeUpBrandList: ArrayList<MakeupBrand>, private val context: Context,
                    private val listener: OnProductItemClickListener
) :
    RecyclerView.Adapter<BrandsAdapter.ParentViewHolder>() {

    inner class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTv: TextView = itemView.findViewById(R.id.brandNameTv)
        val productTypeRecyclerView: RecyclerView = itemView.findViewById(R.id.brandRecyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.brand_item, parent, false)
        return ParentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        val brandItem = makeUpBrandList[position]
        holder.titleTv.text = brandItem.brand
        holder.productTypeRecyclerView.setHasFixedSize(true)
        holder.productTypeRecyclerView.layoutManager = LinearLayoutManager(context)
        val makeupProductTypeAdapter = MakeupProductTypeAdapter(brandItem.productTypes,context,listener)
        holder.productTypeRecyclerView.adapter = makeupProductTypeAdapter
    }

    override fun getItemCount(): Int {
        return makeUpBrandList.size
    }

    fun addBrands(listOfBrands : ArrayList<MakeupBrand>){
        makeUpBrandList.clear()
        makeUpBrandList.addAll(listOfBrands)
        notifyDataSetChanged()
    }
}