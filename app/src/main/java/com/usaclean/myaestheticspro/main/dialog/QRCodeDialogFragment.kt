package com.usaclean.myaestheticspro.main.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.usaclean.myaestheticspro.R
import com.usaclean.myaestheticspro.databinding.FragmentInventoryDetailsBinding
import com.usaclean.myaestheticspro.databinding.FragmentQRCodeDialogBinding

class QRCodeDialogFragment : DialogFragment() {

    private var _binding: FragmentQRCodeDialogBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQRCodeDialogBinding.inflate(inflater)
        binding.apply {

        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }
}