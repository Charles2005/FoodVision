package com.example.food_vision.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.food_vision.R
import com.example.food_vision.database.DatabaseHandler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth
    var dbHandler: DatabaseHandler? = null

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Firabase
        auth = Firebase.auth
        val currentUser = auth.currentUser

        // Components
        val registerButton = findViewById<ImageButton>(R.id.registerButton)
        val adminBtn = findViewById<ImageButton>(R.id.adminButton)
        val loginBtn = findViewById<ImageButton>(R.id.loginButton)

        val emailText = findViewById<EditText>(R.id.emailTextBox)
        val passwordText = findViewById<EditText>(R.id.passwordTextBox)

        // Database
        dbHandler = DatabaseHandler(this)

        if(currentUser != null && currentUser.isEmailVerified()){
            startActivity(Intent(this, Dashboard::class.java))
        }

        registerButton.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        adminBtn.setOnClickListener{
            val intent = Intent(this, AdminLogin::class.java)
            startActivity(intent)
        }

        loginBtn.setOnClickListener{
            when{
                TextUtils.isEmpty(emailText.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Please enter an E-mail.",
                        Toast.LENGTH_LONG
                    ).show()
                }
                TextUtils.isEmpty(passwordText.text.toString().trim{it <= ' '}) -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Please enter a password.",
                        Toast.LENGTH_LONG
                    ).show()
                } else -> {
                    auth.signInWithEmailAndPassword(emailText.text.toString(),
                        passwordText.text.toString())
                        .addOnCompleteListener(this){ task ->
                                if(task.isSuccessful){
                                    if(auth.currentUser?.isEmailVerified()!!){
                                        Toast.makeText(
                                            this,
                                        "Logged-in successfully",
                                        Toast.LENGTH_SHORT).show()

                                        // Directing it to the data privacy
                                        val intent = Intent(this, DataPrivacyPage::class.java)
                                        intent.putExtra("email", emailText.toString())
                                        startActivity(intent)
                                        finish()
                                    }else{
                                        Toast.makeText(this, task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                                    }

                                }else{
                                    Toast.makeText(this, task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                                }
                        }
                }
            }

        }

    }
}