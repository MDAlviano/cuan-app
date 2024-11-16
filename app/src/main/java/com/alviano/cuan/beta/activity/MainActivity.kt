package com.alviano.cuan.beta.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.alviano.cuan.beta.R

class MainActivity : AppCompatActivity() {

    private lateinit var bottomSheetButton: Button
//    private lateinit var inputBookNameLayout: LinearLayout
//    private lateinit var keperluanPenggunaanLayout: LinearLayout
//    private lateinit var bottomSheetLayout: View
//    private lateinit var buttonToHomePage: Button
//    private lateinit var inputBookName: EditText
//    private lateinit var inputKeperluanPengguna: EditText
//
////    property homePageActivity
//    private lateinit var homePage: View
//    lateinit var namaToko: TextView
//    lateinit var keperluanPenggunaan: TextView

//    private fun initComponents() {
//        bottomSheetButton = findViewById(R.id.buttonBottomSheet)
//        bottomSheetLayout = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)
//        buttonToHomePage = bottomSheetLayout.findViewById(R.id.buttonToHomePage)
//        inputBookName = bottomSheetLayout.findViewById(R.id.inputBookName)
//        inputKeperluanPengguna = bottomSheetLayout.findViewById(R.id.inputKeperluanPengguna)
//    }

//    fun toHomePage(v: View) {
//        if (v.id == R.id.buttonBottomSheet) {
////            val bookName = inputBookName.text.toString()
////            val keperluanPengguna = inputKeperluanPengguna.text.toString()
////
////            homePage = layoutInflater.inflate(R.layout.activity_home_page, null)
////            namaToko = homePage.findViewById(R.id.namaToko)
////            keperluanPenggunaan = homePage.findViewById(R.id.keperluanPenggunaan)
////
////            Toast.makeText(this, resources.getString(R.string.suksesMembuatToko), Toast.LENGTH_SHORT).show()
//            val intent = Intent(this@MainActivity, FragmentActivity::class.java)
//            startActivity(intent)
//            finish()
////            namaToko.text = resources.getString(R.string.namaToko, bookName)
////            keperluanPenggunaan.text = resources.getString(R.string.keperluanPenggunaan, keperluanPengguna)
////            Log.i("namaNama", "nama toko = $bookName")
////            Log.i("namaNama", "keperluan = $keperluanPengguna")
//
////            if (bookName.isEmpty() || keperluanPengguna.isEmpty()) {
////                Toast.makeText(this, resources.getString(R.string.emptyInputField), Toast.LENGTH_SHORT).show()
////            } else {
////
////            }
//        }
//    }

//    private fun showDialogLayout() {
//        val dialog = Dialog(this)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setContentView(R.layout.bottom_sheet_layout)
//
//        inputBookNameLayout = dialog.findViewById(R.id.layoutInputBookName)
//        keperluanPenggunaanLayout = dialog.findViewById(R.id.layoutKeperluanPenggunaan)
//
//        inputBookNameLayout.setOnClickListener(View.OnClickListener {
//            fun onClick(v: View) {
//                Toast.makeText(this, "input book name is clicked", Toast.LENGTH_SHORT).show()
//            }
//        })
//
//        keperluanPenggunaanLayout.setOnClickListener(View.OnClickListener {
//            fun onClick(v: View) {
//                Toast.makeText(this, "keperluan penggunaan field is clicked", Toast.LENGTH_SHORT).show()
//            }
//        })
//
//        dialog.show()
//        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
//        dialog.window?.setGravity(Gravity.BOTTOM)
//
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.start_page)

//        initComponents()

        bottomSheetButton = findViewById(R.id.buttonBottomSheet)
        bottomSheetButton.setOnClickListener {
//            showDialogLayout()
            val intent = Intent(this@MainActivity, FragmentActivity::class.java)
            startActivity(intent)
        }

//        buttonToHomePage.setOnClickListener(View.OnClickListener {
//            fun onClick(v: View) {
//
//            }
//        })


//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }
}


