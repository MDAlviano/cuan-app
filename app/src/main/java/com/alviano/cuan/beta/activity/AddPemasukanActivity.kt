package com.alviano.cuan.beta.activity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.databinding.ActivityAddPemasukanBinding
import com.alviano.cuan.beta.model.TransactionModel
import com.alviano.cuan.beta.viewmodel.TransactionViewModel

class AddPemasukanActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddPemasukanBinding
    private lateinit var myViewModel: TransactionViewModel
    private lateinit var totalAmount: EditText
    private lateinit var description: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddPemasukanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showAddProductSheet()

        myViewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)

        totalAmount = findViewById(R.id.totalPemasukan)
        description = findViewById(R.id.keteranganPemasukan)

        binding.saveTransacPemasukan.setOnClickListener{
            addDataToDatabase()
        }
    }

    private fun showAddProductSheet() {
        binding.insertProduct.setOnClickListener {
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.activity_choose_product)
            dialog.show()
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
            dialog.window?.setGravity(Gravity.BOTTOM)
        }
    }

    fun addDataToDatabase() {
        val totalPemasukan = totalAmount.text.toString()
        val keterangan = description.text.toString()

        Log.i("logTotalPemasukan", totalPemasukan)

        val totalInt = totalPemasukan.toInt()
        Log.i("logTotalPemasukan", totalInt.toString())


        // Create user model
        val transactionModel = TransactionModel(0, totalPemasukan.toInt(), keterangan)
        // Add data to database
        myViewModel.addTransaction(transactionModel)
        Toast.makeText(this, "Transaksi berhasil ditambahkan.", Toast.LENGTH_SHORT).show()
        // Finish activity
        finish()
    }

}