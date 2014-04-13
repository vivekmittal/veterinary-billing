package com.veterinary.utils;

import com.veterinary.Patient;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CustomMatchers {
    public static Matcher<? super Patient> isBilled(final int expectedBillAmount) {
        return new TypeSafeMatcher<Patient>() {
            @Override
            protected boolean matchesSafely(Patient patient) {
                return patient.bill().value() == expectedBillAmount;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("the total bill cost to be " + expectedBillAmount);
            }

            @Override
            protected void describeMismatchSafely(Patient item, Description mismatchDescription) {
                mismatchDescription.appendText("was " + item.bill().value());
            }
        };
    }
}
