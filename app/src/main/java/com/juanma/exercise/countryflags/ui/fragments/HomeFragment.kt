package com.juanma.exercise.countryflags.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanma.exercise.countryflags.R
import com.juanma.exercise.countryflags.adapters.CountryAdapter
import com.juanma.exercise.countryflags.databinding.FragmentHomeBinding
import com.juanma.exercise.countryflags.models.CountriesResponseItem
import com.juanma.exercise.countryflags.ui.MainActivity
import com.juanma.exercise.countryflags.ui.viewmodel.CountryViewModel
import com.juanma.exercise.countryflags.util.Resource

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var homeBinding: FragmentHomeBinding? = null
    private val binding get() = homeBinding!!

    private lateinit var countryViewModel: CountryViewModel
    private lateinit var countryAdapter: CountryAdapter
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

        countryViewModel = (activity as MainActivity).countryViewModel

        setupHomeRecyclerView()
        getCountries()
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
}