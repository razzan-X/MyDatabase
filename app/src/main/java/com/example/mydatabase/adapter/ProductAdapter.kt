package com.example.mydatabase.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydatabase.R
import com.example.mydatabase.model.product.Product
import kotlinx.android.synthetic.main.recycle_product.view.*

class ProductAdapter internal constructor(context: Context) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){
    private var inflater: LayoutInflater =  LayoutInflater.from(context)
    private var product = emptyList<Product>()

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productItemView: TextView = itemView.productPrice
        val productNameView: TextView = itemView.productName
        val productDescriptionView: TextView = itemView.productDesciption
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = inflater.inflate(R.layout.recycle_product, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun getItemCount() = product.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentProduct = product[position]
        holder.productItemView.text = currentProduct.productName
        holder.productNameView.text = currentProduct.productDescription
        holder.productDescriptionView.text = currentProduct.productPrice
    }

    internal fun setProducts(product: List<Product>){
        this.product = product
        notifyDataSetChanged()
    }


}