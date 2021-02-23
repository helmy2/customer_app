package com.example.customeraccounts.ui.customers

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.customeraccounts.R
import com.example.customeraccounts.data.Customer
import com.example.customeraccounts.databinding.ItemCustomerBinding

class CustomerAdapter() :
    ListAdapter<Customer, CustomerAdapter.CustomerViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val binding =
            ItemCustomerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    @SuppressLint("ResourceAsColor")
    class CustomerViewHolder(private val binding: ItemCustomerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(customer: Customer) {
            binding.apply {
                name.text = customer.name
                accountBalance.text = customer.accountBalance + "$"

                root.setOnClickListener {
                    val action =
                        CustomerFragmentDirections.actionCustomerFragment2ToDetailsFragment(customer)
                    it.findNavController().navigate(action)
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Customer>() {
        override fun areItemsTheSame(oldItem: Customer, newItem: Customer) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Customer, newItem: Customer) =
            oldItem == newItem
    }
}