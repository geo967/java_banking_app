package com.example.trial1.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.trial1.R
import com.example.trial1.databinding.ActivityCustomerLoginBinding
import com.example.trial1.model.CustomerDetails
import com.example.trial1.viewmodel.CLViewModel

import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Matcher
import java.util.regex.Pattern


class CustomerLogin : AppCompatActivity() {

    private lateinit var clViewModel:CLViewModel
    private lateinit var binding: ActivityCustomerLoginBinding
    private lateinit var customerId: TextInputLayout
    private lateinit var customerPassword: TextInputLayout
    private lateinit var inputCustomerId: Editable
    private lateinit var inputCustomerPassword: Editable

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCustomerLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        clViewModel= ViewModelProvider(this)[CLViewModel::class.java]
        clViewModel.currentId.observe(this, Observer {
            binding.idCustomerId.editText!!.text=it
        })
        clViewModel.currentPass.observe(this, Observer {
            binding.idPasswordCustomer.editText!!.text=it
        })
        handleCustomerSignIn()
        handleNewCustomerSignUp()
    }

    private fun handleNewCustomerSignUp() {
        binding.idNewCustomer.setOnClickListener {
            val intent = Intent(this, CustomerSignUpForm::class.java)
            startActivity(intent)
        }
    }

    private fun handleCustomerSignIn() {
        binding.idSignInCustomer.setOnClickListener {
            customerId = findViewById(R.id.id_customerId)
            customerPassword = findViewById(R.id.id_password_customer)
            inputCustomerId = customerId.editText!!.text
            clViewModel.currentId.value= inputCustomerId
            inputCustomerPassword = customerPassword.editText!!.text
            clViewModel.currentPass.value=inputCustomerPassword

            val resultOfIdValidation = clViewModel.validateCustomerId(inputCustomerId, binding)
            val resultOfPasswordValidation = clViewModel.validateCustomerPassword(inputCustomerPassword, binding)
            Log.d("cat",resultOfIdValidation.toString()+resultOfPasswordValidation.toString())
            if (resultOfIdValidation && resultOfPasswordValidation) {
                Log.d("cat",inputCustomerId.toString()+inputCustomerPassword.toString())
                val resultOfId = clViewModel.checkForId(inputCustomerId.toString(),inputCustomerPassword.toString())
                if (resultOfId) {
                    val sp: SharedPreferences = getSharedPreferences("inputCId", MODE_PRIVATE)
                    val ed: SharedPreferences.Editor = sp.edit()
                    ed.putString("customerIdInput", inputCustomerId.toString())
                    ed.apply()

                    val intent = Intent(this, CustomerLoginFirstPage::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}