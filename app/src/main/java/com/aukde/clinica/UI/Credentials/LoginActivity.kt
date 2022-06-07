package com.aukde.clinica.UI.Credentials

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.ui.setupActionBarWithNavController
import com.aukde.clinica.MainActivity
import com.aukde.clinica.R
import com.aukde.clinica.UI.Menu.MenuActivity
import com.aukde.clinica.databinding.ActivityLoginBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthActionCodeException

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.txtRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btnButtonIngresar.setOnClickListener {
            var correo = binding.editTextUsuario.text.toString()
            var pass = binding.editTextContraseA.text.toString()

            if (correo.isNotEmpty() &&  pass.isNotEmpty())
            {
                    firebaseAuth.signInWithEmailAndPassword(correo, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, MenuActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
            } else{

                Toast.makeText(this, "No se permiten campos vacíos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
