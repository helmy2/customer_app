package com.example.customeraccounts.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCustomer(customer: Customer)

    @Query("SELECT * FROM customer_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<Customer>>

    @Query("DELETE FROM customer_table")
    suspend fun clear()

    @Delete
    suspend fun deleteCustomer(customer: Customer)

    @Update
    suspend fun update(customer: Customer)


}