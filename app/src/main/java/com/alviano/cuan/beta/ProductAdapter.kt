package com.alviano.cuan.beta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        val productImageView: ImageView = itemView.findViewById(R.id.productImage)
        val productNameView: TextView = itemView.findViewById(R.id.productName)
        val productPriceView: TextView = itemView.findViewById(R.id.productPrice)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.productImageView.setImageResource(product.image)
        holder.productNameView.text = product.name
        holder.productPriceView.text = "Rp${product.price}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_card, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

}