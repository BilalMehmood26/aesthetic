package com.usaclean.myaestheticspro.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.usaclean.myaestheticspro.InventoryModel
import com.usaclean.myaestheticspro.R
import com.usaclean.myaestheticspro.databinding.FragmentHomeBinding
import com.usaclean.myaestheticspro.databinding.FragmentProcedureDetailsBinding
import com.usaclean.myaestheticspro.databinding.FragmentQRCodeDialogBinding
import com.usaclean.myaestheticspro.main.dialog.QRCodeDialogFragment
import com.usaclean.myaestheticspro.main.procedureReport.ProcedureReportFragmentDirections
import com.usaclean.myaestheticspro.main.procedureReport.ProcedureReportsAdapter


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var scanDialog : QRCodeDialogFragment

    private val productList : ArrayList<InventoryModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater)
        binding.apply {
            scanDialog = QRCodeDialogFragment()

            productList.add(InventoryModel("Restylane","Contour","1mL",R.drawable.ic_prod_one))
            productList.add(InventoryModel("Restylane","Defyne","1mL",R.drawable.ic_prod_two))
            productList.add(InventoryModel("Restylane","Kysse","80mL",R.drawable.ic_prod_three))
            productList.add(InventoryModel("Restylane","Lyft","80mL",R.drawable.ic_prod_four))
            productList.add(InventoryModel("Restylane","Refyne","80mL",R.drawable.ic_prod_five))
            productList.add(InventoryModel("Dysport","AbobotulinumtoxinA","300",R.drawable.ic_prod_six))
            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = InventoryAdapter(requireContext(), productList){
                    val action =HomeFragmentDirections.actionHomeFragmentToInventoryDetailsFragment()
                    findNavController().navigate(action)
                }
            }

            reportBtn.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToGenerateReportFragment()
                findNavController().navigate(action)
            }

            scanBtn.setOnClickListener {
                scanDialog.show(childFragmentManager,"")
            }
        }
        return binding.root
    }
}