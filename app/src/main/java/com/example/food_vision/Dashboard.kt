package com.example.food_vision

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        val accountBtn = findViewById<ImageView>(R.id.accountButton)
        val nutInq = findViewById<ImageView>(R.id.inquiryButton)
        val feedbackBtn = findViewById<ImageView>(R.id.fdButton)

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