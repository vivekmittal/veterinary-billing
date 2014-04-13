package com.veterinary.billing;

import com.veterinary.Animal;
import com.veterinary.Owner;
import com.veterinary.Patient;
import org.testng.annotations.Test;

import static com.veterinary.service.Procedure.REGULAR_CHECKUP;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Test
public class SingleOwnerBillTest {
    private static final Owner BOB = new Owner("BOB");
    private static final Owner JOHN = new Owner("JOHN");

    public void singleOwnerBill() {
        Patient patient = new Patient(Animal.CAT, "Kessels");

        patient.perform(REGULAR_CHECKUP);

        SingleOwnerBill decoratedBill = new SingleOwnerBill(patient.bill(), BOB);

        assertThat(decoratedBill.costOccurred(), is(1000));
    }
}
