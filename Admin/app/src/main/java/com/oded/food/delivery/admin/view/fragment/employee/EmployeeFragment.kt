package com.oded.food.delivery.admin.view.fragment.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.oded.food.delivery.admin.R
import com.oded.food.delivery.admin.databinding.FragmentEmployeeBinding

class EmployeeFragment : Fragment() {

    private var _binding : FragmentEmployeeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEmployeeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addEmployee.setOnClickListener {
            findNavController().navigate(R.id.nav_add_employee, Bundle())
        }
    }
}