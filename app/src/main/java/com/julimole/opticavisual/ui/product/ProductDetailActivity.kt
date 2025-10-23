package com.julimole.opticavisual.ui.product

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.julimole.opticavisual.R
import com.julimole.opticavisual.data.model.Product
import com.julimole.opticavisual.ui.cart.CartActivity
import com.julimole.opticavisual.ui.cart.MockCart

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        // --- Recupera el producto recibido ---
        product = intent.getParcelableExtra("product") ?: return

        // --- Setea los datos del producto ---
        findViewById<ImageView>(R.id.imgDetail).setImageResource(product.imageRes)
        findViewById<TextView>(R.id.txtNameDetail).text = product.name
        findViewById<TextView>(R.id.txtDescDetail).text = product.description
        findViewById<TextView>(R.id.txtPriceDetail).text = "$${product.price}"

        // --- Botón agregar al carrito ---
        val btnAddCart = findViewById<Button>(R.id.btnAddCart)
        btnAddCart.setOnClickListener {
            MockCart.items.add(product)
            Toast.makeText(this, "Agregado al carrito", Toast.LENGTH_SHORT).show()
            finish()
        }

        // --- Barra superior ---
        val btnCart = findViewById<ImageView>(R.id.btnCart)
        val imgLogo = findViewById<ImageView>(R.id.imgLogo)
        val etSearch = findViewById<EditText>(R.id.etSearch)

        // Ir al carrito
        btnCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        // Buscar producto (placeholder por ahora)
        etSearch.setOnEditorActionListener { _, _, _ ->
            Toast.makeText(this, "Buscando: ${etSearch.text}", Toast.LENGTH_SHORT).show()
            true
        }

        // Logo: regresa al home (MainActivity)
        imgLogo.setOnClickListener {
            finish()
        }
    }
}


