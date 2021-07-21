package com.example.trial1.view

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.trial1.databinding.ActivityStaffLoginCustomerPageBinding
import com.example.trial1.model.CustomerDetails
import com.example.trial1.viewmodel.SLCPViewModel
import java.util.ArrayList

class StaffLoginCustomerPage : AppCompatActivity() {
    lateinit var slcpViewModel :SLCPViewModel
    private lateinit var binding: ActivityStaffLoginCustomerPageBinding
    private lateinit var customerDetails: ArrayList<CustomerDetails>

    private lateinit var creditInput: String
    private lateinit var debitInput: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityStaffLoginCustomerPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        slcpViewModel=ViewModelProvider(this)[SLCPViewModel::class.java]

        slcpViewModel.button1Click.observe(this, Observer {
            if(it){
                val sp: SharedPreferences = getSharedPreferences("inputId", MODE_PRIVATE)
                val getInput: String? = sp.getString("customerIdInput", "")
                slcpViewModel.populateData(getInput, binding)
                if ( binding.linearLayoutCustomerDetails.visibility == View.GONE){
                    binding.linearLayoutCustomerDetails.visibility = View.VISIBLE}
                else{
                    binding.linearLayoutCustomerDetails.visibility = View.GONE
                }
            }
        })
        slcpViewModel.button2Click.observe(this, Observer {
            if(it){
                if(binding.idTransLayout.visibility == View.GONE){
                    binding.idTransLayout.visibility = View.VISIBLE
                    binding.creditButton.visibility = View.VISIBLE
                    binding.debitButton.visibility = View.VISIBLE}
                else{
                    binding.idTransLayout.visibility = View.GONE
                }
            }
        })
        slcpViewModel.button3Click.observe(this, Observer {
            if(it){
                if(binding.displayInterest.visibility == View.GONE){

                    binding.displayInterest.visibility = View.VISIBLE}
                else{

                    binding.displayInterest.visibility = View.GONE
                }
                val sp: SharedPreferences = getSharedPreferences("inputId", MODE_PRIVATE)
                val getInput: String? = sp.getString("customerIdInput", "")
                slcpViewModel.displayInterest(getInput, binding)
            }
        })

        val sp: SharedPreferences = getSharedPreferences("inputId", MODE_PRIVATE)
        val getInput: String? = sp.getString("customerIdInput", "")

        customerDetails = slcpViewModel.getCustomerDetails()
        handleCustomerInfoClick()
        handleMakeTransactionClick()
        handleCreditButtonClick()
        handleDebitButtonClick()
        handleCreditConfButtonClick(getInput)
        handleDebitConfButtonClick(getInput)
        handleInterestButtonClick(getInput)
    }

    private fun handleInterestButtonClick(getInput: String?) {
        binding.idCustomerInterestAmount.setOnClickListener {
            if(binding.displayInterest.visibility == View.GONE){
                slcpViewModel.button3Click.value=true
            binding.displayInterest.visibility = View.VISIBLE}
            else{
                slcpViewModel.button3Click.value=false
                binding.displayInterest.visibility = View.GONE
            }
            slcpViewModel.displayInterest(getInput, binding)
        }
    }

    private fun handleDebitConfButtonClick(getInput: String?) {
        binding.debitConf.setOnClickListener {
            debitInput = binding.idDebitText.text.toString()
            slcpViewModel.doDebitOperation(getInput, debitInput.toDouble(), binding)

                binding.idTransLayout.visibility = View.GONE
            binding.enterAmount.visibility = View.GONE
            binding.debitConf.visibility=View.GONE
        }
    }

    private fun handleCreditConfButtonClick(getInput: String?) {
        binding.creditConf.setOnClickListener {
            creditInput = binding.idCreditText.text.toString()
            slcpViewModel.doCreditOperation(getInput, creditInput.toDouble(), binding)

                binding.idTransLayout.visibility = View.GONE
            binding.enterAmount.visibility = View.GONE
            binding.creditConf.visibility=View.GONE

        }
    }

    private fun handleDebitButtonClick() {
        binding.debitButton.setOnClickListener {
            if(binding.enterAmount.visibility == View.GONE){
            binding.enterAmount.visibility = View.VISIBLE
            binding.idDebitText.visibility = View.VISIBLE
            binding.idTransConfi.visibility = View.VISIBLE
            binding.debitConf.visibility = View.VISIBLE}
            else{
                binding.enterAmount.visibility = View.GONE
            }
        }
    }

    private fun handleCreditButtonClick() {
        binding.creditButton.setOnClickListener {
            if(binding.enterAmount.visibility == View.GONE){
            binding.enterAmount.visibility = View.VISIBLE
            binding.idCreditText.visibility = View.VISIBLE
            binding.idTransConfi.visibility = View.VISIBLE
            binding.creditConf.visibility = View.VISIBLE}
            else{
                binding.enterAmount.visibility =View.GONE
            }
        }
    }

    private fun handleMakeTransactionClick() {
        binding.idCustomerMakeTransaction.setOnClickListener {
            if(binding.idTransLayout.visibility == View.GONE){
                slcpViewModel.button2Click.value=true
            binding.idTransLayout.visibility = View.VISIBLE
            binding.creditButton.visibility = View.VISIBLE
            binding.debitButton.visibility = View.VISIBLE}
            else{
                slcpViewModel.button2Click.value=false
                binding.idTransLayout.visibility = View.GONE
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun handleCustomerInfoClick() {
        binding.idCustomerAccInfo.setOnClickListener {
            val sp: SharedPreferences = getSharedPreferences("inputId", MODE_PRIVATE)
            val getInput: String? = sp.getString("customerIdInput", "")
            slcpViewModel.populateData(getInput, binding)
            if ( binding.linearLayoutCustomerDetails.visibility == View.GONE){
                slcpViewModel.button1Click.value=true
            binding.linearLayoutCustomerDetails.visibility = View.VISIBLE}
            else{
                slcpViewModel.button1Click.value=false
                binding.linearLayoutCustomerDetails.visibility = View.GONE
            }
        }
    }
}