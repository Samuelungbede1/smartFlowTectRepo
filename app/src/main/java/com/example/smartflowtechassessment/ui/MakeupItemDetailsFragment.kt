package com.example.smartflowtechassessment.ui
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.smartflowtechassessment.viewmodel.MakeUpProductsViewModel
import com.example.smartflowtechassessment.R
import com.example.smartflowtechassessment.adapter.BrandsAdapter
import com.example.smartflowtechassessment.adapter.ColorItemAdapter
import com.example.smartflowtechassessment.databinding.FragmentMakeupItemDetailsBinding
import com.example.smartflowtechassessment.model.MakeupBrand
import com.example.smartflowtechassessment.model.ProductColor
import com.example.smartflowtechassessment.utils.ApiCallNetworkResource
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MakeupItemDetailsFragment : Fragment(R.layout.fragment_makeup_item_details) {
    private lateinit var binding: FragmentMakeupItemDetailsBinding
    private val makeUpProductsViewModel: MakeUpProductsViewModel by activityViewModels()
    private var completeProductDescription = "";

    private lateinit var recyclerView: RecyclerView
    private var productColorList = ArrayList<ProductColor>()
    private lateinit var colorAdapter: ColorItemAdapter
//    private lateinit var recyclerView: RecyclerView
//    private var brandItemList = ArrayList<MakeupBrand>()
//    private lateinit var brandsAdapter: BrandsAdapter





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMakeupItemDetailsBinding.bind(view)
        recyclerView = binding.productColorsRv
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(context, 4)
        colorAdapter = ColorItemAdapter(productColorList,requireContext())
        recyclerView.adapter = colorAdapter
//
//        makeUpProductsViewModel.getMakeUpProducts()
        selectedMakeupProductObserver()

        // Remove the maxLines limit and update the text to show the complete description
        binding.seeMoreTv.setOnClickListener {
            binding.productDescriptionTv.maxLines = Integer.MAX_VALUE
            binding.productDescriptionTv.ellipsize = null
            binding.productDescriptionTv.text = completeProductDescription
        }

    }


    @SuppressLint("NotifyDataSetChanged")
    private fun selectedMakeupProductObserver() {
        makeUpProductsViewModel.makeupProductItem.observe(viewLifecycleOwner) {
            binding.productDescriptionTv.text = it.description
            binding.productNameTv.text = it.name
            completeProductDescription = it.description
            productColorList = it.product_colors!!
            colorAdapter.addColors(productColorList)
            colorAdapter.notifyDataSetChanged()

            Glide.with(requireActivity())
                .load("https:${it.api_featured_image}")
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.productImageIv)
            }
        }
    }




