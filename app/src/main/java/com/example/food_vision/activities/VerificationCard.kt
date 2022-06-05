package com.example.food_vision.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.food_vision.R
import com.example.food_vision.database.DatabaseHandler
import com.example.food_vision.database.FoodModelClass

class VerificationCard:AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.verificationpage)
        val db = DatabaseHandler(this)
        val newFoodName = findViewById<TextView>(R.id.textView6)
        val newIngredients = findViewById<TextView>(R.id.textView8)
        val newNutrients = findViewById<EditText>(R.id.newNutrients)
        val acceptBtn = findViewById<Button>(R.id.verify)
        val rejectBtn = findViewById<Button>(R.id.reject)
        val backBtn = findViewById<ImageView>(R.id.verifyBack)

        newFoodName.setText(GlobalVar.newFoodName)
        newIngredients.setText(GlobalVar.newIngredients)

        acceptBtn.setOnClickListener{
            var success:Boolean = false
            val food = FoodModelClass()
            food.name = newFoodName.text.toString().replaceFirstChar { it.uppercase() }
            food.ingredients = newIngredients.text.toString()
            food.nutrients = newNutrients.text.toString()
            success = db.addFood(food) as Boolean
            Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show()
            newFoodName.setText("New Food")
            newIngredients.setText("New Ingredients")
            newNutrients.setText("")

        }
        rejectBtn.setOnClickListener{
            newFoodName.setText("New Food")
            newIngredients.setText("New Ingredients")
            Toast.makeText(this, "Rejected", Toast.LENGTH_SHORT).show()
        }
        backBtn.setOnClickListener {
            onBackPressed()
        }
    }
}