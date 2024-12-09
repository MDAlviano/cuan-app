package com.alviano.cuan.beta.activity.slider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.alviano.cuan.beta.R

class ViewPagerAdapter(private val context: Context): PagerAdapter() {

    val images: Array<Int> = arrayOf(
        R.drawable.logocuancopyremove,
        R.drawable._1,
        R.drawable._2,
        R.drawable._3,
        R.drawable.logocuancopyremove,
    )

    val headings: Array<Int> = arrayOf(
        R.string.header1,
        R.string.header2,
        R.string.header3,
        R.string.header4,
        R.string.header5,
    )

    val descriptions: Array<Int> = arrayOf(
        R.string.desc1,
        R.string.desc2,
        R.string.desc3,
        R.string.desc4,
        R.string.desc5,
    )

    override fun getCount(): Int {
        return headings.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = layoutInflater.inflate(R.layout.slider_layout, container, false)

        val slideImage: ImageView = view.findViewById(R.id.titleImage)
        val slideHeader: TextView = view.findViewById(R.id.textTitle)
        val sliderDescription: TextView = view.findViewById(R.id.textDesc)

        slideImage.setImageResource(images[position])
        slideHeader.setText(headings[position])
        sliderDescription.setText(descriptions[position])

        container.addView(view)

        return view

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

}