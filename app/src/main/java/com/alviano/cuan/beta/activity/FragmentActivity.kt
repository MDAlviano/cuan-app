package com.alviano.cuan.beta.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.alviano.cuan.beta.fragment.home.HomeFragment
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.databinding.ActivityFragmentBinding

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
    }

    // blom bener
    override fun onBackPressed() {

        if (doubleBackToExitPressedOnce){
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Tekan tombol kembali sekali lagi untuk keluar", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

}