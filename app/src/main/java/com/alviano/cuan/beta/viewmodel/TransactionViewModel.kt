package com.alviano.cuan.beta.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.alviano.cuan.beta.data.MainDatabase
import com.alviano.cuan.beta.model.TransactionModel
import com.alviano.cuan.beta.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

    fun deleteTransnaction(transactionModel: TransactionModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTransaction(transactionModel)
        }
    }

}