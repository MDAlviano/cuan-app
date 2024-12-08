package com.alviano.cuan.beta.activity

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.data.TransactionType
import com.alviano.cuan.beta.databinding.ActivityAddPengeluaranBinding
import com.alviano.cuan.beta.model.TransactionModel
import com.alviano.cuan.beta.viewmodel.TransactionViewModel

class AddPengeluaranActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddPengeluaranBinding
    private lateinit var myViewModel: TransactionViewModel
    private lateinit var totalPengeluaranInput: EditText
    private lateinit var keteranganPengeluaranInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddPengeluaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myViewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)

        totalPengeluaranInput = findViewById(R.id.nominalPengeluaran)
        keteranganPengeluaranInput = findViewById(R.id.keteranganPengeluaran)

        binding.saveTransacPengeluaran.setOnClickListener{
            addDataToDatabase()
        }
    }

    fun addDataToDatabase() {
        val totalPengeluaran = totalPengeluaranInput.text.toString()
        val tipeTransaksi = TransactionType.KELUAR
        val keteranganPengeluaran = keteranganPengeluaranInput.text.toString()
        val tanggal = System.currentTimeMillis()

        if (totalPengeluaran.isNotBlank()){
            val transactionModel = TransactionModel(0, totalPengeluaran.toInt(), tipeTransaksi, keteranganPengeluaran, tanggal, null)

            myViewModel.addTransaction(transactionModel)
            Toast.makeText(this, "Transaksi berhasil ditambahkan.", Toast.LENGTH_SHORT).show()
            // Finish activity
            finish()
        } else {
            Toast.makeText(this, "Harap isi kolom total atau pilih produk.", Toast.LENGTH_SHORT).show()
        }
    }
}