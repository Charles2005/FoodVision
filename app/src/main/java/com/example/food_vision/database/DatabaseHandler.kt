package com.example.food_vision.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context:Context):
    SQLiteOpenHelper(context,DATABASE_NAME,null, DATABASE_VERSION){
        companion object{
            // USER TABLE
            private const val DATABASE_VERSION = 1
            private const val DATABASE_NAME = "FoodVisionDatabase"
            private const val USER_TABLE = "UserTable"

            private const val KEY_USER_ID = "id"
            private const val KEY_EMAIL = "email"
            private const val KEY_PASSWORD = "password"
            private const val KEY_RESTRICTION = "restriction"

            // FOOD/INGREDIENTS TABLE
            private const val FOOD_TABLE = "FoodTable"
            private const val KEY_FOOD_ID = "id"
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

    // Getting all data in User data and Food Data
    @SuppressLint("Range")
    fun getAllUser(): List<UserModelClass>{
        val userList = ArrayList<UserModelClass>()
        val db = this.writableDatabase
        val selectQuery = "SELECT * FROM $USER_TABLE"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null){
            if(cursor.moveToFirst()){
                do{
                    val users = UserModelClass()
                    users.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_USER_ID)))
                    users.email = cursor.getString(cursor.getColumnIndex(KEY_EMAIL))
                    users.password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD))
                    users.restriction = cursor.getString(cursor.getColumnIndex(KEY_RESTRICTION))
                    userList.add(users)
                }while (cursor.moveToNext())
            }
        }
        cursor.close()
        return userList
    }
    @SuppressLint("Range")
    fun getAllFood(): List<FoodModelClass>{
        val foodList = ArrayList<FoodModelClass>()
        val db = this.writableDatabase
        val selectQuery = "SELECT * FROM $FOOD_TABLE"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null){
            if(cursor.moveToFirst()){
                do{
                    val food = FoodModelClass()
                    food.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_USER_ID)))
                    food.name = cursor.getString(cursor.getColumnIndex(KEY_FOOD_NAME))
                    food.ingredients = cursor.getString(cursor.getColumnIndex(KEY_FOOD_INGREDIENTS))
                    food.nutrients = cursor.getString(cursor.getColumnIndex(KEY_FOOD_NUTRIENTS))
                    foodList.add(food)
                }while (cursor.moveToNext())
            }
        }
        cursor.close()
        return foodList
    }

    // Method for adding data in user table
    fun addUser(user:UserModelClass): Boolean{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_EMAIL, user.email) // Putting UserModelClass Email
        contentValues.put(KEY_PASSWORD, user.password) // Putting UserModelClass Password
        contentValues.put(KEY_RESTRICTION, user.restriction) // Putting UserModelClass Restriction

        val insertingUser = db.insert(USER_TABLE, null, contentValues)

        db.close() // Closing database connection
        return (Integer.parseInt("$insertingUser") != -1)
    }

    // Method for adding data in Food table
    fun addFood(food:FoodModelClass): Boolean{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_FOOD_NAME, food.name) // Putting FoodModelClass name
        contentValues.put(KEY_FOOD_INGREDIENTS, food.ingredients) // Putting FoodModelClass ingredients
        contentValues.put(KEY_FOOD_NUTRIENTS, food.nutrients) // Putting FoodModelClass nutrients

        val insertingFood = db.insert(FOOD_TABLE, null, contentValues)

        db.close() // Closing database connection
        return (Integer.parseInt("$insertingFood") != -1)
    }
    // Method for getting specific data
    @SuppressLint("Range")
    fun getUser(_id: Int) : UserModelClass{
        val users = UserModelClass()
        val db = writableDatabase
        val selectQuery  = "SELECT * FROM $USER_TABLE WHERE $KEY_USER_ID = $_id"

        val cursor = db.rawQuery(selectQuery, null)

        users.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_USER_ID)))
        users.email = cursor.getString(cursor.getColumnIndex(KEY_EMAIL))
        users.password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD))
        users.restriction = cursor.getString(cursor.getColumnIndex(KEY_RESTRICTION))

        cursor.close()
        return users
    }
    @SuppressLint("Range")
    fun getFood(_id: Int) : FoodModelClass{
        val food = FoodModelClass()
        val db = writableDatabase
        val selectQuery  = "SELECT * FROM $FOOD_TABLE WHERE $KEY_FOOD_ID = $_id"

        val cursor = db.rawQuery(selectQuery, null)

        food.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_USER_ID)))
        food.name = cursor.getString(cursor.getColumnIndex(KEY_FOOD_NAME))
        food.ingredients = cursor.getString(cursor.getColumnIndex(KEY_FOOD_INGREDIENTS))
        food.nutrients = cursor.getString(cursor.getColumnIndex(KEY_FOOD_NUTRIENTS))

        cursor.close()
        return food
    }

    // Method for updating user and food
    fun updateUser(user:UserModelClass): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_EMAIL, user.email)
        contentValues.put(KEY_PASSWORD, user.password)
        contentValues.put(KEY_RESTRICTION, user.restriction)

        // Updating records
        val updateUserData = db.update(USER_TABLE, contentValues, KEY_USER_ID + "=?", arrayOf(user.id.toString())).toLong()

        //Closing database connection
        db.close()
        return Integer.parseInt("$updateUserData") != -1
    }

    fun updateFood(food:FoodModelClass): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_FOOD_NAME, food.name )
        contentValues.put(KEY_FOOD_INGREDIENTS, food.ingredients)
        contentValues.put(KEY_FOOD_NUTRIENTS, food.nutrients)

        // Updating records
        val updateFoodData = db.update(FOOD_TABLE, contentValues, KEY_FOOD_ID + "=?", arrayOf(food.id.toString())).toLong()

        //Closing database connection
        db.close()
        return Integer.parseInt("$updateFoodData") != -1
    }
    //Method for deleting user and food
    fun deleteUser(_id: Int): Boolean{
        val db = this.writableDatabase

        // Deleting data
        val deleteUser = db.delete(USER_TABLE, KEY_USER_ID + "=?" , arrayOf(_id.toString())).toLong()

        // Closing Database
        db.close()
        return (Integer.parseInt("$deleteUser") != -1)
    }

    fun deleteFood(_id: Int): Boolean{
        val db = this.writableDatabase

        // Deleting data
        val deleteFood = db.delete(FOOD_TABLE, KEY_FOOD_ID + "=?" , arrayOf(_id.toString())).toLong()

        // Closing Database
        db.close()
        return (Integer.parseInt("$deleteFood") != -1)
    }


}