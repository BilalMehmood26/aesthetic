package com.usaclean.myaestheticspro.auth.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.usaclean.myaestheticspro.R
import com.usaclean.myaestheticspro.databinding.FragmentSinginBinding
import com.usaclean.myaestheticspro.main.HomeActivity

class SinginFragment : Fragment() {

    private var _binding: FragmentSinginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSinginBinding.inflate(inflater)

        binding.apply {
            signInBtn.setOnClickListener {
                startActivity(Intent(requireActivity(),HomeActivity::class.java))
            }
        }
        return binding.root
    }
}