package com.example.food_vision.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.food_vision.R

class DataPrivacyPage: AppCompatActivity() {
    val prevStarted = "yes"

    override fun onResume() {
        super.onResume()
        var sharedPreferences =
            getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE)
        if (!sharedPreferences.getBoolean(prevStarted, false)){
            var editor = sharedPreferences.edit()
            editor.putBoolean(prevStarted, true)
            editor.apply()
        }else{
            moveToSecondary()
        }
    }

    private fun moveToSecondary() {
        val intent = Intent(this, Dashboard::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

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
            val intent = Intent(this, MainActivity::class.java)
        }

        yesButton.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }



    }
}