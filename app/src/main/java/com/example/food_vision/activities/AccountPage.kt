package com.example.food_vision.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.food_vision.R
import com.example.food_vision.database.DatabaseHandler
import com.example.food_vision.database.UserModelClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AccountPage : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var auth:FirebaseAuth
    // String for Spinner
    var restriction2: String = ""

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.accountpage)

        // Firebase
        auth = Firebase.auth
        val currentUser = auth.currentUser
        // Database
        var db = DatabaseHandler(this)
        // Components
        val restriction = db.getUserRestriction(currentUser?.email.toString())
        val user = UserModelClass()
        val nutrientRestriction = findViewById<Spinner>(R.id.nutrientRestriction)
        val accountBack = findViewById<ImageView>(R.id.accountBack)
        val logoutBtn = findViewById<ImageButton>(R.id.logoutButton)
        val saveBtn = findViewById<ImageButton>(R.id.saveButton)

        Toast.makeText(this, restriction, Toast.LENGTH_SHORT).show()
        // Adapter for spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.nutrients,
            android.R.layout.simple_spinner_item
        ).also {adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            nutrientRestriction.adapter = adapter
            nutrientRestriction.onItemSelectedListener = this
        }
        val spinnerCount = nutrientRestriction.adapter.count - 1
        for (i in 0..spinnerCount){
            if(nutrientRestriction.getItemAtPosition(i).toString().equals(restriction)){
                nutrientRestriction.setSelection(i)
            }
        }


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
            user.restriction = restriction2
            db.updateRestriction(user.restriction, restriction)
            Toast.makeText(this, "Successfully saved diet restriction.", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        restriction2 = parent?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}