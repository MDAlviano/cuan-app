package com.alviano.cuan.beta.activity

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.GridView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.activity.chooseproduct.ChooseProductActivity
import com.alviano.cuan.beta.activity.chooseproduct.ChooseProductAdapter
import com.alviano.cuan.beta.data.TransactionType
import com.alviano.cuan.beta.databinding.ActivityAddPemasukanBinding
import com.alviano.cuan.beta.model.TransactionModel
import com.alviano.cuan.beta.viewmodel.ProductViewModel
import com.alviano.cuan.beta.viewmodel.TransactionViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddPemasukanActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddPemasukanBinding
    private lateinit var mProductViewModel: ProductViewModel
    private lateinit var myViewModel: TransactionViewModel
    private lateinit var totalAmount: EditText
    private lateinit var description: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddPemasukanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.insertProduct.setOnClickListener {
            showAddProductSheet()
        }

        mProductViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        myViewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)

        totalAmount = findViewById(R.id.totalPemasukan)
        description = findViewById(R.id.keteranganPemasukan)

        binding.saveTransacPemasukan.setOnClickListener {
            addDataToDatabase()
        }

        binding.insertProduct.setOnClickListener{
            ChooseProductActivity().show(supportFragmentManager, "ChooseProductTag")
        }
    }

    private fun showAddProductSheet() {
        val dialogView = layoutInflater.inflate(R.layout.activity_choose_product, null)
        val bottomSheetDialog = BottomSheetDialog(this, R.style.DialogAnimation)
        bottomSheetDialog.setContentView(dialogView)

        // Mengatur GridView di dalam BottomSheetDialog
        val gridView = dialogView.findViewById<GridView>(R.id.allProduct)
        val adapter = ChooseProductAdapter(this)
        gridView.adapter = adapter

        // Mengambil data dari ViewModel dan menghubungkan ke adapter
        mProductViewModel.readAllData.observe(this, Observer { products ->
            adapter.setData(products)
        })

        // Mengatur item click listener untuk GridView
        gridView.setOnItemClickListener { _, _, position, _ ->
            val product = adapter.getItem(position)
            Toast.makeText(this, "You clicked on ${product.name}", Toast.LENGTH_SHORT).show()
        }

        // Menampilkan BottomSheetDialog
        bottomSheetDialog.show()
    }

    fun addDataToDatabase() {
        val totalPemasukan = totalAmount.text.toString()
        val tipeTransaksi = TransactionType.MASUK
        val keterangan = description.text.toString()
        val tanggal = System.currentTimeMillis()
        Log.i("tanggal", tanggal.toString())

        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
        val tanggal2 = sdf.format(Date())
        Log.i("tanggal 2", tanggal2)

        // Input check
        if (inputCheck(totalPemasukan)) {
            // Create user model
            val transactionModel =
                TransactionModel(0, totalPemasukan.toInt(), tipeTransaksi, keterangan, tanggal)
            // Add data to database
            myViewModel.addTransaction(transactionModel)
            Toast.makeText(this, "Transaksi berhasil ditambahkan.", Toast.LENGTH_SHORT).show()
            // Finish activity
            finish()
        } else {
            Toast.makeText(this, "Harap isi kolom total atau pilih produk.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(totalPemasukan: String): Boolean {
        return totalPemasukan.isNotBlank()
    }

}