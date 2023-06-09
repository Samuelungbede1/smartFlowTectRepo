package com.example.smartflowtechassessment.ui
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartflowtechassessment.utils.ApiCallNetworkResource
import com.example.smartflowtechassessment.viewmodel.MakeUpProductsViewModel
import com.example.smartflowtechassessment.R
import com.example.smartflowtechassessment.adapter.BrandsAdapter
import com.example.smartflowtechassessment.databinding.FragmentMakeUpProductsBinding
import com.example.smartflowtechassessment.model.MakeUpProductsItem
import com.example.smartflowtechassessment.model.MakeupBrand
import com.example.smartflowtechassessment.utils.OnProductItemClickListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MakeUpProductsFragment : Fragment(R.layout.fragment_make_up_products), OnProductItemClickListener {
    private lateinit var binding: FragmentMakeUpProductsBinding
    private val makeUpProductsViewModel: MakeUpProductsViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private var brandItemList = ArrayList<MakeupBrand>()
    private lateinit var brandsAdapter: BrandsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeUpProductsViewModel.getMakeUpProducts()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMakeUpProductsBinding.bind(view)
        val listener = this

        recyclerView = binding.makeupListRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        brandsAdapter = BrandsAdapter(brandItemList,requireContext(),listener)
        recyclerView.adapter = brandsAdapter

        getMakeUpProductsResponseObserver()

    }



    @SuppressLint("NotifyDataSetChanged")
    private fun getMakeUpProductsResponseObserver() {
        makeUpProductsViewModel.makeUpProductList.observe(viewLifecycleOwner) {
            when (it) {
                is ApiCallNetworkResource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    brandItemList = it.data!!
                    brandsAdapter.addBrands(brandItemList)
                    brandsAdapter.notifyDataSetChanged()
                        Toast.makeText(requireContext(), brandItemList[0].productTypes[0].products.size , Toast.LENGTH_LONG).show()
                }

                is ApiCallNetworkResource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is ApiCallNetworkResource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), "Loading, Please wait", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    override fun onProductItemClick(makeupProduct: MakeUpProductsItem) {
        makeUpProductsViewModel.setSelectedMakeupProduct(makeupProduct)
    }


}



