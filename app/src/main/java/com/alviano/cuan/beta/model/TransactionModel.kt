package com.alviano.cuan.beta.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "transaction_table")
data class TransactionModel(
    @PrimaryKey(autoGenerate = true) val transactionId: Int,
    val totalAmount: Int,
    val transactionType: String,
    val description: String,
) : Parcelable