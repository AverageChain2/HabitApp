package com.example.habitapp.util

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.habitapp.MainActivity
import com.example.habitapp.R
import org.junit.Before
import org.junit.Rule

open abstract class ScreenTest {
    @get:Rule
    var rule = createAndroidComposeRule<MainActivity>()
    // Landing, Register and Login Screens
    lateinit var registerButton : SemanticsMatcher
    lateinit var emailAddressTextField : SemanticsMatcher
    lateinit var passwordTextField : SemanticsMatcher
    lateinit var submitButton : SemanticsMatcher
    lateinit var backButton : SemanticsMatcher
    lateinit var loginButton : SemanticsMatcher

    //Home Screen
    lateinit var groupSelect : SemanticsMatcher


    //Add Screen
    lateinit var unitTextField : SemanticsMatcher
    lateinit var goalTextField : SemanticsMatcher
    lateinit var timeframeTextField : SemanticsMatcher
    lateinit var groupTextField : SemanticsMatcher
    lateinit var addHabitButton : SemanticsMatcher
    lateinit var addScreenText : SemanticsMatcher

    //Habit Progress Screen
    lateinit var progressTextField : SemanticsMatcher
    lateinit var updateButton : SemanticsMatcher
    lateinit var suspended : SemanticsMatcher
    lateinit var suspendButton: SemanticsMatcher
    lateinit var unSuspendButton: SemanticsMatcher
    lateinit var deleteButton: SemanticsMatcher
    lateinit var editButton: SemanticsMatcher
    lateinit var backArrowButton : SemanticsMatcher

    //Nav bar items
    lateinit var bottomNavBar : SemanticsMatcher
    lateinit var homeNavButton : SemanticsMatcher
    lateinit var addNavButton : SemanticsMatcher
    lateinit var exitNavButton : SemanticsMatcher

    lateinit var dateSelector : SemanticsMatcher
    lateinit var dateSelectorLeft : SemanticsMatcher
    lateinit var dateSelectorNow : SemanticsMatcher
    lateinit var dateSelectorRight : SemanticsMatcher


    lateinit var progressIndicator : SemanticsMatcher



    //Valid user details
    val VALID_EMAIL = "newuser@email.com"
    val VALID_PASSWORD = "password"

    //  Invalid user details
    val INVALID_EMIAL = "newuser@email"
    val INVALID_PASSWORD = "pass"

    //Valid Habit
    val VALID_UNIT = "testing"
    val VALID_GOAL = "2"
    val VALID_TIMEFRAME = "1"
    val VALID_GROUP = "test"

    //Edit Habit
    val EDIT_UNIT = "testing_edit"
    val EDIT_GOAL = "3"
    val EDIT_TIMEFRAME = "4"
    val EDIT_GROUP = "test_edit"

    //Invalid Habit
    val INVALID_UNIT = "a".repeat(51)
    val INVALID_GOAL = "0"
    val INVALID_TIMEFRAME = "0"
    val INVALID_GROUP = "b".repeat(51)




@Before
open fun setUp() {

    //Register and Login Screens
     registerButton = hasText(rule.activity.getString(R.string.register_button))
     submitButton = hasText(rule.activity.getString(R.string.submit_button))

     backButton = hasText(rule.activity.getString(R.string.back_button))

     emailAddressTextField =
        hasContentDescription(rule.activity.getString(R.string.email))

     passwordTextField =
        hasContentDescription(rule.activity.getString(R.string.password))

    loginButton = hasText(rule.activity.getString(R.string.login_button))

    //Home Screen

    groupSelect = hasContentDescription(rule.activity.getString(R.string.group_select))

    //Add Screen
    unitTextField = hasText(rule.activity.getString(R.string.habit_unit_hint))
    goalTextField = hasText(rule.activity.getString(R.string.habit_goal_hint))
    timeframeTextField = hasText(rule.activity.getString(R.string.habit_timeframe_hint))
    groupTextField = hasText(rule.activity.getString(R.string.habit_group_hint))
    addHabitButton = hasContentDescription(rule.activity.getString(R.string.add_button))
    addScreenText = hasText(rule.activity.getString(R.string.add_habit))
    
    //Habit Progress Screen
    progressTextField = hasText(rule.activity.getString(R.string.habit_progress_hint))
    updateButton = hasContentDescription(rule.activity.getString(R.string.update_habit))
    suspended = hasText(rule.activity.getString(R.string.suspended))
    suspendButton = hasContentDescription(rule.activity.getString(R.string.suspend))
    unSuspendButton = hasContentDescription(rule.activity.getString(R.string.unsupend))
    deleteButton = hasContentDescription(rule.activity.getString(R.string.delete))
    editButton = hasContentDescription(rule.activity.getString(R.string.Edit))
    backArrowButton = hasContentDescription(rule.activity.getString(R.string.back_button))

    //Nav bar items
    bottomNavBar = hasContentDescription("bottom_nav")
    homeNavButton = hasContentDescription("navHome")
    addNavButton = hasContentDescription("navAdd")
    exitNavButton = hasContentDescription("navExit")

    dateSelector = hasContentDescription("date_selector")
    dateSelectorLeft = hasContentDescription("date_selector_left")
    dateSelectorNow = hasContentDescription("date_selector_now")
    dateSelectorRight = hasContentDescription("date_selector_right")


    progressIndicator = hasContentDescription("progress_indicator")







}

    fun login(){
        rule.onNode(loginButton).performClick()
        rule.onNode(emailAddressTextField).performTextInput(VALID_EMAIL)
//must be a valid email or firebase will put up an error via toast
        rule.onNode(passwordTextField).performTextInput(VALID_PASSWORD)
        rule.onNode(submitButton).performClick()
        Thread.sleep(1000)
        rule.onNode(bottomNavBar).assertExists()
    }
    fun navBar(){
        rule.onNode(bottomNavBar).assertExists()
        rule.onNode(homeNavButton).assertExists()
        rule.onNode(addNavButton).assertExists()
        rule.onNode(exitNavButton).assertExists()
    }
    fun inputHabit(unit: String, goal: String, timeframe: String, group: String){
        rule.onNode(unitTextField).performTextInput(unit)
        rule.onNode(goalTextField).performTextInput(goal)
        rule.onNode(timeframeTextField).performTextInput(timeframe)
        rule.onNode(groupTextField).performTextInput(group)
        rule.onNode(addHabitButton).performClick()

    }
    fun habitCard(unit: String, goal: String, timeframe: String, group: String): SemanticsMatcher {
        return hasContentDescription("Habit + $unit + $goal + $timeframe + $group")
    }
    fun progressText(unit: String, goal: String, timeframe: String): SemanticsMatcher {
        return hasText("${goal} "  +
                (if (goal.toInt() < 2) unit
                else if (unit.endsWith("s")) unit
                else unit + "s")
                + " in " + "${timeframe} " +
                (if (goal.toInt() < 2) "day"
                else "days"))
    }


}

