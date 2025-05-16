package com.example.habitapp.screens
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasContentDescription
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

@FixMethodOrder( MethodSorters.DEFAULT)
class RegisterPageTest {
  

        //Valid user details
        val VALID_EMAIL = "newuser@email.com"
        val VALID_PASSWORD = "password"

        @get:Rule
        var rule = createAndroidComposeRule<MainActivity>()
        lateinit var registerButton : SemanticsMatcher
        lateinit var emailAddressTextField : SemanticsMatcher
        lateinit var passwordTextField : SemanticsMatcher
        lateinit var submitButton : SemanticsMatcher
        lateinit var backButton : SemanticsMatcher
        //Nav bar items
        val bottomNavBar = hasContentDescription("bottom_nav")
        @Before
        open fun setUp() {
            val BUTTON_POSTFIX = " button"
            registerButton = hasText(rule.activity.getString(R.string.register_button))
            submitButton = hasText(rule.activity.getString(R.string.submit_button))

            backButton = hasText(rule.activity.getString(R.string.back_button))

            emailAddressTextField =
                hasContentDescription(rule.activity.getString(R.string.email))

            passwordTextField =
                hasContentDescription(rule.activity.getString(R.string.password))
        }
        @Test
        fun `check default state of the sign up screen`() {
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
}