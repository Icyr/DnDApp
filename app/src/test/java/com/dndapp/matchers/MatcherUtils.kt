package com.dndapp.matchers

import com.nhaarman.mockitokotlin2.check
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsNull

inline fun <reified T : Any> withArg(matcher: Matcher<T>): T = check { MatcherAssert.assertThat(it, matcher) }

inline fun <reified T : Any> isNull() = IsNull.nullValue(T::class.java)

fun <T> withId(id: T) = `is`(id)