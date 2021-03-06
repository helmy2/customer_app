package com.example.customeraccounts.ui.new_customer

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.customeraccounts.data.Customer
import com.example.customeraccounts.databinding.FragmentNewBinding

class NewFragment : Fragment() {

    private val viewModel: NewViewModel by activityViewModels()

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
                    viewModel.addCustomer(customer)

                    // hide keyboard
                    val imm =
                        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view?.windowToken,0)

                    findNavController().navigateUp()
                }
            }
        }

        return binding.root
    }

}