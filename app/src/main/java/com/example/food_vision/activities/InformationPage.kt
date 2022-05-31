package com.example.food_vision.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.food_vision.R

class InformationPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.informationpage)

        val infoBack = findViewById<ImageView>(R.id.nutritionBack)
        val foodImage = findViewById<ImageView>(R.id.foodImage)

        infoBack.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
    }
}