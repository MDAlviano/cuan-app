package com.alviano.cuan.beta.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.alviano.cuan.beta.model.TransactionModel

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTransaction(transactionModel: TransactionModel)

//    @Update
//    suspend fun updateTransaction(transactionModel: TransactionModel)

    @Delete
    suspend fun deleteTransaction(transactionModel: TransactionModel)

//    @Query("DELETE FROM transaction_table")
//    suspend fun deleteAllTransaction()

    @Query("SELECT * FROM transaction_table ORDER BY transactionId DESC")
    fun readALlData(): LiveData<List<TransactionModel>>

}