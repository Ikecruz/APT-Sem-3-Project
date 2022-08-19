package com.example.mydrinks.utils

import android.util.Log
import com.example.mydrinks.Database
import com.example.mydrinks.models.Favourite
import com.example.mydrinks.models.Recipe
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot

class FavouriteUtils() {
    fun addRecipeToFav(recipe:Recipe): Task<DocumentReference> {
        val id = FirebaseAuth.getInstance().currentUser?.uid
        val collection = Database().db.collection("users").document(id.toString()).collection("favs")
        val task =collection.add(recipe)
        return task
    }
    fun recipeIsInFav (recipe:Recipe): Task<QuerySnapshot> {
        val id = FirebaseAuth.getInstance().currentUser?.uid
        val collection = Database().db.collection("users").document(id.toString()).collection("favs")
        val task = collection.whereEqualTo("recipeId",recipe.recipeId).get()
        return task
    }
    fun removeFromFav (recipe:Recipe): Task<QuerySnapshot> {
        val id = FirebaseAuth.getInstance().currentUser?.uid
        val collection = Database().db.collection("users").document(id.toString()).collection("favs")
        val task = collection.whereEqualTo("recipeId",recipe.recipeId).get()
        return task
    }
}