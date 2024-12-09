package com.alviano.cuan.beta.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alviano.cuan.beta.data.TransactionType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "transaction_table")
data class TransactionModel(
    @PrimaryKey(autoGenerate = true) val transactionId: Int,
    val totalAmount: Long,
    val transactionType: TransactionType,
    val description: String?,
    val timestamp: Long,
    val productDetails: String?
) : Parcelable {
    fun getParsedProductDetails(): List<ProductModel> {
        return productDetails?.let {
            Gson().fromJson(it, Array<ProductModel>::class.java).toList()
        } ?: emptyList()
    }
}