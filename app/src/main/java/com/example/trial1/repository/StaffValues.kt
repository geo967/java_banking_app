package com.example.trial1.repository

import com.example.trial1.database.Staff
import com.example.trial1.model.StaffDetails

class StaffValues {
    companion object{
        fun setInitialStaffValues(){
            Staff.staffDetails.add(StaffDetails("S1001","Geo","Geo@12345"))
            Staff.staffDetails.add(StaffDetails("S1002","Adam","Adam@12345"))
            Staff.staffDetails.add(StaffDetails("S1003","Sam","Sam@12345"))
            Staff.staffDetails.add(StaffDetails("S1004","Pinky","Pinky@12345"))
        }
    }
}