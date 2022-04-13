package com.example.arianadziennestawki

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking

class SalaryViewModel (application: Application):
    AndroidViewModel(application) {

        private var salaryRepository = SalaryRepository(application)
        private var allSalaries: Deferred<LiveData<List<SalaryEntity>>> = salaryRepository.getAllSalariesAsync()

    fun insertSalary(salary: SalaryEntity) {
        salaryRepository.insertSalary(salary)
    }

    fun updateSalary(salary: SalaryEntity) {
        salaryRepository.updateSalary(salary)
    }

     fun getAllSalaries(): LiveData<List<SalaryEntity>> = runBlocking {
         allSalaries.await()
     }

    fun getSalaryForSpecifiedUser(name: String): List<String> = runBlocking {
        salaryRepository.getSalaryForSpecifiedUser(name).await()
    }

    fun removeUser(name: String) {
        salaryRepository.removeUser(name)
    }

    fun deleteAllRows() {
        salaryRepository.deleteAllRows()
    }
}