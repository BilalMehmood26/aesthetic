package com.usaclean.myaestheticspro.main.inventoryDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.usaclean.myaestheticspro.R
import com.usaclean.myaestheticspro.databinding.FragmentInventoryDetailsBinding
import com.usaclean.myaestheticspro.databinding.FragmentProcedureDetailsBinding
import com.usaclean.myaestheticspro.main.home.HomeFragmentDirections

class InventoryDetailsFragment : Fragment() {

    private var _binding: FragmentInventoryDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInventoryDetailsBinding.inflate(inflater)
        binding.apply {

            backIv.setOnClickListener {
                findNavController().popBackStack()
            }

            generateBtn.setOnClickListener {
                val action = InventoryDetailsFragmentDirections.actionInventoryDetailsFragmentToGenerateReportFragment()
                findNavController().navigate(action)
            }
        }
        return binding.root
    }
}