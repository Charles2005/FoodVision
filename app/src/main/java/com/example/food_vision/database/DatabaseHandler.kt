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

        addDefaultFood(db, "Adobo",
            "Canola Oil, Garlic, Onion, Chicken, Vinegar, " +
                    "Soy Sauce, Water, Bay Leaves, Black Pepper, Sugar, Spinach",
                    "Calories, Protein, Saturated Fat, Niacin, Selenium, Phosphorus " +
                            "Vitamin B6, Vitamin B12, Riboflavin, Zinc, Thiamine, Potassium, Copper " +
                            "Sodium, Carbohydrates, Fiber, Vitamin C, Magnesium, Vitamin K, Vitamin F " +
                            "Folate, Dietary Fiber")

        addDefaultFood(db, "Sisig",
            "Pork Belly, Onion, Soy Sauce, Black Pepper, Chili, Calamansi, Butter " +
                    "Chicken Liver, Water, Mayonnaise, Salt",
                    "Carbohydrate, Protein, Saturated Fat, Folates, Niacin, Pantothenic Acid, " +
                            "Pyridoxine, Riboflavin, Thiamine, Vitamin A, Vitamin C, Vitamin E " +
                            "Vitamin K, Calcium, Magnesium, Phosphorus, Zinc, Sodium, Vitamins B6 " +
                            "Vitamin B12, Iron, Copper, Dietary Fiber")

        addDefaultFood(db, "Pancit Palabok",
            "Bihon Noodles, Cooking Oil, Garlic " +
                "Onion, Ground Pork, Pork Broth, Atsuete, Fish Sauce, Cornstarch, Shrimp, " +
                    "Smoked Fish Flakes, Pork chicharon, Egg(Hardboiled), Cabbage, Squid, Calamansi",
            "Calories, Carbohydrate, Protein, Saturated Fat, Calcium, Riboflavin, Niacin " +
                    "Iron Phosphorus, Potassium, Vitamin A, Vitamin C, Thiamine, Dietary Fiber, " +
                    "Zinc Vitamin B6, Vitamin B12, Vitamin E, Sodium, Potassium, Folate, Manganese")

        addDefaultFood(db, "Bulalo",
            "Beef Shank, Cabbage, Corn, Black Pepper, Onion, Water, Fish Sauce",
        "Calories, Protein, Fiber, Vitamin K, Vitamin C, Folate, Manganese, Vitamin B, " +
                "Calcium, Potassium, Magnesium, Iron, Vitamin A, Sodium")

        addDefaultFood(db, "Arroz Caldo",
        "Olive Oil, Onion, Garlic, Ginger, Chicken Wings, Fish Sauce, Chicken Broth, " +
                "Sweet Rice, Salt, Pepper, Calamansi",
        "Calories, Sodium, Carbohydrate, Dietary Fiber, Protein, Vitamin C, Zinc, " +
                "Vitamin E, Vitamin K, Saturated Fat, Unsaturated Fat, Monosaturated Fat, Potassium, " +
                "Iron, Vitamin A, Thiamine, Phosphorus, Niacin, Riboflavin, Calcium")

        addDefaultFood(db, "Tinola",
            "Chicken, Fish Sauce, Onion, Garlic, Ginger, Water, Vegetable Oil, Spinach, " +
                    "Moringa Leaves, Black Pepper, Salt, Sayote",
            "Calories, Protein, Saturated Fat, Monosaturated Fat, Niacin, Selenium, " +
                    "Phosphorus, Vitamin B6, Vitamin B12, Riboflavin, Zinc, Thiamine, Potassium, " +
                    "Copper, Sodium, Dietary Fiber, Magnesium, Vitamin A, Vitamin C, Vitamin K, " +
                    "Iron, Folate, Niazimicin")

        addDefaultFood(db, "Kare-Kare",
        "Beef, Tripe, Peanut Butter, Grounded Toasted Rice, Bagoong, Onion, Garlic " +
                "Atsuete, Egg plant, Cabbage, String beans, Okra, Oil, Water, Salt",
        "Calories, Protein, Saturated Fat, Carbohydrates, Fiber, Folate, Vitamin A " +
                "Vitamin C, Vitamin K, Calcium, Iron, Magnesium, Phosphorus, Potassium, Vitamin E, " +
                "Folate, Sodium")

        addDefaultFood(db, "Sinigang",
        "Pork Belly, Kangkong, Fish Sauce, String beans, Tomato, Green Long Chili, " +
                "Cooking Oil, Water, Onion, Gabi, Sinigang mix",
        "Calories, Protein, Saturated Fat, Sodium, Thiamine, Riboflavin, Niacin, " +
                "Pantothenic Acid, Vitamin B6, Vitamin B12, Vitamin E, Iron, Zinc, Copper, Beta-Carotene " +
                "Magnesium, Phosphorus, Potassium, Vitamin A, Vitamin K, Folate")

        addDefaultFood(db, "Dinuguan",
        "Pig blood, Vinegar, Oil, Garlic, Onion, Pork belly, Fish sauce, Salt, " +
                "Pepper, Bay leaves, Water, Green long chili",
        "Calories, Protein, Saturated Fat, Sodium, Thiamine, Riboflavin, Niacin, " +
                "Pantothenic Acid, Vitamin B6, Vitamin B12, Vitamin E, Zinc, Copper, Sodium, " +
                "Potassium, Magnesium, Vitamin K")

        addDefaultFood(db, "Pinakbet",
        "Pork Belly,  Chicharon, Shrimp Paste, Squash, Onion, Garlic, Oil, Tomato, " +
                "Water, Okra, String beans, Ampalaya, Eggplant",
        "Calories, Protein, Fat, Sodium, Thiamine, Riboflavin, Miacin, Pantothenic Acid " +
                "Vitamin B6, Vitamin B12, Vitamin E, Iron, Zinc, Copper, Vitamin K, Potassium, " +
                "Dietary Fiber, Vitamin C, Magnesium, Calcium, Phosphorus, Vitamin A, Folate")

        addDefaultFood(db, "Bicol Express",
        " Pork Belly, Coconut Milk, Coconut Cream, Shrimp Paste, Garlic, Chili pepper, " +
                "Ginger, Onion, Serrano Pepper, Water",
        "Calories, Protein, Fat, Sodium, Thiamine, Riboflavin, Niacin, Pantothenic Acid, " +
                "Vitamin B6, Vitamin B12, Vitamin E, Iron, Zinc, Copper, Vitamin C, Folate, " +
                "Choline, Magnesium, Phosphorus, Sodium")

        addDefaultFood(db, "Kaldereta",
        "Beef Short Ribs, Knorr Beef Cube, Tomato Sauce, Red Wine, Potato, Carrot," +
                "Bell pepper, Manzanilla Olives, Sprigs Thyme, Onion, Garlic,  Soy Sauce, Liver Spread, " +
                "Water, Olive Oil, Salt, Black pepper",
        "Saturated Fat, Sodium, Carbohydrate, Potassium, Protein, Vitamin A, Vitamin C, " +
                "Calcium, Iron, Vitamin E, Vitamin K, Magnesium")

        addDefaultFood(db, "Ginataang Gulay",
        "Squash, Green bean, Pork Belly, Knorr Ginataang Gulay, Shrimp, Onion, " +
                "Garlic, Water, Cooking Oil",
        "Calories, Protein, Saturated Fat, Sodium, Thiamine, Riboflavin, Niacin, " +
                "Panthothenic Acid, Vitamin B6, Vitamin B12, Vitamin E, Iron, Zinc, Copper, " +
                "Vitamin A, Dietary Fiber, Vitamin K, Vitamin C, Magnesium, Potassium, Sodium")

        addDefaultFood(db, "Tokwa't Baboy",
        "Pork Belly, Black Pepper, Salt, Water, Tofu, Oil, Onion, Green long chili",
        "Calories, Protein, Fat, Sodium, Thiamine, Riboflavin, Niacin, Pantothenic Acid, " +
                "Vitamin B6, Vitamin B12, Vitamin E, Iron, Zinc, Copper, Vitamin K, Dietary Fiber, " +
                "Magnesium, Potassium, Manganese, Selenium, Vitamin A, Phosphorus")

        addDefaultFood(db, "Binagoongan",
        "Pork Belly, Tomato, Eggplant, Pork Broth, Vinegar, Bagoong Alamang, Onion, " +
                "Garlic, Sugar, Black pepper, Oil",
        "Calories, Saturated Fat, Polyunsaturated Fat, Monounsaturated Fat, " +
                "Sodium, Carbohydrate, Fiber, Protein, Magnesium, Vitamin K, Calcium, Iron, Vitamin E")
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + USER_TABLE)
        db.execSQL("DROP TABLE IF EXISTS " + FOOD_TABLE)
        onCreate(db)
    }
    // Method for putting default values in FOOD DATABASE
    private fun addDefaultFood(db:SQLiteDatabase?, name: String, ingredients: String, nutrients: String){
        val contentValues = ContentValues()
        contentValues.put(KEY_FOOD_NAME, name)
        contentValues.put(KEY_FOOD_INGREDIENTS, ingredients)
        contentValues.put(KEY_FOOD_NUTRIENTS, nutrients)
        db?.insert(FOOD_TABLE, null, contentValues)

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
    fun getUser(email:String) : String{
        val users = UserModelClass()
        val db = writableDatabase
        val selectQuery  = "SELECT * FROM $USER_TABLE WHERE $KEY_EMAIL = " +'"' +"$email" +'"'

        val cursor = db.rawQuery(selectQuery, null)

        if(cursor != null && cursor.moveToFirst()){
            do {
                users.restriction = cursor.getString(cursor.getColumnIndex(KEY_RESTRICTION))
            }while(cursor.moveToNext())
        }
        cursor.close()
        return users.restriction
    }

    @SuppressLint("Range")
    fun getFood(name: String) : String {
        val food = FoodModelClass()
        val db = writableDatabase
        val selectQuery  = "SELECT * FROM $FOOD_TABLE WHERE $KEY_FOOD_NAME = " + '"' + "$name" +'"'

        val cursor = db.rawQuery(selectQuery, null)

        if(cursor != null && cursor.moveToFirst()){
            do {
                food.nutrients = cursor.getString(cursor.getColumnIndex(KEY_FOOD_NUTRIENTS))
            }while(cursor.moveToNext())
        }
        cursor.close()
        return food.nutrients
    }
    // Getting Name of food
    @SuppressLint("Range")
    fun getFoodName(name: String) : Boolean{
        val food = FoodModelClass()
        val db = writableDatabase
        val selectQuery  = "SELECT * FROM $FOOD_TABLE WHERE $KEY_FOOD_NAME = " + '"' + "$name" +'"'

        val cursor = db.rawQuery(selectQuery, null)

        if(cursor.count <= 0){
            cursor.close()
            return false;
        }
        cursor.close()
        return true;
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