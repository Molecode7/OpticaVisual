package com.julimole.opticavisual

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.julimole.opticavisual.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Acción del botón "Registrarse"
        binding.registrarse.setOnClickListener {
            val nombres = binding.nombres.text.toString().trim()
            val apellidos = binding.apellidos.text.toString().trim()
            val correo = binding.correoElectronico.text.toString().trim()
            val contrasena = binding.contrasena.text.toString().trim()

            if (nombres.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Guardar datos simples en SharedPreferences (solo demostración)
            val prefs = getSharedPreferences("user_prefs", MODE_PRIVATE)
            prefs.edit()
                .putString("NOMBRES", nombres)
                .putString("APELLIDOS", apellidos)
                .putString("EMAIL", correo)
                .putString("PASSWORD", contrasena)
                .apply()

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

            // Ir al LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Texto "Inicia sesión"
        binding.iniciaSesion.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
