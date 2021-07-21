package com.example.trial1.util

import java.util.regex.Matcher
import java.util.regex.Pattern

class CustomerCredentialValidator {
    private val customerIdPattern: Pattern = Pattern.compile("[C][0-9]{4}")
    private val passwordPattern: Pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=()])(?=\\S+$).{8,20}$")

    fun validateInputs(inputId:String,inputPassword:String):Boolean{
        val emailMatcher: Matcher =customerIdPattern.matcher(inputId)
        val passwordMatcher: Matcher =passwordPattern.matcher(inputPassword)
        return emailMatcher.matches() && passwordMatcher.matches()
    }
}