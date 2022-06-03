package com.example.food_vision.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import com.example.food_vision.R

class NewDataPage : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newfoodpage)

        val submitBtn = findViewById<ImageButton>(R.id.submitButton)
        val backBtn = findViewById<ImageView>(R.id.newBack)
        val newFood = findViewById<EditText>(R.id.nameText)
        val newIngredients = findViewById<EditText>(R.id.newIngredients)


        submitBtn.setOnClickListener{
//            val intent = Intent(this, Dashboard::class.java)
//            startActivity(intent)
//            finish()
        }
        backBtn.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}