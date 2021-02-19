package com.example.customeraccounts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.customeraccounts.data.Customer
import com.example.customeraccounts.ui.customers.CustomerViewModel
import com.example.customeraccounts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val customerViewModel: CustomerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // customerViewModel =ViewModelProvider(this).get(CustomerViewModel::class.java)
//
        for (i in 1..9){
        customerViewModel.addCustomer(Customer("$i",id = i))
        }


        customerViewModel.allData.observe(this, Observer { user ->
            binding.textView.text = user.size.toString()
        })
    }
}