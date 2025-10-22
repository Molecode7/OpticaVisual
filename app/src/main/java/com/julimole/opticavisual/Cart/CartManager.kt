package com.julimole.opticavisual.Cart

object CartManager {
    private val cartItems = mutableListOf<CartItem>()

    fun addItem(product: Product) {
        val existingItem = cartItems.find { it.product.id == product.id }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            cartItems.add(CartItem(product, 1))
        }
    }

    fun removeItem(productId: Int) {
        cartItems.removeAll { it.product.id == productId }
    }

    fun updateQuantity(productId: Int, quantity: Int) {
        cartItems.find { it.product.id == productId }?.quantity = quantity
    }

    fun getItems(): List<CartItem> = cartItems.toList()

    fun getTotal(): Double = cartItems.sumOf { it.product.price * it.quantity }

    fun getItemCount(): Int = cartItems.sumOf { it.quantity }

    fun clear() {
        cartItems.clear()
    }
}
