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
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class VistaDetalleTest {

    @Rule
    public ActivityTestRule<ListaGasolinerasActivity> mActivityTestRule = new ActivityTestRule<>(ListaGasolinerasActivity.class);

    @Test
    public void vistaDetalleTest() {
        ViewInteraction relativeLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.customListView),
                                withParent(withId(R.id.activity_main))),
                        0),
                        isDisplayed()));
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.precioDiesel_valor), withText("0.945€"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.precioGasolinera_valor), withText("1.065€"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.precioGasolina98_valor), withText("No disponible"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        textView3.check(matches(isDisplayed()));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.precioGasolina98_valor), withText("No disponible"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        textView4.check(matches(isDisplayed()));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.precioDieselSuper_valor), withText("No disponible"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        textView5.check(matches(isDisplayed()));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.precioDiesel), withText("Precio diésel :"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        textView6.check(matches(withText("Precio diésel :")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.precioGasolina95), withText("Precio gasolina 95 :"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        textView7.check(matches(withText("Precio gasolina 95 :")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.precioGasolina98), withText("Precio gasolina 98 :"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        textView8.check(matches(withText("Precio gasolina 98 :")));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.precioDieselSuper), withText("Precio Diésel Super :"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        textView9.check(matches(withText("Precio Diésel Super :")));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.direccionGasolinera), withText("Dirección :"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                10),
                        isDisplayed()));
        textView10.check(matches(withText("Dirección :")));

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.direccionGasolinera_valor), withText("AVDA. FERNANDEZ VALLEJO, 12"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                11),
                        isDisplayed()));
        textView11.check(matches(isDisplayed()));

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.horario), withText("Horario  :"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                12),
                        isDisplayed()));
        textView12.check(matches(withText("Horario  :")));

        ViewInteraction textView13 = onView(
                allOf(withId(R.id.horario_valor), withText("L-D: 07:00-23:00"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                13),
                        isDisplayed()));
        textView13.check(matches(isDisplayed()));

        ViewInteraction textView14 = onView(
                allOf(withId(R.id.coordenadas), withText("Coordenadas :"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                14),
                        isDisplayed()));
        textView14.check(matches(withText("Coordenadas :")));

        ViewInteraction textView15 = onView(
                allOf(withId(R.id.coordenadas_valor), withText("Latitud:43.343792\nLongitud:-4.046338"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                15),
                        isDisplayed()));
        textView15.check(matches(isDisplayed()));

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
