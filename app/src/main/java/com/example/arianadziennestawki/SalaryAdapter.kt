package com.example.arianadziennestawki

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.arianadziennestawki.databinding.SalaryItemBinding

class SalaryAdapter(private val listOfSalaries: List<SalaryEntity>): RecyclerView.Adapter<SalaryAdapter.SalaryViewHolder>() {

    private lateinit var binding: SalaryItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalaryViewHolder {
        binding = SalaryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SalaryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SalaryViewHolder, position: Int) {
        holder.itemView.apply {
            binding.tvPersonName.text = listOfSalaries[position].name
            binding.tvSalary.text = listOfSalaries[position].amount + "zł"
            binding.tvDate.text = listOfSalaries[position].date
        }
    }

    override fun getItemCount(): Int {
        return listOfSalaries.size
    }

    inner class SalaryViewHolder(binding: SalaryItemBinding): RecyclerView.ViewHolder(binding.root)
}