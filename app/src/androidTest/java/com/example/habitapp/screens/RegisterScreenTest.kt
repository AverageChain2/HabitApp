package com.example.habitapp.screens
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.habitapp.MainActivity
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.runners.MethodSorters
import com.example.habitapp.R
import com.example.habitapp.util.ScreenTest
import org.junit.Test

@FixMethodOrder( MethodSorters.DEFAULT)
class RegisterScreenTest : ScreenTest(){

        @Before
        override fun setUp() {
            super.setUp()
        }
        @Test
        fun `check default state of the register screen`() {
            rule.onNode(registerButton).performClick()

            val pageTitle =
                hasText(rule.activity.getString(R.string.register))
            rule.onNode(pageTitle).assertExists()
            rule.onNode(bottomNavBar).assertDoesNotExist()
            rule.onNode(emailAddressTextField).assertExists()
            rule.onNode(passwordTextField).assertExists()
            rule.onNode(submitButton).assertExists()
            rule.onNode(backButton).assertExists()
        }
    @Test
    fun `enter valid sign up details`(){
        rule.onNode(registerButton).performClick()
        rule.onNode(emailAddressTextField).performTextInput(VALID_EMAIL)
//must be a valid email or firebase will put up an error via toast
        rule.onNode(passwordTextField).performTextInput(VALID_PASSWORD)
        rule.onNode(submitButton).performClick()
    }
    @Test
    fun `enter invalid sign up details`(){
        rule.onNode(registerButton).performClick()
        rule.onNode(emailAddressTextField).performTextInput(INVALID_EMIAL)
        rule.onNode(passwordTextField).performTextInput(INVALID_PASSWORD)
        rule.onNode(submitButton).performClick()
    }
}