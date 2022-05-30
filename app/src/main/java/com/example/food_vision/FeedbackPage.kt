package com.example.food_vision

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class FeedbackPage : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feedbackpage)

        val fbBack = findViewById<ImageView>(R.id.fbBack)
        val fbSubmit = findViewById<Button>(R.id.fbBack)
        val fbEmail = findViewById<EditText>(R.id.fbEmail)
        val fbName = findViewById<EditText>(R.id.fbName)
        val fbMulti = findViewById<EditText>(R.id.fbMulti)

        fbBack.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

    }
}