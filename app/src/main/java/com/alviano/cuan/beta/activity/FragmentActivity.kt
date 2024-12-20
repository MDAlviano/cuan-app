package com.alviano.cuan.beta.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.databinding.ActivityFragmentBinding
import com.alviano.cuan.beta.fragment.home.HomeFragment

class FragmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentBinding
    private var doubleBackToExitPressedOnce = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_fragment)

        binding = ActivityFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mFragmentManager = supportFragmentManager
        val fragmentHome = HomeFragment()
        val fragment = mFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)

        if (fragment !is HomeFragment) {
            mFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragmentHome, HomeFragment::class.java.simpleName)
                .commit()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (doubleBackToExitPressedOnce) {
                    finish() // Menghancurkan aplikasi
                } else {
                    doubleBackToExitPressedOnce = true
                    Toast.makeText(applicationContext, "Tekan tombol kembali sekali lagi untuk keluar", Toast.LENGTH_SHORT).show()
                    mFragmentManager
                        .beginTransaction()
                        .add(R.id.fragment_container, fragmentHome, HomeFragment::class.java.simpleName)
                        .commit()

                    Handler(Looper.getMainLooper()).postDelayed({
                        doubleBackToExitPressedOnce = false
                    }, 2000)
                }
            }
        })

    }


}