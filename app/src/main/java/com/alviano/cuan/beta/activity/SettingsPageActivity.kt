package com.alviano.cuan.beta.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.alviano.cuan.beta.databinding.ActivitySettingsPageBinding

class SettingsPageActivity : AppCompatActivity() {

    lateinit var binding: ActivitySettingsPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        backBtn()

    }

    private fun backBtn() {
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}