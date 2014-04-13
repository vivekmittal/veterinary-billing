package com.veterinary.billing;

import com.veterinary.Owner;

public class SingleOwnerBill {
    private Bill bill;
    private Owner owner;

    public SingleOwnerBill(Bill bill, Owner owner) {
        this.bill = bill;
        this.owner = owner;
    }

    public String toString() {
        StringBuffer decoratedBill = new StringBuffer(bill.toString());
        decoratedBill.append("Paid By: \n");

        decoratedBill.append(
                String.format("%s - %d\n", owner.name(), bill.value())
        );

        return decoratedBill.toString();
    }

    public int costOccurred() {
        return bill.value();
    }
}
