package com.example.testovoe5

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class HistoryActivity : AppCompatActivity() {

    private lateinit var imageViewFon2: ImageView
    private lateinit var textViewStroks: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        textViewStroks = findViewById(R.id.textViewStroks)
        imageViewFon2 = findViewById(R.id.imageViewFon2)

        Glide.with(this)
            .load("http://135.181.248.237/5/fon3.png")
            .into(imageViewFon2)

        val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val lastfood = sharedPreferences.getString("history", "")
        textViewStroks.text = lastfood
    }
}