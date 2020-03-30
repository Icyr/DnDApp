package com.dndapp.landing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.koin.test.AutoCloseKoinTest

abstract class BaseDndAppTest : AutoCloseKoinTest() {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()
}