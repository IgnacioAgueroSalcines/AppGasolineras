package com.example.nachoaguero.appgasolineras.Integracion;


import android.support.test.espresso.DataInteraction;
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

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListaGasolinerasActivityTestOrdenarDistancia {

    @Rule
    public ActivityTestRule<ListaGasolinerasActivity> mActivityTestRule = new ActivityTestRule<>(ListaGasolinerasActivity.class);

    @Test
    public void listaGasolinerasActivityTestOrdenarDistancia() {
        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.ordenarKm), withContentDescription("OrdenKm"), isDisplayed()));
        actionMenuItemView.perform(click());

        //Obtenemos la lista
        ListView list = (ListView) mActivityTestRule.getActivity().findViewById(R.id.customListView);
        //Comprobamos que no sea nula
        Assert.assertNotNull(list);

        for (int i=0;i<list.getCount();i++){
            Double res=0.0;
            Double res2=0.0;
            if(list.getChildAt(i+1)!=null) {
                //Obtenemos la View de la primera fila
                View vPosition0 = list.getChildAt(i);
                //Para obtener el numero de filas-->list.getChildCount()
                TextView textViewDistancia = (TextView) vPosition0.findViewById(R.id.distancia);
                String resString = textViewDistancia.getText().subSequence(0, textViewDistancia.getText().length() - 2).toString();
                res = Double.parseDouble(resString);

                View vPosition1 = list.getChildAt(i + 1);
                //Para obtener el numero de filas-->list.getChildCount()
                TextView textViewDistancia2 = (TextView) vPosition1.findViewById(R.id.distancia);
                String resString2 = textViewDistancia2.getText().subSequence(0, textViewDistancia2.getText().length() - 2).toString();
                res2 = Double.parseDouble(resString2);

                Assert.assertTrue(res <= res2);
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
