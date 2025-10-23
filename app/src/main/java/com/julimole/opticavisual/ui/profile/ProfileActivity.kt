package com.julimole.opticavisual.ui.profile


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.julimole.opticavisual.R
import com.julimole.opticavisual.data.model.User

class ProfileActivity : AppCompatActivity() {

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val name = findViewById<EditText>(R.id.etName)
        val email = findViewById<EditText>(R.id.etEmail)
        val btnSave = findViewById<Button>(R.id.btnSave)

        user = User(1, "Pedro Perez", "pedro@correo.com", "1234")

        name.setText(user.name)
        email.setText(user.email)

        btnSave.setOnClickListener {
            user.name = name.text.toString()
            user.email = email.text.toString()
        }
    }
}
