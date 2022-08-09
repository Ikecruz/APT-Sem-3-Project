package com.example.mydrinks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this, Home::class.java))
            finish()
        },3000)

        val exploreBtn = findViewById<Button>(R.id.explorebtn)
        exploreBtn.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
        }

    }
}