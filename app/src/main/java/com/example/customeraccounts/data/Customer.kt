package com.example.customeraccounts.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "customer_table")
data class Customer (

    @ColumnInfo(name = "name")
    val name: String = " ",

    @ColumnInfo(name = "address")
    val address: String = " ",

    @ColumnInfo(name = "telephone_number")
    val telephoneNumber: String = " ",

    @ColumnInfo(name = "account_balance")
    val accountBalance: String = " ",

    @ColumnInfo(name = "data_of_last_payment")
    val dateOfLastPayment: String = " ",

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
): Parcelable