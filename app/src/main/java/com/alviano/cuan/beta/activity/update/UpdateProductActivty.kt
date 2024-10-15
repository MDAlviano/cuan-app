package com.alviano.cuan.beta.activity.update

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.databinding.ActivityUpdateProductBinding
import com.alviano.cuan.beta.model.ProductModel
import com.alviano.cuan.beta.viewmodel.ProductViewModel
import java.io.ByteArrayOutputStream

class UpdateProductActivty : AppCompatActivity() {

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
        if (selectedProduct.image != null) {
            val bitmap = BitmapFactory.decodeByteArray(selectedProduct.image, 0, selectedProduct.image!!.size)
            binding.btnUpdateImg.setImageBitmap(bitmap)
        }

        binding.updateNamaProdukTxt.setText(selectedProduct.name)
        binding.updateHargaJuaTxt.setText(selectedProduct.sellPrice.toString())
        binding.updateHargaBeliTxt.setText(selectedProduct.buyPrice.toString())

        // Tombol update
        binding.updateBtn.setOnClickListener {
            updateProductInDatabase()
        }

        // Tombol delete
        binding.deleteBtn.setOnClickListener {
            deleteProduct()
        }
    }

    private fun updateProductInDatabase() {
        val updatedImageByteArray: ByteArray? = if (binding.btnUpdateImg.drawable != null) {
            val bitmap = (binding.btnUpdateImg.drawable as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.toByteArray()
        } else {
            selectedProduct.image // Gunakan gambar yang sudah ada jika tidak diubah
        }
        val name = binding.updateNamaProdukTxt.text.toString()
        val sellPrice = binding.updateHargaJuaTxt.text.toString().toInt()
        val buyPrice = binding.updateHargaBeliTxt.text.toString().toInt()

        val updatedProduct = ProductModel(selectedProduct.productId, updatedImageByteArray,name, sellPrice, buyPrice)

        mProductViewModel.updateProduct(updatedProduct)

        Toast.makeText(this, "Berhasil mengupdate", Toast.LENGTH_SHORT).show()
        finish() // Kembali ke ListProductFragment
    }

    private fun deleteProduct() {
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("Yes") { _, _ ->
            mProductViewModel.deleteProduct(selectedProduct)
            Toast.makeText(this, "Berhasil menghapus", Toast.LENGTH_SHORT).show()
            finish() // Kembali ke ListProductFragment
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Hapus ${selectedProduct.name}?")
        builder.setMessage("Yakin ingin menghapus produk ${selectedProduct.name}?")
        builder.create().show()
    }

}