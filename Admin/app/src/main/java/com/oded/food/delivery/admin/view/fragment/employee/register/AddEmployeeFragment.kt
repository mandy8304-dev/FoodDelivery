package com.oded.food.delivery.admin.view.fragment.employee.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import com.oded.food.delivery.admin.R
import com.oded.food.delivery.admin.databinding.FragmentAddEmployeeBinding

class AddEmployeeFragment : Fragment() {

    private var _binding : FragmentAddEmployeeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddEmployeeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = listOf("Option 1", "Option 2", "Option 3", "Option 4")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        (binding.layoutRolSpinner.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }
}