package com.example.habitapp.screens

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import com.example.habitapp.R
import com.example.habitapp.util.ScreenTest
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters


@FixMethodOrder(MethodSorters.DEFAULT)
class EditScreenTest : ScreenTest() {
    @Before
    override fun setUp() {
        super.setUp()

    }


    @Test
    fun `check state of edit page`() {
        login()
        rule.onAllNodes(habitCard(VALID_UNIT, VALID_GOAL, VALID_TIMEFRAME, VALID_GROUP)).onFirst().performClick()

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
        rule.onAllNodes(habitCard(VALID_UNIT, VALID_GOAL, VALID_TIMEFRAME, VALID_GROUP)).onFirst().performClick()

        rule.onNode(editButton).performClick()

        //Change text fields
        rule.onNode(unitTextField).performTextClearance()
        rule.onNode(unitTextField).performTextInput(EDIT_UNIT)

        rule.onNode(goalTextField).performTextClearance()
        rule.onNode(goalTextField).performTextInput(EDIT_GOAL)

        rule.onNode(timeframeTextField).performTextClearance()
        rule.onNode(timeframeTextField).performTextInput(EDIT_TIMEFRAME)



        rule.onNode(updateButton).performClick()
        val pageTitle =
            hasText(rule.activity.getString(R.string.update_progress_habit))

        rule.onNode(pageTitle).assertExists()
        rule.onNode(progressTextField).assertExists()
        rule.onNode(progressText(EDIT_UNIT, EDIT_GOAL, EDIT_TIMEFRAME, VALID_GROUP)).assertExists()

        // Check that home page updates
        rule.onNode(backArrowButton).performClick()
        rule.onAllNodes(habitCard(EDIT_UNIT, EDIT_GOAL, EDIT_TIMEFRAME, VALID_GROUP)).onFirst().performClick()

        //Reset habit
        rule.onNode(editButton).performClick()
        rule.onNode(unitTextField).performTextClearance()
        rule.onNode(unitTextField).performTextInput(VALID_UNIT)
        rule.onNode(goalTextField).performTextClearance()
        rule.onNode(goalTextField).performTextInput(VALID_GOAL)
        rule.onNode(timeframeTextField).performTextClearance()
        rule.onNode(timeframeTextField).performTextInput(VALID_TIMEFRAME)

        rule.onNode(updateButton).performClick()

        //Check that habit has been reset
        rule.onNode(progressText(VALID_UNIT, VALID_GOAL, VALID_TIMEFRAME, VALID_GROUP)).assertExists()




    }
    @Test
    fun `test edit only unit functionality`() {
        login()
        rule.onAllNodes(habitCard(VALID_UNIT, VALID_GOAL, VALID_TIMEFRAME, VALID_GROUP)).onFirst().performClick()

        rule.onNode(editButton).performClick()

        //Change text fields
        rule.onNode(unitTextField).performTextClearance()
        rule.onNode(unitTextField).performTextInput(EDIT_UNIT)
        rule.onNode(updateButton).performClick()

        //Check that the user is redirected to the progress page and habit has been edited
        hasText(rule.activity.getString(R.string.update_progress_habit))
        rule.onNode(progressText(EDIT_UNIT, VALID_GOAL, VALID_TIMEFRAME, VALID_GROUP)).assertExists()

        // Reset habit back to old value
        rule.onNode(editButton).performClick()
        rule.onNode(unitTextField).performTextClearance()
        rule.onNode(unitTextField).performTextInput(VALID_UNIT)
        rule.onNode(updateButton).performClick()


    }
    @Test
    fun `test edit only group functionality`() {
        login()
        rule.onAllNodes(habitCard(VALID_UNIT, VALID_GOAL, VALID_TIMEFRAME, VALID_GROUP)).onFirst().performClick()
        rule.onNode(editButton).performClick()
        rule.onNode(groupTextField).performTextClearance()
        rule.onNode(groupTextField).performTextInput(EDIT_GROUP)
        rule.onNode(updateButton).performClick()

        //Check user was redirected to the home page
        rule.onNode(dateSelector).assertExists()
        rule.onNode(groupSelect).performClick()
        rule.onNode(hasText(EDIT_GROUP)).performClick()

        // Reset habit back to old value
        rule.onNode(habitCard(VALID_UNIT, VALID_GOAL, VALID_TIMEFRAME, EDIT_GROUP)).performClick()
        rule.onNode(editButton).performClick()
        rule.onNode(groupTextField).performTextClearance()
        rule.onNode(groupTextField).performTextInput(VALID_GROUP)
        rule.onNode(updateButton).performClick()
    }

    @Test
    fun `test edit invalid unit functionality`() {
        login()
        rule.onAllNodes(habitCard(VALID_UNIT, VALID_GOAL, VALID_TIMEFRAME, VALID_GROUP)).onFirst().performClick()

        rule.onNode(editButton).performClick()

        //Change text fields
        inputHabit(INVALID_UNIT, VALID_GOAL, VALID_TIMEFRAME, VALID_GROUP)
        rule.onNode(updateButton).performClick()

        //Check that the user is not redirected to the progress page
        val pageTitle =
            hasText(rule.activity.getString(R.string.edit_habit))
        rule.onNode(pageTitle).assertExists()

    }







}