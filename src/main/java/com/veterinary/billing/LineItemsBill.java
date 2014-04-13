package com.veterinary.billing;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.FluentIterable;
import com.veterinary.MedicalService;
import com.veterinary.Owner;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;

public class LineItemsBill extends MultipleOwnerBill {
    private final List<LineItem> lineItems;

    public LineItemsBill(Bill bill, LineItem... lineItems) {
        super(bill, collectOwners(lineItems));
        this.lineItems = newArrayList(lineItems);
    }

    @Override
    protected int totalCostFor(final Owner owner) {
        int billValue = 0;

        List<MedicalService> medicalServices = servicesOwnedBy(owner);

        for (MedicalService medicalService : medicalServices) {
            if(bill.hasService(medicalService)){
                billValue += bill.costOf(medicalService);
            }
        }

        return billValue;
    }

    private List<MedicalService> servicesOwnedBy(final Owner owner) {
        return FluentIterable.from(lineItems).filter(new Predicate<LineItem>() {
            @Override
            public boolean apply(LineItem lineItem) {
                return lineItem.owner() == owner;
            }
        }).transformAndConcat(new Function<LineItem, Iterable<MedicalService>>() {
            @Override
            public Iterable<MedicalService> apply(LineItem lineItem) {
                return lineItem.medicalServices;
            }
        }).toList();
    }

    private static Owner[] collectOwners(LineItem[] lineItems) {
        return Collections2.transform(newArrayList(lineItems), new Function<LineItem, Owner>() {
            @Override
            public Owner apply(LineItem lineItem) {
                return lineItem.owner();
            }
        }).toArray(new Owner[0]);
    }

    public static class LineItem {
        private Owner owner;
        private Set<MedicalService> medicalServices;

        private LineItem(Owner owner, MedicalService... medicalServices) {
            this.owner = owner;
            this.medicalServices = newHashSet(medicalServices);
        }

        private Owner owner() {
            return this.owner;
        }

        public static LineItem newLineItem(Owner owner, MedicalService... medicalServices) {
            return new LineItem(owner, medicalServices);
        }
    }
}
