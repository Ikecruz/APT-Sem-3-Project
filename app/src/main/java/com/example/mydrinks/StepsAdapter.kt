package com.example.mydrinks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class StepsAdapter(private val steps:List<String>)  : RecyclerView.Adapter<StepsAdapter.viewHolder>() {
    lateinit var mcontext: Context

    inner class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView){


        var stepsIndex: TextView
        var stepsText:TextView


        init{
            stepsIndex = itemView.findViewById(R.id.steps_index)
            stepsText =itemView.findViewById(R.id.steps_text)


        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsAdapter.viewHolder {
        var value = LayoutInflater.from(parent.context).inflate(R.layout.steps_list_item, parent, false)
        mcontext = parent.context
        return viewHolder(value)
    }

    override fun onBindViewHolder(holder: StepsAdapter.viewHolder, position: Int) {
        val ingredient = steps[position]
        holder.stepsIndex.text= (position+1).toString()
        holder.stepsText.text = ingredient
    }

    override fun getItemCount(): Int {
        return steps.size
    }

}