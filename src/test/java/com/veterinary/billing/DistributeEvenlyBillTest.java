package com.veterinary.billing;

import com.veterinary.Animal;
import com.veterinary.Owner;
import com.veterinary.Patient;
import org.testng.annotations.Test;

import static com.veterinary.service.Procedure.REGULAR_CHECKUP;
import static com.veterinary.service.Procedure.SURGERY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Test
public class DistributeEvenlyBillTest {
    private static final Owner BOB = new Owner("BOB");
    private static final Owner JOHN = new Owner("JOHN");

    public void billDistributedEvenly() {
        Patient patient = new Patient(Animal.HORSE, "Blacky");

        patient.perform(REGULAR_CHECKUP);
        patient.perform(SURGERY);

        MultipleOwnerBill decoratedBill = new DistributeEvenlyBill(patient.bill(), BOB, JOHN);

        assertThat(decoratedBill.costOccurredFor(BOB), is(7500));
        assertThat(decoratedBill.costOccurredFor(JOHN), is(7500));
    }
}
