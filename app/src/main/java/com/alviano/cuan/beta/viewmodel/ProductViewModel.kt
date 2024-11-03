package com.alviano.cuan.beta.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.alviano.cuan.beta.data.MainDatabase
import com.alviano.cuan.beta.model.ProductModel
import com.alviano.cuan.beta.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<ProductModel>>
    private val repository: ProductRepository

    init {
        val productDao = MainDatabase.getMainDatabase(application).productDao()
        repository = ProductRepository(productDao)
        readAllData = repository.readAllData
    }

    fun addProduct(productModel: ProductModel) {
        viewModelScope.launch(Dispatchers.IO){
            repository.addProduct(productModel)
        }
    }

    fun deleteProduct(productModel: ProductModel) {
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteProduct(productModel)
        }
    }

//    fun deleteAllProducts() {
//        viewModelScope.launch(Dispatchers.IO){
//            repository.deleteAllProducts()
//        }
//    }

}