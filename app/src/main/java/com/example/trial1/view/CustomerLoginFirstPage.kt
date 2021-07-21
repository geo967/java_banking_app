package com.example.trial1.view

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.trial1.databinding.ActivityCustomerLoginFirstPageBinding
import com.example.trial1.database.Customer
import com.example.trial1.model.CustomerDetails
import com.example.trial1.viewmodel.CLFViewModel
import java.util.ArrayList

class CustomerLoginFirstPage : AppCompatActivity() {
    lateinit var clfViewModel: CLFViewModel
    lateinit var binding: ActivityCustomerLoginFirstPageBinding
    private lateinit var customerDetails: ArrayList<CustomerDetails>
    private lateinit var creditInput: String
    private lateinit var debitInput: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCustomerLoginFirstPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        clfViewModel=ViewModelProvider(this)[CLFViewModel::class.java]

        clfViewModel.button1.observe(this, Observer {
            if(it){
                val sp: SharedPreferences = getSharedPreferences("inputCId", MODE_PRIVATE)
                val getInput: String? = sp.getString("customerIdInput", "")
                for (i in 0 until customerDetails.size) {
                    if (customerDetails[i].customerId == getInput.toString()) {
                        binding.idMyBalance.text = "your balance is " + customerDetails[i].balance
                    }
                }
                if(binding.idMyBalance.visibility == View.GONE){

                    binding.idMyBalance.visibility = View.VISIBLE}
                else{

                    binding.idMyBalance.visibility = View.GONE
                }
            }
        })
        clfViewModel.button2.observe(this, Observer {
            if(it){
                if( binding.layout.visibility == View.GONE){

                    binding.layout.visibility = View.VISIBLE}
                else{

                    binding.layout.visibility = View.GONE
                }
            }
        })
        clfViewModel.button3.observe(this, Observer {
            if(it){
                val sp: SharedPreferences = getSharedPreferences("inputCId", MODE_PRIVATE)
                val getInput: String? = sp.getString("customerIdInput", "")
                if( binding.idInterestRate.visibility == View.GONE){
                    binding.idInterestRate.visibility = View.VISIBLE}
                else{
                    binding.idInterestRate.visibility = View.GONE
                }
                clfViewModel.viewInterest(binding, getInput)
            }
        })
        clfViewModel.button4.observe(this, Observer {
            if(it){
                val sp: SharedPreferences = getSharedPreferences("inputCId", MODE_PRIVATE)
                val getInput: String? = sp.getString("customerIdInput", "")
                clfViewModel.populateData(binding, getInput)
                if(binding.linearLayoutCustomerDetails.visibility == View.GONE){

                    binding.linearLayoutCustomerDetails.visibility = View.VISIBLE}
                else{

                    binding.linearLayoutCustomerDetails.visibility = View.GONE
                }
            }
        })

        customerDetails = Customer.customerDetails
        val sp: SharedPreferences = getSharedPreferences("inputCId", MODE_PRIVATE)
        val getInput: String? = sp.getString("customerIdInput", "")

        handleShowBalanceClick(getInput)
        handleMakeTransactionClick()
        handleCreditButtonClick()
        handleDebitButtonClick()
        handleCreditCofirmation(getInput)
        handleDebitConfirmation(getInput)
        handleViewInterest(getInput)
        handleViewAccInfoClick(getInput)
    }

    private fun handleViewAccInfoClick(getInput: String?) {
        binding.idCustomerAccInfo.setOnClickListener {
            clfViewModel.populateData(binding, getInput)
            if(binding.linearLayoutCustomerDetails.visibility == View.GONE){
                clfViewModel.button4.value=true
            binding.linearLayoutCustomerDetails.visibility = View.VISIBLE}
            else{
                clfViewModel.button4.value=false
                binding.linearLayoutCustomerDetails.visibility = View.GONE
            }
        }
    }

    private fun handleViewInterest(getInput: String?) {
        binding.idViewInterestRate.setOnClickListener {
            if( binding.idInterestRate.visibility == View.GONE){
                clfViewModel.button3.value=true
            binding.idInterestRate.visibility = View.VISIBLE}
            else{
                clfViewModel.button3.value=false
                binding.idInterestRate.visibility = View.GONE
            }
            clfViewModel.viewInterest(binding, getInput)
        }
    }

    private fun handleDebitConfirmation(getInput: String?) {
        binding.debitConf.setOnClickListener {
            debitInput = binding.idDebitText.text.toString()
            clfViewModel.debitAmount(debitInput.toDouble(), binding, getInput)
            binding.layout.visibility = View.GONE
            binding.inputLayout.visibility = View.GONE
            binding.debitConf.visibility=View.GONE
        }
    }

    private fun handleCreditCofirmation(getInput: String?) {
        binding.creditConf.setOnClickListener {
            creditInput = binding.idCreditText.text.toString()
            clfViewModel.creditAmount(creditInput.toDouble(), binding, getInput)
            binding.layout.visibility = View.GONE
            binding.inputLayout.visibility = View.GONE
            binding.creditConf.visibility=View.GONE
        }
    }

    private fun handleDebitButtonClick() {
        binding.debitButton.setOnClickListener {
            if(binding.inputLayout.visibility == View.GONE){
            binding.inputLayout.visibility = View.VISIBLE
            binding.idDebitText.visibility = View.VISIBLE
            binding.idTransConfi.visibility = View.VISIBLE
            binding.debitConf.visibility = View.VISIBLE}
            else{
                binding.inputLayout.visibility = View.GONE
            }

        }
    }

    private fun handleCreditButtonClick() {
        binding.creditButton.setOnClickListener {
            if(binding.inputLayout.visibility == View.GONE){
            binding.inputLayout.visibility = View.VISIBLE
            binding.idCreditText.visibility = View.VISIBLE
            binding.idTransConfi.visibility = View.VISIBLE
            binding.creditConf.visibility = View.VISIBLE}
            else{
                binding.inputLayout.visibility = View.GONE
            }
        }
    }

    private fun handleMakeTransactionClick() {
        binding.idCustomerMakeTransaction.setOnClickListener {
            if( binding.layout.visibility == View.GONE){
                clfViewModel.button2.value=true
            binding.layout.visibility = View.VISIBLE}
            else{
                clfViewModel.button2.value=false
                binding.layout.visibility = View.GONE
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun handleShowBalanceClick(getInput: String?) {
        binding.idShowBalance.setOnClickListener {
            for (i in 0 until customerDetails.size) {
                if (customerDetails[i].customerId == getInput.toString()) {
                    binding.idMyBalance.text = "your balance is " + customerDetails[i].balance
                }
            }
            if(binding.idMyBalance.visibility == View.GONE){
                clfViewModel.button1.value=true
            binding.idMyBalance.visibility = View.VISIBLE}
            else{
                clfViewModel.button1.value=false
                binding.idMyBalance.visibility = View.GONE
            }
        }
    }
}