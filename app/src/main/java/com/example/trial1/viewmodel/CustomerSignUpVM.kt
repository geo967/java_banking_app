package com.example.trial1.viewmodel


import android.app.AlertDialog
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.trial1.database.Customer
import com.example.trial1.databinding.ActivityCustomerLoginBinding
import com.example.trial1.databinding.ActivityCustomerSignUpFormBinding
import com.example.trial1.model.CustomerDetails
import com.example.trial1.view.CustomerSignUpForm
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class CustomerSignUpVM:ViewModel() {
    private var mYear = 0
    private var mMonth: Int = 0
    private var mDay: Int = 0
    private var mage: Int = 0
    private var dobYear = 0
    private var dobMonth: Int = 0
    private var dobDay: Int = 0
    var genderSelected: String? = null
    private var radioButtonSelected: String? = null

    //live data

    //connecting to db

    fun getLastCustomerDetails():String{
        val customerDetails=Customer.customerDetails
        val i=Customer.customerDetails.size-1
       return "Id:"+customerDetails[i].customerId+"\n"+"Name:"+
               customerDetails[i].customerName+"\n"+"Acc No:"+customerDetails[i].customerAccNo.toString()+"\n"+"Type:"+customerDetails[i].customerAccType+"\n"+
       "Age:"+ customerDetails[i].customerAge.toString()+"\n"+"password:"+customerDetails[i].customerPassword+"\n"+"Balance:"+customerDetails[i].balance.toString()
    }

    fun setAge(mage: Int){
        this.mage=mage
    }

    fun saveUserData(binding: ActivityCustomerSignUpFormBinding, radioButtonSelected: String):String {
        val nameOfCustomer= binding.name.text.toString()
        val passwordOfCustomer = binding.password.text.toString()
        val mobileOfCustomer = binding.mobileNo.text.toString()
        val accountTypeOfCustomer:String = radioButtonSelected
        val customerDetails: ArrayList<CustomerDetails> = Customer.customerDetails
        Log.d("cat", customerDetails.toString())
        var newCustomerId: String
        var newCustomerAcc: Int
        fun getRandomCustomerId(): String {
            val newRandomCustomerId = (1005..9999).random()
            newCustomerId = "C$newRandomCustomerId"
            for (i in 0 until customerDetails.size) {
                if ((customerDetails[i].customerId) == newCustomerId) {
                    getRandomCustomerId()
                } else {
                    return newCustomerId
                }
            }
            return newCustomerId
        }

        fun getRandomCustomerAcc(): Int {
            val newRandomCustomerAcc = (100000000..999999999).random()
            newCustomerAcc = newRandomCustomerAcc
            for (i in 0 until customerDetails.size) {
                if ((customerDetails[i].customerAccNo) == newRandomCustomerAcc) {
                    getRandomCustomerAcc()
                } else {
                    return newCustomerAcc
                }
            }
            return newCustomerAcc
        }
        fun validateCustomerPassword(inputCustomerPassword: String): Boolean {
            val p: Pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=()])(?=\\S+\$).{8,20}\$")
            val m: Matcher = p.matcher(inputCustomerPassword)
            return if (inputCustomerPassword == "") {
                false
            } else m.matches()
        }

        val result=validateCustomerPassword(passwordOfCustomer)
        Log.d("sump",result.toString())

        if(nameOfCustomer!="" && passwordOfCustomer!="" && mage!=0 && mobileOfCustomer!="" && result){

        Customer.customerDetails.add(CustomerDetails(getRandomCustomerId(), nameOfCustomer, passwordOfCustomer,
                getRandomCustomerAcc(), accountTypeOfCustomer, mobileOfCustomer, mage, 0.00))
        return "y"}
        else if(nameOfCustomer=="" || passwordOfCustomer=="" || mage!=0 && mobileOfCustomer==""){
            return "n"
        }
        else{
            return "p"
        }


    }
    //connecting vm and view
}