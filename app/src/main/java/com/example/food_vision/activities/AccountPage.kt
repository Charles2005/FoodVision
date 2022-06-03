package com.example.food_vision.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.example.food_vision.R
import com.example.food_vision.database.DatabaseHandler
import com.example.food_vision.database.UserModelClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AccountPage : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.accountpage)

        auth = Firebase.auth
        var db = DatabaseHandler(this)
        val currentUser = auth.currentUser

        val restriction = db.getUser(currentUser?.email.toString())
        val user = UserModelClass()
        val nutrientRestriction = findViewById<EditText>(R.id.nutrientRestriction)
        val accountBack = findViewById<ImageView>(R.id.accountBack)
        val logoutBtn = findViewById<ImageButton>(R.id.logoutButton)
        val saveBtn = findViewById<ImageButton>(R.id.saveButton)

        nutrientRestriction.setText(restriction)

        accountBack.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        logoutBtn.setOnClickListener{
            auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
            Toast.makeText(this,"You have successfully logged out.",Toast.LENGTH_SHORT)
        }

        saveBtn.setOnClickListener {
            nutrientRestriction.setText(nutrientRestriction.text.toString())
            user.restriction = nutrientRestriction.text.toString()
            user.id  = 1
            db.updateUser(user)
            Toast.makeText(this, "Restriction has been saved", Toast.LENGTH_SHORT).show()

        }


    }
}