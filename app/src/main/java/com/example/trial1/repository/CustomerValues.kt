package com.example.trial1.repository

import com.example.trial1.database.Customer
import com.example.trial1.model.CustomerDetails

class CustomerValues {
    companion object {
        fun setInitialCustomerValues() {
            Customer.customerDetails.add(
                    CustomerDetails(
                            "C1001", "Sachin", "Sachin@123", 1000000001, "savings", "1234567891", 21, 0.00))
            Customer.customerDetails.add(
                    CustomerDetails(
                            "C1002", "Dhoni", "Dhoni@123", 1000000002, "current", "1234567892", 25, 0.00))
            Customer.customerDetails.add(
                    CustomerDetails("C1003", "Kohli", "Kohli@123", 1000000003, "savings", "1234567893", 23, 0.00))
            Customer.customerDetails.add(
                    CustomerDetails("C1004", "Rohit", "Rohit@123", 1000000004, "current", "1234567894", 24, 0.00))

        }
    }


}