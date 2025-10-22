package com.julimole.opticavisual.Cart

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.julimole.opticavisual.R


class CartActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CartAdapter
    private lateinit var txtTotal: TextView
    private lateinit var txtItemCount: TextView
    private lateinit var btnCheckout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        supportActionBar?.title = "Mi Carrito"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
        setupRecyclerView()
        updateUI()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerViewCart)
        txtTotal = findViewById(R.id.txtTotal)
        txtItemCount = findViewById(R.id.txtItemCount)
        btnCheckout = findViewById(R.id.btnCheckout)

        btnCheckout.setOnClickListener {
            // Proceso de pago
            checkout()
        }
    }

    private fun setupRecyclerView() {
        adapter = CartAdapter(
            CartManager.getItems(),
            onQuantityChanged = { productId, newQuantity ->
                CartManager.updateQuantity(productId, newQuantity)
                updateUI()
            },
            onRemoveItem = { productId ->
                CartManager.removeItem(productId)
                updateUI()
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun updateUI() {
        adapter.updateItems(CartManager.getItems())
        txtTotal.text = "$${String.format("%.2f", CartManager.getTotal())}"
        txtItemCount.text = "${CartManager.getItemCount()} items"
        btnCheckout.isEnabled = CartManager.getItemCount() > 0
    }

    private fun checkout() {
        android.widget.Toast.makeText(
            this,
            "Total a pagar: $${String.format("%.2f", CartManager.getTotal())}",
            android.widget.Toast.LENGTH_LONG
        ).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}