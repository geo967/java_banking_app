package com.example.trial1.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trial1.model.Bank
import com.example.trial1.database.Customer
import com.example.trial1.databinding.ActivityCustomerLoginFirstPageBinding
import com.example.trial1.model.CustomerDetails

class CLFViewModel:ViewModel() {
    private lateinit var customerDetails:ArrayList<CustomerDetails>
    //live data
    val button1:MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val button2:MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val button3:MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val button4:MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    //connecting to db

    fun getCustomerDetails():ArrayList<CustomerDetails>{
       return Customer.customerDetails
    }

    fun creditAmount(amt:Double,binding: ActivityCustomerLoginFirstPageBinding,getInput:String?){
        customerDetails= getCustomerDetails()
        for (i in 0 until customerDetails.size) {
            if (customerDetails[i].customerId== getInput) {
                customerDetails[i].balance+=amt
            }
        }
    }

    fun debitAmount(amt:Double,binding: ActivityCustomerLoginFirstPageBinding,getInput:String?){
        customerDetails= getCustomerDetails()
        for (i in 0 until customerDetails.size) {
            if (customerDetails[i].customerId== getInput.toString()) {
                customerDetails[i].balance-=amt
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun viewInterest(binding: ActivityCustomerLoginFirstPageBinding, getInput: String?){
        customerDetails= getCustomerDetails()

        for (i in 0 until customerDetails.size) {
            if (customerDetails[i].customerId == getInput.toString()) {
                if(customerDetails[i].customerAccType=="savings"){
                    val b=Bank()
                    val interest=(customerDetails[i].balance* b.savingsInterest*.01)
                    binding.idInterestRate.text= "Your interest amount is :$interest"
                }
                if(customerDetails[i].customerAccType=="current"){
                    val b=Bank()
                    val interest=(customerDetails[i].balance* b.currentInterest*.01)
                    binding.idInterestRate.text= "Your interest amount is :$interest"
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun populateData(binding: ActivityCustomerLoginFirstPageBinding, getInput: String?){
        customerDetails= getCustomerDetails()
        for (i in 0 until customerDetails.size) {
            if (customerDetails[i].customerId == getInput.toString()) {

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
    //connecting vm and view
}