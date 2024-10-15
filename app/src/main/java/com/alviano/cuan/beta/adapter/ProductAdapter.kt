package com.alviano.cuan.beta.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.databinding.ProductCardBinding
import com.alviano.cuan.beta.model.Product

class ProductAdapter(private val onItemClick: (Product) -> Unit): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var productList = emptyList<Product>()
    private lateinit var context: Context

    class ProductViewHolder(val binding: ProductCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
//        holder.productImageView.setImageResource(product.image)
        holder.binding.productName.text = product.name
        holder.binding.productPrice.text = "Rp${product.sellPrice}"

        holder.itemView.setOnClickListener {
            onItemClick(product)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        context = parent.context
        val binding = ProductCardBinding.inflate(LayoutInflater.from(context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setData(product: List<Product>){
        this.productList = product
        notifyDataSetChanged()
    }

}