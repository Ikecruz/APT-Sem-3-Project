package com.example.mydrinks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydrinks.models.Recipe

class SearchAdapter(private val recipe: List<Recipe>): Filterable, RecyclerView.Adapter<SearchAdapter.viewHolder>() {

    lateinit var mcontext: Context

    var filterList = ArrayList<Recipe>()

    inner class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var name: TextView

        init{

            name = itemView.findViewById(R.id.search_item_name)

        }

    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var value = LayoutInflater.from(parent.context).inflate(R.layout.search_recipes_list_item, parent, false)
        mcontext = parent.context
        return viewHolder(value)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        var singleRecipe = recipe[position]
        holder.name.text = singleRecipe.name

        // click
    }

    override fun getItemCount(): Int {
        return recipe.size
    }

}