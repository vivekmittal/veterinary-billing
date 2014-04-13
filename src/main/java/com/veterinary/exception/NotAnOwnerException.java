package com.veterinary.exception;

import com.veterinary.Owner;
import com.veterinary.Patient;

public class NotAnOwnerException extends RuntimeException {
    public NotAnOwnerException(Owner owner, Patient patient) {
        super(
            String.format("%s is not an owner of %s (%s)", owner.name(), patient.name(), patient.animal())
        );
    }
}
