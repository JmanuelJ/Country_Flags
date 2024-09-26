package com.juanma.exercise.countryflags.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.juanma.exercise.countryflags.R
import com.juanma.exercise.countryflags.databinding.FragmentCountryDetailBinding
import com.juanma.exercise.countryflags.models.CountryInfoResponseItem
import com.juanma.exercise.countryflags.ui.MainActivity
import com.juanma.exercise.countryflags.ui.viewmodel.CountryViewModel
import com.juanma.exercise.countryflags.util.Resource


class CountryDetailFragment : Fragment(R.layout.fragment_country_detail) {
    private var detailBinding: FragmentCountryDetailBinding? = null
    private val binding get() = detailBinding!!
    private lateinit var countryViewModel: CountryViewModel
    private val args: CountryDetailFragmentArgs by navArgs()
    private lateinit var information: CountryInfoResponseItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailBinding = FragmentCountryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countryViewModel = (activity as MainActivity).countryViewModel

        getInfo()
        checkInfo()
        onClickViewMap()
    }

    private fun onClickViewMap() {

        binding.mapBtn.setOnClickListener {
            val direction = CountryDetailFragmentDirections.actionDetailFragmentToViewMap(information.maps.googleMaps)
            it.findNavController().navigate(direction)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        detailBinding = null
    }

    private fun getInfo() {
        countryViewModel.getInfo(args.officialName)
    }

    private fun checkInfo() {
        countryViewModel.info.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Error -> {
                    response.message.let { message ->
                        Toast.makeText(activity, "Sorry error: $message", Toast.LENGTH_SHORT).show()

                    }
                }

                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    information = response.data!![0]
                    binding.apply {
                        Glide.with(requireActivity()).load(information.flags.png).into(flagImage)
                        nameOfficial.text = information.name.official
                        nameCommon.text = information.name.common
                        nameCapital.text = information.capital.toString()
                        continent.text = information.continents.toString()
                        if (information.flags.alt == null) {
                            flagDescription.text = "Sorry! There is no description"
                        } else {
                            flagDescription.text = information.flags.alt
                        }
                        if (information.coatOfArms.png == null) {
                            coatOfArms.setImageResource(R.drawable.no_fotos)
                        } else {
                            Glide.with(requireActivity()).load(information.coatOfArms.png)
                                .into(coatOfArms)
                        }
                    }
                }
            }
        }
    }
}
