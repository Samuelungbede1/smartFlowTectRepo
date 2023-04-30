package com.example.smartflowtechassessment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.smartflowtechassessment.utils.ApiCallNetworkResource
import com.example.smartflowtechassessment.viewmodel.MakeUpProductsViewModel
import com.example.smartflowtechassessment.R
import com.example.smartflowtechassessment.databinding.FragmentMakeUpProductsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MakeUpProductsFragment : Fragment(R.layout.fragment_make_up_products) {
    private lateinit var binding: FragmentMakeUpProductsBinding
    private val makeUpProductsViewModel: MakeUpProductsViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMakeUpProductsBinding.bind(view)


        getMakeUpProductsResponseObserver()
        makeUpProductsViewModel.getMakeUpProducts()

    }

    private fun getMakeUpProductsResponseObserver() {
        makeUpProductsViewModel.makeUpProductList.observe(viewLifecycleOwner) {
            when (it) {
                is ApiCallNetworkResource.Success -> {
//                    it.data?.let { it1 -> Toast.makeText(requireContext(),  it.data.size.toString(), Toast.LENGTH_LONG).show() }
                    binding.tvApp.text = it.data!![100].brand
//                    Log.d(TAG, "-----------------------------------------------------")
//                    Log.d(TAG, "getMakeUpProductsResponseObserver: ${it.data.gr}")
//                    binding.viewCover.visibility = View.GONE
//                    binding.registerProgressBar.visibility = View.GONE
//                    makeList = it.data!!
//                    makeListAdapter.addMake(makeList)
//                    makeListAdapter.notifyDataSetChanged()
                }


                is ApiCallNetworkResource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
//                    binding.viewCover.visibility = View.GONE
//                    binding.registerProgressBar.visibility = View.GONE
                }

                is ApiCallNetworkResource.Loading -> {
                    Toast.makeText(requireContext(), "Fetching List of Vehicles", Toast.LENGTH_LONG).show()
//                    binding.viewCover.visibility = View.VISIBLE
//                    binding.registerProgressBar.visibility = View.VISIBLE
                }
            }
        }
    }
}



