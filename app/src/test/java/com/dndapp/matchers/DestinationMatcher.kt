package com.dndapp.matchers

import android.os.Bundle
import com.dndapp.viewmodel.Destination
import com.dndapp.viewmodel.NavigationCommand
import com.dndapp.viewmodel.PopUpTo
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class DestinationMatcher internal constructor(
    private val destinationResIdMatcher: Matcher<Int>,
    private val argsMatcher: Matcher<Bundle>,
    private val popUpToMatcher: Matcher<PopUpTo>
) : TypeSafeMatcher<NavigationCommand>() {

    override fun describeTo(description: Description?) {
        description?.run {
            appendText("<Destination(destinationId: ")
            appendDescriptionOf(destinationResIdMatcher)
            appendText(", args: ")
            appendDescriptionOf(argsMatcher)
            appendText(", popUpTo: ")
            appendDescriptionOf(popUpToMatcher)
            appendText(")>")
        }
    }

    override fun matchesSafely(item: NavigationCommand?): Boolean = item is Destination && item.run {
        destinationResIdMatcher.matches(destinationId) && argsMatcher.matches(args) && popUpToMatcher.matches(popUpTo)
    }
}

fun withDestination(destinationResIdMatcher: Matcher<Int>): Matcher<NavigationCommand> {
    return DestinationMatcher(destinationResIdMatcher, isNull(), isNull())
}

fun withDestination(
    destinationResIdMatcher: Matcher<Int>,
    popUpToMatcher: Matcher<PopUpTo>
): Matcher<NavigationCommand> {
    return DestinationMatcher(destinationResIdMatcher, isNull(), popUpToMatcher)
}