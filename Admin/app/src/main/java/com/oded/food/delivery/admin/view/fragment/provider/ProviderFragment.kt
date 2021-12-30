package com.oded.food.delivery.admin.view.fragment.provider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.oded.food.delivery.admin.R
import com.oded.food.delivery.admin.databinding.FragmentProviderBinding

class ProviderFragment : Fragment() {

    private var _binding : FragmentProviderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProviderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addProvider.setOnClickListener {
            findNavController().navigate(R.id.nav_add_provider, Bundle())
        }
    }
}