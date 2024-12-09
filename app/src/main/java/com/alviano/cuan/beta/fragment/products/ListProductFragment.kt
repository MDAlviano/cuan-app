package com.alviano.cuan.beta.fragment.products

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
//import com.alviano.cuan.beta.data.Product
//import com.alviano.cuan.beta.fragment.products.ProductAdapter
import com.alviano.cuan.beta.R
import com.alviano.cuan.beta.fragment.transaction.TransactionFragment
import com.alviano.cuan.beta.activity.CreateProductActivity
import com.alviano.cuan.beta.activity.update.UpdateProductActivity
import com.alviano.cuan.beta.databinding.FragmentListProductBinding
import com.alviano.cuan.beta.fragment.report.ReportFragment
import com.alviano.cuan.beta.fragment.home.HomeFragment
import com.alviano.cuan.beta.viewmodel.ProductViewModel

class ListProductFragment : Fragment() {

    private lateinit var binding: FragmentListProductBinding
    private lateinit var mProductViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_list_product, container, false)
        binding = FragmentListProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProductAdapter { selectedProduct ->
            val intent = Intent(activity, UpdateProductActivity::class.java)
            intent.putExtra("selected_product", selectedProduct) // Kirim data produk
            activity?.startActivity(intent)
        }
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mProductViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        mProductViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

//        data.add(Product(R.drawable.pensil, "Pensil", 1200))
//        data.add(Product(R.drawable.spidol, "Spidol", 2000))
//        data.add(Product(R.drawable.buku_tulis, "Buku Tulis", 1500))

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

        binding.addProductBtn.setOnClickListener {
            val intent = Intent(activity, CreateProductActivity::class.java)
            activity?.startActivity(intent)
        }
    }

}