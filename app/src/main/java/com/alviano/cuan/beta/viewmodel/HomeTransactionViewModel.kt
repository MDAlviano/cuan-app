package com.alviano.cuan.beta.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.alviano.cuan.beta.data.MainDatabase
import com.alviano.cuan.beta.data.TransactionType
import com.alviano.cuan.beta.model.TransactionModel
import com.alviano.cuan.beta.repository.TransactionRepository
import java.util.Calendar

class HomeTransactionViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<TransactionModel>>
    private val repository: TransactionRepository

    init {
        val transactionDao = MainDatabase.getMainDatabase(application).transactionDao()
        repository = TransactionRepository(transactionDao)
        readAllData = repository.readAllData
    }

    val todayTransactions: LiveData<List<TransactionModel>> = readAllData.map() { transactions ->
        // Filter to only display today's transaction
        val currentDate = Calendar.getInstance()
        val todayStart = currentDate.apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis

        val todayEnd = currentDate.apply {
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
            set(Calendar.MILLISECOND, 999)
        }.timeInMillis

        transactions.filter { it.timestamp in todayStart..todayEnd }
    }

    val todayIncome: LiveData<Long> = todayTransactions.map() { transactions ->
        transactions.filter { it.transactionType == TransactionType.MASUK }.sumOf { it.totalAmount }
    }

    val todayExpense: LiveData<Long> = todayTransactions.map() { transactions ->
        transactions.filter { it.transactionType == TransactionType.KELUAR }.sumOf { it.totalAmount }
    }
}
