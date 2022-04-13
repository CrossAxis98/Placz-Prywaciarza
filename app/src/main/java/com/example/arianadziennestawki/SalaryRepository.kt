package com.example.arianadziennestawki

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.arianadziennestawki.SalaryDatabase
import kotlinx.coroutines.*

class SalaryRepository (application: Application) {
    
    private var salaryDao: SalaryDao
    
    init {
        val database = SalaryDatabase
                        .getInstance(application.applicationContext)
        
        salaryDao = database!!.salaryDao()
    }

    fun insertSalary(salary: SalaryEntity) =
        CoroutineScope(Dispatchers.IO).launch {
            salaryDao.insert(salary)
        }

    fun updateSalary(salary: SalaryEntity) =
        CoroutineScope(Dispatchers.IO).launch {
            salaryDao.update(salary)
        }

    fun getAllSalariesAsync(): Deferred<LiveData<List<SalaryEntity>>> =
        CoroutineScope(Dispatchers.IO).async {
            salaryDao.getAllSalaries()
        }

    fun getSalaryForSpecifiedUser(name: String): Deferred<List<String>> =
        CoroutineScope(Dispatchers.IO).async {
            salaryDao.getSalaryForSpecifiedUser(name)
        }

    fun removeUser(name: String) =
        CoroutineScope(Dispatchers.IO).launch {
            salaryDao.removeUser(name)
        }

    fun deleteAllRows() =
        CoroutineScope(Dispatchers.IO).launch {
            salaryDao.deleteAllRows()
        }
    
}