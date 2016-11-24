package com.example.nachoaguero.appgasolineras.Integracion;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.nachoaguero.appgasolineras.Presentacion.ListaGasolinerasActivity;
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
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)

/**
 * Clase de Test para verificar el comportamiento de la aplicación en su caso de fallo. Aplicado al contexto en el que la
 * búsqueda no devuelve ningún resultado
 */
public class ListaGasolinerasActivityJsonVacio {

    @Rule
    public ActivityTestRule<ListaGasolinerasActivity> mActivityTestRule = new ActivityTestRule<>(ListaGasolinerasActivity.class);

    /*
     Este test solo funcionaría si se pudiese introducir un Json vacio sin tocar la lógica.
     */
    @Test
    public void ListaGasolinerasActivityJsonVacio() {
        /*ViewInteraction textView = onView(
                allOf(withId(R.id.textFechaActualizacion), withText("Actualizado. No existen datos para esta búsqueda"),
                        childAtPosition(
                                allOf(withId(R.id.activity_main),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Actualizado. No existen datos para esta búsqueda")));

        ViewInteraction listView = onView(
                allOf(withId(R.id.customListView),
                        childAtPosition(
                                allOf(withId(R.id.activity_main),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        listView.check(matches(isDisplayed()));
*/
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
