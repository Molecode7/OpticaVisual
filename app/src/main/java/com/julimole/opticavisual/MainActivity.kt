package com.julimole.opticavisual

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.julimole.opticavisual.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Usamos ViewBinding como en tus otras Activities
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mensaje o acción de ejemplo
        binding.textViewWelcome.text = "Bienvenido a Óptica Visual 👓"
    }
}
