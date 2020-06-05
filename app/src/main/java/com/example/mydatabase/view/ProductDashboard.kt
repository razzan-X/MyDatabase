package com.example.mydatabase.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydatabase.R
import com.example.mydatabase.adapter.ProductAdapter
import com.example.mydatabase.model.product.Product
import com.example.mydatabase.view.ProductActivity.Companion.PRODUCT_DESCRIPTION
import com.example.mydatabase.view.ProductActivity.Companion.PRODUCT_NAME
import com.example.mydatabase.view.ProductActivity.Companion.PRODUCT_PRICE
import com.example.mydatabase.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.activity_product_dashboard.*

class ProductDashboard : AppCompatActivity() {
    companion object{
        const val productDisplay = 1
    }
    private lateinit var productViewModel: ProductViewModel
    private lateinit var productName: String
    private lateinit var productDescription: String
    private lateinit var productPrice: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_dashboard)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_product)
        val adapter = ProductAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

       productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        productViewModel.allProducts.observe(this, Observer { products ->
            products?.let{ adapter.setProducts(it)}
        })
        addProduct.setOnClickListener {
            val intent = Intent(this@ProductDashboard, ProductActivity::class.java)
            startActivityForResult(intent, productDisplay)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== productDisplay && resultCode== Activity.RESULT_OK){
            data?.getStringExtra(PRODUCT_NAME)?.let {
                productName = it
            }
            data?.getStringExtra(PRODUCT_PRICE)?.let {
                productPrice = it
            }
            data?.getStringExtra(PRODUCT_DESCRIPTION)?.let {
                productDescription = it
            }
            val product = Product(productName, productDescription, productPrice)
            productViewModel.insertProduct(product)
        }
        else {
            Toast.makeText(
                applicationContext,
                "Product Field Empty",
                Toast.LENGTH_LONG).show()
        }

    }
}
