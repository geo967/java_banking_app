package com.example.trial1.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trial1.model.Bank
import com.example.trial1.database.Customer
import com.example.trial1.model.CustomerDetails


class SLFViewModel:ViewModel() {
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
    val button4Click:MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    //connecting to db via repo

    fun getCustomerDetails():ArrayList<CustomerDetails>{
        return Customer.customerDetails
    }

    fun checkForCustomerId(inputId:String):Boolean{
        customerDetails=Customer.customerDetails
        for (i in 0 until customerDetails.size) {
            if (customerDetails[i].customerId == inputId) {
                return true
            }
        }
        return false
    }

    fun getTotalNoOfCustomers():Int{
        val b=Bank()
        return Customer.customerDetails.size
    }
    fun getSavingsInterestRate():Double{
        val b=Bank()
        return b.savingsInterest
    }
    fun getCurrentInterestRate():Double{
        val b=Bank()
        return b.currentInterest
    }

    //connecting view model and view
}