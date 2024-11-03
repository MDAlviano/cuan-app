package com.alviano.cuan.beta.fragment.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.activity.SettingsPageActivity
import com.alviano.cuan.beta.fragment.transaction.TransactionFragment
import com.alviano.cuan.beta.databinding.FragmentHomeBinding
import com.alviano.cuan.beta.fragment.BottomSheetTransac
import com.alviano.cuan.beta.fragment.products.ListProductFragment
import com.alviano.cuan.beta.fragment.ReportFragment
import com.alviano.cuan.beta.fragment.transaction.TransactionAdapter
import com.alviano.cuan.beta.viewmodel.TransactionViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var transactionViewModel: TransactionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TransactionAdapter()
        Log.i("adapterHome", print(adapter).toString())
        val recentRecyclerView = view.findViewById<RecyclerView>(R.id.recentTransactionRecyclerView)
        recentRecyclerView.adapter = adapter
        recentRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        transactionViewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)
        transactionViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

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
            BottomSheetTransac().show(parentFragmentManager, "transactTag")
        }

        binding.lebihDetail.setOnClickListener {
            val transactionFragment = TransactionFragment()
            val mFragmentManager = parentFragmentManager
            mFragmentManager
                .beginTransaction().apply {
                    replace(R.id.fragment_container, transactionFragment, TransactionFragment::class.java.simpleName)
                    addToBackStack(null)
                    commit()
                }
        }

    }

}