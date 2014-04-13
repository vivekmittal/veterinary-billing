package com.veterinary.billing;

import com.veterinary.Animal;
import com.veterinary.Owner;
import com.veterinary.Patient;
import com.veterinary.exception.NotAnOwnerException;
import org.testng.annotations.Test;

import static com.veterinary.service.Procedure.REGULAR_CHECKUP;
import static com.veterinary.service.Procedure.SURGERY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Test
public class OneOwnerPaysItAllTest {
    private static final Owner BOB = new Owner("BOB");
    private static final Owner JOHN = new Owner("JOHN");

    public void oneOwnerPaysItAll() {
        Patient patient = new Patient(Animal.HORSE, "Whitey");

        patient.perform(REGULAR_CHECKUP);
        patient.perform(SURGERY);

        MultipleOwnerBill decoratedBill = new OneOwnerPaysItAll(patient.bill(), BOB, BOB, JOHN);

        assertThat(decoratedBill.costOccurredFor(BOB), is(15000));
        assertThat(decoratedBill.costOccurredFor(JOHN), is(0));
    }

    @Test(expectedExceptions = NotAnOwnerException.class)
    public void bobIsNotAnOwner(){
        Patient patient = new Patient(Animal.HORSE, "Whitey");

        patient.perform(REGULAR_CHECKUP);
        patient.perform(SURGERY);

        MultipleOwnerBill decoratedBill = new OneOwnerPaysItAll(patient.bill(), JOHN, JOHN);

        decoratedBill.costOccurredFor(BOB);
    }
}
