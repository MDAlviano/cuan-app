package com.alviano.cuan.beta.adapter

import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.model.ProductModel
import com.alviano.cuan.beta.databinding.ViewholderProductBinding
import com.alviano.cuan.beta.utils.formatAsCurrency

class InsertProductAdapter(private val onProductSelected: (ProductModel, Boolean) -> Unit) :
    RecyclerView.Adapter<InsertProductAdapter.MyViewHolder>() {

    private var productList = mutableListOf<ProductModel>()

    class MyViewHolder(val binding: ViewholderProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val context = parent.context
        val binding = ViewholderProductBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = productList[position]
        val imagePath = product.imagePath

        // Checks if there's no image in DB
        if (imagePath != null) {
            // Converts path to Bitmap
            val bitmap = BitmapFactory.decodeFile(imagePath)
            holder.binding.imageTxt.setImageBitmap(bitmap)
            holder.binding.imageTxt.scaleType = ImageView.ScaleType.CENTER_CROP
        } else {
            // Handles if there's no image data
            holder.binding.imageTxt.setImageResource(R.drawable.image_placeholder)
        }

        holder.binding.titleTxt.text = product.name

        val formattedDataAmount = product.sellPrice
        holder.binding.priceTxt.text = formatAsCurrency(formattedDataAmount)

        // atur warna background berdasarkan status seleksi
        holder.itemView.setBackgroundResource(
            if (product.isSelected) R.drawable.selected_product_bg
            else R.drawable.display_bg
        )

        // atur aksi add dan remove
        holder.binding.add.setOnClickListener {
            product.quantity++
            product.isSelected = true
            onProductSelected(product, true)
        }

        holder.binding.remove.setOnClickListener {
            if (product.quantity > 0) {
                product.quantity--
                if (product.quantity == 0) {
                    product.isSelected = false
                }
            }
            onProductSelected(product, product.quantity > 0)
        }

//        holder.itemView.setOnClickListener {
//            val isSelected = !product.isSelected
//            product.isSelected = isSelected
//            onProductSelected(product, isSelected)
//            notifyItemChanged(position)
//        }

    }

    fun updateProductSelection(product: ProductModel) {
        val index = productList.indexOfFirst { it.productId == product.productId }
        if (index != -1) {
            notifyItemChanged(index)
        }
    }

    fun setData(productModel: List<ProductModel>) {
        productList.clear() // Clear existing data
        productList.addAll(productModel)
        notifyDataSetChanged()
    }

}