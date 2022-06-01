package com.example.food_vision.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import com.example.food_vision.R
import com.example.food_vision.database.DatabaseHandler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth
    var dbHandler: DatabaseHandler? = null

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Firabase
        auth = Firebase.auth

        // Components
        val registerButton = findViewById<ImageButton>(R.id.registerButton)
        val adminBtn = findViewById<ImageButton>(R.id.adminButton)
        val loginBtn = findViewById<ImageButton>(R.id.loginButton)

        val emailText = findViewById<EditText>(R.id.emailTextBox)
        val passwordText = findViewById<EditText>(R.id.passwordTextBox)

        // Database
        dbHandler = DatabaseHandler(this)

        registerButton.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        adminBtn.setOnClickListener{
            val intent = Intent(this, AdminLogin::class.java)
            startActivity(intent)
        }

        loginBtn.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

    }
}