package com.example.habitapp.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.habitapp.presentation.components.CustomTextField
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder( MethodSorters.DEFAULT)
class CustomTextFieldTest {
    //    Data to initialise component
    private val HINT_TEXT = "hint text"
    private val TEXT_TO_BE_DISPLAYED = "text"
    private var textInput = ""
    private val ERROR_MESSAGE_TEXT = "error displayed"
    private var errorIsNotPresent = true

    //Screen elements to test
    private val textToEnter = hasText(TEXT_TO_BE_DISPLAYED)
    private val hintText = hasText(HINT_TEXT)
    private val errorMessageText = hasText(ERROR_MESSAGE_TEXT)

    @get:Rule
    var rule = createComposeRule()

    @Test
    fun `check default state of the textfield with text present`() {
        rule.setContent {
            CustomTextField(
                HINT_TEXT,
                TEXT_TO_BE_DISPLAYED,
                isPasswordField = false,
                onValueChange = { textInput = it },
                ERROR_MESSAGE_TEXT,
                errorIsNotPresent
            )
        }
        rule.onNode(hintText).assertExists()
        rule.onNode(textToEnter).assertExists()
        rule.onNodeWithText(ERROR_MESSAGE_TEXT).assertDoesNotExist()
        assertTrue(errorIsNotPresent)
    }

    @Test
    fun `check state of the textfield with additional valid text input`() {
        rule.setContent {
            MaterialTheme {
                CustomTextField(
                    HINT_TEXT,
                    TEXT_TO_BE_DISPLAYED,
                    isPasswordField = false,
                    onValueChange = { textInput = it },
                    ERROR_MESSAGE_TEXT,
                    errorIsNotPresent
                )
            }
        }
        val ADDITIONAL_TEXT = "something"
        rule.onNode(hintText).assertExists()
        rule.onNode(textToEnter).performTextInput(ADDITIONAL_TEXT)
        rule.onNode(errorMessageText).assertDoesNotExist()
        assertTrue(errorIsNotPresent)
//Cursor is not at the end and setting the mouse cursor as such requires modification to onValueChange
                assertEquals(textInput, ADDITIONAL_TEXT.plus(TEXT_TO_BE_DISPLAYED))
    }

    @Test
    fun `textfield with error present state set should display an error_message`()
    {
//As validation of button is external and this component is tested separately we need to set the errorIsNotPresent to false explicitly
//Text - regardless of contents - is irrelevant to the validation in the context of this component
        rule.setContent {
            MaterialTheme {
                CustomTextField(
                    HINT_TEXT,
                    "",
                    isPasswordField = false,
                    onValueChange = { textInput = it },
                    ERROR_MESSAGE_TEXT,
                    !errorIsNotPresent
                )
            }
        }
        rule.onNodeWithText("").assertExists()
        rule.onNode(errorMessageText).assertExists()
    }
}