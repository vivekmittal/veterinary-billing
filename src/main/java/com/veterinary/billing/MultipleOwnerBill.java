package com.veterinary.billing;

import com.veterinary.Owner;
import com.veterinary.exception.NotAnOwnerException;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public abstract class MultipleOwnerBill {
    protected final Bill bill;
    protected final Set<Owner> owners;

    public MultipleOwnerBill(Bill bill, Owner... owners) {
        this.bill = bill;
        this.owners = newHashSet(owners);
    }

    protected boolean notAnOwner(Owner owner) {
        return !owners.contains(owner);
    }

    public int costOccurredFor(final Owner owner) {
        if (notAnOwner(owner)) {
            throw new NotAnOwnerException(owner, bill.patient());
        }

        return this.totalCostFor(owner);
    }

    protected abstract int totalCostFor(final Owner owner);

    public String toString() {
        StringBuffer decoratedBill = new StringBuffer(bill.toString());
        decoratedBill.append("Distribution among Owners\n");

        for (Owner owner : owners) {
            decoratedBill.append(
                    String.format("%s - %d\n", owner.name(), this.totalCostFor(owner))
            );
        }

        return decoratedBill.toString();
    }
}
