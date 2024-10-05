package com.alviano.cuan.beta.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alviano.cuan.beta.data.Product
import com.alviano.cuan.beta.databinding.ViewholderProductBinding

class InsertProductAdapter(private val dataList: List<Product>) :
    RecyclerView.Adapter<InsertProductAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ViewholderProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val context = parent.context
        val binding = ViewholderProductBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = dataList[position]
        holder.binding.imageTxt.setImageResource(product.image)
        holder.binding.titleTxt.text = product.name
        holder.binding.priceTxt.text = "Rp${product.price.toString()}"
    }
}