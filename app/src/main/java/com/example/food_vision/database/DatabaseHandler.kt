package com.example.food_vision.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context:Context):
    SQLiteOpenHelper(context,DATABASE_NAME,null, DATABASE_VERSION){
        companion object{
            // USER TABLE
            private const val DATABASE_VERSION = 1
            private const val DATABASE_NAME = "FoodVisionDatabase"
            private const val USER_TABLE = "UserTable"

            private const val KEY_USER_ID = "_id"
            private const val KEY_EMAIL = "email"
            private const val KEY_PASSWORD = "password"
            private const val KEY_RESTRICTION = "restriction"

            // FOOD/INGREDIENTS TABLE
            private const val FOOD_TABLE = "FoodTable"
            private const val KEY_FOOD_ID = "_id"
            private const val KEY_FOOD_NAME = "name"
            private const val KEY_FOOD_INGREDIENTS = "ingredients"
            private const val KEY_FOOD_NUTRIENTS = "nutrients"

        }

    override fun onCreate(db: SQLiteDatabase?) {
        // SQL Query for creating user table
        val CREATE_USER_TABLE = ("CREATE TABLE " + USER_TABLE + "("
                + KEY_USER_ID + " INTEGER PRIMARY KEY," + KEY_EMAIL + " TEXT," + KEY_PASSWORD
                + " TEXT," + KEY_RESTRICTION + " TEXT" + ")")
        // SQL Query for creating food table
        val CREATE_FOOD_TABLE = ("CREATE TABLE " + FOOD_TABLE + "("
                + KEY_FOOD_ID + " INTEGER PRIMARY KEY," + KEY_FOOD_NAME + " TEXT," + KEY_FOOD_INGREDIENTS
                + " TEXT," + KEY_FOOD_NUTRIENTS + " TEXT" + ")")

        db?.execSQL(CREATE_USER_TABLE)
        db?.execSQL(CREATE_FOOD_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + USER_TABLE)
        db.execSQL("DROP TABLE IF EXISTS " + FOOD_TABLE)
        onCreate(db)
    }

    // Method for adding data in user table
    fun addUser(user:UserModelClass): Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_EMAIL, user.email) // Putting UserModelClass Email
        contentValues.put(KEY_PASSWORD, user.password) // Putting UserModelClass Password
        contentValues.put(KEY_RESTRICTION, user.restriction) // Putting UserModelClass Restriction

        val insertingUser = db.insert(USER_TABLE, null, contentValues)

        db.close() // Closing database connection
        return insertingUser
    }

    // Method for adding data in Food table
    fun addFood(food:FoodModelClass): Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_FOOD_NAME, food.name) // Putting FoodModelClass name
        contentValues.put(KEY_FOOD_INGREDIENTS, food.ingredients) // Putting FoodModelClass ingredients
        contentValues.put(KEY_FOOD_NUTRIENTS, food.nutrients) // Putting FoodModelClass nutrients

        val insertingFood = db.insert(FOOD_TABLE, null, contentValues)

        db.close() // Closing database connection
        return insertingFood
    }
}