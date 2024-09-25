package com.juanma.exercise.countryflags.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanma.exercise.countryflags.R
import com.juanma.exercise.countryflags.adapters.CountryAdapter
import com.juanma.exercise.countryflags.databinding.FragmentHomeBinding
import com.juanma.exercise.countryflags.models.CountriesResponseItem
import com.juanma.exercise.countryflags.ui.MainActivity
import com.juanma.exercise.countryflags.ui.viewmodel.CountryViewModel
import com.juanma.exercise.countryflags.util.Resource

class HomeFragment : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener, MenuProvider {
    private var homeBinding: FragmentHomeBinding? = null
    private val binding get() = homeBinding!!

    private lateinit var countryViewModel: CountryViewModel
    private lateinit var countryAdapter: CountryAdapter
    private lateinit var menuHost: MenuHost
    private val countries = mutableListOf<CountriesResponseItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFragment()
        getCountries()
        setupHomeRecyclerView()
    }

    private fun setupFragment() {
        countryViewModel = (activity as MainActivity).countryViewModel
        menuHost= requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroy() {
        super.onDestroy()

        homeBinding = null
    }

    private fun setupHomeRecyclerView() {
        countryAdapter = CountryAdapter(countries)
        binding.homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = countryAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getCountries() {
        countryViewModel.countries.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {

                    response.data?.let { newResponse ->
                        countries.clear()
                        countries.addAll(newResponse)
                        countryAdapter.notifyDataSetChanged()
                    }
                }

                is Resource.Error -> {
                    response.message.let { message ->
                        Toast.makeText(activity, "Sorry error: $message", Toast.LENGTH_SHORT).show()
                    }
                }

                is Resource.Loading -> {
                }
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText != null){
            filterByName(newText)
        }

        return true
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.search_menu, menu)

        val menuSearch = menu.findItem(R.id.searchMenu).actionView as SearchView
        menuSearch.isSubmitButtonEnabled = false
        menuSearch.setOnQueryTextListener(this)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }

    private fun filterByName(name: String){
        val countriesFiltered = countries.filter { country ->
            country.name.official.lowercase().contains(name.lowercase())
        }

        countryAdapter.updateCountries(countriesFiltered)
    }
}