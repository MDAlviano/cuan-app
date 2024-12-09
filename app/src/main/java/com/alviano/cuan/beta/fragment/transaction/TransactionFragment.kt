package com.alviano.cuan.beta.fragment.transaction

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.activity.TransactionDetailActivity
import com.alviano.cuan.beta.databinding.FragmentTransactionBinding
import com.alviano.cuan.beta.fragment.BottomSheetTransac
import com.alviano.cuan.beta.fragment.home.HomeFragment
import com.alviano.cuan.beta.fragment.products.ListProductFragment
import com.alviano.cuan.beta.fragment.report.ReportFragment
import com.alviano.cuan.beta.model.TransactionItemModel
import com.alviano.cuan.beta.model.TransactionModel
import com.alviano.cuan.beta.utils.formatAsCurrency
import com.alviano.cuan.beta.viewmodel.TransactionViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TransactionFragment : Fragment() {

    private lateinit var binding: FragmentTransactionBinding
    private lateinit var transactionViewModel: TransactionViewModel
    private lateinit var pemasukanTextView: TextView
    private lateinit var pengeluaranTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TransactionAdapter { selectedTransaction ->
            val intent = Intent(activity, TransactionDetailActivity::class.java)
            intent.putExtra("selected_transaction", selectedTransaction)
            activity?.startActivity(intent)
        }

        val recyclerView = binding.transactionRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        pemasukanTextView = binding.totalPemasukanTextView
        pengeluaranTextView = binding.totalPengeluaranTextView

        transactionViewModel = ViewModelProvider(this)[TransactionViewModel::class.java]
        transactionViewModel.readAllData.observe(viewLifecycleOwner, Observer { transactions ->
            val groupedTransactions = groupTransactionsByDate(transactions) // Kelompokkan transaksi
            adapter.setData(groupedTransactions)
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

        binding.addTransactionBtn.setOnClickListener {
            BottomSheetTransac().show(parentFragmentManager, "newTransactTag")
        }


    }

    private fun groupTransactionsByDate(transactions: List<TransactionModel>): List<TransactionItemModel> {
        val groupedItems = mutableListOf<TransactionItemModel>()

        transactions.groupBy { formatDate(it.timestamp) }
            .forEach { (date, transactionList) ->
                // Tambahkan header tanggal
                groupedItems.add(TransactionItemModel.DateHeader(date))
                // Tambahkan transaksi
                groupedItems.addAll(transactionList.map { TransactionItemModel.Transaction(it) })
            }

        return groupedItems
    }

    private fun formatDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
        return sdf.format(Date(timestamp))
    }

}