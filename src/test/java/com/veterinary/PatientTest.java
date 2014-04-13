package com.veterinary;

import com.veterinary.exception.ServiceNotAvailableForAnimalException;
import com.veterinary.service.Medicine;
import com.veterinary.service.Procedure;
import org.testng.annotations.Test;

import static com.veterinary.utils.CustomMatchers.isBilled;
import static org.hamcrest.MatcherAssert.assertThat;

public class PatientTest {
    @Test
    public void regularCheckupOnCat() {
        Patient patient = new Patient(Animal.CAT, "Drowny");

        patient.perform(Procedure.REGULAR_CHECKUP);
        patient.perform(Medicine.SOME_SORT);

        assertThat(patient, isBilled(1100));
    }

    @Test(expectedExceptions = ServiceNotAvailableForAnimalException.class)
    public void noSurgeryForCats(){
        Patient patient = new Patient(Animal.CAT, "Browny");

        patient.perform(Procedure.SURGERY);
    }
}
