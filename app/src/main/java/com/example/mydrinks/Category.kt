package com.example.mydrinks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydrinks.models.Recipe

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

        var recipesFromDb: ArrayList<Recipe> = ArrayList();

        Database().db.collection("recipes").whereEqualTo("category", categoryName)
            .addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.documents.size > 0) {
                for (document in snapshot.documents) {
                    Log.d("single",document.toString())
                    val recipe = Recipe(
                        document.id,
                        document.get("name") as String,
                        document.get("img") as String,
                        document.get("time") as String,
                        document.get("level") as String,
                        document.get("category") as String,
                        document.get("ingredients") as ArrayList<String>,
                        document.get("steps") as ArrayList<String>
                    )
                    recipesFromDb.add(recipe)
                }

                var recyclerView: RecyclerView = findViewById(R.id.category_recipes)

                var adapter = CategoryAdapter(recipesFromDb)

                recyclerView.adapter = adapter
            }
        }
    }
}