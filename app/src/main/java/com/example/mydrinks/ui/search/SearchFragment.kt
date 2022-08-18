package com.example.mydrinks.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mydrinks.Database
import com.example.mydrinks.PopularAdapter
import com.example.mydrinks.R
import com.example.mydrinks.SearchAdapter
import com.example.mydrinks.databinding.FragmentSearchBinding
import com.example.mydrinks.models.Recipe

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root
        var recipesFromDb: ArrayList<Recipe> = ArrayList();
        var recyclerView: RecyclerView = binding.root.findViewById(R.id.search_recipes)
        val search_box: SearchView = binding.root.findViewById(R.id.search_box)
        lateinit var adapter:SearchAdapter
        Database().db.collection("recipes").addSnapshotListener { snapshot, e ->
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

                adapter = SearchAdapter(recipesFromDb)
                adapter.filter.filter("")
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
                search_box.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        adapter.filter.filter(query)
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        adapter.filter.filter(newText)
                        return false
                    }

                })
            }
        }

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}