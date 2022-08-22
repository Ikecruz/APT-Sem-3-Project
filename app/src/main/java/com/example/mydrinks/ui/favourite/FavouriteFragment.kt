package com.example.mydrinks.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mydrinks.CategoryAdapter
import com.example.mydrinks.Database
import com.example.mydrinks.PopularAdapter
import com.example.mydrinks.R
import com.example.mydrinks.databinding.FragmentFavouriteBinding
import com.example.mydrinks.models.Recipe
import com.google.firebase.auth.FirebaseAuth

class FavouriteFragment : Fragment() {

    private lateinit var favouriteViewModel: FavouriteViewModel
    private var _binding: FragmentFavouriteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favouriteViewModel =
            ViewModelProvider(this).get(FavouriteViewModel::class.java)

        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val root: View = binding.root
        var recipesFromDb: ArrayList<Recipe> = ArrayList();
        val id = FirebaseAuth.getInstance().currentUser?.uid
        Database().db.collection("users").document(id.toString()).collection("favs").addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.documents.size > 0) {
                for (document in snapshot.documents) {
                    val recipe = Recipe(
                        document.get("recipeId") as String,
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

                val scrollView = binding.root.findViewById<ScrollView>(R.id.favourites_scrollview)
                scrollView.visibility=View.VISIBLE

                val nodata = binding.root.findViewById<LinearLayout>(R.id.favourites_no_data)
                nodata.visibility=View.GONE

                var recyclerView: RecyclerView = binding.root.findViewById(R.id.favourite_recipes)

                var adapter = CategoryAdapter(recipesFromDb)

                recyclerView.adapter = adapter

            }

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}