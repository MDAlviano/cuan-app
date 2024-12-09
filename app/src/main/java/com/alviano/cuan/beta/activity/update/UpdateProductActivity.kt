package com.alviano.cuan.beta.activity.update

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alviano.cuan.beta.activity.CreateProductActivity
import com.alviano.cuan.beta.databinding.ActivityUpdateProductBinding
import com.alviano.cuan.beta.model.ProductModel
import com.alviano.cuan.beta.viewmodel.ProductViewModel
import java.io.File

class UpdateProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateProductBinding
    private lateinit var mProductViewModel: ProductViewModel
    private lateinit var selectedProduct: ProductModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUpdateProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mProductViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        // Menerima data produk yang dikirim dari fragment sebelumnya
        selectedProduct = intent.getParcelableExtra("selected_product")!!

        // Menampilkan data produk pada EditText
        if (selectedProduct.imagePath != null) {
            val bitmap = BitmapFactory.decodeFile(selectedProduct.imagePath)
            binding.btnUpdateImg.setImageBitmap(bitmap)
        }

        binding.updateNamaProdukTxt.setText(selectedProduct.name)
        binding.updateHargaJuaTxt.setText(selectedProduct.sellPrice.toString())
        binding.updateHargaBeliTxt.setText(selectedProduct.buyPrice.toString())



        binding.btnUpdateImg.setOnClickListener {
            pickImageGallery()
        }

        // Tombol update
        binding.updateBtn.setOnClickListener {
            updateProductInDatabase()
        }

        // Tombol delete
        binding.deleteBtn.setOnClickListener {
            deleteProduct()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, CreateProductActivity.IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val imageUri: Uri? = data?.data
        val source = imageUri?.let { ImageDecoder.createSource(this.contentResolver, it) }
        val imageBitmap = source?.let { ImageDecoder.decodeBitmap(it) }
//        Log.d("imageDataBitmap", imageBitmap.toString())

        if (requestCode == CreateProductActivity.IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            binding.btnUpdateImg.setImageBitmap(imageBitmap)
            binding.btnUpdateImg.scaleType = ImageView.ScaleType.CENTER_CROP
            Toast.makeText(this, "Sukses menambahkan foto", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Gagal menambahkan foto", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateProductInDatabase() {
        val name = binding.updateNamaProdukTxt.text.toString()
        val sellPrice = binding.updateHargaJuaTxt.text.toString().toIntOrNull()
        val buyPrice = binding.updateHargaBeliTxt.text.toString().toIntOrNull()

        if (name.isBlank() || sellPrice == null || buyPrice == null) {
            Toast.makeText(this, "Harap isi semua kolom dengan benar!", Toast.LENGTH_SHORT).show()
            return
        }

        val oldImagePath = selectedProduct.imagePath

        val updatedImagePath = if (binding.btnUpdateImg.drawable != null) {
            val bitmap = (binding.btnUpdateImg.drawable as BitmapDrawable).bitmap

            oldImagePath?.let {
                if (!it.contains("default_image_path")) { // Ganti "default_image_path" dengan path default
                    deleteImageLocally(it)
                }
            }

            saveImage(bitmap, "product_image_${System.currentTimeMillis()}.jpg")
        } else {
            oldImagePath // Gunakan gambar lama jika tidak diubah
        }

        val updatedProduct = ProductModel(
            selectedProduct.productId,
            updatedImagePath,
            name,
            sellPrice,
            buyPrice
        )

        mProductViewModel.updateProduct(updatedProduct)

        Toast.makeText(this, "Berhasil mengupdate", Toast.LENGTH_SHORT).show()
        finish() // Kembali ke ListProductFragment
    }

    private fun deleteProduct() {
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("Yes") { _, _ ->
            selectedProduct.imagePath?.let { deleteImageLocally(it) }

            mProductViewModel.deleteProduct(selectedProduct)
            Toast.makeText(this, "Berhasil menghapus", Toast.LENGTH_SHORT).show()
            finish()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Hapus ${selectedProduct.name}?")
        builder.setMessage("Yakin ingin menghapus produk ${selectedProduct.name}?")
        builder.create().show()
    }

    private fun saveImage(bitmap: Bitmap, name: String): String? {
        return try {
            val fileName = "product_image_${System.currentTimeMillis()}.jpg"
            val file = File(filesDir, fileName)

            val outputStream = file.outputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 80, outputStream)
            outputStream.flush()
            outputStream.close()

            file.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun deleteImageLocally(imagePath: String) {
        val file = File(imagePath)
        if (file.exists()) {
            if (file.delete()) {
                Log.d("UpdateProductActivity", "Gambar lama berhasil dihapus: $imagePath")
            } else {
                Log.e("UpdateProductActivity", "Gagal menghapus gambar: $imagePath")
            }
        } else {
            Log.w("UpdateProductActivity", "Gambar tidak ditemukan: $imagePath")
        }
    }


}