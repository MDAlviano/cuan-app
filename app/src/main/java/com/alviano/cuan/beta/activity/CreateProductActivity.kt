package com.alviano.cuan.beta.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModelProvider
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.model.ProductModel
import com.alviano.cuan.beta.viewmodel.ProductViewModel
import com.google.android.material.imageview.ShapeableImageView
import java.io.ByteArrayOutputStream

class CreateProductActivity : AppCompatActivity() {

    private lateinit var mViewModel: ProductViewModel

    private lateinit var imageButton: ShapeableImageView
    private lateinit var btnBack: ImageButton
    private lateinit var saveButton: Button
    private lateinit var addNamaProduk: EditText
    private lateinit var addHargaJual: EditText
    private lateinit var addHargaBeli: EditText

    companion object {
        const val IMAGE_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_product)

        mViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        addNamaProduk = findViewById(R.id.namaProduk)
        addHargaBeli = findViewById(R.id.hargaBeli)
        addHargaJual = findViewById(R.id.hargaJual)
        saveButton = findViewById(R.id.saveBtn)

        saveButton.setOnClickListener{
            insertDataToDatabase()
        }

        imageButton = findViewById(R.id.btnAddImg)
        btnBack = findViewById(R.id.btnBack)

        imageButton.setOnClickListener{
            pickImageGallery()
        }

        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val imageUri: Uri? = data?.data
        val source = imageUri?.let { ImageDecoder.createSource(this.contentResolver, it) }
        val imageBitmap = source?.let { ImageDecoder.decodeBitmap(it) }
        Log.d("imageDataBitmap", imageBitmap.toString())

        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            imageButton.setImageBitmap(imageBitmap)
            imageButton.scaleType = ImageView.ScaleType.CENTER_CROP
            Toast.makeText(this, "Sukses menambahkan foto", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Gagal menambahkan foto", Toast.LENGTH_LONG).show()
        }
    }

    private fun insertDataToDatabase() {

        // Get button drawable as bitmap
        val imageButtonBitmap: Bitmap = imageButton.drawable.toBitmap()
        Log.i("imageButtonBitmap", imageButtonBitmap.toString())

        // Convert imageBitmap to ByteArray
        val outputStream = ByteArrayOutputStream()
        imageButtonBitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream)
        val productImage = outputStream.toByteArray()
        Log.i("imageDataByteArray", productImage.toString())

        val productName = addNamaProduk.text.toString()
        val sellPrice = addHargaJual.text.toString()
        val buyPrice = addHargaBeli.text.toString()

        if (inputCheck(productName, sellPrice, buyPrice)){
            // Create user model
            val productModel = ProductModel(0, productImage, productName, sellPrice.toInt(), buyPrice.toInt())
            // Add data to database
            mViewModel.addProduct(productModel)
            Toast.makeText(this, "Produk telah ditambahkan.", Toast.LENGTH_LONG).show()
            // Finish activity after done adding data
            finish()
        } else {
            Toast.makeText(this, "Harap isi semua kolom.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(productName: String, sellPrice: String, buyPrice: String): Boolean {
        return productName.isNotBlank() && sellPrice.isNotBlank() && buyPrice.isNotBlank()
    }
}