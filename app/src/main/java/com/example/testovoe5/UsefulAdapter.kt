package com.example.testovoe5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UsefulAdapter(val useful: List<Diet>) : RecyclerView.Adapter<UsefulAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.useful_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val diet = useful[position]
        holder.textViewPriem.text = diet.priem
        holder.textViewFood.text = diet.food
        // Load image using a library like Glide or Picasso
        Glide.with(holder.itemView.context).load(diet.photo).into(holder.imageFood)
    }

    override fun getItemCount(): Int {
        return useful.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewPriem: TextView = itemView.findViewById(R.id.textViewPriem)
        val textViewFood: TextView = itemView.findViewById(R.id.textViewFood)
        val imageFood: ImageView = itemView.findViewById(R.id.imageFood)
    }
}