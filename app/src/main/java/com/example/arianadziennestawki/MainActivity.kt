package com.example.arianadziennestawki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.arianadziennestawki.databinding.ActivityMainBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: SalaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application)
            .create(SalaryViewModel::class.java)

        binding.btnAdd.setOnClickListener {
            val personName = binding.etPersonName.text.toString()
            val amount = binding.etSalary.text.toString()
            val date = LocalDate.now()
            val formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            val salary = SalaryEntity(personName, amount, "${formattedDate}r.")
            viewModel.insertSalary(salary)
            Toast.makeText(this, "Dodaję nowy rekord. $personName zarobił dzisiaj $amount zł.", Toast.LENGTH_LONG).show()
        }

        binding.btnShowRecords.setOnClickListener {
            startActivity(
                Intent(this, RecordsActivity::class.java)
            )
        }
    }
}