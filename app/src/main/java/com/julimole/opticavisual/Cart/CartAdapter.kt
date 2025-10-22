package com.julimole.opticavisual.Cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.julimole.opticavisual.R


class CartAdapter(
    private var items: List<CartItem>,
    private val onQuantityChanged: (Int, Int) -> Unit,
    private val onRemoveItem: (Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImage: ImageView = view.findViewById(R.id.productImage)
        val productName: TextView = view.findViewById(R.id.productName)
        val productPrice: TextView = view.findViewById(R.id.productPrice)
        val quantity: TextView = view.findViewById(R.id.quantity)
        val btnDecrease: ImageButton = view.findViewById(R.id.btnDecrease)
        val btnIncrease: ImageButton = view.findViewById(R.id.btnIncrease)
        val btnRemove: ImageButton = view.findViewById(R.id.btnRemove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]

        holder.productName.text = item.product.name
        holder.productPrice.text = "$${item.product.price}"
        holder.quantity.text = item.quantity.toString()

        Glide.with(holder.itemView.context)
            .load(item.product.imageUrl)
            .into(holder.productImage)

        holder.btnIncrease.setOnClickListener {
            onQuantityChanged(item.product.id, item.quantity + 1)
        }

        holder.btnDecrease.setOnClickListener {
            if (item.quantity > 1) {
                onQuantityChanged(item.product.id, item.quantity - 1)
            }
        }

        holder.btnRemove.setOnClickListener {
            onRemoveItem(item.product.id)
        }
    }

    override fun getItemCount() = items.size

    fun updateItems(newItems: List<CartItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}