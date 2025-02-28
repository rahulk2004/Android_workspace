package com.example.module7junit

import org.junit.Assert.*
import org.junit.Test

class RegistrationUtilTest {

    @Test
    fun `empty username returns false`() {
        val result = RegistrationUtil.validRegistrationInput(
            "",
            "123",
            "123"
        )
        assertFalse(result)
    }


    @Test
    fun `username and correctly repeated password returns true`() {
        val result = RegistrationUtil.validRegistrationInput(
            "NewUser",
            "123",
            "123"
        )
        assertTrue(result)
    }



    @Test
    fun `username already taken returns false`() {
        val result = RegistrationUtil.validRegistrationInput(
            "Rohan",
            "123",
            "123"
        )
        assertFalse(result)
    }


    @Test
    fun `incorrect confirm password returns false`() {
        val result = RegistrationUtil.validRegistrationInput(
            "Rahul",
            "123",
            "1234"
        )
        assertFalse(result)
    }


    @Test
    fun `less than two digit password returns false`() {
        val result = RegistrationUtil.validRegistrationInput(
            "Rahul",
            "abcd1",
            "abcd1"
        )
        assertFalse(result)
    }
}
