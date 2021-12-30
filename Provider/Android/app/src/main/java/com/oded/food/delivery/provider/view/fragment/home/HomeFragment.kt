package com.oded.food.delivery.provider.view.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.oded.food.delivery.provider.R
import com.oded.food.delivery.provider.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDispatch.setOnClickListener {
            findNavController().navigate(R.id.nav_dispatch, bundleOf())
        }

        binding.btnInventory.setOnClickListener {
            findNavController().navigate(R.id.nav_inventory, bundleOf())
        }
    }
}