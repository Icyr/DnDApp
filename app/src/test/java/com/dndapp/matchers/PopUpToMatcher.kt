package com.dndapp.matchers

import com.dndapp.viewmodel.PopUpTo
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class PopUpToMatcher internal constructor(
    private val destinationIdMatcher: Matcher<Int>,
    private val inclusiveMatcher: Matcher<Boolean>
) : TypeSafeMatcher<PopUpTo>() {

    override fun describeTo(description: Description?) {
        description?.run {
            appendText("<PopUpTo(destinationId: ")
            appendDescriptionOf(destinationIdMatcher)
            appendText(", inclusive: ")
            appendDescriptionOf(inclusiveMatcher)
            appendText(")>")
        }
    }

    override fun matchesSafely(item: PopUpTo?): Boolean = item?.run {
        destinationIdMatcher.matches(destinationId) && inclusiveMatcher.matches(inclusive)
    } ?: false
}

fun withPopUpTo(
    destinationIdMatcher: Matcher<Int>,
    inclusiveMatcher: Matcher<Boolean>
): PopUpToMatcher {
    return PopUpToMatcher(destinationIdMatcher, inclusiveMatcher)
}

fun inclusive(): Matcher<Boolean> = `is`(true)