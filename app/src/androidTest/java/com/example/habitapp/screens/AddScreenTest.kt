package com.example.habitapp.screens

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.runners.MethodSorters
import com.example.habitapp.R
import com.example.habitapp.util.ScreenTest
import org.junit.Test


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
        rule.onNode(habitCard(VALID_UNIT, VALID_GOAL, VALID_TIMEFRAME, VALID_GROUP)).assertExists()




    }

    







}