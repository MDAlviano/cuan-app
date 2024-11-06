package com.alviano.cuan.beta.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.alviano.cuan.beta.databinding.BottomSheetLayoutBinding

class SetStoreNameActivity : AppCompatActivity() {

    private lateinit var binding: BottomSheetLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = BottomSheetLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonToHomePage.setOnClickListener {
            val intent = Intent(this@SetStoreNameActivity, FragmentActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun sendData() {
        val storeName = binding.inputBookName.text.toString()
        val keperluan = binding.inputKeperluanPengguna.text.toString()
        Toast.makeText(this, "Success send data", Toast.LENGTH_SHORT).show()
    }
}