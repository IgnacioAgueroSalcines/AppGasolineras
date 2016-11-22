package com.example.nachoaguero.appgasolineras.Presentacion;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.nachoaguero.appgasolineras.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListaGasolineraActivityOrdenaPrecioTest {

    @Rule
    public ActivityTestRule<ListaGasolinerasActivity> mActivityTestRule = new ActivityTestRule<>(ListaGasolinerasActivity.class);

    @Test
    public void listaGasolineraActivityOrdenaPrecioTest() {
        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.ordenarPrecio), withContentDescription("Orden€"), isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.precio), withText("0.999€/L"), withContentDescription("Precio"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.customListView),
                                        0),
                                2),
                        isDisplayed()));
        textView.check(matches(withText("0.999€/L")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.precio), withText("1.049€/L"), withContentDescription("Precio"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.customListView),
                                        1),
                                2),
                        isDisplayed()));
        textView2.check(matches(withText("1.049€/L")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.precio), withText("1.049€/L"), withContentDescription("Precio"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.customListView),
                                        2),
                                2),
                        isDisplayed()));
        textView3.check(matches(withText("1.049€/L")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.precio), withText("1.059€/L"), withContentDescription("Precio"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.customListView),
                                        3),
                                2),
                        isDisplayed()));
        textView4.check(matches(withText("1.059€/L")));

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
