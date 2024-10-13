package com.alviano.cuan.beta.fragment.products

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.databinding.ProductCardBinding
import com.alviano.cuan.beta.model.ProductModel

class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var productModelList = emptyList<ProductModel>()
    private lateinit var context: Context

    class ProductViewHolder(val binding: ProductCardBinding) :
        RecyclerView.ViewHolder(binding.root){}

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productModelList[position]

        val imageByteArray = product.image
        // Checks if there's no image in DB
        if (imageByteArray != null) {
            // Converts ByteArray to Bitmap
            val bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)
            holder.binding.productImage.setImageBitmap(bitmap)
        } else {
            // Handles if there's no image data
            holder.binding.productImage.setImageResource(R.drawable.image_placeholder)
        }

        holder.binding.productName.text = product.name
        holder.binding.productPrice.text = "Rp${product.sellPrice}"

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        context = parent.context
        val binding = ProductCardBinding.inflate(LayoutInflater.from(context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productModelList.size
    }

    fun setData(productModel: List<ProductModel>){
        this.productModelList = productModel
        notifyDataSetChanged()
    }

}