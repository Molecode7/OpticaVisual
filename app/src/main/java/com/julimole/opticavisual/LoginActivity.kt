package com.julimole.opticavisual

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.julimole.opticavisual.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializamos ViewBinding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Acción del botón “Iniciar Sesión”
        binding.botoniniciarsesion.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            val pass = binding.editTextTextPassword.text.toString().trim()

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa tus datos", Toast.LENGTH_SHORT).show()
            } else {
                // Aquí podrías validar contra SharedPreferences o base de datos
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        // Acción del botón “Regístrate”
        binding.botonregistrate.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            // Si quieres poder regresar con "Atrás", comenta la línea de abajo
            finish()
        }
    }
}
