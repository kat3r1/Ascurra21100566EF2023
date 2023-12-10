package com.example.ascurra21100566ef2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()
    var auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val txtEmail: EditText = findViewById(R.id.txtEmail)
        val txtPassword: EditText = findViewById(R.id.txtPassword)
        val btnLogin: Button = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val Email = txtEmail.text.toString()
            val Password = txtPassword.text.toString()

        }
    }
            private fun authenticateUser(Email: String, Password: String) {
            auth.signInWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Snackbar
                            .make(
                                findViewById(android.R.id.content),
                                "Ingreso exitoso",
                                Snackbar.LENGTH_LONG
                            ).show()
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        Snackbar
                            .make(
                                findViewById(android.R.id.content),
                                "Usuario y/o Email no existe",
                                Snackbar.LENGTH_LONG
                            ).show()
                    }
                }
        }

    private fun checkUserTypeAndRedirect() {
        val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid

        if (currentUserUid != null) {
            Log.d("Firestore", "UID del usuario actual: $currentUserUid")

            // Colecciones en Firestore
            val userCollection = FirebaseFirestore.getInstance().collection("users")

            // Obtener el documento del usuario en la colección de usuarios
            userCollection.document(currentUserUid).get()
                .addOnSuccessListener { userDocument ->
                    if (userDocument.exists()) {
                        Log.d("Firestore", "Usuario encontrado en la colección de usuarios.")
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Log.d("Firestore", "Usuario NO encontrado en la colección de usuarios. Verificando en la colección de restaurantes.")
                        // Usuario no encontrado en la colección de usuarios

                            }

                  }
                .addOnFailureListener { userException ->
                    Log.e("Firestore", "Error al obtener el documento de usuario: $userException")
                }
        }
    }

    public override fun onStart() {
        super.onStart()
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val currentUser = auth.currentUser
            val currentUserId = currentUser?.uid
            checkUserTypeAndRedirect()
        }else{

        }
    }


    }

