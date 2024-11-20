package com.alviano.cuan.beta.data

import androidx.room.TypeConverter
import java.util.Date

enum class TransactionType {
    MASUK, KELUAR
}

class Converters {
    @TypeConverter
    fun fromTransactionType(value: TransactionType): String {
        return value.name
    }

    @TypeConverter
    fun toTransactionType(value: String): TransactionType {
        return TransactionType.valueOf(value)
    }
}