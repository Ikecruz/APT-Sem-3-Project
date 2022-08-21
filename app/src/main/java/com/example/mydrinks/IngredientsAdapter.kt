package com.example.mydrinks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class IngredientsAdapter(private val ingredients:List<String>)  : RecyclerView.Adapter<IngredientsAdapter.viewHolder>() {
    lateinit var mcontext: Context

    inner class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView){


        var txtfield: TextView


        init{

            txtfield = itemView.findViewById(R.id.txt_ingredient)


        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsAdapter.viewHolder {
        var value = LayoutInflater.from(parent.context).inflate(R.layout.ingredients_list_item, parent, false)
        mcontext = parent.context
        return viewHolder(value)
    }

    override fun onBindViewHolder(holder: IngredientsAdapter.viewHolder, position: Int) {
        val ingredient = ingredients[position]
        holder.txtfield.text = ingredient
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

}