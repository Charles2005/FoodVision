package com.example.food_vision.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.food_vision.R
import com.example.food_vision.database.FoodModelClass

class FoodItemAdapter {

    class ItemAdapter(val context: Context, val items: ArrayList<FoodModelClass>) :
        RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.food_card,
                    parent,
                    false
                )
            )
        }


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val item = items.get(position)

            holder.tvName.text = item.name

        }

        override fun getItemCount(): Int {
            return items.size
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            // Holds the TextView that will add each item to
            val tvName = view.findViewById<TextView>(R.id.nameOfFood)
        }
    }
}