package com.alviano.cuan.beta.repository

import androidx.lifecycle.LiveData
import com.alviano.cuan.beta.data.ProductDao
import com.alviano.cuan.beta.model.ProductModel

class ProductRepository(private val productDao: ProductDao) {

    val readAllData: LiveData<List<ProductModel>> = productDao.readALlData()

    suspend fun addProduct(productModel: ProductModel) {
        productDao.addProduct(productModel)
    }

    suspend fun updateProduct(productModel: ProductModel) {
        productDao.updateProduct(productModel)
    }

    suspend fun deleteProduct(productModel: ProductModel) {
        productDao.deleteProduct(productModel)
    }

//    suspend fun deleteAllProducts() {
//        productDao.deleteAllProducts()
//    }
}