package com.julimole.opticavisual.ui.cart

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.julimole.opticavisual.R
import com.julimole.opticavisual.data.model.Product

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerCart: RecyclerView
    private lateinit var txtTotal: TextView
    private lateinit var btnBuy: Button
    private lateinit var adapter: CartAdapter



    private val cartItems: MutableList<Product> get() = MockCart.items

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        recyclerCart = findViewById(R.id.recyclerCart)
        txtTotal = findViewById(R.id.txtTotal)
        btnBuy = findViewById(R.id.btnBuy)

        recyclerCart.layoutManager = LinearLayoutManager(this)

        adapter = CartAdapter(cartItems) {
            updateTotal()
        }

        recyclerCart.adapter = adapter
        updateTotal()

        btnBuy.setOnClickListener {
            // Acción de compra (puedes añadir lógica real más adelante)
            MockCart.items.clear()
            adapter.notifyDataSetChanged()
            updateTotal()
        }
    }


    private fun updateTotal() {
        val total = cartItems.sumOf { it.price * it.quantity }
        txtTotal.text = "Total: $$total"
    }
}




