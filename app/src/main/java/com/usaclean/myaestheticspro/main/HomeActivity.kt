package com.usaclean.myaestheticspro.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.usaclean.myaestheticspro.R
import com.usaclean.myaestheticspro.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            bottomNavigationView.itemIconTintList = null
            val navController = findNavController(R.id.main_fragment_Container)
            bottomNavigationView.setupWithNavController(navController)
        }
    }
}