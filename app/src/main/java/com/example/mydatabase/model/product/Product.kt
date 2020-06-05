package com.example.mydatabase.model.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "product")
data class Product(
    @ColumnInfo(name = "product_name") val productName: String,
    @ColumnInfo(name = "product_price") val productPrice: String,
    @ColumnInfo(name = "product_description") val productDescription: String
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_id")
    var productId: Int = 0



}