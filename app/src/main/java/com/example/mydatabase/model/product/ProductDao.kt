package com.example.mydatabase.model.product

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {
    @Query("SELECT * FROM product ORDER BY product_id DESC")
    fun getAllProducts(): LiveData<List<Product>>

    @Insert
    fun insertProduct(product: Product)

    @Query("DELETE FROM product")
    suspend fun deleteAll()
}
