package com.example.food_vision.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.food_vision.R

class AdminDashboard : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admindashboard)

        val adminLogout = findViewById<ImageButton>(R.id.adminLogout)
        val foodDatabase = findViewById<ImageButton>(R.id.fdButton)
        val verificationPage = findViewById<ImageButton>(R.id.verifyButton)

        adminLogout.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        foodDatabase.setOnClickListener {
            val intent = Intent(this, FoodDatabaseView::class.java)
            startActivity(intent)
        }
        verificationPage.setOnClickListener {
            val intent = Intent(this, VerificationCard::class.java)
            startActivity(intent)
        }
    }
}