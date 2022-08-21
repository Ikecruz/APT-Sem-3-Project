package com.example.mydrinks

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mydrinks.models.Recipe
import com.example.mydrinks.utils.FavouriteUtils

class SingleRecipe : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_recipe)

        var backBtn = findViewById<ImageView>(R.id.back_btn)
        var id = intent.getStringExtra("id").toString()
        backBtn.setOnClickListener {
            this.finish()
        }
        Database().db.collection("recipes").document(id)
            .addSnapshotListener { document, e ->
                if (e != null) {
                    return@addSnapshotListener
                }

                if (document != null) {
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
                    var ingredientsAdapter = IngredientsAdapter(recipe.ingredients)
                    var stepsAdapter = StepsAdapter(recipe.steps)
                    var ingredientsView = findViewById<RecyclerView>(R.id.single_recipe_ingredients)
                    var stepsView = findViewById<RecyclerView>(R.id.single_recipe_steps)
                    ingredientsView.adapter = ingredientsAdapter
                    stepsView.adapter = stepsAdapter
                    var txtLevel = findViewById<TextView>(R.id.single_recipe_level)
                    var txtTime = findViewById<TextView>(R.id.single_recipe_time)
                    var img = findViewById<ImageView>(R.id.single_recipe_image)
                    var txtCat = findViewById<TextView>(R.id.single_recipe_category)
                    var favBtn = findViewById<ImageButton>(R.id.like_btn)
                    var txtName =  findViewById<TextView>(R.id.single_recipe_name)
                    txtLevel.text = recipe.level
                    txtCat.text = recipe.category
                    txtTime.text = recipe.time + " mins"
                    txtName.text=recipe.name
                    Glide.with(baseContext).load(recipe.img).into(img)
                    FavouriteUtils().recipeIsInFav(recipe).addOnSuccessListener { data ->
                            if (data.documents.size > 0) {
                                favBtn.setImageResource(R.drawable.ic_fav_filled)
                            } else {
                                favBtn.setImageResource(R.drawable.ic_fav_open)

                        }
                    }
                    favBtn.setOnClickListener {
                        FavouriteUtils().recipeIsInFav(recipe).addOnSuccessListener { data ->
                            if (data.documents.size > 0) {
                                FavouriteUtils().removeFromFav(recipe)
                                    .addOnSuccessListener { data ->
                                        if (data.documents.size > 0) {
                                           data.documents[0].reference.delete().addOnSuccessListener {
                                               favBtn.setImageResource(R.drawable.ic_fav_open)
                                           }
                                        }
                                    }

                            } else {
                                FavouriteUtils().addRecipeToFav(recipe)
                                    .addOnSuccessListener { data ->
                                        favBtn.setImageResource(R.drawable.ic_fav_filled)
                                    }

                            }
                        }
                    }
                }
            }
    }
}
