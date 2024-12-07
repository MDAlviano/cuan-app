package com.alviano.cuan.beta.fragment.transaction

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.data.TransactionType
import com.alviano.cuan.beta.databinding.ViewholderTransacmasukTransacpageBinding
import com.alviano.cuan.beta.model.TransactionModel
import com.alviano.cuan.beta.utils.formatAsCurrency
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
        val totalAmount = transaction.totalAmount
        val formattedTotalAmount = formatAsCurrency(totalAmount)
        val date = formatDate(transaction.timestamp)
        val time = formatTime(transaction.timestamp)

        holder.binding.totalPemasukan.text = formattedTotalAmount
        if (transaction.transactionType == TransactionType.MASUK){
            holder.binding.jenisTransaksi.text = "Pemasukan"
//            holder.binding.jenisTransaksi.setTextColor(ContextCompat.getColor(context, R.color.color8))
            holder.binding.totalPemasukan.setTextColor(ContextCompat.getColor(context, R.color.color8))
            holder.binding.transacTypeImage.setImageResource(R.drawable.pemasukan)
            holder.binding.background.setBackgroundResource(R.drawable.sprapatc8_bg)
            holder.binding.date.text = date
            holder.binding.time.text = time
        } else {
            holder.binding.jenisTransaksi.text = "Pengeluaran"
//            holder.binding.jenisTransaksi.setTextColor(ContextCompat.getColor(context, R.color.color4))
            holder.binding.totalPemasukan.setTextColor(ContextCompat.getColor(context, R.color.color4))
            holder.binding.transacTypeImage.setImageResource(R.drawable.pengeluaran)
            holder.binding.background.setBackgroundResource(R.drawable.sprapatc4_bg)
            holder.binding.date.text = date
            holder.binding.time.text = time
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