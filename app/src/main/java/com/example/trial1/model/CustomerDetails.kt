package com.example.trial1.model

data class CustomerDetails(val customerId:String,
                           val customerName:String,
                           val customerPassword:String,
                           val customerAccNo:Int,
                           val customerAccType:String,
                           val customerPhoneNo:String,
                           val customerAge:Int,
                           var balance:Double)
