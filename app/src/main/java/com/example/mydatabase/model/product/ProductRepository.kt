package com.example.mydatabase.model.product

import androidx.lifecycle.LiveData
import com.example.mydatabase.model.product.Product
import com.example.mydatabase.model.product.ProductDao

class ProductRepository(private val productDao: ProductDao) {
    val allProduct: LiveData<List<Product>> = productDao.getAllProducts()

    fun insertProduct(product: Product){
        productDao.insertProduct(product)
    }
}