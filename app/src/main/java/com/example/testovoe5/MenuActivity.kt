package com.example.testovoe5

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

class MenuActivity : AppCompatActivity() {

    private lateinit var buttonDiet: AppCompatButton
    private lateinit var buttonUseful: AppCompatButton
    private lateinit var buttonControl: AppCompatButton
    private lateinit var buttonSettings: AppCompatButton
    private lateinit var imageViewFon2: ImageView

    private lateinit var textViewKol: TextView
    private lateinit var textViewNow: TextView
    private lateinit var progressBarMenu: ProgressBar

    private var weight=0
    private var height=0
    private var age=0
    private var pol=0
    private var koefPol=0
    private var res=0

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        buttonDiet = findViewById(R.id.buttonDiet)
        buttonUseful = findViewById(R.id.buttonUseful)
        buttonControl = findViewById(R.id.buttonControl)
        buttonSettings = findViewById(R.id.buttonSettings)
        imageViewFon2 = findViewById(R.id.imageViewFon2)
        textViewKol = findViewById(R.id.textViewKol)
        textViewNow = findViewById(R.id.textViewNow)
        progressBarMenu = findViewById(R.id.progressBarMenu)

        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        weight = sharedPreferences.getInt("weight",0)
        height = sharedPreferences.getInt("height",0)
        age = sharedPreferences.getInt("age",0)
        pol = sharedPreferences.getInt("pol",0)
        val current = sharedPreferences.getInt("current",0)
        if (pol==1){
            koefPol = 5
        } else {
            koefPol = -161
        }
        res = ((weight*10+height*6.25-age*5+koefPol)*1.6).toInt()
        progressBarMenu.max = res
        progressBarMenu.progress = current
        textViewKol.text = res.toString()
        textViewNow.text = current.toString()

        Glide.with(this)
            .load("http://135.181.248.237/5/fon3.png")
            .into(imageViewFon2)

        buttonDiet.setOnClickListener {
            val intent = Intent(this, DietActivity::class.java)
            startActivity(intent)
        }
        buttonUseful.setOnClickListener {
            val intent = Intent(this, UsefulActivity::class.java)
            startActivity(intent)
        }
        buttonControl.setOnClickListener {
            val intent = Intent(this, ControlActivity::class.java)
            startActivity(intent)
            finish()
        }
        buttonSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}