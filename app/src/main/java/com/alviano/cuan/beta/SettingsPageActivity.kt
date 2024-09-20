package com.alviano.cuan.beta

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alviano.cuan.beta.databinding.ActivitySettingsPageBinding

class SettingsPageActivity : AppCompatActivity() {

    lateinit var binding: ActivitySettingsPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
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