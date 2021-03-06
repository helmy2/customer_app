package com.example.customeraccounts.ui.customers

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.customeraccounts.R
import com.example.customeraccounts.databinding.FragmentCustomerBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.*


class CustomerFragment : Fragment() {

    private val customerViewModel: CustomerViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        val binding = FragmentCustomerBinding.inflate(inflater, container, false)

        val customerAdapter = CustomerAdapter()
        binding.apply {
            recyclerViewCustomer.apply {
                adapter = customerAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }

            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val customer = customerAdapter.currentList[viewHolder.adapterPosition]
                    customerViewModel.deleteCustomer(customer)
                }
            }).attachToRecyclerView(recyclerViewCustomer)

        }

        customerViewModel.apply {

            allData.observe(viewLifecycleOwner) {
                customerAdapter.submitList(it)
            }

            customerHasDelete.observe(viewLifecycleOwner) { customer ->
                if (customer != null) {
                    make(requireView(), "Task deleted", LENGTH_LONG)
                        .setBackgroundTint(Color.parseColor("#5e92f3"))
                        .setTextColor(Color.parseColor("#ffffff"))
                        .setActionTextColor(Color.parseColor("#ffffff"))
                        .setAction("UNDO") {
                            customerViewModel.addCustomer(customer)
                        }.show()
                    confirmDeleteCustomer()
                }
            }
        }
        binding.fabAddTask.setOnClickListener {
            val action =
                CustomerFragmentDirections.actionCustomerFragment2ToNewFragment()
            findNavController().navigate(action)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_bar_search, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.onQueryTextChanged {
            customerViewModel.searchQuery.value = it
        }
    }

    private inline fun SearchView.onQueryTextChanged(crossinline listener: (String) -> Unit) {
        this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                listener(newText.orEmpty())
                return true
            }
        })
    }
}

