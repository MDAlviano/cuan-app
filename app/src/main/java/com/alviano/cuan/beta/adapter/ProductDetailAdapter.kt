package com.alviano.cuan.beta.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alviano.cuan.beta.databinding.ViewholderDetailTransaksiBinding
import com.alviano.cuan.beta.databinding.ViewholderProductBinding
import com.alviano.cuan.beta.databinding.ViewholderTransacmasukTransacpageBinding
import com.alviano.cuan.beta.model.ProductModel
import com.alviano.cuan.beta.utils.formatAsCurrency

class ProductDetailAdapter(private val products: List<ProductModel>) :
    RecyclerView.Adapter<ProductDetailAdapter.ProductDetailViewHolder>() {

    class ProductDetailViewHolder(val binding: ViewholderDetailTransaksiBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDetailViewHolder {
        val binding = ViewholderDetailTransaksiBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductDetailViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductDetailViewHolder, position: Int) {
        val product = products[position]

        holder.binding.apply {
            namaProduk.text = product.name
            val kuantitas = product.quantity
            val harga = product.sellPrice * kuantitas
            hargaProduk.text = formatAsCurrency(harga)
            kuantitasProduk.text = kuantitas.toString()
        }
    }

}