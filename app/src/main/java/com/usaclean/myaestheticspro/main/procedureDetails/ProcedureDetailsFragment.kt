package com.usaclean.myaestheticspro.main.procedureDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.usaclean.myaestheticspro.R
import com.usaclean.myaestheticspro.databinding.FragmentProcedureDetailsBinding
import com.usaclean.myaestheticspro.databinding.FragmentProcedureReportBinding


class ProcedureDetailsFragment : Fragment() {

    private var _binding: FragmentProcedureDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProcedureDetailsBinding.inflate(inflater)
        binding.apply {

            backIv.setOnClickListener {
                findNavController().popBackStack()
            }
        }
        return binding.root
    }
}