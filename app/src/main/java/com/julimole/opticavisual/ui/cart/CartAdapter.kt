package com.julimole.opticavisual.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.julimole.opticavisual.R
import com.julimole.opticavisual.data.model.Product

class CartAdapter(
    private val cartItems: MutableList<Product>,
    private val onQuantityChanged: (Double) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgCartProduct: ImageView = itemView.findViewById(R.id.imgCartProduct)
        val txtCartName: TextView = itemView.findViewById(R.id.txtCartName)
        val txtCartPrice: TextView = itemView.findViewById(R.id.txtCartPrice)
        val txtQuantity: TextView = itemView.findViewById(R.id.txtQuantity)
        val btnPlus: ImageButton = itemView.findViewById(R.id.btnPlus)
        val btnMinus: ImageButton = itemView.findViewById(R.id.btnMinus)
        val btnRemove: Button = itemView.findViewById(R.id.btnRemove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = cartItems[position]

        // 🔹 CORREGIDO: campo real imageResId
        holder.imgCartProduct.setImageResource(product.imageRes)
        holder.txtCartName.text = product.name
        holder.txtCartPrice.text = "$${product.price}"
        holder.txtQuantity.text = product.quantity.toString()

        holder.btnPlus.setOnClickListener {
            product.quantity++
            holder.txtQuantity.text = product.quantity.toString()
            onQuantityChanged(getTotal())
        }

        holder.btnMinus.setOnClickListener {
            if (product.quantity > 1) {
                product.quantity--
                holder.txtQuantity.text = product.quantity.toString()
                onQuantityChanged(getTotal())
            }
        }

        holder.btnRemove.setOnClickListener {
            val currentPosition = holder.bindingAdapterPosition
            if (currentPosition != RecyclerView.NO_POSITION && currentPosition < cartItems.size) {
                cartItems.removeAt(currentPosition)
                notifyItemRemoved(currentPosition)
                onQuantityChanged(getTotal())
            }
        }

    }

    override fun getItemCount(): Int = cartItems.size

    // 🔹 CORREGIDO: conversión a Double
    private fun getTotal(): Double {
        return cartItems.sumOf { it.price.toDouble() * it.quantity.toDouble() }
    }
}


