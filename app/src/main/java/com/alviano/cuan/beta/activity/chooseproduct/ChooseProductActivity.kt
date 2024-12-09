package com.alviano.cuan.beta.activity.chooseproduct

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.alviano.cuan.beta.adapter.InsertProductAdapter
import com.alviano.cuan.beta.databinding.ActivityChooseProductBinding
import com.alviano.cuan.beta.model.ProductModel
import com.alviano.cuan.beta.viewmodel.ProductViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ChooseProductActivity : BottomSheetDialogFragment() {

    private lateinit var binding: ActivityChooseProductBinding
    private lateinit var mProductViewModel: ProductViewModel
    private val selectedProducts = mutableListOf<ProductModel>()
    private lateinit var adapter: InsertProductAdapter

    var onProductsSelected: ((List<ProductModel>) -> Unit)? = null

    companion object {
        const val SELECTED_PRODUCTS_KEY = "selected_products"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_bottom_sheet_transac, container, false)
        binding = ActivityChooseProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupProductList()
        setupDoneButton()
    }

    private fun setupProductList() {
        adapter = InsertProductAdapter { product, isSelected ->
            if (isSelected) {
                val existingProduct = selectedProducts.find { it.productId == product.productId }
                if (existingProduct == null) {
                    selectedProducts.add(product)
                } else {
                    existingProduct.quantity = product.quantity
                }
            } else {
                selectedProducts.removeAll{ it.productId == product.productId }
            }

            adapter.updateProductSelection(product)

        }

        binding.allProduct.apply {
            adapter = this@ChooseProductActivity.adapter
            layoutManager = GridLayoutManager(context, 2)
        }

        mProductViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        mProductViewModel.readAllData.observe(viewLifecycleOwner) { products ->
            adapter.setData(products)
        }

    }

    private fun setupDoneButton() {
        binding.doneButton.setOnClickListener {
            val filteredProducts = selectedProducts.filter { it.quantity > 0 }

            // kirim data
            val resultIntent = Intent().apply {
                putParcelableArrayListExtra(SELECTED_PRODUCTS_KEY, ArrayList(filteredProducts))
            }

            // trigger callback
            onProductsSelected?.invoke(filteredProducts)



//            selectedProducts.clear()
//
//             set result untuk parent activity
            parentFragment?.let {
                it.requireActivity().setResult(Activity.RESULT_OK, resultIntent)
            } ?: run {
                activity?.setResult(Activity.RESULT_OK, resultIntent)
            }
//
            dismiss()
//
//            view?.post {
//                dialog?.dismiss()
//                dialog?.cancel()
//            }

        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
    }

    // unused
    private fun showProductList() {
        val adapter = InsertProductAdapter { product, isSelected ->
            if (isSelected) selectedProducts.add(product) else selectedProducts.remove(product)
        }

        val recyclerView = binding.allProduct
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        mProductViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        mProductViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })
    }

}