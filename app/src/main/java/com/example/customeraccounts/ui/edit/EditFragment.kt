package com.example.customeraccounts.ui.edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.customeraccounts.data.Customer
import com.example.customeraccounts.databinding.FragmentEditBinding

class EditFragment : Fragment() {
    private val args: EditFragmentArgs by navArgs()
    private val viewModel: EditViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentEditBinding.inflate(layoutInflater, container, false)

        binding.apply {
            editTextName.hint = args.customer.name
            editTelephoneNumber.hint = args.customer.telephoneNumber
            editTextAccountBalance.hint = args.customer.accountBalance
            editTextDataOfLastPayment.hint = args.customer.dateOfLastPayment
            editTextAddress.hint = args.customer.address

            fabSaveEdit.setOnClickListener {
                val name = if (editTextName.text.isEmpty()) {
                    args.customer.name
                } else editTextName.text.toString()

                val address = if (editTextAddress.text.isEmpty()) {
                    args.customer.address
                } else editTextAddress.text.toString()

                val number = if (editTelephoneNumber.text.isEmpty()) {
                    args.customer.telephoneNumber
                } else editTelephoneNumber.text.toString()

                val lastPayment = if (editTextDataOfLastPayment.text.isEmpty()) {
                    args.customer.dateOfLastPayment
                } else editTextDataOfLastPayment.text.toString()

                val balance = if (editTextAccountBalance.text.isEmpty()) {
                    args.customer.accountBalance
                } else editTextAccountBalance.text.toString()

                viewModel.update(
                    Customer(name, address, number, balance, lastPayment, args.customer.id)
                )

                findNavController().navigateUp()
            }
        }
        return binding.root
    }

}