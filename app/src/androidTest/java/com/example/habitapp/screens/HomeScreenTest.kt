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
import java.time.LocalDate


@FixMethodOrder(MethodSorters.DEFAULT)
class HomeScreenTest : ScreenTest() {
    @Before
    override fun setUp() {
        super.setUp()
    }

    @Test
    fun `check state of home page`() {
        login()
        navBar()
        rule.onNode(dateSelector).assertExists()
        rule.onNode(progressIndicator).assertExists()

        val nullHabitsMessage = hasText(rule.activity.getString(R.string.habits_null))

        val groupSelectExists = try {
            rule.onNode(groupSelect).assertExists()
            true
        } catch (e: AssertionError) {
            false
        }

        val nullHabitsMessageExists = try {
            rule.onNode(nullHabitsMessage).assertExists()
            true
        } catch (e: AssertionError) {
            false
        }

        assert(groupSelectExists || nullHabitsMessageExists) { "Neither groupSelect nor nullHabitsMessage exists!" }
    }

    @Test
    fun `test date selector functionality`() {
        login()
        // initial
        rule.onNode(dateSelector).assertExists()
        val date = hasText("Today")
        rule.onNode(date).assertExists()
        rule.onNode(dateSelectorLeft).assertExists()
        rule.onNode(dateSelectorNow).assertDoesNotExist()
        rule.onNode(dateSelectorRight).assertDoesNotExist()

        // minus 1 day
        rule.onNode(dateSelectorLeft).performClick()
        val dateLeft = hasText("Yesterday")
        rule.onNode(dateLeft).assertExists()
        rule.onNode(dateSelectorLeft).assertExists()
        rule.onNode(dateSelectorNow).assertExists()
        rule.onNode(dateSelectorRight).assertExists()

        // Check right arrow works
        rule.onNode(dateSelectorRight).assertExists()
        rule.onNode(dateSelectorRight).performClick()
        rule.onNode(date).assertExists()

        // minus 2 day
        rule.onNode(dateSelectorLeft).performClick()
        rule.onNode(dateSelectorLeft).performClick()
        val dateLeft2 = hasText(LocalDate.now().minusDays(2).toString())
        rule.onNode(dateLeft2).assertExists()
        rule.onNode(dateSelectorLeft).assertExists()
        rule.onNode(dateSelectorNow).assertExists()
        rule.onNode(dateSelectorRight).assertExists()

        //Back to today using now button
        rule.onNode(dateSelectorNow).performClick()
        rule.onNode(date).assertExists()

    }









}