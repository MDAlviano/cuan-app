package com.alviano.cuan.beta.activity.slider

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewpager.widget.ViewPager
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.activity.FragmentActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mSlideViewPager: ViewPager
    private lateinit var mDotLayout: LinearLayout
    private lateinit var bottomSheetButton: TextView
    private lateinit var buttonStart: Button

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

        setContentView(R.layout.activity_slider)

        buttonStart = findViewById(R.id.btnStart)
        buttonStart.visibility = View.INVISIBLE
        buttonStart.setOnClickListener {
            sharedPreferences.edit().putBoolean("isFirstStart", false).apply()
            moveActivity()
            finish()
        }

        bottomSheetButton = findViewById(R.id.nextSlideBtn)
        bottomSheetButton.setOnClickListener {
            if (getItem(0) < 4) {
                mSlideViewPager.setCurrentItem(getItem(1), true)
            }
        }

        mSlideViewPager = findViewById(R.id.slideViewPager)
        mDotLayout = findViewById(R.id.indicator_layout)

        val viewPagerAdapter = ViewPagerAdapter(this)

        mSlideViewPager.adapter = viewPagerAdapter

        setUpIndicator(0)
        mSlideViewPager.addOnPageChangeListener(viewListener)

    }

    // fungsi punya sendiri
    private fun moveActivity() {
        startActivity(Intent(this, FragmentActivity::class.java))
        finish()
    }

    private fun setUpIndicator(position: Int) {
        val dots = arrayOfNulls<TextView>(5)
        mDotLayout.removeAllViews()

        for (i in 0 until dots.size)  {
            dots[i] = TextView(this)
            dots[i]?.setText(Html.fromHtml("&#8226"))
            dots[i]?.setTextSize(35F)
            dots[i]?.setTextColor(resources.getColor(R.color.gray, applicationContext.theme))
            mDotLayout.addView(dots[i])
        }

        dots[position]?.setTextColor(resources.getColor(R.color.color4, applicationContext.theme))

    }

    val viewListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {

        }

        override fun onPageSelected(position: Int) {
            setUpIndicator(position)
            if (position == 4) {
                buttonStart.visibility = View.VISIBLE
                bottomSheetButton.visibility = View.INVISIBLE
                mDotLayout.visibility = View.INVISIBLE
            } else {
                buttonStart.visibility = View.INVISIBLE
                bottomSheetButton.visibility = View.VISIBLE
                mDotLayout.visibility = View.VISIBLE
            }
        }

        override fun onPageScrollStateChanged(state: Int) {
            when (state) {
                ViewPager.SCROLL_STATE_IDLE -> Log.d("ViewPager", "Idle")
                ViewPager.SCROLL_STATE_DRAGGING -> Log.d("ViewPager", "Dragging")
                ViewPager.SCROLL_STATE_SETTLING -> Log.d("ViewPager", "Settling")
            }
        }
    }

    private fun getItem(i: Int): Int {
        return mSlideViewPager.currentItem + i
    }

}


