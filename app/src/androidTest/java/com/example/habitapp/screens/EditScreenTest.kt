package com.example.habitapp.screens

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.assertValueEquals
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
import java.time.LocalDate


@FixMethodOrder(MethodSorters.DEFAULT)
class EditScreenTest : ScreenTest() {
    @Before
    override fun setUp() {
        super.setUp()

    }


    @Test
    fun `check state of edit page`() {
        login()
        rule.onNode(habitCard(VALID_UNIT, VALID_GOAL, VALID_TIMEFRAME, VALID_GROUP)).performClick()
        rule.onNode(editButton).performClick()

        val pageTitle =
            hasText(rule.activity.getString(R.string.edit_habit))
        rule.onNode(pageTitle).assertExists()
        rule.onNode(unitTextField).assertExists()
        rule.onNode(goalTextField).assertExists()
        rule.onNode(timeframeTextField).assertExists()
        rule.onNode(groupTextField).assertExists()

        //Check text fields have correctly passed data
        rule.onNode(hasText(VALID_UNIT))
        rule.onNode(hasText(VALID_GOAL))
        rule.onNode(hasText(VALID_TIMEFRAME))
        rule.onNode(hasText(VALID_GROUP))


    }

    @Test
    fun `test edit functionality`() {
        login()
        rule.onNode(habitCard(VALID_UNIT, VALID_GOAL, VALID_TIMEFRAME, VALID_GROUP)).performClick()
        rule.onNode(editButton).performClick()

        //Change text fields
        inputHabit(EDIT_UNIT, EDIT_GOAL, EDIT_TIMEFRAME, EDIT_GROUP)
        rule.onNode(updateButton).performClick()
        val pageTitle =
            hasText(rule.activity.getString(R.string.update_progress_habit))
        rule.onNode(pageTitle).assertExists()
        rule.onNode(progressTextField).assertExists()
        rule.onNode(progressText(EDIT_UNIT, EDIT_GOAL, EDIT_TIMEFRAME, EDIT_GROUP)).assertExists()



    }
    @Test
    fun `test edit only unit functionality`() {
        login()
        rule.onNode(habitCard(VALID_UNIT, VALID_GOAL, VALID_TIMEFRAME, VALID_GROUP)).performClick()
        rule.onNode(editButton).performClick()

        //Change text fields
        inputHabit(EDIT_UNIT, VALID_GOAL, VALID_TIMEFRAME, VALID_GROUP)
        rule.onNode(updateButton).performClick()


    }







}