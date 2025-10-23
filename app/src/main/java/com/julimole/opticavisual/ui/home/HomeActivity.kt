package com.julimole.opticavisual.ui.home


import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.julimole.opticavisual.R
import com.julimole.opticavisual.data.model.Product
import com.julimole.opticavisual.ui.product.ProductDetailActivity
import com.julimole.opticavisual.ui.product.ProductAdapter
import com.julimole.opticavisual.ui.cart.CartActivity


class HomeActivity : AppCompatActivity() {

    private lateinit var recyclerProducts: RecyclerView
    private lateinit var adapter: ProductAdapter
    private val productList = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerProducts = findViewById(R.id.recyclerProducts)
        recyclerProducts.layoutManager = GridLayoutManager(this, 2)

        // Productos de ejemplo
        productList.addAll(
            listOf(
                Product("Gafas de Sol Cuadradas", 12000, "Gafas modernas con protección UV400", R.drawable.ic_glasses),
                Product("Gafas Redondas", 15000, "Diseño retro con estilo único", R.drawable.ic_glasses)
            )
        )

        adapter = ProductAdapter(productList) { product ->
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("product", product)
            startActivity(intent)
        }

        recyclerProducts.adapter = adapter

        // 🔹 Evento del botón del carrito
        val btnCart = findViewById<ImageView>(R.id.btnCart)
        btnCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }
}

