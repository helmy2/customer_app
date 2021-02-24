package com.example.customeraccounts.data

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
import kotlinx.coroutines.launch

class CustomerViewModel(application: Application) : AndroidViewModel(application) {

    private val customerDao = CustomerDatabase.getDatabase(application).getDao()


    val searchQuery = MutableStateFlow("")
    private val customerFlow = searchQuery.flatMapLatest {
        customerDao.getAllData(it)
    }
    val allData: LiveData<List<Customer>> = customerFlow.asLiveData()


    val customerHasDelete: MutableLiveData<Customer> = MutableLiveData()

    fun addCustomer(customer: Customer) {
        viewModelScope.launch(Dispatchers.IO) {
            customerDao.addCustomer(customer)
        }
    }

    fun clear() {
        viewModelScope.launch(Dispatchers.IO) {
            customerDao.clear()
        }
    }

    fun update(customer: Customer) {
        viewModelScope.launch(Dispatchers.IO) {
            customerDao.update(customer)
        }
    }

    fun deleteCustomer(customer: Customer) {
        viewModelScope.launch(Dispatchers.IO) {
            customerDao.deleteCustomer(customer)
        }
        customerHasDelete.value = customer
    }

    fun confirmDeleteCustomer() {
        customerHasDelete.value = null
    }
}