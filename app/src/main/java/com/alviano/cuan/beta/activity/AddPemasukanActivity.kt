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
import androidx.recyclerview.widget.RecyclerView
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.activity.chooseproduct.ChooseProductActivity
import com.alviano.cuan.beta.activity.chooseproduct.ChooseProductAdapter
import com.alviano.cuan.beta.data.TransactionType
import com.alviano.cuan.beta.databinding.ActivityAddPemasukanBinding
import com.alviano.cuan.beta.model.ProductModel
import com.alviano.cuan.beta.model.TransactionModel
import com.alviano.cuan.beta.viewmodel.ProductViewModel
import com.alviano.cuan.beta.viewmodel.TransactionViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddPemasukanActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddPemasukanBinding
    private lateinit var mProductViewModel: ProductViewModel
    private lateinit var myViewModel: TransactionViewModel
    private lateinit var totalAmount: EditText
    private lateinit var description: EditText

    private var selectedProducts = mutableListOf<ProductModel>()

    companion object {
        private const val CHOOSE_PRODUCT_REQUEST_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddPemasukanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mProductViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        myViewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)

        totalAmount = binding.totalPemasukan
        description = binding.keteranganPemasukan

        binding.insertProduct.setOnClickListener{
            val chooseProductActivity = ChooseProductActivity()
            val bottomSheetDialog = BottomSheetDialog(this)

            chooseProductActivity.onProductsSelected = { selectedProducts ->
                updateSelectedProducts(selectedProducts)
            }

            chooseProductActivity.show(supportFragmentManager, "ChooseProductFragment")

            // tambahkan listener untuk menerima hasil
//            bottomSheetDialog.setOnDismissListener {
//                val selectedProducts = bottomSheetDialog.findViewById<RecyclerView>(R.id.allProduct)
//                    ?.tag as? List<ProductModel>
//                selectedProducts?.let { products ->
//                    updateSelectedProducts(products)
//                }
//            }

//            bottomSheetDialog.show()

        }

        binding.saveTransacPemasukan.setOnClickListener {
            addDataToDatabase()
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
//        bottomSheetDialog.show()
    }

    fun addDataToDatabase() {
        val totalPemasukan = totalAmount.text.toString()
        val tipeTransaksi = TransactionType.MASUK
        val keteranganText = description.text.toString().trim()
        val keterangan = if (keteranganText.isBlank()) null else keteranganText
        val tanggal = System.currentTimeMillis()
        Log.i("tanggal", tanggal.toString())

        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
        val tanggal2 = sdf.format(Date())
        Log.i("tanggal 2", tanggal2)

        // Input check
        if (inputCheck(totalPemasukan)) {
            // Konversi selected products ke JSON atau string untuk disimpan
            val productDetailsJson = Gson().toJson(selectedProducts)

            // Create user model
            val transactionModel =
                TransactionModel(0, totalPemasukan.toInt(), tipeTransaksi, keterangan, tanggal, productDetailsJson)
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

    private fun updateSelectedProducts(selectedProducts: List<ProductModel>) {
        //  terima data dari choose product activity
//        val data = intent.getParcelableExtra<ProductModel>(ChooseProductActivity.SELECTED_PRODUCTS_KEY)
        // seharusnya adalah menangkap total barang yang dipilih, namun saya salah karena hanya memasukkan harga jual
//        val totalPrice = data?.sellPrice
        // jika data total sellPrice berhasil dikirim, maka
        val totalPrice = selectedProducts.sumOf { it.sellPrice * it.quantity }
        binding.totalPemasukan.setText(totalPrice.toString())

//        val productCountMap = selectedProducts.groupBy { it.name }
//            .mapValues { it.value.size }

        val productDetails = selectedProducts.joinToString("\n") { product ->
            "${product.name} (${product.quantity})"
        }
        binding.productFieldTxt.text = productDetails
    }

    private fun inputCheck(totalPemasukan: String): Boolean {
        return totalPemasukan.isNotBlank()
    }

}