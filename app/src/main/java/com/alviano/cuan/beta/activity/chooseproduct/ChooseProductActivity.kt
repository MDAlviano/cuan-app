package com.alviano.cuan.beta.activity.chooseproduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alviano.cuan.beta.databinding.ActivityChooseProductBinding
import com.alviano.cuan.beta.viewmodel.ProductViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ChooseProductActivity : BottomSheetDialogFragment() {

    private lateinit var binding: ActivityChooseProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_bottom_sheet_transac, container, false)
        binding = ActivityChooseProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}