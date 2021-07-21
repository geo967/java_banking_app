package com.example.trial1.viewmodel

import android.text.Editable
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trial1.database.Customer
import com.example.trial1.database.Staff
import com.example.trial1.databinding.ActivityCustomerLoginBinding

import java.util.regex.Matcher
import java.util.regex.Pattern

class CLViewModel : ViewModel() {
    //live data

    val currentId: MutableLiveData<Editable> by lazy {
        MutableLiveData<Editable>()
    }
    val currentPass: MutableLiveData<Editable> by lazy {
        MutableLiveData<Editable>()
    }

    //connecting to db
    fun checkForId(getInput:String,getPass:String):Boolean{
        val customerDetails= Customer.customerDetails
        for (i in 0 until customerDetails.size) {
            if (customerDetails[i].customerId == getInput) {
                if(customerDetails[i].customerPassword == getPass){
                return true}
            }
        }
        return false
    }

    fun validateCustomerPassword(inputCustomerPassword: Editable, binding: ActivityCustomerLoginBinding): Boolean {
        val p: Pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=()])(?=\\S+\$).{8,20}\$")
        val m: Matcher = p.matcher(inputCustomerPassword)
        return if (inputCustomerPassword.equals("")) {
            binding.idPasswordCustomer.error = "Field is empty"
            false
        } else if (!m.matches()) {
            binding.idPasswordCustomer.error = "not a valid password format"
            false
        } else if (m.matches()) {
            true
        } else {
            binding.idPasswordCustomer.error = null
            false
        }
    }

    fun validateCustomerId(inputCustomerId: Editable, binding: ActivityCustomerLoginBinding): Boolean {
        val p: Pattern = Pattern.compile("[C][0-9]{4}")
        val m: Matcher = p.matcher(inputCustomerId)
        return if (inputCustomerId .equals("")) {
            binding.idCustomerId.error = "Field is empty"
            false
        } else if (!m.matches()) {
            binding.idCustomerId.error = "not a valid email format"
            false
        } else if (m.matches()) {
            true
        } else {
            binding.idCustomerId.error = null
            false
        }
    }

    //connecting view model and view
}