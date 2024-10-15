package com.alviano.cuan.beta.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alviano.cuan.beta.model.ProductModel
import com.alviano.cuan.beta.model.TransactionModel

@Database(
    entities = [ProductModel::class],
    version = 1,
    exportSchema = false
)
abstract class MainDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getProductDatabase(context: Context): MainDatabase {
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