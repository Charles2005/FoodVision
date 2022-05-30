package com.example.food_vision

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class AdminLogin : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adminlogin)

        val adminBack = findViewById<ImageView>(R.id.adminBack)
        val adminLogin = findViewById<Button>(R.id.adminLogin)

        val adminEmail = findViewById<EditText>(R.id.adminEmail)
        val adminPassword = findViewById<EditText>(R.id.adminPassword)

        adminBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}