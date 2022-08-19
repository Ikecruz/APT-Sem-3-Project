package com.example.mydrinks

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mydrinks.models.Recipe
import com.example.mydrinks.utils.FavouriteUtils

class PopularAdapter(private val recipe: List<Recipe>) : RecyclerView.Adapter<PopularAdapter.viewHolder>() {

    lateinit var mcontext: Context

    inner class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var img: ImageView
        var name: TextView
        var time: TextView
        var level: TextView


        init{

            img = itemView.findViewById(R.id.popular_card_img)
            name = itemView.findViewById(R.id.popular_card_name)
            time = itemView.findViewById(R.id.popular_card_time)
            level = itemView.findViewById(R.id.popular_card_level)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var value = LayoutInflater.from(parent.context).inflate(R.layout.popular_recipes_list_item, parent, false)
        mcontext = parent.context
        return viewHolder(value)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        var singleRecipe = recipe[position]
        Glide.with(mcontext).load(singleRecipe.img).into(holder.img)
        holder.time.text = singleRecipe.time + " mins"
        holder.level.text = singleRecipe.level
        holder.name.text = singleRecipe.name

    }

    override fun getItemCount(): Int {
        return recipe.size
    }


}