package com.example.customeraccounts.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.customeraccounts.databinding.FragmentDetailsBinding
import com.example.customeraccounts.data.CustomerViewModel

class DetailsFragment : Fragment() {

    private val customerViewModel: CustomerViewModel by activityViewModels()
    private val args: DetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentDetailsBinding.inflate(inflater,container,false)
        binding.apply {
            textName.text = args.customer.name
            textDataOfLastPayment.text = args.customer.dateOfLastPayment
            textAccountBalance.text = args.customer.accountBalance
            textAddress.text = args.customer.address
            textTelephoneNumber.text = args.customer.telephoneNumber
        }
        return binding.root
    }

}