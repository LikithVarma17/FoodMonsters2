package com.example.acer.foodmonsters2;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FoodMonstersTabTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void foodMonstersTabTest() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.id_1), withText("Nutella Pie"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());




        ViewInteraction textView4 = onView(
                allOf(withId(R.id.content), withText("Receipe Ingredients"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.item_list),
                                        0),
                                0),
                        isDisplayed()));

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.content), withText("Receipe Ingredients"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.item_list),
                                        0),
                                1),
                        isDisplayed()));
        appCompatTextView2.perform(click());


        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.item_detail_container),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frameLayout),
                                        0),
                                1),
                        isDisplayed()));
        frameLayout.check(matches(isDisplayed()));

        ViewInteraction frameLayout2 = onView(
                allOf(withId(R.id.item_detail_container),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frameLayout),
                                        0),
                                1),
                        isDisplayed()));
        frameLayout2.check(matches(isDisplayed()));

        ViewInteraction frameLayout3 = onView(
                allOf(withId(R.id.item_detail_container),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frameLayout),
                                        0),
                                1),
                        isDisplayed()));
        frameLayout3.check(matches(isDisplayed()));

        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(R.id.content), withText("Recipe Introduction"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.item_list),
                                        1),
                                1),
                        isDisplayed()));
        appCompatTextView4.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.item_detail), withText("descriptionRecipe Introduction"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.item_detail_container),
                                        0),
                                0),
                        isDisplayed()));
        textView5.check(matches(withText("descriptionRecipe Introduction")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.item_detail), withText("descriptionRecipe Introduction"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.item_detail_container),
                                        0),
                                0),
                        isDisplayed()));
        textView6.check(matches(isDisplayed()));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.app_bar),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
