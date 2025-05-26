package com.example.habitapp.screens

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasClickAction
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
import com.example.habitapp.util.ScreenTest
import org.junit.Test
import java.time.LocalDate


@FixMethodOrder(MethodSorters.DEFAULT)
class HabitProgressScreenTest : ScreenTest() {
    @Before
    override fun setUp() {
        super.setUp()

    }


    @Test
    fun `check state of progress screen for today`() {
        login()
        rule.onNode(habitCard(VALID_UNIT, VALID_GOAL, VALID_TIMEFRAME, VALID_GROUP)).performClick()
        rule.onNode(suspendButton).assertExists()
        rule.onNode(editButton).assertExists()
        rule.onNode(unSuspendButton).assertDoesNotExist()
        rule.onNode(deleteButton).assertDoesNotExist()
        rule.onNode(progressIndicator).assertExists()
        val pageTitle =
            hasText(rule.activity.getString(R.string.update_progress_habit))
        rule.onNode(pageTitle).assertExists()

        rule.onNode(progressText(VALID_UNIT, VALID_GOAL, VALID_TIMEFRAME, VALID_GROUP)).assertExists()

        rule.onNode(progressTextField).assertExists()
        rule.onNode(updateButton).assertExists()


    }

    @Test
    fun `check state of progress screen for yesterday`() {
        login()
        rule.onNode(dateSelectorLeft).performClick()
        rule.onNode(habitCard(VALID_UNIT, VALID_GOAL, VALID_TIMEFRAME, VALID_GROUP)).performClick()
        rule.onNode(suspendButton).assertDoesNotExist()
        rule.onNode(editButton).assertExists()
        rule.onNode(unSuspendButton).assertDoesNotExist()
        rule.onNode(deleteButton).assertDoesNotExist()
        rule.onNode(progressIndicator).assertExists()
        val pageTitle =
            hasText(rule.activity.getString(R.string.update_progress_habit))
        rule.onNode(pageTitle).assertExists()

        rule.onNode(progressText(VALID_UNIT, VALID_GOAL, VALID_TIMEFRAME, VALID_GROUP)).assertExists()

        rule.onNode(progressTextField).assertExists()
        rule.onNode(updateButton).assertExists()


    }

    @Test
    fun `test suspend functionality`() {
        login()
        rule.onNode(habitCard(VALID_UNIT, VALID_GOAL, VALID_TIMEFRAME, VALID_GROUP)).performClick()

        rule.onNode(unSuspendButton).assertDoesNotExist()
        // Suspend habit
        rule.onNode(suspendButton).performClick()
        val pageTitle =
            hasText(rule.activity.getString(R.string.update_progress_habit))
        rule.onNode(pageTitle).assertExists()

        rule.onNode(suspended).assertExists()
        rule.onNode(deleteButton).assertExists()

        // Unsuspend habit
        rule.onNode(unSuspendButton).performClick()
        rule.onNode(suspended).assertDoesNotExist()

    }



    







}