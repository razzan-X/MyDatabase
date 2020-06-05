package com.example.mydatabase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mydatabase.database.MyDatabase
import com.example.mydatabase.model.product.Product
import com.example.mydatabase.model.product.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ProductRepository
    val allProducts: LiveData<List<Product>>

    init{
        val productDao = MyDatabase.getDatabase(
            application,
            viewModelScope
        ).productDao()
        repository =
            ProductRepository(productDao)
        allProducts = repository.allProduct
    }

    fun insertProduct(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertProduct(product)
    }
}