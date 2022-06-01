package com.example.food_vision.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
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

class Register : AppCompatActivity() {

    // Initializing Firebase Authentication
    private lateinit var auth: FirebaseAuth
    var dbHandler:DatabaseHandler ? = null

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registerpage)
        // Firebase
        auth = Firebase.auth
        // Components
        val regBack = findViewById<ImageView>(R.id.adminBack)
        val registerBtn = findViewById<ImageButton>(R.id.adminLogin)
        val emailReg = findViewById<EditText>(R.id.emailReg)
        val passwordReg = findViewById<EditText>(R.id.adminPassword)
        val reEnterPassword = findViewById<EditText>(R.id.reenterPassword)

        // Database
        dbHandler = DatabaseHandler(this)

        regBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        registerBtn.setOnClickListener {
            when{
                TextUtils.isEmpty(emailReg.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(
                        this@Register,
                        "Please enter an E-mail.",
                        Toast.LENGTH_LONG
                    ).show()
                }
                TextUtils.isEmpty(passwordReg.text.toString().trim{it <= ' '}) -> {
                    Toast.makeText(
                        this@Register,
                        "Please enter a password.",
                        Toast.LENGTH_LONG
                    ).show()
                }
                TextUtils.isEmpty(reEnterPassword.text.toString().trim{it <= ' '}) -> {
                    Toast.makeText(
                        this@Register,
                        "Please re-enter your password.",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    var success : Boolean = false
                    val user: UserModelClass = UserModelClass()
                    user.email = emailReg.text.toString().trim{it <= ' '}
                    user.password = passwordReg.text.toString().trim{it <= ' '}
                    val rePassword: String = reEnterPassword.text.toString().trim{it <= ' '}

                    // Firebase Instance
                    if (user.password.equals(rePassword)){
                        //auth.createUserWithEmailAndPassword(email, password)
                        Toast.makeText(this@Register, "Equal", Toast.LENGTH_SHORT).show()
                        //success = dbHandler?.addUser(user) as Boolean

                    }else{
                        Toast.makeText(this@Register, "Not Equal", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}