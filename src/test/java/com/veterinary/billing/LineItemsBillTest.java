package com.veterinary.billing;

import com.veterinary.Animal;
import com.veterinary.Owner;
import com.veterinary.Patient;
import org.testng.annotations.Test;

import static com.veterinary.billing.LineItemsBill.LineItem.newLineItem;
import static com.veterinary.service.Medicine.PARACETAMOL;
import static com.veterinary.service.Procedure.REGULAR_CHECKUP;
import static com.veterinary.service.Procedure.SURGERY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Test
public class LineItemsBillTest {
    private static final Owner BOB = new Owner("BOB");
    private static final Owner JOHN = new Owner("JOHN");

    public void lineItemsDistribution() {
        Patient patient = new Patient(Animal.HORSE, "Whitey");

        patient.perform(REGULAR_CHECKUP);
        patient.perform(PARACETAMOL);
        patient.perform(SURGERY);

        MultipleOwnerBill decoratedBill = new LineItemsBill(patient.bill(),
                newLineItem(BOB, SURGERY, PARACETAMOL),
                newLineItem(JOHN, REGULAR_CHECKUP));

        assertThat(decoratedBill.costOccurredFor(BOB), is(10100));
        assertThat(decoratedBill.costOccurredFor(JOHN), is(5000));
    }
}
