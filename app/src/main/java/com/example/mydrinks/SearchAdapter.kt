package com.example.mydrinks

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydrinks.models.Recipe
import java.util.*
import kotlin.collections.ArrayList

class SearchAdapter(private val recipe: ArrayList<Recipe>): Filterable, RecyclerView.Adapter<SearchAdapter.viewHolder>() {

    lateinit var mcontext: Context

    var filterList = ArrayList<Recipe>()

    inner class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var name: TextView

        init{

            name = itemView.findViewById(R.id.search_item_name)

        }

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()

                if (charSearch.isEmpty()) {
                    filterList.clear()
                } else {
                    val resultList = ArrayList<Recipe>()
                    for (singleRecipe in recipe) {
                        if (singleRecipe.name.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(singleRecipe)
                        }
                    }
                    filterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as ArrayList<Recipe>
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var value = LayoutInflater.from(parent.context).inflate(R.layout.search_recipes_list_item, parent, false)
        mcontext = parent.context
        return viewHolder(value)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        var singleRecipe = filterList[position]
        holder.name.text = singleRecipe.name

        // click
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

}