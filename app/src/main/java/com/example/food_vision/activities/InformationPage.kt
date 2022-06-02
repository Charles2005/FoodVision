package com.example.food_vision.activities

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.food_vision.R

class InformationPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.informationpage)
        //Components
        val intent = getIntent()
        var bitmap = intent.getParcelableExtra<Bitmap>("foodImage")
        val infoBack = findViewById<ImageView>(R.id.nutritionBack)
        val foodImage = findViewById<ImageView>(R.id.foodImage)

        // Setting food image
        foodImage.setImageBitmap(bitmap)

        infoBack.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
    }
}