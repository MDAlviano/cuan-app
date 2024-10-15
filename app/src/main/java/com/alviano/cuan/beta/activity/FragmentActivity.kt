package com.alviano.cuan.beta.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.databinding.ActivityFragmentBinding
import com.alviano.cuan.beta.fragment.HomeFragment

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
}