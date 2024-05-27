package com.usaclean.myaestheticspro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.usaclean.myaestheticspro.auth.LoginActivity
import com.usaclean.myaestheticspro.utils.postDelayed

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postDelayed(2000) {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }
    }
}