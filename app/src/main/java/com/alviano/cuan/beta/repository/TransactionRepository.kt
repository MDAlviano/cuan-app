package com.alviano.cuan.beta.repository

import androidx.lifecycle.LiveData
import com.alviano.cuan.beta.data.TransactionDao
import com.alviano.cuan.beta.model.TransactionModel

class TransactionRepository(private val transactionDao: TransactionDao) {

    val readAllData: LiveData<List<TransactionModel>> = transactionDao.readALlData()

    suspend fun addTransaction(transactionModel: TransactionModel) {
        transactionDao.addTransaction(transactionModel)
    }

    suspend fun deleteTransaction(transactionModel: TransactionModel) {
        transactionDao.deleteTransaction(transactionModel)
    }

}