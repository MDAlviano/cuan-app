package com.alviano.cuan.beta.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "product_table")
data class ProductModel(
    @PrimaryKey(autoGenerate = true) val productId: Int,
    val imagePath: String?,
    val name: String,
    val sellPrice: Long,
    val buyPrice: Long,
    var isSelected: Boolean = false,
    var quantity: Int = 0
) : Parcelable
