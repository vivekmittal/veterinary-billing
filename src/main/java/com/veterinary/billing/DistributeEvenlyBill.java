package com.veterinary.billing;

import com.veterinary.Owner;

public class DistributeEvenlyBill extends MultipleOwnerBill {

    public DistributeEvenlyBill(Bill bill, Owner... owners) {
        super(bill, owners);
    }

    @Override
    protected int totalCostFor(final Owner owner) {
        return bill.value() / owners.size();
    }
}
