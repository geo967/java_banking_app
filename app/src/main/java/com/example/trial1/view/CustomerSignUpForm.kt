package com.example.trial1.view

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.trial1.R
import com.example.trial1.database.Customer
import com.example.trial1.databinding.ActivityCustomerSignUpFormBinding
import com.example.trial1.model.Bank
import com.example.trial1.viewmodel.CustomerSignUpVM
import java.util.*


class CustomerSignUpForm : AppCompatActivity() {


    private val customerSignUpVM= CustomerSignUpVM()
    private var mYear = 0
    private var mMonth: Int = 0
    private var mDay: Int = 0
    private var mage: Int = 0
    private var dobYear = 0
    private var dobMonth: Int = 0
    private var dobDay: Int = 0
    var genderSelected: String? = null
    private  var radioButtonSelected: String="current"
    private lateinit var binding: ActivityCustomerSignUpFormBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCustomerSignUpFormBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.datePicker.setOnClickListener {
            openCalender(binding)
        }
        val gender = arrayListOf("Male", "Female", "Others")
        val adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, gender)
        binding.sex.adapter = adapter
        handleGenderSelection(gender)
        handleRadioButtonSelection()
        handleSubmitButtonClick()
    }

    private fun handleSubmitButtonClick() {
        binding.submitButton.setOnClickListener {
            val result = customerSignUpVM.saveUserData(binding, radioButtonSelected)
            Log.d("sum",result)
            // saveUserDate()
            if (result=="y"){
            val builder1 = AlertDialog.Builder(this)
            builder1.setMessage(customerSignUpVM.getLastCustomerDetails())
            builder1.setCancelable(true)
            builder1.setPositiveButton(
                    "Yes"

            )
            { dialog, _ ->
                dialog.cancel()
                finish()
            }

            builder1.setNegativeButton(
                    "No"
            )
            { dialog, _ ->
                Customer.customerDetails.removeLast()
                dialog.cancel()
            }

            val alert11 = builder1.create()
            alert11.show()
            //finish()
        }
            else if(result=="n"){
                val builder1 = AlertDialog.Builder(this)
                builder1.setMessage("Ensure To Fill All Data")
                builder1.setCancelable(true)

                builder1.setNegativeButton(
                        "OK"
                )
                { dialog, _ ->
                    Customer.customerDetails.removeLast()
                    dialog.cancel()
                }

                val alert11 = builder1.create()
                alert11.show()
                //finish()
            }
            else{
                val builder1 = AlertDialog.Builder(this)
                builder1.setMessage("Password condition not met [min 8 chars [ 1 nos, 1 upper case, 1 lower, 1 special char]")
                builder1.setCancelable(true)

                builder1.setNegativeButton(
                        "OK"
                )
                { dialog, _ ->
                    Customer.customerDetails.removeLast()
                    dialog.cancel()
                }

                val alert11 = builder1.create()
                alert11.show()
            }
        }
    }

    private fun handleRadioButtonSelection() {
        binding.radioGp.setOnCheckedChangeListener { _, checkedId ->

            if (checkedId == R.id.radio_savings) {
                radioButtonSelected = "savings"
            }
            if (checkedId == R.id.radio_current) {
                radioButtonSelected = "current"
            }
        }
    }

    private fun handleGenderSelection(gender: ArrayList<String>) {
        binding.sex.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View,
                    position: Int,
                    id: Long
            ) {
                genderSelected = gender[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                genderSelected = "male"
            }
        }
    }




    @RequiresApi(Build.VERSION_CODES.O)
    private fun openCalender(binding: ActivityCustomerSignUpFormBinding) {
        val c = Calendar.getInstance()
        mYear = c[Calendar.YEAR]
        mMonth = c[Calendar.MONTH]
        mDay = c[Calendar.DAY_OF_MONTH]
        val datePickerDialog = DatePickerDialog(
                this,
                { _: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                    val dateToSetText =
                            dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
                    dobYear = year
                    dobMonth = monthOfYear + 1
                    dobDay = dayOfMonth
                    mage = mYear - dobYear
                    if (dobMonth > mMonth) {
                        mage--
                    } else if (mMonth == dobMonth) {
                        if (dobDay > mDay) {
                            mage--
                        }
                    }
                    customerSignUpVM.setAge(mage)
                    val ageForText = "Age:$mage"
                    binding.age.text = ageForText
                    binding.datePicker.setText(dateToSetText)
                }, mYear, mMonth, mDay
        )
        datePickerDialog.show()
    }
}