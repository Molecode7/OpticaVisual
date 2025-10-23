package com.julimole.opticavisual

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.julimole.opticavisual.data.model.Product
import com.julimole.opticavisual.databinding.ActivityMainBinding
import com.julimole.opticavisual.databinding.IncludeTopbarBinding
import com.julimole.opticavisual.ui.cart.CartActivity
import com.julimole.opticavisual.ui.product.ProductAdapter
import com.julimole.opticavisual.ui.product.ProductDetailActivity
import androidx.activity.addCallback

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var topbarBinding: IncludeTopbarBinding
    private lateinit var adapter: ProductAdapter
    private val productList = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ Binding del include (sin findViewById ni bind(...))
        topbarBinding = binding.topBar

        // ---------- Topbar ----------
        topbarBinding.btnMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        topbarBinding.btnCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
        topbarBinding.imgLogo.setOnClickListener {
            topbarBinding.etSearch.setText("")
            setAdapter(productList)
        }

        // ---------- Recycler ----------
        binding.recyclerProducts.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerProducts.addItemDecoration(UniformSpaceDecoration(dp(8)))

        // Datos de ejemplo
        productList.addAll(
            listOf(
                Product("Gafas de Sol Cuadradas", 12000, "Gafas modernas con protección UV400", R.drawable.ic_glasses),
                Product("Gafas Redondas",       15000, "Diseño retro con estilo único",       R.drawable.ic_glasses),
                Product("Lentes Deportivos",    18000, "Resistentes y cómodos",                R.drawable.ic_glasses),
                Product("Lentes Classic",       20000, "Armazón liviano para uso diario",      R.drawable.ic_glasses)
            )
        )
        setAdapter(productList)

        // Búsqueda en vivo
        topbarBinding.etSearch.addTextChangedListener { s ->
            val q = s?.toString()?.trim().orEmpty()
            val filtered = if (q.isEmpty()) productList
            else productList.filter { it.name.contains(q, ignoreCase = true) }
            setAdapter(filtered)
        }

        // ---------- Drawer ----------
        binding.navView.setNavigationItemSelectedListener(navListener)

        onBackPressedDispatcher.addCallback(this) {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                finish()
            }
        }
    }


    // Manejo de clicks del menú lateral (drawer)
    private val navListener = NavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_inicio -> {
                topbarBinding.etSearch.setText("")
                setAdapter(productList)
            }
            R.id.nav_agendar -> {
                Toast.makeText(this, "Agendar Cita (pendiente)", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_favoritos -> {
                Toast.makeText(this, "Mis Favoritos (pendiente)", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_productos -> {
                binding.recyclerProducts.scrollToPosition(0)
            }
            R.id.nav_tiendas -> {
                Toast.makeText(this, "Tiendas (pendiente)", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_cerrar_sesion -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        true
    }

    private fun setAdapter(items: List<Product>) {
        adapter = ProductAdapter(items) { product ->
            startActivity(
                Intent(this, ProductDetailActivity::class.java).putExtra("product", product)
            )
        }
        binding.recyclerProducts.adapter = adapter
    }

    // Espaciado uniforme para el grid
    private class UniformSpaceDecoration(private val spacePx: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.set(spacePx, spacePx, spacePx, spacePx)
        }
    }

    private fun dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()


}
