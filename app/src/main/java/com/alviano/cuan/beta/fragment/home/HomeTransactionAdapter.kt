package com.alviano.cuan.beta.fragment.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.data.TransactionType
import com.alviano.cuan.beta.databinding.VieholderTransactionHomepageBinding
import com.alviano.cuan.beta.databinding.ViewholderTransacmasukTransacpageBinding
import com.alviano.cuan.beta.model.TransactionModel
import com.alviano.cuan.beta.utils.formatAsCurrency
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeTransactionAdapter:RecyclerView.Adapter<HomeTransactionAdapter.HomeTransactionViewHolder>() {

    private var transactionModelList = mutableListOf<TransactionModel>()
    private lateinit var context: Context

    class HomeTransactionViewHolder(val binding: VieholderTransactionHomepageBinding) :
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTransactionViewHolder {
        context = parent.context
        val binding = VieholderTransactionHomepageBinding.inflate(LayoutInflater.from(context), parent, false)
        return HomeTransactionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return transactionModelList.size
    }

    override fun onBindViewHolder(holder: HomeTransactionViewHolder, position: Int) {
        val transaction = transactionModelList[position]
        val totalAmount = transaction.totalAmount
        val formattedTotalAmount = formatAsCurrency(totalAmount)
        val date = formatDate(transaction.timestamp)
        val time = formatTime(transaction.timestamp)

        holder.binding.totalTransaksi.text = formattedTotalAmount
        holder.binding.waktuHome.text = time

        if (transaction.transactionType == TransactionType.MASUK){
            holder.binding.jenisTransaksi.text = "Pemasukan"
            holder.binding.totalTransaksi.setTextColor(ContextCompat.getColor(context, R.color.color8))
            holder.binding.imageViewHome.setImageResource(R.drawable.pemasukan)
        } else {
            holder.binding.jenisTransaksi.text = "Pengeluaran"
            holder.binding.totalTransaksi.setTextColor(ContextCompat.getColor(context, R.color.color4))
            holder.binding.imageViewHome.setImageResource(R.drawable.pengeluaran)
        }
    }

    fun setData(transactionModel: List<TransactionModel>) {
        transactionModelList.clear() // Clear existing data
        transactionModelList.addAll(transactionModel) // Add new data
        notifyDataSetChanged() // Notify about inserted items
    }

    fun formatDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
        return sdf.format(Date(timestamp))
    }

    fun formatTime(timestamp: Long): String {
        val sdf = SimpleDateFormat("HH:mm", Locale("id", "ID"))
        return sdf.format(Date(timestamp))
    }
}