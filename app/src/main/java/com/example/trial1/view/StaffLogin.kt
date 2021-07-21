package com.example.trial1.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.trial1.R
import com.example.trial1.databinding.ActivityStaffLoginBinding

import com.example.trial1.viewmodel.SLViewModel
import com.google.android.material.textfield.TextInputLayout


class StaffLogin : AppCompatActivity() {

    private lateinit var binding: ActivityStaffLoginBinding
    lateinit var slViewModel:SLViewModel
    private lateinit var staffId: TextInputLayout
    private lateinit var staffPassword: TextInputLayout
    private lateinit var inputStaffId: Editable
    private lateinit var inputStaffPassword: Editable

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityStaffLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        slViewModel= ViewModelProvider(this)[SLViewModel::class.java]
        slViewModel.currentId.observe(this, Observer {
            binding.idStaffId.editText!!.text=it
        })
        slViewModel.currentPass.observe(this, Observer {
            binding.idStaffPassword.editText!!.text=it
        })
        handleStaffSignIn()
    }

    private fun handleStaffSignIn() {
        binding.idSignInStaff.setOnClickListener {
            staffId = findViewById(R.id.id_staffId)
            staffPassword = findViewById(R.id.id_staff_password)
            inputStaffId = staffId.editText!!.text
            slViewModel.currentId.value=inputStaffId
            inputStaffPassword = staffPassword.editText!!.text
            slViewModel.currentPass.value=inputStaffPassword
            val resultOfIdValidation = slViewModel.validateStaffId(inputStaffId,binding)
            val resultOfPasswordValidation =  slViewModel.validateStaffPassword(inputStaffPassword,binding)
            if (resultOfIdValidation && resultOfPasswordValidation) {
                val resultOfId=slViewModel.checkForId(inputStaffId.toString(),inputStaffPassword.toString())
                if(resultOfId ){
                val intent = Intent(this, StaffLoginFirstPage::class.java)
                startActivity(intent)}
            }
        }
    }
}