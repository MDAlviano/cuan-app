package com.alviano.cuan.beta.fragment.transaction

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.data.TransactionType
import com.alviano.cuan.beta.databinding.ItemDateHeaderBinding
import com.alviano.cuan.beta.databinding.ViewholderTransacmasukTransacpageBinding
import com.alviano.cuan.beta.model.TransactionItemModel
import com.alviano.cuan.beta.model.TransactionModel
import com.alviano.cuan.beta.utils.formatAsCurrency
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TransactionAdapter(private val onItemClick: (TransactionModel) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var transactionModelList = mutableListOf<TransactionItemModel>()
    private lateinit var context: Context

    class DateHeaderViewHolder(private val binding: ItemDateHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(header: TransactionItemModel.DateHeader) {
            binding.dateText.text = header.date
        }
    }

    class TransactionViewHolder(private val binding: ViewholderTransacmasukTransacpageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            transaction: TransactionModel,
            context: Context,
            onItemClick: (TransactionModel) -> Unit
        ) {
            val formattedTotalAmount = formatAsCurrency(transaction.totalAmount)
            val time = formatTime(transaction.timestamp)

            binding.totalPemasukan.text = formattedTotalAmount
            binding.time.text = time

            if (transaction.transactionType == TransactionType.MASUK) {
                binding.jenisTransaksi.text = "Pemasukan"
                binding.totalPemasukan.setTextColor(ContextCompat.getColor(context, R.color.color8))
                binding.transacTypeImage.setImageResource(R.drawable.pemasukan)
                binding.background.setBackgroundResource(R.drawable.sprapatc8_bg)
            } else {
                binding.jenisTransaksi.text = "Pengeluaran"
                binding.totalPemasukan.setTextColor(ContextCompat.getColor(context, R.color.color4))
                binding.transacTypeImage.setImageResource(R.drawable.pengeluaran)
                binding.background.setBackgroundResource(R.drawable.sprapatc4_bg)
            }

            binding.root.setOnClickListener { onItemClick(transaction) }
        }

        private fun formatTime(timestamp: Long): String {
            val sdf = SimpleDateFormat("HH:mm", Locale("id", "ID"))
            return sdf.format(Date(timestamp))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return when (viewType) {
            R.layout.item_date_header -> {
                val binding = ItemDateHeaderBinding.inflate(LayoutInflater.from(context), parent, false)
                DateHeaderViewHolder(binding)
            }
            else -> {
                val binding = ViewholderTransacmasukTransacpageBinding.inflate(LayoutInflater.from(context), parent, false)
                TransactionViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (transactionModelList[position]) {
            is TransactionItemModel.DateHeader -> R.layout.item_date_header
            is TransactionItemModel.Transaction -> R.layout.viewholder_transacmasuk_transacpage
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = transactionModelList[position]) {
            is TransactionItemModel.DateHeader -> {
                (holder as DateHeaderViewHolder).bind(item)
            }
            is TransactionItemModel.Transaction -> {
                (holder as TransactionViewHolder).bind(item.transactionModel, context, onItemClick)
            }
        }
    }

    override fun getItemCount(): Int {
        return transactionModelList.size
    }

//    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
//        val transaction = transactionModelList[position]
//        val totalAmount = transaction.totalAmount
//        val formattedTotalAmount = formatAsCurrency(totalAmount)
//        val date = formatDate(transaction.timestamp)
//        val time = formatTime(transaction.timestamp)
//
//        holder.binding.totalPemasukan.text = formattedTotalAmount
//        holder.binding.date.text = date
//        holder.binding.time.text = time
//        if (transaction.transactionType == TransactionType.MASUK) {
//            holder.binding.jenisTransaksi.text = "Pemasukan"
////            holder.binding.jenisTransaksi.setTextColor(ContextCompat.getColor(context, R.color.color8))
//            holder.binding.totalPemasukan.setTextColor(
//                ContextCompat.getColor(
//                    context,
//                    R.color.color8
//                )
//            )
//            holder.binding.transacTypeImage.setImageResource(R.drawable.pemasukan)
//            holder.binding.background.setBackgroundResource(R.drawable.sprapatc8_bg)
//        } else {
//            holder.binding.jenisTransaksi.text = "Pengeluaran"
////            holder.binding.jenisTransaksi.setTextColor(ContextCompat.getColor(context, R.color.color4))
//            holder.binding.totalPemasukan.setTextColor(
//                ContextCompat.getColor(
//                    context,
//                    R.color.color4
//                )
//            )
//            holder.binding.transacTypeImage.setImageResource(R.drawable.pengeluaran)
//            holder.binding.background.setBackgroundResource(R.drawable.sprapatc4_bg)
//        }
//
//        holder.itemView.setOnClickListener {
//            onItemClick(transaction)
//        }
//    }

    fun setData(items: List<TransactionItemModel>) {
        transactionModelList.clear()
        transactionModelList.addAll(items)
        notifyDataSetChanged() // Notify about inserted items
    }

    fun groupTransactionsByDate(transactions: List<TransactionModel>): List<TransactionItemModel> {
        val groupedItems = mutableListOf<TransactionItemModel>()

        transactions.groupBy { formatDate(it.timestamp) }
            .forEach { (date, transactionList) ->
                // Tambahkan header tanggal
                groupedItems.add(TransactionItemModel.DateHeader(date))
                // Tambahkan transaksi
                groupedItems.addAll(transactionList.map { TransactionItemModel.Transaction(it) })
            }

        return groupedItems
    }

    fun formatDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
        return sdf.format(Date(timestamp))
    }

//    fun formatTime(timestamp: Long): String {
//        val sdf = SimpleDateFormat("HH:mm", Locale("id", "ID"))
//        return sdf.format(Date(timestamp))
//    }
}