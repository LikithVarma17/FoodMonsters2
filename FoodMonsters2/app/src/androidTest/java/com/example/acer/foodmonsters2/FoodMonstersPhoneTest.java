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
public class FoodMonstersPhoneTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void foodMonstersPhoneTest() {
        try {
            Thread.sleep(5000);
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


        ViewInteraction textView2 = onView(
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


        ViewInteraction textView3 = onView(
                allOf(withId(R.id.item_detail), withText("Ingredients:-Graham Cracker crumbs\nQuantity:-2.0\nMeasure:-CUP\nIngredients:-unsalted butter, melted\nQuantity:-6.0\nMeasure:-TBLSP\nIngredients:-granulated sugar\nQuantity:-0.5\nMeasure:-CUP\nIngredients:-salt\nQuantity:-1.5\nMeasure:-TSP\nIngredients:-vanilla\nQuantity:-5.0\nMeasure:-TBLSP\nIngredients:-Nutella or other chocolate-hazelnut spread\nQuantity:-1.0\nMeasure:-K\nIngredients:-Mascapone Cheese(room temperature)\nQuantity:-500.0\nMeasure:-G\nIngredients:-heavy cream(cold)\nQuantity:-1.0\nMeasure:-CUP\nIngredients:-cream cheese(softened)\nQuantity:-4.0\nMeasure:-OZ\n"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.item_detail_container),
                                        0),
                                0),
                        isDisplayed()));
        textView3.check(matches(isDisplayed()));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.item_detail), withText("Ingredients:-Graham Cracker crumbs\nQuantity:-2.0\nMeasure:-CUP\nIngredients:-unsalted butter, melted\nQuantity:-6.0\nMeasure:-TBLSP\nIngredients:-granulated sugar\nQuantity:-0.5\nMeasure:-CUP\nIngredients:-salt\nQuantity:-1.5\nMeasure:-TSP\nIngredients:-vanilla\nQuantity:-5.0\nMeasure:-TBLSP\nIngredients:-Nutella or other chocolate-hazelnut spread\nQuantity:-1.0\nMeasure:-K\nIngredients:-Mascapone Cheese(room temperature)\nQuantity:-500.0\nMeasure:-G\nIngredients:-heavy cream(cold)\nQuantity:-1.0\nMeasure:-CUP\nIngredients:-cream cheese(softened)\nQuantity:-4.0\nMeasure:-OZ\n"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.item_detail_container),
                                        0),
                                0),
                        isDisplayed()));
        textView4.check(matches(isDisplayed()));

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.detail_toolbar),
                                        childAtPosition(
                                                withId(R.id.toolbar_layout),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton.perform(click());

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
        textView6.check(matches(withText("descriptionRecipe Introduction")));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.detail_toolbar),
                                        childAtPosition(
                                                withId(R.id.toolbar_layout),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.app_bar),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

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
