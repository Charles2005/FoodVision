package com.example.food_vision.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.example.food_vision.R
import com.example.food_vision.database.DatabaseHandler
import com.example.food_vision.database.UserModelClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Register : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    // Initializing Firebase Authentication
    private lateinit var auth: FirebaseAuth
    // Initializing Database
    var dbHandler:DatabaseHandler ? = null

    // String for Spinner
    var restriction: String = ""

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
        val spinner = findViewById<Spinner>(R.id.spinner2)
        // Adapter for spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.nutrients,
            android.R.layout.simple_spinner_item
        ).also {adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = this
        }

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
                    // Components
                    var success : Boolean = false
                    val user: UserModelClass = UserModelClass()
                    user.email = emailReg.text.toString().trim{it <= ' '}
                    user.password = passwordReg.text.toString().trim{it <= ' '}
                    user.restriction = restriction
                    val rePassword: String = reEnterPassword.text.toString().trim{it <= ' '}
                    // Firebase Instance
                    // Check if the password and the reenter password is match
                    // Check if the email is already in the database
                    if (user.password.equals(rePassword)){
                        // Creating User with Firebase
                        auth.createUserWithEmailAndPassword(user.email, user.password)
                            .addOnCompleteListener(this){ task ->
                                if(task.isSuccessful){
                                    //success = dbHandler?.addUser(user) as Boolean
                                    // Sending Email Verification
                                    val firebaseUser = Firebase.auth.currentUser
                                    firebaseUser!!.sendEmailVerification()
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                Toast.makeText(this,
                                                    "Sending E-mail verification, Please verify your E-mail",
                                                    Toast.LENGTH_SHORT).show()
                                                emailReg.setText("")
                                                passwordReg.setText("")

                                                val intent = Intent(this, MainActivity::class.java)
                                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                                startActivity(intent)
                                                finish()
                                            }else{
                                                Toast.makeText(this, task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                }else{
                                    Toast.makeText(this, task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                                }

                            }


                    }else{
                        Toast.makeText(this@Register, "Password is not match", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        restriction = parent?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}