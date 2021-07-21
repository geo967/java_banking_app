package com.example.trial1.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trial1.model.Bank
import com.example.trial1.database.Customer
import com.example.trial1.databinding.ActivityStaffLoginCustomerPageBinding
import com.example.trial1.model.CustomerDetails

class SLCPViewModel:ViewModel() {
    private lateinit var customerDetails:ArrayList<CustomerDetails>
    //live data
    val button1Click:MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val button2Click:MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val button3Click:MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    //connecting db via repo
    fun getCustomerDetails():ArrayList<CustomerDetails>{
        return Customer.customerDetails
    }

    //connecting view and view model
    fun doCreditOperation(getInput:String?,cr:Double,binding:ActivityStaffLoginCustomerPageBinding){
        customerDetails=Customer.customerDetails
        for (i in 0 until customerDetails.size) {
            if (customerDetails[i].customerId== getInput.toString()) {
                customerDetails[i].balance+=cr
            }
        }
    }
    fun doDebitOperation(getInput:String?,dr:Double,binding:ActivityStaffLoginCustomerPageBinding){
        customerDetails=Customer.customerDetails
        for (i in 0 until customerDetails.size) {
            if (customerDetails[i].customerId== getInput.toString()) {
                customerDetails[i].balance-=dr
            }
        }
    }
    @SuppressLint("SetTextI18n")
    fun displayInterest(getInput:String?, binding: ActivityStaffLoginCustomerPageBinding){
        customerDetails=Customer.customerDetails
        for (i in 0 until customerDetails.size) {
            if (customerDetails[i].customerId == getInput.toString()) {
                if(customerDetails[i].customerAccType=="savings"){
                    val b=Bank()
                    val interest=(customerDetails[i].balance* Bank().savingsInterest*.01)
                    binding.displayInterest.text= "Your interest amount is :$interest"
                }
                if(customerDetails[i].customerAccType=="current"){
                    val interest=(customerDetails[i].balance* Bank().currentInterest*.01)
                    binding.displayInterest.text= "Your interest amount is :$interest"
                }
            }
        }
    }
    @SuppressLint("SetTextI18n")
    fun populateData(inputId:String?, binding: ActivityStaffLoginCustomerPageBinding){
        customerDetails=Customer.customerDetails
        for (i in 0 until customerDetails.size) {
            if (customerDetails[i].customerId == inputId.toString()) {
                binding.idCustomerId.text =
                        String.format("Customer Id is %s", customerDetails[i].customerId)
                binding.name.text =
                        "Customer Name :"+ customerDetails[i].customerName
                binding.password.text =
                        "Customer Password :"+ customerDetails[i].customerPassword
                binding.accountNo.text =
                        "Customer Acc No :"+
                                customerDetails[i].customerAccNo.toString()
                binding.idAccountType.text =
                        "Customer Account Type :"+ customerDetails[i].customerAccType
                binding.idPhone.text =
                        "Customer Phone No :"+customerDetails[i].customerPhoneNo
                binding.idAge.text =
                        "Customer Age"+customerDetails[i].customerAge.toString()
                binding.idBalance.text="Balance is"+customerDetails[i].balance

            }
        }
    }
}