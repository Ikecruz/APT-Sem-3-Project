package com.example.mydrinks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class SingleRecipe : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_recipe)

        var backBtn = findViewById<ImageView>(R.id.back_btn)

        backBtn.setOnClickListener {
            finishActivity(0)
        }


    }
}