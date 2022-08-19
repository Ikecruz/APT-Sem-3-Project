package com.example.mydrinks

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Splash : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        auth= FirebaseAuth.getInstance()

        Handler().postDelayed({
            signInAnon()
            startActivity(Intent(this, Home::class.java))
            finish()
        },3000)

        val exploreBtn = findViewById<Button>(R.id.explorebtn)
        exploreBtn.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
        }

    }

    private fun signInAnon() {
        auth.signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                } else {
                    Log.w(TAG, "signInAnonymously:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

}