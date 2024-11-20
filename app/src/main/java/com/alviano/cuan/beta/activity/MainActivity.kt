package com.alviano.cuan.beta.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.alviano.cuan.beta.R

class MainActivity : AppCompatActivity() {

    private lateinit var bottomSheetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        val sharedPreferences = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        val isFirstStart = sharedPreferences.getBoolean("isFirstStart", true)

        if (!isFirstStart) {
            moveActivity()
            return
        }

        setContentView(R.layout.start_page)

        bottomSheetButton = findViewById(R.id.buttonBottomSheet)
        bottomSheetButton.setOnClickListener {
            sharedPreferences.edit().putBoolean("isFirstStart", false).apply()
            moveActivity()
            finish()
        }

    }

    // fungsi punya sendiri
    private fun moveActivity() {
        startActivity(Intent(this, FragmentActivity::class.java))
        finish()
    }

}


