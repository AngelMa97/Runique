package com.angelme.auth.domain

interface PatternValidator {
    fun matches(value: String): Boolean
}