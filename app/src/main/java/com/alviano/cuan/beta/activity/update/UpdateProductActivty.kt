package com.alviano.cuan.beta.activity.update

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.databinding.ActivityUpdateProductBinding
import com.alviano.cuan.beta.viewmodel.ProductViewModel

class UpdateProductActivty : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateProductBinding

    private lateinit var mProductViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUpdateProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mProductViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)


    }
}