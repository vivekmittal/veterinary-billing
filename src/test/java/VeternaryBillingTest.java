import com.veterinary.Animal;
import com.veterinary.Owner;
import com.veterinary.Patient;
import com.veterinary.billing.DistributeEvenlyBill;
import com.veterinary.billing.LineItemsBill;
import com.veterinary.billing.MultipleOwnerBill;
import com.veterinary.billing.OneOwnerPaysItAll;
import com.veterinary.exception.ServiceNotAvailableForAnimalException;
import org.testng.annotations.Test;

import static com.veterinary.billing.LineItemsBill.LineItem.newLineItem;
import static com.veterinary.service.Medicine.SOME_SORT;
import static com.veterinary.service.Procedure.REGULAR_CHECKUP;
import static com.veterinary.service.Procedure.SURGERY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class VeternaryBillingTest {
    private static final Owner BOB = new Owner("BOB");
    private static final Owner JOHN = new Owner("JOHN");

    @Test
    public void shouldBillClient() {
        Patient patient = new Patient(Animal.CAT, "Kessels");

        patient.perform(REGULAR_CHECKUP);

        assertThat(patient.bill().value(), is(1000));
        System.out.println(patient.bill());
    }

    @Test(expectedExceptions = ServiceNotAvailableForAnimalException.class)
    public void serviceNotAvailable(){
        Patient patient = new Patient(Animal.CAT, "Lily");

        patient.perform(SURGERY);
    }

    @Test
    public void billDistributedEvenly() {
        Patient patient = new Patient(Animal.HORSE, "Blacky");

        patient.perform(REGULAR_CHECKUP);
        patient.perform(SURGERY);

        MultipleOwnerBill decoratedBill = new DistributeEvenlyBill(patient.bill(), BOB, JOHN);

        assertThat(decoratedBill.costOccurredFor(BOB), is(7500));
        assertThat(decoratedBill.costOccurredFor(JOHN), is(7500));
    }

    @Test
    public void oneOwnerPaysItAll() {
        Patient patient = new Patient(Animal.HORSE, "Whitey");

        patient.perform(REGULAR_CHECKUP);
        patient.perform(SURGERY);

        MultipleOwnerBill decoratedBill = new OneOwnerPaysItAll(patient.bill(), BOB, BOB, JOHN);

        assertThat(decoratedBill.costOccurredFor(BOB), is(15000));
        assertThat(decoratedBill.costOccurredFor(JOHN), is(0));
    }

    @Test
    public void lineItemsDistribution() {
        Patient patient = new Patient(Animal.HORSE, "Whitey");

        patient.perform(REGULAR_CHECKUP);
        patient.perform(SOME_SORT);
        patient.perform(SURGERY);

        MultipleOwnerBill decoratedBill = new LineItemsBill(patient.bill(),
                newLineItem(BOB, SURGERY),
                newLineItem(JOHN, REGULAR_CHECKUP),
                newLineItem(BOB, SOME_SORT));

        assertThat(decoratedBill.costOccurredFor(BOB), is(10100));
        assertThat(decoratedBill.costOccurredFor(JOHN), is(5000));
    }
}
