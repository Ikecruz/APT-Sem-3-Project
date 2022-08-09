package com.example.mydrinks.ui.discover

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mydrinks.Category
import com.example.mydrinks.R
import com.example.mydrinks.databinding.FragmentDiscoverBinding

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

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}