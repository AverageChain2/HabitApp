package com.example.habitapp.suite

import com.example.habitapp.components.CustomButtonTest
import com.example.habitapp.components.CustomTextFieldTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    CustomButtonTest::class,
    CustomTextFieldTest::class,
//    LandingScreenTest::class,
//    LoginScreenTest::class,
//    RegisterScreenTest::class,
//    HomeScreenTest::class,
//    HabitProgressScreenTest::class,
//    AddScreenTest::class,
//    EditScreenTest::class
)
class TestSuite