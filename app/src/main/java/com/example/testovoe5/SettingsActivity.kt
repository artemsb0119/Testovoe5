package com.example.testovoe5

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.bumptech.glide.Glide

class SettingsActivity : AppCompatActivity() {

    private lateinit var imageViewFon2: ImageView
    private lateinit var buttonClear: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        buttonClear = findViewById(R.id.buttonClear)
        imageViewFon2 = findViewById(R.id.imageViewFon2)

        Glide.with(this)
            .load("http://135.181.248.237/5/fon3.png")
            .into(imageViewFon2)

        buttonClear.setOnClickListener {
            val editor = getSharedPreferences("my_preferences", Context.MODE_PRIVATE).edit()
            editor.putBoolean("is_first_start", true)
            editor.putInt("weight", 0)
            editor.putInt("height", 0)
            editor.putInt("age", 0)
            editor.putInt("pol", 0)
            editor.putInt("current", 0)
            editor.putString("history","")
            editor.apply()
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}