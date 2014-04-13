package com.veterinary.exception;

import com.veterinary.Animal;
import com.veterinary.Service;

public class ServiceNotAvailableForAnimalException extends RuntimeException {
    public ServiceNotAvailableForAnimalException(Service service, Animal animal) {
        super(
                String.format("Requested Service %s is not available for %s", service, animal)
        );
    }
}
