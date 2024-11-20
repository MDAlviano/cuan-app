package com.alviano.cuan.beta.fragment.transaction

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.databinding.FragmentTransactionBinding
import com.alviano.cuan.beta.fragment.BottomSheetTransac
import com.alviano.cuan.beta.fragment.home.HomeFragment
import com.alviano.cuan.beta.fragment.products.ListProductFragment
import com.alviano.cuan.beta.fragment.report.ReportFragment
import com.alviano.cuan.beta.utils.formatAsCurrency
import com.alviano.cuan.beta.viewmodel.TransactionViewModel

class TransactionFragment : Fragment() {

    private lateinit var binding: FragmentTransactionBinding
    private lateinit var transactionViewModel: TransactionViewModel
    private lateinit var pemasukanTextView: TextView
    private lateinit var pengeluaranTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TransactionAdapter()
        Log.i("adapterTransaction", print(adapter).toString())
        val recyclerView = view.findViewById<RecyclerView>(R.id.transactionRecyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        pemasukanTextView = binding.totalPemasukanTextView
        pengeluaranTextView = binding.totalPengeluaranTextView

        transactionViewModel = ViewModelProvider(this)[TransactionViewModel::class.java]
        transactionViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })



        transactionViewModel.totalPemasukan.observe(viewLifecycleOwner) { total ->
            val formattedTotal = formatAsCurrency(total)
            pemasukanTextView.text = formattedTotal
        }

        transactionViewModel.totalPengeluaran.observe(viewLifecycleOwner) { total ->
            val formattedTotal = formatAsCurrency(total)
            pengeluaranTextView.text = formattedTotal
        }

        binding.toHomeBtn.setOnClickListener {
            val homeFragment = HomeFragment()
            val mFragmentManager = parentFragmentManager
            mFragmentManager
                .beginTransaction().apply {
                    replace(R.id.fragment_container, homeFragment, HomeFragment::class.java.simpleName)
                    addToBackStack(null)
                    commit()
                }
        }

        binding.toProductBtn.setOnClickListener {
            val productFragment = ListProductFragment()
            val mFragmentManager = parentFragmentManager
            mFragmentManager
                .beginTransaction().apply {
                    replace(R.id.fragment_container, productFragment, ListProductFragment::class.java.simpleName)
                    addToBackStack(null)
                    commit()
                }
        }

        binding.toTransacBtn.setOnClickListener {
            val transactionFragment = TransactionFragment()
            val mFragmentManager = parentFragmentManager
            mFragmentManager
                .beginTransaction().apply {
                    replace(R.id.fragment_container, transactionFragment, TransactionFragment::class.java.simpleName)
                    addToBackStack(null)
                    commit()
                }
        }

        binding.toLaporBtn.setOnClickListener {
            val reportFragment = ReportFragment()
            val mFragmentManager = parentFragmentManager
            mFragmentManager
                .beginTransaction().apply {
                    replace(R.id.fragment_container, reportFragment, ReportFragment::class.java.simpleName)
                    addToBackStack(null)
                    commit()
                }
        }

//        binding.toSettingsBtn.setOnClickListener {
//            val intent = Intent(activity, SettingsPageActivity::class.java)
//            activity?.startActivity(intent)
//        }

        binding.addTransactionBtn.setOnClickListener {
            BottomSheetTransac().show(parentFragmentManager, "newTransactTag")
        }

    }

}