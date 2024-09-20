package com.juanma.exercise.countryflags.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juanma.exercise.countryflags.databinding.ItemCountryBinding
import com.juanma.exercise.countryflags.models.CountriesResponseItem

class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemCountryBinding.bind(view)

    fun bind(item: CountriesResponseItem) {
        binding.apply {
            Glide.with(itemView).load(item.flags.png).into(flagImage)
            nameOfficial.text = item.name.official
            nameCommon.text = item.name.common
        }
    }
}