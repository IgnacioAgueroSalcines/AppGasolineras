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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListaGasolinerasActivityTest4 {

    /**
     * Clase de Test para verificar "teóricamente" el caso de fallo en el que no se completa la descarga
     * de datos pero existen datos previos para mostrar. Esta comprobación requiere una apertura de la
     * app con acceso a internet, y una segunda apertura sin acceso, y nos es imposible generar esa
     * "interacción" a través de este test.
     */

    @Rule
    public ActivityTestRule<ListaGasolinerasActivity> mActivityTestRule = new ActivityTestRule<>(ListaGasolinerasActivity.class);

    @Test
    public void listaGasolinerasActivityTest4() {
        ViewInteraction textView = onView(
                allOf(withId(R.id.textFechaActualizacion), withText("No Actualizado. Datos previos"),
                        childAtPosition(
                                allOf(withId(R.id.activity_main),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("No Actualizado. Datos previos")));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.image), withContentDescription("Logo"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.customListView),
                                        0),
                                0),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.infoSection),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.customListView),
                                        0),
                                1),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

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
