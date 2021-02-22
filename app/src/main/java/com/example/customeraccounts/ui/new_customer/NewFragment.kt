package com.example.customeraccounts.ui.new_customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.customeraccounts.R
import com.example.customeraccounts.data.Customer
import com.example.customeraccounts.data.CustomerViewModel
import com.example.customeraccounts.databinding.FragmentNewBinding
import com.example.customeraccounts.ui.customers.CustomerFragmentDirections

class NewFragment : Fragment() {

    private val customerViewModel: CustomerViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentNewBinding.inflate(inflater, container, false)

        binding.root.setOnClickListener {

            binding.apply {
                if (
                    editTextName.text.isNotEmpty() &&
                    editTelephoneNumber.text.isNotEmpty() &&
                    editTextAccountBalance.text.isNotEmpty() &&
                    editTextAddress.text.isNotEmpty() &&
                    editTextDataOfLastPayment.text.isNotEmpty()
                ) {
                    buttonSave.isEnabled = true
                }

                buttonSave.setOnClickListener {
                    val customer = Customer(
                        editTextName.text.toString(),
                        editTextAddress.text.toString(),
                        editTelephoneNumber.text.toString(),
                        editTextAccountBalance.text.toString(),
                        editTextDataOfLastPayment.text.toString()
                    )
                    customerViewModel.addCustomer(customer)
                    val action =
                        NewFragmentDirections.actionNewFragmentToDetailsFragment(customer)
                    findNavController().navigate(action)
                }
            }
        }
        return binding.root
    }

}