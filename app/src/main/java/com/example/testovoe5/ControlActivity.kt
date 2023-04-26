package com.example.testovoe5

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import com.bumptech.glide.Glide

class ControlActivity : AppCompatActivity() {

    private lateinit var editTextFood: EditText
    private lateinit var editTextKal: EditText
    private lateinit var editTextWeight: EditText
    private lateinit var imageViewFon2: ImageView
    private var weight = 0
    private var kcal = 0
    private var food = ""

    private lateinit var buttonAdd: AppCompatButton
    private lateinit var buttonHistory: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)

        var isGood = true
        buttonAdd = findViewById(R.id.buttonAdd)
        buttonHistory = findViewById(R.id.buttonHistory)
        editTextFood = findViewById(R.id.editTextFood)
        editTextKal = findViewById(R.id.editTextKal)
        editTextWeight = findViewById(R.id.editTextWeight)
        imageViewFon2 = findViewById(R.id.imageViewFon2)

        Glide.with(this)
            .load("http://135.181.248.237/5/fon3.png")
            .into(imageViewFon2)

        buttonAdd.setOnClickListener {
            if (TextUtils.isEmpty(editTextFood.text)) {
                editTextFood.setBackgroundResource(R.drawable.rounded_edittext_red)
            } else {
                editTextFood.setBackgroundResource(R.drawable.rounded_edittext_green)
                food = editTextFood.text.toString()
            }
            if (TextUtils.isEmpty(editTextWeight.text)) {
                editTextWeight.setBackgroundResource(R.drawable.rounded_edittext_red)
            } else {
                editTextWeight.setBackgroundResource(R.drawable.rounded_edittext_green)
                val weightText = editTextWeight.text.toString()
                weight = weightText.toIntOrNull()!!
            }
            if (TextUtils.isEmpty(editTextKal.text)) {
                editTextKal.setBackgroundResource(R.drawable.rounded_edittext_red)
            } else {
                editTextKal.setBackgroundResource(R.drawable.rounded_edittext_green)
                val ckalText = editTextKal.text.toString()
                kcal = ckalText.toIntOrNull()!!
                isGood = true
                if (weight == null) {
                    editTextWeight.setBackgroundResource(R.drawable.rounded_edittext_red)
                    isGood = false
                }
            }
            if (!TextUtils.isEmpty(editTextFood.text) && !TextUtils.isEmpty(editTextKal.text) && !TextUtils.isEmpty(
                    editTextWeight.text)
            ) {
                val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
                val lastres = sharedPreferences.getInt("current", 0)
                val lastfood = sharedPreferences.getString("history", "")
                val hist = String.format("$food     $weight(g)     $kcal(KCal)\n")
                val all = hist + lastfood
                val editor = getSharedPreferences("my_preferences", Context.MODE_PRIVATE).edit()
                editor.putInt("current", lastres+kcal)
                editor.putString("history", all)
                editor.apply()
                editTextFood.setText("")
                editTextKal.setText("")
                editTextWeight.setText("")
            }
        }
        buttonHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        startActivity(Intent(this, MenuActivity::class.java))
        finish()
        return super.onKeyDown(keyCode, event)
    }
}