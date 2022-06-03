package com.example.food_vision.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.example.food_vision.R

class AdminLogin : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adminlogin)

        val adminBack = findViewById<ImageView>(R.id.adminBack)
        val adminLogin = findViewById<ImageButton>(R.id.adminLogin)

        val adminEmail = findViewById<EditText>(R.id.adminEmail)
        val adminPassword = findViewById<EditText>(R.id.adminPassword)

        adminBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        adminLogin.setOnClickListener {
            if (adminEmail.text.toString().equals("foodvision@gmail.com") &&
                adminPassword.text.toString().equals("foodvision00")) {
                val intent = Intent(this, AdminDashboard::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "Wrong Credentials", Toast.LENGTH_SHORT).show()
            }
        }

    }
}