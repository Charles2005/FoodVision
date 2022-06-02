package com.example.food_vision.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.food_vision.R

class DataPrivacyPage: AppCompatActivity() {

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dataprivacypage)

        val noButton = findViewById<ImageButton>(R.id.noButton)
        val yesButton = findViewById<ImageButton>(R.id.yesButton)
        val currentUserEmail = intent.getStringExtra("email")


        noButton.setOnClickListener{
            Toast.makeText(this,
                "You must agree to the agreement to continue",
                      Toast.LENGTH_SHORT).show()
            onBackPressed()
        }

        yesButton.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            intent.putExtra("currentEmail", currentUserEmail)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }



    }
}