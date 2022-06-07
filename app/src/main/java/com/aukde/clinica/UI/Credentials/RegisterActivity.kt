package com.aukde.clinica.UI.Credentials

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aukde.clinica.R
import com.aukde.clinica.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnButtonRegistro.setOnClickListener {

            var dni = binding.editTextDni.text.toString()
            var name = binding.editTextNombres.text.toString()
            var surname = binding.editTextApellidos.text.toString()
            var phone =binding.editTextCelular.text.toString()
            var correo = binding.editTextCorreo.text.toString()
            var adress = binding.editTextDireccion.text.toString()
            var pass = binding.editTextContraseAReg.text.toString()
            var confirmPass = binding.editTextConfirmarContraseA.text.toString()

            if (correo.isNotEmpty() && dni.isNotEmpty() && name.isNotEmpty() && surname.isNotEmpty() && phone.isNotEmpty() && adress.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty())
            {
                if (pass == confirmPass){

                    firebaseAuth.createUserWithEmailAndPassword(correo, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, RegisterActivity::class.java)
                            startActivity(intent)

                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else{

                    Toast.makeText(this, "La contraseña no coincide", Toast.LENGTH_SHORT).show()
                }
            } else{

                Toast.makeText(this, "No se permiten campos vacíos", Toast.LENGTH_SHORT).show()
            }
            }
        }
        }


