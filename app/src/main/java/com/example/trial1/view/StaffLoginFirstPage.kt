package com.example.trial1.view

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.trial1.R

import com.example.trial1.databinding.ActivityStaffLoginFirstPageBinding
import com.example.trial1.model.CustomerDetails
import com.example.trial1.viewmodel.SLFViewModel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import java.util.ArrayList

class StaffLoginFirstPage : AppCompatActivity() {
    lateinit var staffLoginViewModel :SLFViewModel
    private lateinit var bindingStaffLoginFirstPage: ActivityStaffLoginFirstPageBinding
    private lateinit var customerIdLayout: TextInputLayout
    private lateinit var inputId: String
    private lateinit var customerDetails: ArrayList<CustomerDetails>

    @DelicateCoroutinesApi
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        bindingStaffLoginFirstPage = ActivityStaffLoginFirstPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(bindingStaffLoginFirstPage.root)
        staffLoginViewModel=ViewModelProvider(this)[SLFViewModel::class.java]

        staffLoginViewModel.button1Click.observe(this, Observer {
            if(it){
                if(customerIdLayout.visibility == View.GONE){
                        customerIdLayout.visibility = View.VISIBLE
                        bindingStaffLoginFirstPage.idSignInCustomerFromStaffLogin.visibility = View.VISIBLE}
                else{
                        customerIdLayout.visibility = View.GONE
                        bindingStaffLoginFirstPage.idSignInCustomerFromStaffLogin.visibility = View.GONE
                    }
            }
        })
        staffLoginViewModel.button2Click.observe(this, Observer {
            if(it){
                handleNewCustomerCreation()
            }
        })
          staffLoginViewModel.button3Click.observe(this, Observer {
              if(it){
                  val savingsInterest = staffLoginViewModel.getSavingsInterestRate()
                  val currentInterestRate = staffLoginViewModel.getCurrentInterestRate()
                  bindingStaffLoginFirstPage.idCurrentInterest.text = "Current Account Interest Rate is $currentInterestRate"
                  bindingStaffLoginFirstPage.idSavingsInterest.text = "Savings Account Interest Rate is $savingsInterest"
                  if(bindingStaffLoginFirstPage.idCurrentInterest.visibility == View.GONE){

                      bindingStaffLoginFirstPage.idCurrentInterest.visibility = View.VISIBLE
                      bindingStaffLoginFirstPage.idSavingsInterest.visibility = View.VISIBLE}
                  else{

                      bindingStaffLoginFirstPage.idCurrentInterest.visibility = View.GONE
                      bindingStaffLoginFirstPage.idSavingsInterest.visibility = View.GONE
                  }
              }
          })
          staffLoginViewModel.button4Click.observe(this, Observer {
              if(it){
                  val count = staffLoginViewModel.getTotalNoOfCustomers()
                  bindingStaffLoginFirstPage.idCustomerCount.text = "The total no of customers is $count"
                  if(bindingStaffLoginFirstPage.idCustomerCount.visibility == View.GONE){

                      bindingStaffLoginFirstPage.idCustomerCount.visibility = View.VISIBLE}
                  else{

                      bindingStaffLoginFirstPage.idCustomerCount.visibility = View.GONE
                  }
              }
          })

        customerIdLayout = findViewById(R.id.id_customerId_staffLogin)
        customerDetails = staffLoginViewModel.getCustomerDetails()

        handleCustomerSelection()
        handleNewCustomerCreation()
        handleInterestRateView()
        handleTotalCustomerView()
        handleCustomerSignInFromStaff()
    }

    @DelicateCoroutinesApi
    private fun handleCustomerSignInFromStaff() {
        bindingStaffLoginFirstPage.idSignInCustomerFromStaffLogin.setOnClickListener {

            inputId = bindingStaffLoginFirstPage.idCustomerIdStaffLogin.editText!!.text.toString()
            GlobalScope.launch(Dispatchers.IO) {
                doCall()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun handleTotalCustomerView() {

        bindingStaffLoginFirstPage.idTotalNoOfCustomers.setOnClickListener {

            val count = staffLoginViewModel.getTotalNoOfCustomers()
            bindingStaffLoginFirstPage.idCustomerCount.text = "The total no of customers is $count"
            if(bindingStaffLoginFirstPage.idCustomerCount.visibility == View.GONE){
                staffLoginViewModel.button4Click.value=true
            bindingStaffLoginFirstPage.idCustomerCount.visibility = View.VISIBLE}
            else{
                staffLoginViewModel.button4Click.value=false
                bindingStaffLoginFirstPage.idCustomerCount.visibility = View.GONE
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun handleInterestRateView() {

        bindingStaffLoginFirstPage.idViewInterestRate.setOnClickListener {

            val savingsInterest = staffLoginViewModel.getSavingsInterestRate()
            val currentInterestRate = staffLoginViewModel.getCurrentInterestRate()
            bindingStaffLoginFirstPage.idCurrentInterest.text = "Current Account Interest Rate is $currentInterestRate"
            bindingStaffLoginFirstPage.idSavingsInterest.text = "Savings Account Interest Rate is $savingsInterest"
            if(bindingStaffLoginFirstPage.idCurrentInterest.visibility == View.GONE){
                staffLoginViewModel.button3Click.value=true
            bindingStaffLoginFirstPage.idCurrentInterest.visibility = View.VISIBLE
            bindingStaffLoginFirstPage.idSavingsInterest.visibility = View.VISIBLE}
            else{
                staffLoginViewModel.button3Click.value=false
                bindingStaffLoginFirstPage.idCurrentInterest.visibility = View.GONE
                bindingStaffLoginFirstPage.idSavingsInterest.visibility = View.GONE
            }
        }
    }

    private fun handleNewCustomerCreation() {

        bindingStaffLoginFirstPage.idNewCustomerCreation.setOnClickListener {
            staffLoginViewModel.button2Click.value=true
            val intent = Intent(this, CustomerSignUpForm::class.java)
            startActivity(intent)
        }
    }

    private fun handleCustomerSelection() {

        bindingStaffLoginFirstPage.idCustomerSelection.setOnClickListener {

            if(customerIdLayout.visibility == View.GONE){
                staffLoginViewModel.button1Click.value=true
            customerIdLayout.visibility = View.VISIBLE
            bindingStaffLoginFirstPage.idSignInCustomerFromStaffLogin.visibility = View.VISIBLE}
            else{
                staffLoginViewModel.button1Click.value=false
                customerIdLayout.visibility = View.GONE
                bindingStaffLoginFirstPage.idSignInCustomerFromStaffLogin.visibility = View.GONE
            }
        }
    }

    private fun doCall() {
        val result = staffLoginViewModel.checkForCustomerId(inputId)
        if (result) {
            val sp: SharedPreferences = getSharedPreferences("inputId", MODE_PRIVATE)
            val ed: SharedPreferences.Editor = sp.edit()
            ed.putString("customerIdInput", inputId)
            ed.apply()
            val intent = Intent(this, StaffLoginCustomerPage::class.java)
            startActivity(intent)
        }
    }
}