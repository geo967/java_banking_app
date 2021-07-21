package com.example.trial1.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trial1.databinding.ActivityMainBinding
import com.example.trial1.viewmodel.MainActivityViewModel


class MainActivity : AppCompatActivity() {
    private val mainActivityViewModel = MainActivityViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        handleStaffSelection(binding)
        handleCustomerSelection(binding)
        mainActivityViewModel.initialisation()
    }

    private fun handleCustomerSelection(binding: ActivityMainBinding) {
        binding.customerLogin.setOnClickListener {
            val intent = Intent(this, CustomerLogin::class.java)
            startActivity(intent)
        }
    }

    private fun handleStaffSelection(binding: ActivityMainBinding) {
        binding.staffLogin.setOnClickListener {
            val intent = Intent(this, StaffLogin::class.java)
            startActivity(intent)
        }
    }
}