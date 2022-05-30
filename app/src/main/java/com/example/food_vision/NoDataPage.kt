package com.example.food_vision

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class NoDataPage : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nodatapage)

        val leaveBtn = findViewById<ImageButton>(R.id.leaveButton)
        val sureBtn = findViewById<ImageButton>(R.id.sureButton)

        leaveBtn.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        sureBtn.setOnClickListener{
            val intent = Intent(this, NewDataPage::class.java)
            startActivity(intent)
        }
    }
}