package com.alviano.cuan.beta.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.alviano.cuan.beta.data.MainDatabase
import com.alviano.cuan.beta.data.TransactionType
import com.alviano.cuan.beta.model.TransactionModel
import com.alviano.cuan.beta.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Calendar
import java.util.Currency
import java.util.Locale

class TransactionViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<TransactionModel>>
    private val repository: TransactionRepository

    init {
        val transactionDao = MainDatabase.getMainDatabase(application).transactionDao()
        repository = TransactionRepository(transactionDao)
        readAllData = repository.readAllData
    }

    fun addTransaction(transactionModel: TransactionModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTransaction(transactionModel)
        }
    }

    fun deleteTransaction(transactionModel: TransactionModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTransaction(transactionModel)
        }
    }

    val totalPemasukan: LiveData<Int> = readAllData.map { transactionList ->
        transactionList.filter { it.transactionType == TransactionType.MASUK }
            .sumOf { it.totalAmount }
    }

    val totalPengeluaran: LiveData<Int> = readAllData.map { transactionList ->
        transactionList.filter { it.transactionType == TransactionType.KELUAR }
            .sumOf { it.totalAmount }
    }
}