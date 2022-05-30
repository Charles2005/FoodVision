package com.example.food_vision

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val registerButton = findViewById<ImageButton>(R.id.registerButton)
        val adminBtn = findViewById<ImageButton>(R.id.adminButton)
        val loginBtn = findViewById<ImageButton>(R.id.loginButton)

        val emailText = findViewById<EditText>(R.id.emailTextBox)
        val passwordText = findViewById<EditText>(R.id.passwordTextBox)

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