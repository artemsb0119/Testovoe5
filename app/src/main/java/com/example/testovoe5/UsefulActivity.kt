package com.example.testovoe5

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

class UsefulActivity : AppCompatActivity() {

    private lateinit var useful: List<Diet>
    private lateinit var activity: Activity
    private lateinit var imageViewFon2: ImageView

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UsefulAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_useful)

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
                val data = URL("http://135.181.248.237/5/useful.json").readText()
                val gson = Gson()
                useful = gson.fromJson(data, Array<Diet>::class.java).toList()

                activity.runOnUiThread {
                    adapter = UsefulAdapter(useful)
                    recyclerView.adapter = adapter
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}