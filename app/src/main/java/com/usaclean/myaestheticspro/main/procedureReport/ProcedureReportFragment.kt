package com.usaclean.myaestheticspro.main.procedureReport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.usaclean.myaestheticspro.R
import com.usaclean.myaestheticspro.databinding.FragmentAccountBinding
import com.usaclean.myaestheticspro.databinding.FragmentProcedureReportBinding


class ProcedureReportFragment : Fragment() {

    private var _binding: FragmentProcedureReportBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProcedureReportBinding.inflate(inflater)
        binding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = ProcedureReportsAdapter(requireContext(), arrayListOf()){
                    val action = ProcedureReportFragmentDirections.actionProcedureReportFragmentToProcedureDetailsFragment()
                    findNavController().navigate(action)
                }
            }
        }
        return binding.root
    }
}