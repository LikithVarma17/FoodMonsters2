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
import org.hamcrest.core.IsInstanceOf;
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
public class TabTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void tabTest() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.id_1), withText("Brownies"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler),
                                        1),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());




        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.content), withText("Receipe Ingredients"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.item_list),
                                        0),
                                1),
                        isDisplayed()));
        appCompatTextView2.perform(click());


        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(R.id.item_detail), withText("Ingredients:-Bittersweet chocolate (60-70% cacao)\nQuantity:-350.0\nMeasure:-G\nIngredients:-unsalted butter\nQuantity:-226.0\nMeasure:-G\nIngredients:-granulated sugar\nQuantity:-300.0\nMeasure:-G\nIngredients:-light brown sugar\nQuantity:-100.0\nMeasure:-G\nIngredients:-large eggs\nQuantity:-5.0\nMeasure:-UNIT\nIngredients:-vanilla extract\nQuantity:-1.0\nMeasure:-TBLSP\nIngredients:-all purpose flour\nQuantity:-140.0\nMeasure:-G\nIngredients:-cocoa powder\nQuantity:-40.0\nMeasure:-G\nIngredients:-salt\nQuantity:-1.5\nMeasure:-TSP\nIngredients:-semisweet chocolate chips\nQuantity:-350.0\nMeasure:-G\n"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.item_detail_container),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView4.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.item_detail_container),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1)),
                        0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction linearLayout2 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.item_detail_container),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1)),
                        0),
                        isDisplayed()));
        linearLayout2.check(matches(isDisplayed()));

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatTextView5 = onView(
                allOf(withId(R.id.content), withText("Mix together dry ingredients."),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.item_list),
                                        5),
                                1),
                        isDisplayed()));
        appCompatTextView5.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.item_detail), withText("description4. Sift together the flour, cocoa, and salt in a small bowl and whisk until mixture is uniform and no clumps remain. "),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.item_detail_container),
                                        0),
                                0),
                        isDisplayed()));
        textView3.check(matches(isDisplayed()));

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
