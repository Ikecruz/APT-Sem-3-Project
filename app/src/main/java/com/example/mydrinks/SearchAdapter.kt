package com.example.mydrinks

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mydrinks.models.Recipe
import java.util.*
import kotlin.collections.ArrayList

class SearchAdapter(private val recipe: ArrayList<Recipe>): Filterable, RecyclerView.Adapter<SearchAdapter.viewHolder>() {

    lateinit var mcontext: Context

    var filterList = ArrayList<Recipe>()

    inner class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var name: TextView
        var time: TextView
        var level: TextView
        var img: ImageView
        var box: LinearLayout

        init{

            name = itemView.findViewById(R.id.recipe_name)
            time = itemView.findViewById(R.id.recipe_time)
            level = itemView.findViewById(R.id.recipe_level)
            img = itemView.findViewById(R.id.recipe_img)
            box=itemView.findViewById(R.id.box_cat)

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
        var value = LayoutInflater.from(parent.context).inflate(R.layout.recipes_list_item, parent, false)
        mcontext = parent.context
        return viewHolder(value)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        var singleRecipe = filterList[position]
        holder.name.text = singleRecipe.name
        holder.level.text = singleRecipe.level
        holder.time.text = singleRecipe.time + " mins"
        Glide.with(mcontext).load(singleRecipe.img).into(holder.img)
        holder.box.setOnClickListener {
            var intent = Intent(mcontext,SingleRecipe::class.java)
            intent.putExtra("id",singleRecipe.recipeId)
            mcontext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

}