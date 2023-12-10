package com.example.ascurra21100566ef2023.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CustomerDao {
    @Insert
    fun insert(ProductEntity: CustomerEntity)
    @Update
    fun update(customerEntity: CustomerEntity)
    @Delete
    fun delete(customerEntity: CustomerEntity)
    @Query("SELECT * FROM customer ORDER BY last_name")
    fun getCustomerOrderByLastName():LiveData<List<CustomerEntity>>
}