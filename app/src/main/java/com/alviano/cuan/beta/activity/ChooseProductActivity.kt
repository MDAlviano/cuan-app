package com.alviano.cuan.beta.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alviano.cuan.beta.databinding.ActivityChooseProductBinding

class ChooseProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityChooseProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.allProduct
        recyclerView.layoutManager = LinearLayoutManager(this)

//        val data = ArrayList<Product>()

//        data.add(Product(R.drawable.pensil, "Pensil", 1200))
//        data.add(Product(R.drawable.spidol, "Spidol", 2000))
//        data.add(Product(R.drawable.buku_tulis, "Buku Tulis", 1500))

//        val adapter = InsertProductAdapter(data)
//        recyclerView.adapter = adapter

    }
}