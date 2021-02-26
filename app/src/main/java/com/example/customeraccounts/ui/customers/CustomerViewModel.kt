package com.example.customeraccounts.ui.customers

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.customeraccounts.data.Customer
import com.example.customeraccounts.data.CustomerDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import androidx.lifecycle.asLiveData
import com.example.customeraccounts.repository.CustomerRepository
import kotlinx.coroutines.launch

class CustomerViewModel(application: Application) : AndroidViewModel(application) {

    private val customerDao = CustomerDatabase.getDatabase(application).getDao()
    private val repository = CustomerRepository(customerDao)

    val searchQuery = MutableStateFlow("")
    private val customerFlow = searchQuery.flatMapLatest {
        repository.allData(it)
    }
    val allData: LiveData<List<Customer>> = customerFlow.asLiveData()


    val customerHasDelete: MutableLiveData<Customer> = MutableLiveData()

    fun addCustomer(customer: Customer) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCustomer(customer)
        }
    }

    fun deleteCustomer(customer: Customer) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCustomer(customer)
        }
        customerHasDelete.value = customer
    }

    fun confirmDeleteCustomer() {
        customerHasDelete.value = null
    }
}