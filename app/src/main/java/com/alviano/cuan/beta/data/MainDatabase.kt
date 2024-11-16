package com.alviano.cuan.beta.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alviano.cuan.beta.model.ProductModel
import com.alviano.cuan.beta.model.TransactionModel

@Database(
    entities = [ProductModel::class, TransactionModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MainDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getMainDatabase(context: Context): MainDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDatabase::class.java,
                    "main_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}