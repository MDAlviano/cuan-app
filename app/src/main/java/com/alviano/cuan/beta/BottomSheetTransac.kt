package com.alviano.cuan.beta

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.alviano.cuan.beta.databinding.FragmentBottomSheetTransacBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
class BottomSheetTransac : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetTransacBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_bottom_sheet_transac, container, false)
        binding = FragmentBottomSheetTransacBinding.inflate(inflater, container, false)
        return binding.root
    }

    // style for bottom_sheet_transac
//    val dialog = Dialog(context)
//    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//    dialog.setContentView(R.layout.bottom_sheet_transac)
//    dialog.show()
//    dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//    dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
//    dialog.window?.setGravity(Gravity.BOTTOM)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toPemasukan.setOnClickListener {
            val intent = Intent(activity, AddPemasukanActivity::class.java)
            activity?.startActivity(intent)
        }

        binding.toPengeluaran.setOnClickListener {
            val intent = Intent(activity, AddPengeluaranActivity::class.java)
            activity?.startActivity(intent)
        }
    }

}

