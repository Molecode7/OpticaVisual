package com.julimole.opticavisual.ui.product

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.julimole.opticavisual.R
import com.julimole.opticavisual.data.model.Product
import com.julimole.opticavisual.ui.cart.MockCart

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        product = intent.getParcelableExtra("product") ?: return

        findViewById<ImageView>(R.id.imgDetail).setImageResource(product.imageRes)
        findViewById<TextView>(R.id.txtNameDetail).text = product.name
        findViewById<TextView>(R.id.txtDescDetail).text = product.description
        findViewById<TextView>(R.id.txtPriceDetail).text = "$${product.price}"

        val btnAddCart = findViewById<Button>(R.id.btnAddCart)
        btnAddCart.setOnClickListener {
            MockCart.items.add(product)
            finish() // Regresa al Home
        }
    }
}

