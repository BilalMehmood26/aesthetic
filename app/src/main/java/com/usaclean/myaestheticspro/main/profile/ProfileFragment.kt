package com.usaclean.myaestheticspro.main.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.navigation.fragment.findNavController
import com.usaclean.myaestheticspro.R
import com.usaclean.myaestheticspro.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater)
        binding.apply {

            accountLayout.setOnClickListener {
                val action = ProfileFragmentDirections.actionProfileFragment2ToAccountFragment()
                findNavController().navigate(action)
            }
            logoutIv.setOnClickListener {
                requireActivity().finish()
            }
            supportLayout.setOnClickListener {
                val action = ProfileFragmentDirections.actionProfileFragment2ToSupportFragment()
                findNavController().navigate(action)
            }

            faqsLayout.setOnClickListener {
                val action = ProfileFragmentDirections.actionProfileFragment2ToFaqFragment()
                findNavController().navigate(action)
            }
        }
        return binding.root
    }
}