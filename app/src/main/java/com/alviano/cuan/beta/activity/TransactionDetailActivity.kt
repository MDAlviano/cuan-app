package com.alviano.cuan.beta.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.adapter.ProductDetailAdapter
import com.alviano.cuan.beta.data.TransactionType
import com.alviano.cuan.beta.databinding.ActivityTransactionDetailBinding
import com.alviano.cuan.beta.model.ProductModel
import com.alviano.cuan.beta.model.TransactionModel
import com.alviano.cuan.beta.utils.formatAsCurrency
import com.alviano.cuan.beta.viewmodel.TransactionViewModel
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TransactionDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionDetailBinding
    private lateinit var transactionViewModel: TransactionViewModel
    private lateinit var selectedTransaction: TransactionModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTransactionDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        transactionViewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)

        selectedTransaction = intent.getParcelableExtra<TransactionModel>("selected_transaction")!!
        displayTransactionDetails(selectedTransaction)

        if (selectedTransaction.transactionType == TransactionType.MASUK) {
            binding.jenisTransaksi.text = "Pemasukan"
        } else {
            binding.jenisTransaksi.text = "Pengeluaran"
            binding.jenisTransaksi.setTextColor(ContextCompat.getColor(this, R.color.color4))
            binding.keterangan.setTextColor(ContextCompat.getColor(this, R.color.color4))
            binding.totalTransaksi.setTextColor(ContextCompat.getColor(this, R.color.color4))
            binding.jumlahProduk.setTextColor(ContextCompat.getColor(this, R.color.color4))
            binding.waktu.setTextColor(ContextCompat.getColor(this, R.color.color4))
        }
        if (selectedTransaction.description !== null) {
            binding.keterangan.text = selectedTransaction.description
        } else {
            binding.keterangan.text = "-"
        }
        binding.waktu.text = formatTimestamp(selectedTransaction.timestamp)

        binding.deleteBtn.setOnClickListener{
            finish()
        }

        binding.backBtn.setOnClickListener {
            finish()
        }

    }

    private fun displayTransactionDetails(transaction: TransactionModel) {
        binding.totalTransaksi.text = formatAsCurrency(transaction.totalAmount)
        val productList = transaction.getParsedProductDetails()

        binding.jumlahProduk.text = transaction.productDetails?.length.toString()

        val productAdapter = ProductDetailAdapter(productList)
        binding.dataProdukRecyclerView.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(this@TransactionDetailActivity)
        }
    }

    private fun formatTimestamp(timestamp: Long): String {
        val sdf = SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm", Locale("id", "ID"))
        return sdf.format(Date(timestamp))
    }
}