package com.example.food_vision

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class Register : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registerpage)

        val regBack = findViewById<ImageView>(R.id.adminBack)
        val registerBtn = findViewById<Button>(R.id.registerButton)

        val emailReg = findViewById<EditText>(R.id.emailReg)
        val passwordReg = findViewById<EditText>(R.id.adminPassword)
        val reEnterPassword = findViewById<EditText>(R.id.reenterPassword)

        regBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}