package com.alviano.cuan.beta.activity.chooseproduct

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alviano.cuan.beta.databinding.ActivityChooseProductBinding
import com.alviano.cuan.beta.viewmodel.ProductViewModel

class ChooseProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseProductBinding
    private lateinit var mProductViewModel: ProductViewModel
    private lateinit var adapter: ChooseProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityChooseProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        // Set up GridView with Adapter
//        adapter = ChooseProductAdapter(this)
//        binding.allProduct.adapter = adapter
//
//        // Set up ViewModel and LiveData observation
//        mProductViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
//        mProductViewModel.readAllData.observe(this, Observer { products ->
//            adapter.setData(products)
//        })
//
//        // Set up item click listener for GridView
//        binding.allProduct.setOnItemClickListener { _, _, position, _ ->
//            val product = adapter.getItem(position)
//            Toast.makeText(this, "You clicked on ${product.name}", Toast.LENGTH_SHORT).show()
//        }

    }
}