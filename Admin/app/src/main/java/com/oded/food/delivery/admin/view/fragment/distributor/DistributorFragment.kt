package com.oded.food.delivery.admin.view.fragment.distributor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.oded.food.delivery.admin.R
import com.oded.food.delivery.admin.databinding.FragmentDistributorBinding

class DistributorFragment : Fragment() {

    private var _binding : FragmentDistributorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDistributorBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addDistributor.setOnClickListener {
            findNavController().navigate(R.id.nav_add_distributor, Bundle())
        }
    }
}