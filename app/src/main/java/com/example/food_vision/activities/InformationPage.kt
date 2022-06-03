package com.example.food_vision.activities

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.food_vision.R
import com.example.food_vision.database.DatabaseHandler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class InformationPage : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.informationpage)
        // Firebase
        auth = Firebase.auth
        val currentUser = auth.currentUser
        //Database
        var db = DatabaseHandler(this)
        val restriction = db.getUserRestriction(currentUser?.email.toString())
        //Components
        val intent = getIntent()
        var bitmap = intent.getParcelableExtra<Bitmap>("foodImage")
        val foodName = findViewById<TextView>(R.id.foodName)
        val infoBack = findViewById<ImageView>(R.id.nutritionBack)
        val foodImage = findViewById<ImageView>(R.id.foodImage)
        val foodInput = findViewById<EditText>(R.id.inputFoodName)
        val enterFoodInput = findViewById<Button>(R.id.enterFood)
        val infoNutrients = findViewById<TextView>(R.id.nutrientsInfo)


        // Setting food image
        foodImage.setImageBitmap(bitmap)

        infoBack.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        enterFoodInput.setOnClickListener{
            foodName.setText(foodInput.text.toString())
            val foodNutrients =  db.getFood(foodInput.text.toString().replaceFirstChar { it.uppercase() })
            val compareFoodName = db.getFoodName(foodInput.text.toString().replaceFirstChar { it.uppercase() })
            val foodList: List<String> = foodNutrients.split(",").toList()
            val formattedFoodList = StringBuilder()
            foodInput.setText("")
            foodInput.setEnabled(false)

            for(i in foodList){
                formattedFoodList.append(i).append("\n")

                if(i == restriction) {
                    Toast.makeText(this,
                        restriction,
                        Toast.LENGTH_LONG).show()
                }
            }
            infoNutrients.setText(formattedFoodList)

            if (!compareFoodName){
                val intent = Intent(this, NoDataPage::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }
    }
}