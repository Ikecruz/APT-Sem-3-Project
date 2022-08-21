package com.example.mydrinks.ui.discover

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mydrinks.Category
import com.example.mydrinks.Database
import com.example.mydrinks.PopularAdapter
import com.example.mydrinks.R
import com.example.mydrinks.databinding.FragmentDiscoverBinding
import com.example.mydrinks.models.Recipe

class DiscoverFragment : Fragment() {

    private lateinit var discoverViewModel: DiscoverViewModel
    private var _binding: FragmentDiscoverBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        discoverViewModel =
            ViewModelProvider(this).get(DiscoverViewModel::class.java)

        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)

        val softDrink = binding.root.findViewById<LinearLayout>(R.id.soft_drink_card)
        val cockTail = binding.root.findViewById<LinearLayout>(R.id.cocktail_card)
        val shake = binding.root.findViewById<LinearLayout>(R.id.shake_card)
        val cocoa = binding.root.findViewById<LinearLayout>(R.id.cocoa_card)


        val intent = Intent(requireContext(), Category::class.java)

        softDrink.setOnClickListener {
            intent.putExtra("category_name", "Soft Drinks")
            startActivity(intent)
        }

        cockTail.setOnClickListener {
            intent.putExtra("category_name", "Cocktails")
            startActivity(intent)
        }

        shake.setOnClickListener {
            intent.putExtra("category_name", "Shakes")
            startActivity(intent)
        }

        cocoa.setOnClickListener {
            intent.putExtra("category_name", "Cocoa")
            startActivity(intent)
        }

        var recipesFromDb: ArrayList<Recipe> = ArrayList();

        Database().db.collection("recipes").limit(6).addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.documents.size > 0) {
                for (document in snapshot.documents) {
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

                var recyclerView: RecyclerView = binding.root.findViewById(R.id.popular_recipes)

                var adapter = PopularAdapter(recipesFromDb)

                recyclerView.adapter = adapter

            }
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}