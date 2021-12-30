package com.oded.food.delivery.provider.view.fragment.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.oded.food.delivery.provider.R
import com.oded.food.delivery.provider.databinding.FragmentProductBinding

class ProductFragment : Fragment() {

    private var _binding : FragmentProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProductBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addProduct.setOnClickListener {
            findNavController().navigate(R.id.nav_add_product, Bundle())
        }
    }
}