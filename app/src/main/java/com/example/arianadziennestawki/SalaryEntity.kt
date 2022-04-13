package com.example.arianadziennestawki

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "salary_table")
data class SalaryEntity (var name: String,
                         var amount: String,
                         var date: String) {
    @PrimaryKey(autoGenerate = true)
    var salary_id: Int = 0
}