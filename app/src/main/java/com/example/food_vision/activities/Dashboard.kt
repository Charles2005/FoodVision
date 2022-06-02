package com.example.food_vision.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import com.example.food_vision.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Dashboard : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        // Intent value
        val currentUserEmail = intent.getStringExtra("currentEmail")

        // Components
        val accountBtn = findViewById<ImageView>(R.id.accountButton)
        val nutInq = findViewById<ImageButton>(R.id.inquiryButton)
        val feedbackBtn = findViewById<ImageButton>(R.id.inquiryButton2)

        accountBtn.setOnClickListener{
            val intent = Intent(this, AccountPage::class.java)
            startActivity(intent)
        }

        nutInq.setOnClickListener{
            val intent = Intent(this, NutritionalInquiry::class.java)
            startActivity(intent)
        }

        feedbackBtn.setOnClickListener{
            val intent = Intent(this, FeedbackPage::class.java)
            startActivity(intent)
        }
    }
}