package com.alviano.cuan.beta.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.databinding.ActivityAddPengeluaranBinding

class AddPengeluaranActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddPengeluaranBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddPengeluaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}