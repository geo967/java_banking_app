package com.example.trial1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trial1.repository.CustomerValues
import com.example.trial1.repository.StaffValues


class MainActivityViewModel: ViewModel() {



    //live data


    //connecting to db via repository
     fun initialisation(){
        CustomerValues.setInitialCustomerValues()
        StaffValues.setInitialStaffValues()
    }


    //connecting main activity and view model

}
