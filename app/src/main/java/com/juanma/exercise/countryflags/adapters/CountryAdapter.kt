package com.juanma.exercise.countryflags.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.juanma.exercise.countryflags.R
import com.juanma.exercise.countryflags.models.CountriesResponseItem
import com.juanma.exercise.countryflags.ui.fragments.HomeFragmentDirections

class CountryAdapter(private val countries: List<CountriesResponseItem>) :
    RecyclerView.Adapter<CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return CountryViewHolder(layoutInflater.inflate(R.layout.item_country, parent, false))
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val item = countries[position]
        holder.apply {
            bind(item)
            itemView.setOnClickListener {
                val direction =
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(item.name.common)
                it.findNavController().navigate(direction)
            }
        }
    }
}