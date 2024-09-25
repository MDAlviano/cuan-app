package com.alviano.cuan.beta.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.alviano.cuan.beta.R
import com.google.android.material.imageview.ShapeableImageView

class CreateProductActivity : AppCompatActivity() {

    private lateinit var imageButton: ShapeableImageView
    private lateinit var btnBack: ImageButton

    companion object {
        const val IMAGE_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_product)

        imageButton =findViewById(R.id.btnAddImg)
        btnBack=findViewById(R.id.btnBack)

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
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            imageButton.setImageURI(data?.data)
            imageButton.scaleType = ImageView.ScaleType.CENTER_CROP
            Toast.makeText(this, "Sukses menambahkan foto", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Gagal menambahkan foto", Toast.LENGTH_LONG).show()
        }
    }
}