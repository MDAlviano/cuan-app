package com.alviano.cuan.beta

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomePageActivity : AppCompatActivity() {

    private lateinit var toHomeBtn: ImageButton
    private lateinit var toTransaksiBtn: ImageButton
    private lateinit var toProdukBtn: ImageButton
    private lateinit var toLaporanBtn: ImageButton

    private fun initHomeComponents() {
        toHomeBtn = findViewById(R.id.toHomeBtn)
        toTransaksiBtn = findViewById(R.id.toTransacBtn)
        toProdukBtn = findViewById(R.id.toProductBtn)
        toLaporanBtn = findViewById(R.id.toLaporBtn)
    }

    private fun toHomePage() {
        val changeActivity = Intent(this@HomePageActivity, HomePageActivity::class.java)
        startActivity(changeActivity)
    }

    private fun toTransacPage() {
        val changeActivity = Intent(this@HomePageActivity, TransactionPageActivity::class.java)
        startActivity(changeActivity)
    }

    private fun toProductPage() {
        val changeActivity = Intent(this@HomePageActivity, ProductPageActivity::class.java)
        startActivity(changeActivity)
    }

    private fun toLaporanPage() {
        val changeActivity = Intent(this@HomePageActivity, LaporanPageActivity::class.java)
        startActivity(changeActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)
        initHomeComponents()

        // method2 utk navbar
        toHomeBtn.setOnClickListener {
            toHomePage()
        }

        toTransaksiBtn.setOnClickListener {
            toTransacPage()
        }

        toProdukBtn.setOnClickListener {
            toProductPage()
        }

        toLaporanBtn.setOnClickListener {
            toLaporanPage()
        }

    }
}