package com.alviano.cuan.beta.activity

import android.content.ContentProvider
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.room.PrimaryKey
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.model.Product
import com.alviano.cuan.beta.viewmodel.ProductViewModel
import com.google.android.material.imageview.ShapeableImageView
import java.util.zip.Inflater

class CreateProductActivity : AppCompatActivity() {

    private lateinit var mViewModel: ProductViewModel

    private lateinit var imageButton: ShapeableImageView
    private lateinit var btnBack: ImageButton
    private lateinit var saveBtn: Button
    private lateinit var addNamaProduk: EditText
    private lateinit var addHargaJual: EditText
    private lateinit var addHargaBeli: EditText

    private lateinit var fab: Button

    companion object {
        const val IMAGE_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_product)

        fab = findViewById(R.id.saveBtn)

        mViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        addNamaProduk = findViewById(R.id.namaProduk)
        addHargaBeli = findViewById(R.id.hargaBeli)
        addHargaJual = findViewById(R.id.hargaJual)

        fab.setOnClickListener{
            insertDataToDatabase()
        }

        imageButton = findViewById(R.id.btnAddImg)
        btnBack = findViewById(R.id.btnBack)

//        imageButton.setOnClickListener{
//            pickImageGallery()
//        }

        btnBack.setOnClickListener {
            finish()
        }

    }

//    fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        // Inflate the layout
//        val view = inflater.inflate(R.layout.activity_create_product, container, false)
//        val fab = view.findViewById<View>(R.id.saveBtn)
//
//        mViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
//
//        addNamaProduk = findViewById(R.id.namaProduk)
//        addHargaBeli = findViewById(R.id.hargaBeli)
//        addHargaJual = findViewById(R.id.hargaJual)
//
//        fab.setOnClickListener{
//            insertDataToDatabase()
//        }
//
//        return view
//    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            imageButton.setImageURI(data?.data)
            imageButton.scaleType = ImageView.ScaleType.CENTER_CROP
            Toast.makeText(this, "Sukses menambahkan foto", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Gagal menambahkan foto", Toast.LENGTH_LONG).show()
        }
    }

    private fun insertDataToDatabase() {
        val productName = addNamaProduk.text.toString()
        val sellPrice = addHargaJual.text.toString()
        val buyPrice = addHargaBeli.text.toString()

        if (inputCheck(productName, sellPrice, buyPrice)){
            // Create user model
            val product = Product(0, productName, sellPrice.toInt(), buyPrice.toInt())
            // Add data to database
            mViewModel.addProduct(product)
            Toast.makeText(this, "Produk telah ditambahkan.", Toast.LENGTH_LONG).show()
            finish()
            // Navigate back
//            findNavController().navigate(R.id.)
        } else {
            Toast.makeText(this, "Harap isi semua kolom.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(productName: String, sellPrice: String, buyPrice: String): Boolean {
        return productName.isNotBlank() && sellPrice.isNotBlank() && buyPrice.isNotBlank()
    }
}