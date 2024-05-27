package com.usaclean.myaestheticspro.main.generateReport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.usaclean.myaestheticspro.databinding.FragmentGenerateReportBinding
import com.usaclean.myaestheticspro.utils.CustomViewer
import com.usaclean.myaestheticspro.utils.MySurfaceView


class GenerateReportFragment : Fragment() {

    private var _binding: FragmentGenerateReportBinding? = null
    private val binding get() = _binding!!

    private lateinit var mySurfaceView: MySurfaceView
    var customViewer: CustomViewer = CustomViewer()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGenerateReportBinding.inflate(inflater)
        binding.apply {

            backIv.setOnClickListener {
                findNavController().popBackStack()
            }

              customViewer.run {
                loadEntity()
                setSurfaceView(requireNotNull(modelIV),requireContext())

                loadGltf(requireActivity(),"scene")

                //Enviroments and Lightning (OPTIONAL)
                loadIndirectLight(requireActivity(), "venetian_crossroads_2k")
                //loadEnviroment(requireContext(), "venetian_crossroads_2k");
            }

        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        customViewer.onResume()
    }

    override fun onPause() {
        super.onPause()
        customViewer.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        customViewer.onDestroy()
    }
}