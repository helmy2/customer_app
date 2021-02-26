package com.example.customeraccounts.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCustomer(customer: Customer)

    @Query("SELECT * FROM customer_table WHERE name LIKE '%' || :searchQuery || '%' ORDER BY id DESC")
    fun getAllData(searchQuery: String): Flow<List<Customer>>

    @Delete
    suspend fun deleteCustomer(customer: Customer)

    @Update
    suspend fun update(customer: Customer)


}