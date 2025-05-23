package com.example.habitapp.screens

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import com.example.habitapp.MainActivity
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.runners.MethodSorters
import com.example.habitapp.R
import com.example.habitapp.util.ScreenTest
import org.junit.Test


@FixMethodOrder(MethodSorters.DEFAULT)
class LandingScreenTest : ScreenTest() {
    @Before
    override fun setUp() {
        super.setUp()
    }

    @Test
    fun `check state of landing page`() {
        rule.onNode(loginButton).assertExists()
        rule.onNode(registerButton).assertExists()
    }
    @Test
    fun `move to the login page`(){
        rule.onNode(loginButton).performClick()
//on sign up page
        val pageTitle =
            hasText(rule.activity.getString(R.string.login))
        rule.onNode(pageTitle).assertExists()
    }
    @Test
    fun `move to the register page`(){
        rule.onNode(registerButton).performClick()
//on sign up page
        val pageTitle =
            hasText(rule.activity.getString(R.string.register))
        rule.onNode(pageTitle).assertExists()
    }


}