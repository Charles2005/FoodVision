package com.example.food_vision.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food_vision.R
import com.example.food_vision.adapter.FoodItemAdapter
import com.example.food_vision.database.DatabaseHandler
import com.example.food_vision.database.FoodModelClass

class FoodDatabaseView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.food_database_view)

        setupListofDataIntoRecyclerView()
    }

    private fun setupListofDataIntoRecyclerView() {
        val rvItemsList = findViewById<RecyclerView>(R.id.rvItemsList)

        if (getItemsList().size > 0) {

            rvItemsList.visibility = View.VISIBLE

            // Set the LayoutManager that this RecyclerView will use.
            rvItemsList.layoutManager = LinearLayoutManager(this)
            // Adapter class is initialized and list is passed in the param.
            val itemAdapter = FoodItemAdapter(this,
                getItemsList() as ArrayList<FoodModelClass>
            )
            // adapter instance is set to the recyclerview to inflate the items.
            rvItemsList.adapter = itemAdapter
        } else {

            rvItemsList.visibility = View.GONE
        }
    }

    private fun getItemsList(): List<FoodModelClass> {
        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val foodList: List<FoodModelClass> = databaseHandler.getAllFood()

        return foodList
    }

}