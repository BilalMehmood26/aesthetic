package com.usaclean.myaestheticspro.main.faq

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.usaclean.myaestheticspro.R
import com.usaclean.myaestheticspro.databinding.FragmentFaqBinding
import com.usaclean.myaestheticspro.databinding.FragmentSupportBinding

class FaqFragment : Fragment() {

    private var _binding: FragmentFaqBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFaqBinding.inflate(inflater)
        binding.apply {
            backIv.setOnClickListener {
                findNavController().popBackStack()
            }
        }
        return binding.root
    }
}