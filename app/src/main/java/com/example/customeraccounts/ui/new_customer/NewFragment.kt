package com.example.customeraccounts.ui.new_customer

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.customeraccounts.R
import com.example.customeraccounts.data.Customer
import com.example.customeraccounts.data.CustomerViewModel
import com.example.customeraccounts.databinding.FragmentNewBinding
import com.example.customeraccounts.ui.customers.CustomerFragmentDirections
import kotlin.math.absoluteValue

class NewFragment : Fragment() {

    private val customerViewModel: CustomerViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentNewBinding.inflate(inflater, container, false)

        binding.fabSave.setOnClickListener {
            binding.apply {
                if (
                    editTextName.text.isEmpty() ||
                    editTelephoneNumber.text.isEmpty() ||
                    editTextAccountBalance.text.isEmpty() ||
                    editTextAddress.text.isEmpty() ||
                    editTextDataOfLastPayment.text.isEmpty()
                ) {
                    Toast.makeText(context, "The fields are empty", Toast.LENGTH_SHORT).show()
                } else {

                    val customer = Customer(
                        editTextName.text.toString(),
                        editTextAddress.text.toString(),
                        editTelephoneNumber.text.toString(),
                        editTextAccountBalance.text.toString(),
                        editTextDataOfLastPayment.text.toString()
                    )
                    customerViewModel.addCustomer(customer)
                    val action =
                        NewFragmentDirections.actionNewFragmentToCustomerFragment2()
                    findNavController().navigate(action)
                }
            }
        }

        return binding.root
    }

}