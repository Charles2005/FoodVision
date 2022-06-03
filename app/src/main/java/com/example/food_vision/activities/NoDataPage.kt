package com.example.food_vision.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.food_vision.R

class NoDataPage : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nodatapage)

        val leaveBtn = findViewById<ImageButton>(R.id.leaveButton)
        val sureBtn = findViewById<ImageButton>(R.id.sureButton)

        leaveBtn.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        sureBtn.setOnClickListener{
            val intent = Intent(this, NewDataPage::class.java)
            startActivity(intent)
            finish()
        }
    }
}