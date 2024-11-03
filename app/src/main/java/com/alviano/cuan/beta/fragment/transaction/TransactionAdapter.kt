package com.alviano.cuan.beta.fragment.transaction

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alviano.cuan.beta.databinding.ViewholderTransacmasukTransacpageBinding
import com.alviano.cuan.beta.model.TransactionModel

class TransactionAdapter:RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    private var transactionModelList = mutableListOf<TransactionModel>()
    private lateinit var context: Context

    class TransactionViewHolder(val binding: ViewholderTransacmasukTransacpageBinding) :
            RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        context = parent.context
        val binding = ViewholderTransacmasukTransacpageBinding.inflate(LayoutInflater.from(context), parent, false)
        return TransactionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return transactionModelList.size
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactionModelList[position]

        holder.binding.totalPemasukan.text = "Rp${transaction.totalAmount}"
    }

    fun setData(transactionModel: List<TransactionModel>) {
        val oldSize = transactionModelList.size
        transactionModelList.clear() // Clear existing data
        transactionModelList.addAll(transactionModel) // Add new data
        notifyItemRangeInserted(oldSize, transactionModel.size) // Notify about inserted items
    }
}