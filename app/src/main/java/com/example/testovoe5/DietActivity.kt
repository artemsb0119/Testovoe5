package com.example.testovoe5

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL
import java.util.*

class DietActivity : AppCompatActivity() {

    private lateinit var dieta: List<Diet>
    private lateinit var activity: Activity
    private lateinit var imageViewFon2: ImageView
    private lateinit var textViewDay: TextView

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DietAdapter

    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet)

        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US)

        textViewDay = findViewById(R.id.textViewDay)
        textViewDay.text = dayOfWeek

        url = when (calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.MONDAY -> "http://135.181.248.237/5/monday.json"
            Calendar.TUESDAY -> "http://135.181.248.237/5/tuesday.json"
            Calendar.WEDNESDAY -> "http://135.181.248.237/5/wednesday.json"
            Calendar.THURSDAY -> "http://135.181.248.237/5/thursday.json"
            Calendar.FRIDAY -> "http://135.181.248.237/5/friday.json"
            Calendar.SATURDAY -> "http://135.181.248.237/5/monday.json"
            Calendar.SUNDAY -> "http://135.181.248.237/5/tuesday.json"
            else -> ""
        }

        imageViewFon2 = findViewById(R.id.imageViewFon2)
        Glide.with(this)
            .load("http://135.181.248.237/5/fon3.png")
            .into(imageViewFon2)

        recyclerView = findViewById(R.id.rv_table)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        activity = this
        loadData()
    }

    private fun loadData() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val data = URL(url).readText()
                val gson = Gson()
                dieta = gson.fromJson(data, Array<Diet>::class.java).toList()

                activity.runOnUiThread {
                    adapter = DietAdapter(dieta)
                    recyclerView.adapter = adapter
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}