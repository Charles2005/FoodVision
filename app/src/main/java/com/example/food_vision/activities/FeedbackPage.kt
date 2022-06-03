package com.example.food_vision.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.example.food_vision.R
import com.example.food_vision.databinding.FeedbackpageBinding
import java.net.URL

class FeedbackPage : AppCompatActivity() {
    lateinit var binding: FeedbackpageBinding

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FeedbackpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fbBack.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

       binding.feedbackSubmit.setOnClickListener{
            val email = "foodvision40@gmail.com"
            val subject = "FoodVision application feedback"
            val message = binding.fbMulti.text.toString()

            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:")
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            intent.putExtra(Intent.EXTRA_TEXT, message)

            if(intent.resolveActivity(packageManager) != null){
                startActivity(intent)
            }else{
                Toast.makeText(this, "Required app is not installed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}