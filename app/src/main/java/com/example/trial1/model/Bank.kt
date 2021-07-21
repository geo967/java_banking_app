package com.example.trial1.model

/*class Bank{

    companion object {
         var noOfCustomers: Int = 4
        const val noOfStaffs: Int = 4
        const val savingsInterest: Double = 5.00
        const val currentInterest: Double = 6.00
    }

}*/

data class Bank(var noOfCustomers: Int = 4,
        val noOfStaffs: Int = 4,
        val savingsInterest: Double = 5.00,
        val currentInterest: Double = 6.00)


