package com.example.smartflowtechassessment.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartflowtechassessment.utils.ApiCallNetworkResource
import com.example.smartflowtechassessment.viewmodel.MakeUpProductsViewModel
import com.example.smartflowtechassessment.R
import com.example.smartflowtechassessment.adapter.BrandsAdapter
import com.example.smartflowtechassessment.databinding.FragmentMakeUpProductsBinding
import com.example.smartflowtechassessment.model.MakeUpProductsItem
import com.example.smartflowtechassessment.model.MakeupBrand
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MakeUpProductsFragment : Fragment(R.layout.fragment_make_up_products) {
    private lateinit var binding: FragmentMakeUpProductsBinding
    private val makeUpProductsViewModel: MakeUpProductsViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private var brandItemList = ArrayList<MakeupBrand>()
    private lateinit var brandsAdapter: BrandsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMakeUpProductsBinding.bind(view)

        recyclerView = binding.makeupListRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        brandsAdapter = BrandsAdapter(brandItemList, requireContext())
        recyclerView.adapter = brandsAdapter

        getMakeUpProductsResponseObserver()
        makeUpProductsViewModel.getMakeUpProducts()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getMakeUpProductsResponseObserver() {
        makeUpProductsViewModel.makeUpProductList.observe(viewLifecycleOwner) {
            when (it) {
                is ApiCallNetworkResource.Success -> {
                    brandItemList = it.data
                    brandsAdapter.addBrands(brandItemList)
                    brandsAdapter.notifyDataSetChanged()
//
                }


                is ApiCallNetworkResource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
//                    binding.viewCover.visibility = View.GONE
//                    binding.registerProgressBar.visibility = View.GONE
                }

                is ApiCallNetworkResource.Loading -> {
                    Toast.makeText(requireContext(), "Fetching List Brands ", Toast.LENGTH_LONG).show()
//                    binding.viewCover.visibility = View.VISIBLE
//                    binding.registerProgressBar.visibility = View.VISIBLE
                }
            }
        }
    }
}



