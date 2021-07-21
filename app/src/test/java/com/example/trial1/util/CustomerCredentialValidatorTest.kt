package com.example.trial1.util

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CustomerCredentialValidatorTest{
    private lateinit var credentialsValidatorClass:CustomerCredentialValidator

    @Before
    fun setUp(){
        credentialsValidatorClass= CustomerCredentialValidator()
    }
    @Test
    fun if_Given_ValidUsername_And_ValidPassword_ThenReturn_True(){
        val result:Boolean=credentialsValidatorClass.validateInputs("C1009","Tcs@sd232")
        assertEquals(result,true)
    }
    @Test
    fun if_Given_InValidUsername_And_ValidPassword_ThenReturn_False(){
        val result:Boolean=credentialsValidatorClass.validateInputs("we23223","Tcs!sd232")
        assertFalse(result)
    }
    @Test
    fun if_Given_InValidUsername_And_InValidPassword_ThenReturn_False(){
        val result:Boolean=credentialsValidatorClass.validateInputs("W2323","Tcssd232")
        assertFalse(result)
    }
    @Test
    fun if_given_invalidUserName_and_validPassword_then_return_false(){
        val result=credentialsValidatorClass.validateInputs("S1001","Tcs@12345")
        assertFalse(result)
    }
}