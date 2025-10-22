package com.julimole.opticavisual.Cart

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val imageUrl: String
)

data class CartItem(
    val product: Product,
    var quantity: Int = 1
)
