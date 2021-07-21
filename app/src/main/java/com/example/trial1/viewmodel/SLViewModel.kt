package com.example.trial1.viewmodel

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trial1.database.Customer
import com.example.trial1.database.Staff
import com.example.trial1.databinding.ActivityStaffLoginBinding
import com.example.trial1.model.StaffDetails
import java.util.regex.Matcher
import java.util.regex.Pattern

class SLViewModel : ViewModel() {


    //live data
    val currentId: MutableLiveData<Editable> by lazy {
        MutableLiveData<Editable>()
    }
    val currentPass: MutableLiveData<Editable> by lazy {
        MutableLiveData<Editable>()
    }

    //connecting to db via repo

    fun checkForId(getInput:String,getPass: String):Boolean{
        val staffDetails= Staff.staffDetails
        for (i in 0 until staffDetails.size) {
            if (staffDetails[i].staffId== getInput) {
                if(staffDetails[i].staffPassword==getPass){
                return true}
            }
        }
        return false
    }
    fun checkForPassword(getInput:String):Boolean{
        val staffDetails= Staff.staffDetails
        for (i in 0 until staffDetails.size) {
            if (staffDetails[i].staffPassword== getInput) {
                return true
            }
        }
        return false
    }
    fun validateStaffId(inputStaffId: Editable, binding: ActivityStaffLoginBinding): Boolean {
        val p: Pattern = Pattern.compile("[S][0-9]{4}")
        val m: Matcher = p.matcher(inputStaffId)
        return if (inputStaffId.equals("")) {
            binding.idStaffId.error = "Field is empty"
            false
        } else if (!m.matches()) {
            binding.idStaffId.error = "not a valid email format"
            false
        } else if (m.matches()) {
            true
        } else {
            binding.idStaffId.error = null
            false
        }
    }

    fun validateStaffPassword(inputStaffPassword: Editable, binding: ActivityStaffLoginBinding): Boolean {
        val p: Pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=()])(?=\\S+\$).{8,20}\$")
        val m: Matcher = p.matcher(inputStaffPassword)
        return if (inputStaffPassword .equals("")) {
            binding.idStaffPassword.error = "Field is empty"
            false
        } else if (!m.matches()) {
            binding.idStaffPassword.error = "not a valid password format"
            false
        } else if (m.matches()) {
            true
        } else {
            binding.idStaffPassword.error = null
            false
        }
    }


    //connecting view model and view
}