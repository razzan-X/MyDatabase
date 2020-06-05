package com.example.mydatabase.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mydatabase.R
import com.example.mydatabase.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {
    private lateinit var productViewModel: ProductViewModel
    companion object{
        const val PRODUCT_PRICE = "productCode"
        const val PRODUCT_NAME = "productName"
        const val PRODUCT_DESCRIPTION = "productDescription"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        productViewModel.allProducts.observe(this, Observer{ products ->
            //this where new products changes happens
            Log.d("keyTrack", "print")

            for (product in products){
                Log.d("PRODUCT", "${product.productId} & is ${product.productName}")
            }
        })
        add_product.setOnClickListener {
            val addProduct = Intent()
            if (TextUtils.isEmpty(product_price.text) || TextUtils.isEmpty(product_Description.text) || TextUtils.isEmpty(product_name.text)){
                setResult(Activity.RESULT_CANCELED, addProduct)
                } else {
                addProduct.putExtra(PRODUCT_PRICE, product_price.text.toString())
                addProduct.putExtra(PRODUCT_NAME, product_name.text.toString())
                addProduct.putExtra(PRODUCT_DESCRIPTION, product_Description.text.toString())
                setResult(Activity.RESULT_OK, addProduct)
            }
            finish()
        }
    }
}
