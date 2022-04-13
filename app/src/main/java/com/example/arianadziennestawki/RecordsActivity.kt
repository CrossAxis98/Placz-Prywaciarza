package com.example.arianadziennestawki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arianadziennestawki.databinding.ActivityRecordsBinding

class RecordsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecordsBinding
    private lateinit var viewModel: SalaryViewModel
    private lateinit var listOfSalaries: LiveData<List<SalaryEntity>>
    private lateinit var salaryAdapter: SalaryAdapter
    private var namesList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application)
            .create(SalaryViewModel::class.java)

        binding.rvSalaryRecords.layoutManager = LinearLayoutManager(this)
        listOfSalaries = viewModel.getAllSalaries()

        listOfSalaries.observe(this, Observer {
            if(it.isNotEmpty()){
                salaryAdapter = SalaryAdapter(it)
                binding.rvSalaryRecords.adapter = salaryAdapter
                it.forEach { if (it.name !in namesList) namesList.add(it.name) }
            }
        })

        binding.btnCalculate.setOnClickListener {
            calculateSalaryDialog()
        }

        binding.btnDelete.setOnClickListener {
            removeUser()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    fun calculateSalaryDialog() {
        var selectedName = ""
        val calculateDialog = AlertDialog.Builder(this)
            .setTitle("Kogo chcesz podliczyć?")
            .setSingleChoiceItems(namesList.toTypedArray(), -1) { dialog, which ->
                Toast.makeText(this, namesList[which], Toast.LENGTH_SHORT).show()
                selectedName = namesList[which]
            }
            .setPositiveButton("Podlicz") { _, _ ->
                val calculatedSalary = calculateSalary(selectedName)
                amountToBeWithdrawnDialog(calculatedSalary)
            }
            .setNegativeButton("Wróć") { _, _ ->
            }.create()

        calculateDialog.show()
    }

    fun removeUser() {
        var selectedName = ""
        val removeDialog = AlertDialog.Builder(this)
            .setTitle("Kogo usunąć?")
            .setSingleChoiceItems(namesList.toTypedArray(), -1) { dialog, which ->
                Toast.makeText(this, namesList[which], Toast.LENGTH_SHORT).show()
                selectedName = namesList[which]
            }
            .setPositiveButton("Usuń") { _, _ ->
                viewModel.removeUser(selectedName)
                namesList.remove(selectedName)
                Toast.makeText(this, "Rozliczenia użytkownika $selectedName zostały usunięte", Toast.LENGTH_LONG).show()
            }
            .setNegativeButton("Anuluj") { _, _ ->
            }.create()

        removeDialog.show()
    }

    fun calculateSalary(name: String): Int {
        var totalAmount = 0
        val listOfSalariesForSpecifiedUser = viewModel.getSalaryForSpecifiedUser(name)
        listOfSalariesForSpecifiedUser.forEach { dailySalary ->
                    totalAmount += dailySalary.toInt()
        }
        return totalAmount
    }

    fun amountToBeWithdrawnDialog(salary: Int) {
        val amountDialog = AlertDialog.Builder(this)
            .setTitle("Wypłata")
            .setMessage("Do wypłaty ${salary}zł")
            .setPositiveButton("Ok") { _, _ ->
            }.create()

        amountDialog.show()
    }

}