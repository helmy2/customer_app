package com.example.customeraccounts.ui.details

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.customeraccounts.R
import com.example.customeraccounts.databinding.FragmentDetailsBinding
import com.example.customeraccounts.data.CustomerViewModel
import com.example.customeraccounts.ui.customers.CustomerFragmentDirections
import com.google.android.material.snackbar.Snackbar

class DetailsFragment : Fragment() {

    private val customerViewModel: CustomerViewModel by activityViewModels()
    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var binding:FragmentDetailsBinding
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater,container,false)
        binding.apply {
            textName.text = args.customer.name
            textDataOfLastPayment.text = args.customer.dateOfLastPayment
            textAccountBalance.text = "${args.customer.accountBalance} $"
            textAddress.text = args.customer.address
            textTelephoneNumber.text = args.customer.telephoneNumber
        }

        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_bar, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.delete -> {
                customerViewModel.deleteCustomer(args.customer)
                val action =
                    DetailsFragmentDirections.actionDetailsFragmentToCustomerFragment2()
                findNavController().navigate(action)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}