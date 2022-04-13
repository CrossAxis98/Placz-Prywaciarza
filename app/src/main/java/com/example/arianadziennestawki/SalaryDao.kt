package com.example.arianadziennestawki

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SalaryDao {

    @Insert
    fun insert(salary: SalaryEntity)

    @Update
    fun update(salary: SalaryEntity)

    @Query("SELECT * FROM salary_table")
    fun getAllSalaries(): LiveData<List<SalaryEntity>>

    @Query("SELECT amount FROM salary_table WHERE name = :name")
    fun getSalaryForSpecifiedUser(name: String): List<String>

    @Query("DELETE FROM salary_table WHERE name = :name")
    fun removeUser(name: String)

    @Query("DELETE FROM salary_table")
    fun deleteAllRows()

}