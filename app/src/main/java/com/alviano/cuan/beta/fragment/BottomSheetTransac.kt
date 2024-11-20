package com.alviano.cuan.beta.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alviano.cuan.beta.activity.AddPemasukanActivity
import com.alviano.cuan.beta.activity.AddPengeluaranActivity
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

