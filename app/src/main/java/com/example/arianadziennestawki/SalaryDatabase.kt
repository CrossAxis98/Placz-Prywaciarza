package com.example.arianadziennestawki

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SalaryEntity::class], version = 2)
abstract class SalaryDatabase: RoomDatabase() {
    abstract fun salaryDao(): SalaryDao

    companion object {
        private var instance: SalaryDatabase? = null

        fun getInstance(context: Context): SalaryDatabase? {

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    SalaryDatabase::class.java,
                    "salary_table"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }

        fun deleteInstanceOfDatabase() {
            instance = null
        }
    }
}