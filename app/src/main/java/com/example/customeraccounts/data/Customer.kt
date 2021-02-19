package com.example.customeraccounts.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer_table")
data class Customer(

    @ColumnInfo(name = "name")
    val name: String = " ",

    @ColumnInfo(name = "address")
    val address: String = " ",

    @ColumnInfo(name = "city")
    val city: String = " ",

    @ColumnInfo(name = "state")
    val state: String = " ",

    @ColumnInfo(name = "zip")
    val zip: String = " ",

    @ColumnInfo(name = "telephone_number")
    val telephoneNumber: String = " ",

    @ColumnInfo(name = "account_balance")
    val accountBalance: String = " ",

    @ColumnInfo(name = "data_of_last_payment")
    val dateOfLastPayment: String = " ",

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)