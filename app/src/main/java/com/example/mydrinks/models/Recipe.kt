package com.example.mydrinks.models

data class Recipe(
    val recipeId: String,
    val name: String,
    val img: String,
    val time: String,
    val level: String,
    val category: String,
    val ingredients: ArrayList<String>,
    val steps: ArrayList<String>
)