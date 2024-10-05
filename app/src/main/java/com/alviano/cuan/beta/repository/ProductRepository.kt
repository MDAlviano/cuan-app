package com.alviano.cuan.beta.repository

import androidx.lifecycle.LiveData
import com.alviano.cuan.beta.data.ProductDao
import com.alviano.cuan.beta.model.Product

class ProductRepository(private val productDao: ProductDao) {

    val readAllData: LiveData<List<Product>> = productDao.readALlData()

    suspend fun addProduct(product: Product) {
        productDao.addProduct(product)
    }

    suspend fun updateProduct(product: Product) {
        productDao.updateProduct(product)
    }

    suspend fun deleteProduct(product: Product) {
        productDao.deleteProduct(product)
    }

//    suspend fun deleteAllProducts() {
//        productDao.deleteAllProducts()
//    }
}