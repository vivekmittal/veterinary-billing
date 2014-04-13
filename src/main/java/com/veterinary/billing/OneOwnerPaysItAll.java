package com.veterinary.billing;

import com.veterinary.Owner;

public class OneOwnerPaysItAll extends MultipleOwnerBill {
    private Owner owner;

    public OneOwnerPaysItAll(Bill bill, Owner ownerPayingTheBill, Owner... owners) {
        super(bill, owners);
        this.owner = ownerPayingTheBill;
    }

    @Override
    protected int totalCostFor(final Owner owner) {
        if(owner == this.owner) {
            return bill.value();
        }

        return 0;
    }
}
