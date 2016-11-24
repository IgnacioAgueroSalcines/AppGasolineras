package com.example.nachoaguero.appgasolineras.Integracion;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nachoaguero.appgasolineras.Presentacion.ListaGasolinerasActivity;
import com.example.nachoaguero.appgasolineras.R;

import junit.framework.Assert;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListaGasolinerasActivityTestFiltroTipoCarburantePrecioNoExiste {

    @Rule
    public ActivityTestRule<ListaGasolinerasActivity> mActivityTestRule = new ActivityTestRule<>(ListaGasolinerasActivity.class);

    @Test
    public void listaGasolinerasActivityTestFiltroTipoCarburantePrecioNoExiste() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Filtros"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinnerCarburante), isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(android.R.id.text1), withText("Gasolina 95"),
                        childAtPosition(
                                allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                                        withParent(withClassName(is("android.widget.FrameLayout")))),
                                1),
                        isDisplayed()));
        appCompatCheckedTextView.perform(click());

        //Obtenemos la lista
        ListView list = (ListView) mActivityTestRule.getActivity().findViewById(R.id.customListView);
        //Comprobamos que no sea nula
        Assert.assertNotNull(list);

        for (int i=0;i<list.getCount();i++) {
            if (list.getChildAt(i+1) != null) {
                //Obtenemos la View de la primera fila
                View vPosition0 = list.getChildAt(i);
                //Para obtener el numero de filas-->list.getChildCount()
                TextView textViewDistancia = (TextView) vPosition0.findViewById(R.id.precio);
                String res = textViewDistancia.getText().toString().trim().toUpperCase();


                String res2 = "No Disponible";
                res2= res2.trim().toUpperCase();

                Assert.assertTrue(!res.equals(res2));
            }
        }

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.title), withText("Filtros"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.spinnerCarburante), isDisplayed()));
        appCompatSpinner2.perform(click());

        ViewInteraction appCompatCheckedTextView2 = onView(
                allOf(withId(android.R.id.text1), withText("Gasolina 98"),
                        childAtPosition(
                                allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                                        withParent(withClassName(is("android.widget.FrameLayout")))),
                                2),
                        isDisplayed()));
        appCompatCheckedTextView2.perform(click());

        //Obtenemos la lista
        ListView list2 = (ListView) mActivityTestRule.getActivity().findViewById(R.id.customListView);
        //Comprobamos que no sea nula
        Assert.assertNotNull(list);

        for (int i=0;i<list.getCount();i++) {
            if (list.getChildAt(i+1) != null) {
                //Obtenemos la View de la primera fila
                View vPosition0 = list.getChildAt(i);
                //Para obtener el numero de filas-->list.getChildCount()
                TextView textViewDistancia = (TextView) vPosition0.findViewById(R.id.precio);
                String res = textViewDistancia.getText().toString().trim().toUpperCase();


                String res2 = "No Disponible";
                res2= res2.trim().toUpperCase();

                Assert.assertTrue(!res.equals(res2));
            }
        }

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.title), withText("Filtros"), isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction appCompatSpinner3 = onView(
                allOf(withId(R.id.spinnerCarburante), isDisplayed()));
        appCompatSpinner3.perform(click());

        ViewInteraction appCompatCheckedTextView3 = onView(
                allOf(withId(android.R.id.text1), withText("Diésel"),
                        childAtPosition(
                                allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                                        withParent(withClassName(is("android.widget.FrameLayout")))),
                                3),
                        isDisplayed()));
        appCompatCheckedTextView3.perform(click());

        //Obtenemos la lista
        ListView list3 = (ListView) mActivityTestRule.getActivity().findViewById(R.id.customListView);
        //Comprobamos que no sea nula
        Assert.assertNotNull(list);

        for (int i=0;i<list.getCount();i++) {
            if (list.getChildAt(i+1) != null) {
                //Obtenemos la View de la primera fila
                View vPosition0 = list.getChildAt(i);
                //Para obtener el numero de filas-->list.getChildCount()
                TextView textViewDistancia = (TextView) vPosition0.findViewById(R.id.precio);
                String res = textViewDistancia.getText().toString().trim().toUpperCase();


                String res2 = "No Disponible";
                res2= res2.trim().toUpperCase();

                Assert.assertTrue(!res.equals(res2));
            }
        }

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(R.id.title), withText("Filtros"), isDisplayed()));
        appCompatTextView4.perform(click());

        ViewInteraction appCompatSpinner4 = onView(
                allOf(withId(R.id.spinnerCarburante), isDisplayed()));
        appCompatSpinner4.perform(click());

        ViewInteraction appCompatCheckedTextView4 = onView(
                allOf(withId(android.R.id.text1), withText("Diésel Super"),
                        childAtPosition(
                                allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                                        withParent(withClassName(is("android.widget.FrameLayout")))),
                                4),
                        isDisplayed()));
        appCompatCheckedTextView4.perform(click());

        //Obtenemos la lista
        ListView list4 = (ListView) mActivityTestRule.getActivity().findViewById(R.id.customListView);
        //Comprobamos que no sea nula
        Assert.assertNotNull(list);

        for (int i=0;i<list.getCount();i++) {
            if (list.getChildAt(i+1) != null) {
                //Obtenemos la View de la primera fila
                View vPosition0 = list.getChildAt(i);
                //Para obtener el numero de filas-->list.getChildCount()
                TextView textViewDistancia = (TextView) vPosition0.findViewById(R.id.precio);
                String res = textViewDistancia.getText().toString().trim().toUpperCase();


                String res2 = "No Disponible";
                res2= res2.trim().toUpperCase();

                Assert.assertTrue(!res.equals(res2));
            }
        }

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
