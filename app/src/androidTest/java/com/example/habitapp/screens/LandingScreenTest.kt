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
import org.junit.Test


@FixMethodOrder(MethodSorters.DEFAULT)
class LandingScreenTest {
    @get:Rule
    var rule = createAndroidComposeRule<MainActivity>()

    private lateinit var loginButton : SemanticsMatcher
    private lateinit var registerButton : SemanticsMatcher

    @Before
    fun setUp() {
        loginButton =
            hasText(rule.activity.getString(R.string.login)) and hasClickAction()

        registerButton =
            hasText(rule.activity.getString(R.string.register)) and hasClickAction()

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