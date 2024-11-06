package com.alviano.cuan.beta.activity.chooseproduct

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.databinding.ViewholderProductBinding
import com.alviano.cuan.beta.model.ProductModel

class ChooseProductAdapter(private val context: Context) : BaseAdapter() {

    private var productList = emptyList<ProductModel>()

    override fun getCount(): Int {
        return productList.size
    }

    override fun getItem(position: Int): ProductModel {
        return productList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: ViewholderProductBinding
        val view: View

        if (convertView == null) {
            binding = ViewholderProductBinding.inflate(LayoutInflater.from(context), parent, false)
            view = binding.root
            view.tag = binding
        } else {
            binding = convertView.tag as ViewholderProductBinding
            view = convertView
        }

        // Bind data to views
        val product = getItem(position)
        binding.titleTxt.text = product.name
        binding.priceTxt.text = "Rp${product.sellPrice}"
        // Uncomment and load the image if available
         binding.imageTxt.setImageResource(R.drawable.buku_tulis)

        return view
    }

    fun setData(product: List<ProductModel>) {
        this.productList = product
        notifyDataSetChanged()
    }
}

