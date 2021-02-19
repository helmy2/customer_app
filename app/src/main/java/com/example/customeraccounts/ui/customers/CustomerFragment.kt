package com.example.customeraccounts.ui.customers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customeraccounts.databinding.FragmentCustomerBinding


class CustomerFragment : Fragment() {

    private val customerViewModel: CustomerViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentCustomerBinding.inflate(inflater,container,false)

        val customerAdapter = CustomerAdapter()

        binding.apply {
            recyclerViewTasks.apply {
                adapter = customerAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
        customerViewModel.allData.observe(viewLifecycleOwner ) {
            customerAdapter.submitList(it)
        }

        return binding.root
    }
}