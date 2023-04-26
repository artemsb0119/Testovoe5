package com.example.testovoe5

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatButton
import com.bumptech.glide.Glide

class RegistrationActivity : AppCompatActivity() {
    private lateinit var editTextName: EditText
    private lateinit var editTextHeight: EditText
    private lateinit var editTextWeight: EditText
    private lateinit var editTextAge: EditText
    private lateinit var imageViewFon2: ImageView
    private lateinit var radio: RadioGroup
    private lateinit var buttonMan: RadioButton
    private lateinit var buttonWoman: RadioButton

    private lateinit var buttonBegin: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        var isGood = true

        editTextName = findViewById(R.id.editTextName)
        editTextHeight = findViewById(R.id.editTextHeight)
        editTextWeight = findViewById(R.id.editTextWeight)
        editTextAge = findViewById(R.id.editTextAge)
        buttonBegin = findViewById(R.id.buttonBegin)
        imageViewFon2 = findViewById(R.id.imageViewFon2)
        radio = findViewById(R.id.radio)
        buttonMan = findViewById(R.id.buttonMan)
        buttonWoman = findViewById(R.id.buttonWoman)

        Glide.with(this)
            .load("http://135.181.248.237/5/fon2.png")
            .into(imageViewFon2)

        buttonBegin.setOnClickListener {
            if (TextUtils.isEmpty(editTextName.text)) {
                editTextName.setBackgroundResource(R.drawable.rounded_edittext_red)
            } else {
                editTextName.setBackgroundResource(R.drawable.rounded_edittext_green)
            }
            if (TextUtils.isEmpty(editTextHeight.text)) {
                editTextHeight.setBackgroundResource(R.drawable.rounded_edittext_red)
            } else {
                editTextHeight.setBackgroundResource(R.drawable.rounded_edittext_green)
                val heightText = editTextHeight.text.toString()
                val height = heightText.toIntOrNull()
                isGood = true
                if (height == null) {
                    editTextHeight.setBackgroundResource(R.drawable.rounded_edittext_red)
                    isGood = false
                }
            }
            if (TextUtils.isEmpty(editTextWeight.text)) {
                editTextWeight.setBackgroundResource(R.drawable.rounded_edittext_red)
            } else {
                editTextWeight.setBackgroundResource(R.drawable.rounded_edittext_green)
                val weightText = editTextWeight.text.toString()
                val weight = weightText.toIntOrNull()
                isGood = true
                if (weight == null) {
                    editTextWeight.setBackgroundResource(R.drawable.rounded_edittext_red)
                    isGood = false
                }
            }
            if (TextUtils.isEmpty(editTextAge.text)) {
                editTextAge.setBackgroundResource(R.drawable.rounded_edittext_red)
            } else {
                editTextAge.setBackgroundResource(R.drawable.rounded_edittext_green)
                val ageText = editTextAge.text.toString()
                val age = ageText.toIntOrNull()
                isGood = true
                if (age == null) {
                    editTextAge.setBackgroundResource(R.drawable.rounded_edittext_red)
                    isGood = false
                }
            }
            if (!TextUtils.isEmpty(editTextName.text) && !TextUtils.isEmpty(editTextHeight.text) && !TextUtils.isEmpty(
                    editTextWeight.text) && !TextUtils.isEmpty(editTextAge.text)
            ) {
                val weightText = editTextWeight.text.toString()
                val weight = weightText.toInt()
                val heightText = editTextHeight.text.toString()
                val height = heightText.toInt()
                val ageText = editTextAge.text.toString()
                val age = ageText.toInt()
                val selectedId = radio.checkedRadioButtonId

                val editor = getSharedPreferences("my_preferences", Context.MODE_PRIVATE).edit()
                editor.putBoolean("is_first_start", false)
                editor.putInt("weight", weight)
                editor.putInt("height", height)
                editor.putInt("age", age)
                if(selectedId == R.id.buttonMan) {
                    editor.putInt("pol", 1)
                } else {
                    editor.putInt("pol", 2)
                }
                editor.apply()
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}