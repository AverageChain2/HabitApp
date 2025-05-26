package com.example.habitapp.screens

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.habitapp.R
import com.example.habitapp.util.ScreenTest
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters


@FixMethodOrder(MethodSorters.DEFAULT)
class AddScreenTest : ScreenTest() {
    @Before
    override fun setUp() {
        super.setUp()

    }


    @Test
    fun `check state of add page`() {
        login()
        navBar()
        rule.onNode(addNavButton).performClick()
        navBar()
        val pageTitle =
            hasText(rule.activity.getString(R.string.add_habit))
        rule.onNode(pageTitle).assertExists()
        rule.onNode(unitTextField).assertExists()
        rule.onNode(goalTextField).assertExists()
        rule.onNode(timeframeTextField).assertExists()
        rule.onNode(groupTextField).assertExists()
        rule.onNode(addHabitButton).assertExists()
    }

    @Test
    fun `test add functionality`() {
        login()
        rule.onNode(addNavButton).performClick()
        inputHabit(VALID_UNIT, VALID_GOAL, VALID_TIMEFRAME, VALID_GROUP)

        //Check that the user is redirected to the home page
        rule.onNode(dateSelector).assertExists()
        rule.onAllNodes(habitCard(VALID_UNIT, VALID_GOAL, VALID_TIMEFRAME, VALID_GROUP)).onFirst().assertExists()
    }

    @Test
    fun `test add only unit functionality`() {
        login()
        rule.onNode(addNavButton).performClick()
        rule.onNode(addScreenText).assertExists()
        rule.onNode(unitTextField).performTextInput(VALID_UNIT)
        rule.onNode(addHabitButton).performClick()

        //Check that the user is not redirected to the home page
        rule.onNode(dateSelector).assertDoesNotExist()
        rule.onNode(addScreenText).assertExists()
    }

    @Test
    fun `test add invalid habit functionality`() {
        login()
        rule.onNode(addNavButton).performClick()
        inputHabit(INVALID_UNIT, INVALID_GOAL, INVALID_TIMEFRAME, INVALID_GROUP)
        //Check that the user is not redirected to the home page
        rule.onNode(dateSelector).assertDoesNotExist()
        rule.onNode(addScreenText).assertExists()
    }

    @Test
    fun `test add empty habit functionality`() {
        login()
        rule.onNode(addNavButton).performClick()
        inputHabit("", "", "", "")
        //Check that the user is not redirected to the home page
        rule.onNode(dateSelector).assertDoesNotExist()
        rule.onNode(addScreenText).assertExists()
    }

    @Test
    fun `test add only goal functionality`() {
        login()
        rule.onNode(addNavButton).performClick()
        rule.onNode(addScreenText).assertExists()
        rule.onNode(goalTextField).performTextInput(VALID_GOAL)
        rule.onNode(addHabitButton).performClick()

        //Check that the user is not redirected to the home page
        rule.onNode(dateSelector).assertDoesNotExist()
        rule.onNode(addScreenText).assertExists()
    }

    // Used Gemini to write the tests for the other fields
    @Test
    fun `test add only timeframe functionality`() {
        login()
        rule.onNode(addNavButton).performClick()
        rule.onNode(addScreenText).assertExists()
        rule.onNode(timeframeTextField).performTextInput(VALID_TIMEFRAME)
        rule.onNode(addHabitButton).performClick()

        //Check that the user is not redirected to the home page
        rule.onNode(dateSelector).assertDoesNotExist()
        rule.onNode(addScreenText).assertExists()
    }

    @Test
    fun `test add only group functionality`() {
        login()
        rule.onNode(addNavButton).performClick()
        rule.onNode(addScreenText).assertExists()
        rule.onNode(groupTextField).performTextInput(VALID_GROUP)
        rule.onNode(addHabitButton).performClick()

        //Check that the user is not redirected to the home page
        rule.onNode(dateSelector).assertDoesNotExist()
        rule.onNode(addScreenText).assertExists()
    }

    @Test
    fun `test add invalid unit functionality`() {
        login()
        rule.onNode(addNavButton).performClick()
        inputHabit(INVALID_UNIT, VALID_GOAL, VALID_TIMEFRAME, VALID_GROUP)
        //Check that the user is not redirected to the home page
        rule.onNode(dateSelector).assertDoesNotExist()
        rule.onNode(addScreenText).assertExists()
    }

    @Test
    fun `test add invalid goal functionality`() {
        login()
        rule.onNode(addNavButton).performClick()
        inputHabit(VALID_UNIT, INVALID_GOAL, VALID_TIMEFRAME, VALID_GROUP)
        //Check that the user is not redirected to the home page
        rule.onNode(dateSelector).assertDoesNotExist()
        rule.onNode(addScreenText).assertExists()
    }

    @Test
    fun `test add invalid timeframe functionality`() {
        login()
        rule.onNode(addNavButton).performClick()
        inputHabit(VALID_UNIT, VALID_GOAL, INVALID_TIMEFRAME, VALID_GROUP)
        //Check that the user is not redirected to the home page
        rule.onNode(dateSelector).assertDoesNotExist()
        rule.onNode(addScreenText).assertExists()
    }

    @Test
    fun `test add invalid group functionality`() {
        login()
        rule.onNode(addNavButton).performClick()
        inputHabit(VALID_UNIT, VALID_GOAL, VALID_TIMEFRAME, INVALID_GROUP)
        //Check that the user is not redirected to the home page
        rule.onNode(dateSelector).assertDoesNotExist()
        rule.onNode(addScreenText).assertExists()
    }

    @Test
    fun `test add with some empty fields functionality`() {
        login()
        rule.onNode(addNavButton).performClick()
        inputHabit(VALID_UNIT, "", VALID_TIMEFRAME, "")
        //Check that the user is not redirected to the home page
        rule.onNode(dateSelector).assertDoesNotExist()
        rule.onNode(addScreenText).assertExists()
    }





}