package com.example.mydrinks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class Category : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val category_name_display = findViewById<TextView>(R.id.category_name_display)
        val intent: Intent = getIntent()

        val categoryName: String = intent.getStringExtra("category_name").toString()

        category_name_display.setText(categoryName)

        val backBtn = findViewById<ImageButton>(R.id.back_btn)

        backBtn.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
        }
    }
}