package com.example.smartflowtechassessment.ui
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.smartflowtechassessment.viewmodel.MakeUpProductsViewModel
import com.example.smartflowtechassessment.R
import com.example.smartflowtechassessment.adapter.ColorItemAdapter
import com.example.smartflowtechassessment.adapter.TagItemAdapter
import com.example.smartflowtechassessment.databinding.FragmentMakeupItemDetailsBinding
import com.example.smartflowtechassessment.model.ProductColor
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MakeupItemDetailsFragment : Fragment(R.layout.fragment_makeup_item_details) {
    private lateinit var binding: FragmentMakeupItemDetailsBinding
    private val makeUpProductsViewModel: MakeUpProductsViewModel by activityViewModels()
    private var completeProductDescription = ""

    private lateinit var colorsRecyclerView: RecyclerView
    private lateinit var tagsRecyclerView: RecyclerView
    private var productColorList = ArrayList<ProductColor>()
    private var tagsList = ArrayList<String>()
    private lateinit var colorAdapter: ColorItemAdapter
    private lateinit var tagItemAdapter: TagItemAdapter




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMakeupItemDetailsBinding.bind(view)
        colorsRecyclerView = binding.productColorsRv
        tagsRecyclerView = binding.tagsRvTv

        colorsRecyclerView.setHasFixedSize(true)
        tagsRecyclerView.setHasFixedSize(true)

        colorsRecyclerView.layoutManager = GridLayoutManager(context, 4)
        tagsRecyclerView.layoutManager = GridLayoutManager(context, 6)

        colorAdapter = ColorItemAdapter(productColorList,requireContext())
        tagItemAdapter = TagItemAdapter(tagsList,requireContext())

        colorsRecyclerView.adapter = colorAdapter
        tagsRecyclerView.adapter = tagItemAdapter

        // Remove the maxLines limit and update the text to show the complete description
        binding.seeMoreTv.setOnClickListener {
            binding.productDescriptionTv.maxLines = Integer.MAX_VALUE
            binding.productDescriptionTv.ellipsize = null
            binding.productDescriptionTv.text = completeProductDescription
            binding.seeMoreTv.visibility = View.GONE
        }


        selectedMakeupProductObserver()

    }


    @SuppressLint("NotifyDataSetChanged")
    private fun selectedMakeupProductObserver() {
        makeUpProductsViewModel.makeupProductItem.observe(viewLifecycleOwner) {
            binding.productDescriptionTv.text = it.description
            binding.productNameTv.text = it.name.trim()
            completeProductDescription = it.description
            binding.productPriceTv.text = "${it.price_sign+it.price+" "+it.currency}"

            productColorList = it.product_colors!!
            colorAdapter.addColors(productColorList)
            colorAdapter.notifyDataSetChanged()

            tagsList = it.tag_list!!
            tagItemAdapter.addTags(tagsList)
            tagItemAdapter.notifyDataSetChanged()


            Glide.with(requireActivity())
                .load("https:${it.api_featured_image}")
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.productImageIv)
            }
        }
    }




